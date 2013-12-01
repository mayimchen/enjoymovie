package com.banking.xc.utils;

import java.util.Collection;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemLongClickListener;
import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.ImageUtil.InputWay;
import com.banking.xc.utils.MyActivity.DestroyListener;
import com.banking.xc.utils.MyActivity.PauseListener;
import com.banking.xc.utils.MyActivity.ResumeListener;
import com.banking.xc.utils.adapter.ImageLoader;
import com.banking.xc.utils.adapter.SimpleImageProcessor;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.banking.xc.utils.cache.GlobalImageCache.ImageState;

public class MySimpleAdapter extends SimpleBeanAdapter implements DestroyListener, PauseListener, ResumeListener {

	private MyActivity myActivity;
	private Handler handler;// 长期持有

	private NextPageLoader nextPageLoader;

	public static final int THUMB_TYPE_NONE = 0;// 不使用缩略图
	public static final int THUMB_TYPE_CENTER = 1;// 使用缩略图，保持比例并居中
	private int thumbType;
	private float thumbWidth;
	private float thumbHeight;

	/**
	 * 整个ViewGroup使用一个事件
	 */
	private OnItemLongClickListener imageClickListener;

	/**
	 * 整个ViewGroup使用一个事件
	 */
	private OnTouchListener imageParentTouchListener;

	/**
	 * 数据导致界面改变时应该禁止交互，否则容易出现意外情况
	 */
	private boolean isLongClickAndDataSetChange;

	private boolean isFinishing;

	/**
	 * 默认不使用缩略图
	 */
	public MySimpleAdapter(MyActivity myActivity, List<?> beanList, int itemId, String[] from, int[] to) {
		super(myActivity, beanList, itemId, from, to);
		this.myActivity = myActivity;// 用于取出HttpGroup
		this.handler = myActivity.getHandler();
		myActivity.addDestroyListener(this);// 销毁通知
		myActivity.addPauseListener(this);// 暂停通知
		myActivity.addResumeListener(this);// 恢复通知
	}

	/**
	 * 使用缩略图
	 */
	public MySimpleAdapter(MyActivity myActivity, List<?> beanList, int itemId, String[] from, int[] to, int thumbType, float thumbWidth, float thumbHeight) {
		this(myActivity, beanList, itemId, from, to);
		this.thumbType = thumbType;
		this.thumbWidth = thumbWidth;
		this.thumbHeight = thumbHeight;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(Log.D){
			Log.d(MySimpleAdapter.class.getName(), "position = " + position + " convertView = " + convertView + " -->> ");
		}
		View view = super.getView(position, convertView, parent);
		if(Log.D){
			Log.d(MySimpleAdapter.class.getName(), "position = " + position + " view = " + view + " -->> ");
		}
		return view;
	}

	@Override
	public void onDestroy() {
		isFinishing = true;
		gc();
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onResume() {
		notifyDataSetChanged();
	}
	
	public void gc(){
		myActivity = null;
		nextPageLoader = null;
		super.gc();
	}

	public void setNextPageLoader(NextPageLoader nextPageLoader) {
		this.nextPageLoader = nextPageLoader;
	}

	/**
	 * 本实例允许无图版功能生效并且当前环境是无图版才返回true
	 */
	public boolean isNoImage() {
		return isAllowNoImage() && NoImageUtils.needNoImage();
	}

	/**
	 * 接口
	 */
	public interface ImageProcessor {

		Bitmap create(InputWay inputWay, BitmapDigest bd);

		/**
		 * image state setup to view
		 */
		void show(SubViewHolder subViewHolder, ImageState is);

	}

}
