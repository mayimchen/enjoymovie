package com.banking.xc.utils.ui;

import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.MyActivity;
import com.banking.xc.utils.MySimpleAdapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

public class MyHorizontalImagesView extends AdapterView<ListAdapter> implements GestureDetector.OnGestureListener {

	private static final String TAG = "ProductImagesView";

	private MySimpleAdapter mAdapter;

	/**
	 * 当前坐标偏移量
	 */
	private int offset;

	/**
	 * 目的坐标偏移量（动画用）
	 */
	private int targetOffset;

	/**
	 * 最后一页的区域坐标（头一页的区域坐标是0因此不用定义和计算）
	 */
	private int lastPageArea;

	/**
	 * 当前区域坐标
	 */
	private int area = 0;

	/**
	 * 防止多次测量
	 */
	private boolean alreadyMeasureChild;

	/**
	 * 左右留白最小需要多少
	 */
	private int minPaddingLeft;

	/**
	 * 左右留白最小需要多少
	 */
	private int minPaddingRight;

	/**
	 * 总个数
	 */
	private int childCount;
	
	/**
	 * 旧个数
	 */
	private int oldCount;

	/**
	 * 每页个数
	 */
	private int pageSize;

	/**
	 * 元素与其背景宽高
	 */
	private int childWidthWithPadding;

	/**
	 * 元素与其背景宽高
	 */
	private int childHeightWithPadding;

	/**
	 * 元素与其间隔宽度，用于排版
	 */
	private int unitWidth;

	private GestureDetector mGestureDetector;// 手势识别器

	/**
	 * 前冲或后冲标记
	 */
	private int flingFlag;

	private BorderListener borderListener;// 边界监听器
	private Border border = new Border();

	public void setOnBorderListener(BorderListener borderListener) {
		this.borderListener = borderListener;
	}

	public MyHorizontalImagesView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initProductImagesView();
	}

	public MyHorizontalImagesView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initProductImagesView();
	}

	public MyHorizontalImagesView(Context context) {
		super(context);
		initProductImagesView();
	}

	/**
	 * Common code for different constructor flavors
	 */
	private void initProductImagesView() {
		mGestureDetector = new GestureDetector(this.getContext(), this);
		mGestureDetector.setIsLongpressEnabled(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (null == mAdapter || mAdapter.getCount() < 1) {
			return true;
		}

		if (null == thread) {
			thread = new Thread(animationDelegate);
			thread.start();
		}

		boolean retValue = mGestureDetector.onTouchEvent(event);

		int action = event.getAction();
		if (action == MotionEvent.ACTION_UP) {
			onUp();
		} else if (action == MotionEvent.ACTION_CANCEL) {
			onCancel();
		}

		return retValue;
	}

	private Thread thread;

	private AnimationDelegate animationDelegate = new AnimationDelegate();

	private class AnimationDelegate implements Runnable {

		private boolean fit = true;

		private static final int NONE = 0;
		private static final int LEFT = 1;
		private static final int RIGHT = 2;

		@Override
		public void run() {
			int vx = 0;
			while (true) {
				isFit();

				vx += (targetOffset - offset) * 0.4f;// spring为弹性系数
				offset += (vx *= 0.3f);// friction为摩擦力

				if (Math.abs(targetOffset - offset) < 9) {
					offset = targetOffset;
					vx = 0;
				}

				fit = offset == targetOffset;

				if (Log.D) {
					Log.d(TAG, "offset -->> " + offset);
				}

				// 刷新界面
				((MyActivity) getContext()).post(new Runnable() {
					@Override
					public void run() {
						requestLayout();
					}
				});

				try {
					Thread.sleep(30);// 帧数
				} catch (InterruptedException e) {
				}

			}
		}

		private synchronized void isFit() {
			if (fit) {
				try {
					if (Log.D) {
						Log.d(TAG, "product image wait start -->> ");
					}
					wait();
					if (Log.D) {
						Log.d(TAG, "product image wait end -->> ");
					}
				} catch (InterruptedException e) {
					isFit();
				}
			}
		}

		public synchronized void adjust(int flag) {

			if (Log.D) {
				Log.d(TAG, "adjust() -->> " + flag);
			}

			switch (flag) {
			case RIGHT:
				area -= pageSize;
				if (area <= -lastPageArea) {
					area = -lastPageArea;
				}
				break;
			case LEFT:
				area += pageSize;
				if (area >= 0) {
					area = 0;
				}
				break;
			case NONE:
				area = Math.round((float) offset / unitWidth);
				break;
			}

			if (Log.D) {
				Log.d(TAG, "adjust() -->> area : " + area);
			}

			if (Log.D) {
				Log.d(TAG, "adjust() -->> targetOffset before : " + targetOffset);
			}

			if (childCount == pageSize) {
				targetOffset = 0;
			} else {
				targetOffset = area * unitWidth;
			}

			if (Log.D) {
				Log.d(TAG, "adjust() -->> targetOffset after : " + targetOffset);
			}

			notify();
		}

	};

	private void borderChange() {
		if (null != borderListener) {
			borderListener.onChange(border);
		}
	}

	@Override
	public ListAdapter getAdapter() {
		return mAdapter;
	}

	private DataSetObserver dataSetObserver = new DataSetObserver() {
		@Override
		public void onChanged() {
			if (Log.D) {
				Log.d(TAG, "onChanged() -->> adapter count:" + mAdapter.getCount());
			}
			MyActivity myActivity = MyApplication.getInstance().getCurrentMyActivity();
			if(1==2){
				myActivity.post(new Runnable() {
					public void run() {
						if(mAdapter.getCount() < 1){
							border.left = 0;
							border.right = 0;
							borderChange();
						}
						if(mAdapter.getCount() != oldCount){
							oldCount = mAdapter.getCount();
							alreadyMeasureChild = false;
							detachAllViewsFromParent();
							for (int i = 0; i < mAdapter.getCount(); i++) {
								View child = mAdapter.getView(i, null, MyHorizontalImagesView.this);
								addViewInLayout(child, i, child.getLayoutParams());
							}
						}else{
							for (int i = 0; i < mAdapter.getCount(); i++) {
								View child = mAdapter.getView(i, getChildAt(i), MyHorizontalImagesView.this);
							}
						}
					}
				});
			}
		}
	};

	@Override
	public void setAdapter(ListAdapter adapter) {

		if (Log.D) {
			Log.d(TAG, "setAdapter() -->> ");
		}

		mAdapter = (MySimpleAdapter) adapter;
		mAdapter.registerDataSetObserver(dataSetObserver);
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public View getSelectedView() {
		return null;
	}

	@Override
	public void setSelection(int position) {
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),//
				getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec) + getPaddingTop() + getPaddingBottom());

	}

	// 测量元素
	private void measureChild(View child) {

		// 防止多次测量
		if (alreadyMeasureChild) {
			return;
		}

		if (Log.D) {
			Log.d(TAG, "measureChild() -->> ");
		}

		// 为左右留白保留原始数值
		if (0 == minPaddingLeft) {
			minPaddingLeft = getPaddingLeft();
		}

		// 为左右留白保留原始数值
		if (0 == minPaddingRight) {
			minPaddingRight = getPaddingRight();
		}

		LayoutParams p = child.getLayoutParams();

		childWidthWithPadding = p.width + child.getPaddingLeft() + child.getPaddingRight();
		childHeightWithPadding = p.height + child.getPaddingTop() + child.getPaddingBottom();

		int visualRangeWidth = getMeasuredWidth() - minPaddingLeft - minPaddingRight;// 可视范围

		int minSpacing = DPIUtil.dip2px(10);// 最起码的间隔

		// 可视范围除以，每个元素的宽度加上最起码的间距，计算出可视范围能容纳的元素个数
		pageSize = visualRangeWidth / (childWidthWithPadding + minSpacing);
		// 虽然说可以容纳10个，未必就有10个以上的元素
		if (pageSize > childCount) {
			pageSize = childCount;
		}
		if (Log.D) {
			Log.d(TAG, "measureChild() -->> childCount:" + childCount);
			Log.d(TAG, "measureChild() -->> pageSize:" + pageSize);
		}

		// 可视范围去掉元素所占宽度后，计算出剩余的可分配的间隔空间
		int visualSpacingTotal = visualRangeWidth - pageSize * childWidthWithPadding;

		// 计算出平均的间隙
		int spacing = 0;
		if (pageSize - 1 == 0) {// 页面只显示一个元素的时候需要特殊处理
			spacing = 0;
			setPadding(minPaddingLeft + visualSpacingTotal / 2, getPaddingTop(), minPaddingRight + visualSpacingTotal / 2, getPaddingBottom());
		} else {
			spacing = visualSpacingTotal / (pageSize - 1);
			setPadding(minPaddingLeft, getPaddingTop(), minPaddingRight, getPaddingBottom());
		}

		// 避免左右两边间隔小，图片之间间隔大，因此再进行调整。
		int spacingLR = minPaddingLeft + minPaddingRight;
		if (spacing > spacingLR) {
			spacing = (spacingLR + visualSpacingTotal) / (pageSize + 1);// 
			setPadding(spacing, getPaddingTop(), spacing, getPaddingBottom());
		}

		// 一个元素加上间隔的宽度，用于排版
		unitWidth = childWidthWithPadding + spacing;

		// 最后一页的坐标
		lastPageArea = childCount - pageSize;

		// 如果是后来更改了数据，应该检查修正位置
		if (Log.D) {
			Log.d(TAG, "area -->> " + area);
		}
		if (Log.D) {
			Log.d(TAG, "lastPageArea -->> " + lastPageArea);
		}
		if (area <= -lastPageArea) {
			area = -lastPageArea;
			animationDelegate.adjust(-1);
		}

		// 标记已经测量过
		alreadyMeasureChild = true;

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

		if (Log.D) {
			Log.d(TAG, "onLayout() -->> ");
		}

		childCount = getChildCount();// 总个数
		for (int i = 0; i < childCount; i++) {// 迭代

			View child = getChildAt(i);

			// 尝试测量
			measureChild(child);

			int left = 0;
			int top = 0;

			// 通知左右箭头的隐藏与显示
			if (childCount == pageSize) {
				// 通知
				border.left = 0;
				border.right = 0;
				borderChange();
			} else {
				// 通知
				if (area >= 0) {// 左边去到尽头
					border.left = 0;
				} else {
					border.left = 1;
				}
				if (area <= -lastPageArea) {// 右边去到尽头
					border.right = 0;
				} else {
					border.right = 1;
				}
				borderChange();
			}

			left = getPaddingLeft() + offset + i * unitWidth;

			top = getPaddingTop();

			child.layout(left, top, left + childWidthWithPadding, top + childHeightWithPadding);

		}
	}

	public boolean onSingleTapUp(MotionEvent e) {// 松开

		if (Log.D) {
			Log.d(TAG, "onSingleTapUp -->> ");
		}

		int index = (-Math.round(((float) offset - e.getX()) / unitWidth - 0.5f)) - 1;
		if (Log.D) {
			Log.d(TAG, "index -->> " + index);
		}
		if (index > getChildCount() - 1) {
			index = getChildCount() - 1;
		} else if (index < 0) {
			index = 0;
		}
		
		View child = getChildAt(index);
		
		if(child != null){
			performItemClick(child, index, child.getId());
		}
	
		/*
		 * if (mDownTouchPosition >= 0) {
		 * 
		 * // An item tap should make it selected, so scroll to this child. scrollToChild(mDownTouchPosition - mFirstPosition);
		 * 
		 * // Also pass the click so the client knows, if it wants to. if (mShouldCallbackOnUnselectedItemClick || mDownTouchPosition == mSelectedPosition) { performItemClick(mDownTouchView,
		 * mDownTouchPosition, mAdapterf .getItemId(mDownTouchPosition)); }
		 * 
		 * return true; }
		 */

		return false;
	}

	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

		if (velocityX > 300) {
			flingFlag = AnimationDelegate.LEFT;
		} else if (velocityX < -300) {
			flingFlag = AnimationDelegate.RIGHT;
		} else {
			flingFlag = AnimationDelegate.NONE;
		}

		/*
		 * if (!mShouldCallbackDuringFling) { // We want to suppress selection changes
		 * 
		 * // Remove any future code to set mSuppressSelectionChanged = false removeCallbacks(mDisableSuppressSelectionChangedRunnable);
		 * 
		 * // This will get reset once we scroll into slots if (!mSuppressSelectionChanged) mSuppressSelectionChanged = true; }
		 * 
		 * // Fling the gallery! mFlingRunnable.startUsingVelocity((int) -velocityX);
		 */

		return true;
	}

	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

		if (childCount == pageSize) {
			return true;
		}

		getParent().requestDisallowInterceptTouchEvent(true);

		// if (localLOGV) if(Log.V){Log.v(TAG, String.valueOf(e2.getX() -
		// e1.getX()));}

		/*
		 * Now's a good time to tell our parent to stop intercepting our events! The user has moved more than the slop amount, since GestureDetector ensures this before calling this method. Also, if a
		 * parent is more interested in this touch's events than we are, it would have intercepted them by now (for example, we can assume when a Gallery is in the ListView, a vertical scroll would
		 * not end up in this method since a ListView would have intercepted it by now).
		 */

		// As the user scrolls, we want to callback selection changes so
		// related-
		// info on the screen is up-to-date with the gallery's selection
		/*
		 * if (!mShouldCallbackDuringFling) { if (mIsFirstScroll) {
		 * 
		 * We're not notifying the client of selection changes during the fling, and this scroll could possibly be a fling. Don't do selection changes until we're sure it is not a fling.
		 * 
		 * if (!mSuppressSelectionChanged) mSuppressSelectionChanged = true; postDelayed(mDisableSuppressSelectionChangedRunnable, SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT); } } else { if
		 * (mSuppressSelectionChanged) mSuppressSelectionChanged = false; }
		 */

		// Track the motion
		/*
		 * trackMotionScroll(-1 * (int) distanceX);
		 * 
		 * mIsFirstScroll = false;
		 */

		int tempOffset = offset + Math.round(-distanceX);
		if (tempOffset > 0) {
			offset = 0;
		} else if (tempOffset < lastPageArea * unitWidth * -1) {
			offset = lastPageArea * unitWidth * -1;
		} else {
			offset = tempOffset;
		}
		requestLayout();

		flingFlag = AnimationDelegate.NONE;

		return true;
	}

	public boolean onDown(MotionEvent e) {

		// 初始化
		flingFlag = AnimationDelegate.NONE;

		if (Log.D) {
			Log.d(TAG, "onDown -->> ");
		}

		// Kill any existing fling/scroll 停止缓冲动画或调整动画
		// mFlingRunnable.stop(false);

		// Get the item's view that was touched 找到点击位置的子 View 以及 显示为点中状态
		/*
		 * mDownTouchPosition = pointToPosition((int) e.getX(), (int) e.getY());
		 * 
		 * if (mDownTouchPosition >= 0) { mDownTouchView = getChildAt(mDownTouchPosition - mFirstPosition); mDownTouchView.setPressed(true); }
		 */

		// Reset the multiple-scroll tracking state 标记开始滚动
		/* mIsFirstScroll = true; */

		// Must return true to get matching events for this down event.

		return true;
	}

	/**
	 * Called when a touch event's action is MotionEvent.ACTION_UP.
	 */
	void onUp() {

		animationDelegate.adjust(flingFlag);

		/*
		 * if (mFlingRunnable.mScroller.isFinished()) { scrollIntoSlots(); }
		 * 
		 * dispatchUnpress();
		 */
	}

	/**
	 * Called when a touch event's action is MotionEvent.ACTION_CANCEL.
	 */
	void onCancel() {
		onUp();
	}

	public void onLongPress(MotionEvent e) {// 长按

		if (Log.D) {
			Log.d(TAG, "onLongPress -->> ");
		}

		int index = (-Math.round(((float) offset - e.getX()) / unitWidth - 0.5f)) - 1;
		if (Log.D) {
			Log.d(TAG, "index -->> " + index);
		}
		if (index > getChildCount() - 1) {
			index = getChildCount() - 1;
		} else if (index < 0) {
			index = 0;
		}
		View child = getChildAt(index);
		performItemLongClick(child, index, child.getId());

		/*
		 * if (mDownTouchPosition < 0) { return; }
		 * 
		 * performHapticFeedback(HapticFeedbackConstants.LONG_PRESS); long id = getItemIdAtPosition(mDownTouchPosition); dispatchLongPress(mDownTouchView, mDownTouchPosition, id);
		 */
	}

	private boolean performItemLongClick(View view, int position, long id) {
		boolean handled = false;
		OnItemLongClickListener l = getOnItemLongClickListener();

		if (l != null) {
			handled = l.onItemLongClick(this, view, position, id);
		}

		if (handled) {
			performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
		}

		return handled;
	}

	public void onShowPress(MotionEvent e) {
		if (Log.D) {
			Log.d(TAG, "onShowPress -->> ");
		}
	}

	public static class Border {
		public int left;// 0:到达边界,1:还可以前进
		public int right;// 0:到达边界,1:还可以前进
	}

	public interface BorderListener {
		void onChange(Border border);
	}

}
