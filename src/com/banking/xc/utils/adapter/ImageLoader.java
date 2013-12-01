package com.banking.xc.utils.adapter;

import android.graphics.Bitmap;

import com.banking.xc.utils.HttpGroup.HttpGroupSetting;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.SimpleBeanAdapter;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.HttpGroup.OnCommonListener;
import com.banking.xc.utils.ImageUtil.InputWay;
import com.banking.xc.utils.MySimpleAdapter.ImageProcessor;
import com.banking.xc.utils.SimpleBeanAdapter.SubViewHolder;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.banking.xc.utils.cache.GlobalImageCache.ImageState;

public class ImageLoader implements OnCommonListener {

	private ImageState imageState;
	private ImageProcessor imageProcessor;

	private SubViewHolder subViewHolder;

	private Boolean manualGetImage = false;

	public ImageLoader(SubViewHolder subViewHolder, ImageState imageState, ImageProcessor imageProcessor) {
		this.imageState = imageState;
		this.imageProcessor = imageProcessor;
		this.subViewHolder = new SubViewHolder();

		// 用到的才保留，不要过多引用
		this.subViewHolder.setAdapter(subViewHolder.getAdapter());
		this.subViewHolder.setPosition(subViewHolder.getPosition());
		this.subViewHolder.setSubViewId(subViewHolder.getSubViewId());
		this.subViewHolder.setItemData(subViewHolder.getItemData());
		this.subViewHolder.setSubData(subViewHolder.getSubData());

		Object o = subViewHolder.getMoreParameter(SubViewHolder.MORE_PARAMETER_MANUAL_GET_IMAGE);
		if (null != o && o instanceof Boolean) {
			manualGetImage = (Boolean) o;
		}
	}

	/**
	 * 发起网络加载请求
	 */
	public void load() {
		BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(imageState);
		if (null == bitmapDigest) {
			return;
		}
		
		imageState.loading();

		String url = bitmapDigest.getUrl();
		if (Log.D) {
			Log.d(ImageLoader.class.getName(), "load() url -->> " + url);
			Log.d(ImageLoader.class.getName(), "load() position -->> " + subViewHolder.getPosition());
		}
		SimpleBeanAdapter adapter = subViewHolder.getAdapter();

		HttpSetting httpSetting = new HttpSetting();
		httpSetting.setEffect(HttpSetting.EFFECT_NO);
		//httpSetting.setType(HttpGroupSetting.TYPE_IMAGE);
		httpSetting.setUrl(url);

		if (!manualGetImage && adapter.allowNoImageAndIsNoImage()) {
			httpSetting.setCacheMode(HttpSetting.CACHE_MODE_ONLY_CACHE);
			subViewHolder.putMoreParameter(SubViewHolder.MORE_PARAMETER_LOCAL_LOAD_IMAGE, true);
		}

		if (adapter.isAssetsCache()) {
			httpSetting.setCacheMode(HttpSetting.CACHE_MODE_ASSETS);
		}

		httpSetting.setListener(this);

		adapter.getAdapterHelper().getImageHttpGroup().add(httpSetting);

	}

	@Override
	public void onError(HttpError error) {
		if (Log.D) {
			Log.d(ImageLoader.class.getName(), "onError() position -->> " + subViewHolder.getPosition());
		}

		try {

			/*
			 * 缓存
			 */
			imageState.failure();

			/*
			 * 显示（外部提供方法）
			 */
			subViewHolder.putMoreParameter(SubViewHolder.MORE_PARAMETER_LOADED, true);
			imageProcessor.show(subViewHolder, imageState);

		} finally {
			gc();
		}
	}

	@Override
	public void onEnd(final HttpResponse httpResponse) {
		if (Log.D) {
			Log.d(ImageLoader.class.getName(), "onEnd() position -->> " + subViewHolder.getPosition());
		}

		try {

			BitmapDigest bitmapDigest = GlobalImageCache.getBitmapDigest(imageState);
			if (Log.D) {
				Log.d(ImageLoader.class.getName(), "onEnd() bitmapDigest -->> " + bitmapDigest);
			}
			if (null == bitmapDigest) {
				if (Log.D) {
					Log.d(ImageLoader.class.getName(), "onEnd() bitmapDigest is null position -->> " + subViewHolder.getPosition());
				}
				return;
			}

			/*
			 * 图像（外部提供方法）
			 */
			Bitmap b = imageProcessor.create(InputWay.createInputWay(httpResponse), bitmapDigest);

			/*
			 * 缓存
			 */
			if (null == b) {
				if (Log.D) {
					Log.d(ImageLoader.class.getName(), "onEnd() bitmap is null position -->> " + subViewHolder.getPosition());
				}
				imageState.none();
				return;
			}
			imageState.success(b);

			/*
			 * 显示（外部提供方法）
			 */
			subViewHolder.putMoreParameter(SubViewHolder.MORE_PARAMETER_LOADED, true);
			imageProcessor.show(subViewHolder, imageState);

		} finally {
			gc();
		}

	}

	@Override
	public void onReady(HttpSettingParams httpSettingParams) {
	}

	/**
	 * 内存回收
	 */
	public void gc() {
		imageState = null;
		imageProcessor = null;
		subViewHolder = null;
	}

}
