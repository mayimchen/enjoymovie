package com.banking.xc.utils.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.AdapterHelper;
import com.banking.xc.utils.ExceptionDrawable;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.SimpleBeanAdapter;
import com.banking.xc.utils.SimpleSubViewBinder;
import com.banking.xc.utils.SimpleBeanAdapter.SubViewHolder;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.banking.xc.utils.cache.GlobalImageCache.ImageState;

public class UIRunnable implements Runnable {
	private final static String APP_NAME = MyApplication.getInstance().getString(R.string.app_name);
	private static final String TAG = UIRunnable.class.getSimpleName();
	/**
	 * 默认图
	 */
	private static final ExceptionDrawable defaultDrawable = new ExceptionDrawable(MyApplication.getInstance(), APP_NAME,ExceptionDrawable.LOADING);
	private static final ExceptionDrawable noPictureDrawable = new ExceptionDrawable(MyApplication.getInstance(),"",ExceptionDrawable.NOPICTURE);

	private SubViewHolder subViewHolder;
	private ImageState imageState;

	public UIRunnable(SubViewHolder subViewHolder, ImageState imageState) {
		this.subViewHolder = subViewHolder;
		this.imageState = imageState;
	}

	@Override
	public void run() {

		if (Log.D) {
			Log.d(UIRunnable.class.getName(), "run() position -->> " + getSubViewHolder().getPosition());
		}

		/**
		 * 确保正确获得itemView和subView
		 */
		View itemView = subViewHolder.getItemView();
		if (null == itemView) {
			itemView = getItemView();
		}

		if (null == itemView) {
			if (Log.D) {
				Log.d(UIRunnable.class.getName(), "run() itemView null -->> ");
			}
			gc();
			return;
		}

		if (Log.D) {
			Log.d(UIRunnable.class.getName(), "run() itemView -->> " + itemView);
		}
		View subView = itemView.findViewById(getSubViewHolder().getSubViewId());
		if (Log.D) {
			Log.d(UIRunnable.class.getName(), "run() subView -->> " + subView);
		}
		if (null == subView && itemView.getId() == getSubViewHolder().getSubViewId()) {
			subView = itemView;
		}

		SimpleBeanAdapter adapter = subViewHolder.getAdapter();

		/**
		 * UI处理
		 */
		if (subView instanceof ImageView) {
			ImageView iv = (ImageView) subView;

			switch (imageState.getState()) {
			case GlobalImageCache.STATE_NONE:
				if (Log.D) {
					Log.d(UIRunnable.class.getName(), "STATE_NONE position -->> " + getSubViewHolder().getPosition());
				}
				iv.setImageDrawable(defaultDrawable);
				break;
			case GlobalImageCache.STATE_LOADING:
				if (Log.D) {
					Log.d(UIRunnable.class.getName(), "STATE_LOADING position -->> " + getSubViewHolder().getPosition());
				}
				iv.setImageDrawable(defaultDrawable);
				break;
			case GlobalImageCache.STATE_FAILURE:
				if (Log.D) {
					Log.d(UIRunnable.class.getName(), "STATE_FAILURE position -->> " + getSubViewHolder().getPosition());
				}
				/**
				 * 得到localLoadImage
				 */
				Boolean localLoadImage = false;
				Object o = subViewHolder.getMoreParameter(SubViewHolder.MORE_PARAMETER_LOCAL_LOAD_IMAGE);
				if (null != o && o instanceof Boolean) {
					localLoadImage = (Boolean) o;
				}
				if (Log.D) {
					Log.d(UIRunnable.class.getName(), "STATE_FAILURE localLoadImage -->> " + localLoadImage);
				}
				iv.setImageDrawable(noPictureDrawable);
				break;
			case GlobalImageCache.STATE_SUCCESS:
				if (Log.D) {
					Log.d(UIRunnable.class.getName(), "STATE_SUCCESS position -->> " + getSubViewHolder().getPosition());
				}
				BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(imageState);
				Bitmap bitmap = imageState.getBitmap();
				
				if(bitmap == null || (bitmapDigest == null && bitmap.isRecycled())) {
					iv.setImageDrawable(defaultDrawable);
					imageState.none();
				} else {
					iv.setImageDrawable(new MyBitmapDrawable(iv.getResources(), subViewHolder, bitmapDigest, bitmap));
				}
				break;
			}

		}
		gc();
	}

	/**
	 * 子类可复写
	 */
	protected View getItemView() {
		AdapterHelper adapterHelper = getSubViewHolder().getAdapter().getAdapterHelper();

		SubViewHolder holder = getSubViewHolder();
		Object item = holder.getAdapter().getItem(holder.getPosition());
		if (Log.D) {
			Log.d(UIRunnable.class.getName(), "getItemView() old item -->> " + holder.getItemData());
			Log.d(UIRunnable.class.getName(), "getItemView() new item -->> " + item);
		}
		if (null != item && holder.getItemData() == item) {
			if (Log.D) {
				Log.d(UIRunnable.class.getName(), "oldItemData and newItemData is equals -->> ");
			}
			return adapterHelper.getItemView(getSubViewHolder().getPosition(), true);
		} else {
			if (Log.D) {
				Log.d(UIRunnable.class.getName(), "oldItemData and newItemData not equals -->> ");
			}
			return null;
		}

	}

	/**
	 * 供子类调用
	 */
	protected SubViewHolder getSubViewHolder() {
		return subViewHolder;
	}

	private void gc() {
		subViewHolder = null;
		imageState = null;
	}

	/**
	 * 无图版的长按事件（这个用于view，还有一个用于adapterView的已经删了）
	 */
	private static class ViewLongClickForNoImage implements OnLongClickListener {

		private SubViewHolder subViewHolder;
		private BitmapDigest bitmapDigest;
		private SimpleImageProcessor imageProcessor;

		public ViewLongClickForNoImage(SubViewHolder subViewHolder, BitmapDigest bitmapDigest) {
			this.subViewHolder = subViewHolder;
			this.bitmapDigest = bitmapDigest;
			this.imageProcessor = ((SimpleSubViewBinder) subViewHolder.getAdapter().getViewBinder()).getSimpleImageProcessor();
		}

		@Override
		public boolean onLongClick(View v) {
			v.setLongClickable(false);// 去掉交互事件
			if (Log.D) {
				Log.d(TAG, "ViewLongClickForNoImage v.getId -->> " + v.getId());
				Log.d(TAG, "ViewLongClickForNoImage subViewHolder -->> " + subViewHolder);
			}
			if (null != subViewHolder) {
				subViewHolder.putMoreParameter(SubViewHolder.MORE_PARAMETER_MANUAL_GET_IMAGE, true);
				imageProcessor.show(subViewHolder, GlobalImageCache.getImageState(bitmapDigest));
			}

			gc();
			return true;
		}

		private void gc() {
			subViewHolder = null;
			bitmapDigest = null;
			imageProcessor = null;
		}

	}

}
