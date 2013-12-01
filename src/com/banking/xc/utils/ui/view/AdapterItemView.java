package com.banking.xc.utils.ui.view;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import skytv_com.banking.enjoymovie.R;

import com.banking.xc.utils.ExceptionDrawable;
import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpGroupSetting;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.ImageUtil;
import com.banking.xc.utils.ImageUtil.InputWay;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.MyActivity;

public class AdapterItemView {

	private final String TAG = "AdapterItemView";

	private View root;
	private View line;
	private MyActivity mContext;

	public AdapterItemView(MyActivity context) {
		mContext = context;
		root = ImageUtil.inflate(R.layout.app_my_jd_item, null);

	}

	public View getView() {
		return root;
	}

	

	public void setLineVisibility(int flag) {
		if (line != null) {
			line.setVisibility(flag);
		}
	}

	/**
	 * 加载商品图片
	 * 
	 * @param context
	 * @param icon
	 * @param url
	 */
	public void setDrawable(final ImageView icon, String url) {
		icon.setBackgroundDrawable(new ExceptionDrawable(mContext, "加载中",ExceptionDrawable.LOADING));
		HttpSetting httpSetting = new HttpSetting();
		httpSetting.setUrl(url);
		httpSetting.setType(HttpGroupSetting.TYPE_IMAGE);
		//httpSetting.setCacheMode(HttpSetting.CACHE_MODE_AUTO);
		//httpSetting.setLocalFileCache(true);
		//httpSetting.setLocalFileCacheTime(CacheTimeConfig.MY_JD_ORDER_BUTTON_IMAGE_CACHE);
		httpSetting.setEffect(HttpSetting.EFFECT_NO);
		httpSetting.setListener(new HttpGroup.OnAllListener() {

			@Override
			public void onProgress(int max, int progress) {

			}

			@Override
			public void onError(HttpError error) {
				if (Log.D) {
					Log.d(TAG, " -->> " + error.getErrorCodeStr());
				}
				mContext.post(new Runnable() {
					@Override
					public void run() {
						icon.setBackgroundDrawable(new ExceptionDrawable(mContext, "暂无图片",ExceptionDrawable.NOPICTURE));
					}
				});
			}

			@Override
			public void onEnd(HttpResponse httpResponse) {
				if (Log.D) {
					Log.d(TAG, " -->> onEnd");
				}
				Bitmap bitmap = ImageUtil.createBitmap(InputWay.createInputWay(httpResponse) , 0, 0);
				BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
				final Drawable drawable = bitmapDrawable;
				mContext.post(new Runnable() {
					@Override
					public void run() {
						if (drawable != null) {
							icon.setBackgroundDrawable(drawable);
						} else {
							icon.setBackgroundDrawable(new ExceptionDrawable(mContext, "暂无图片",ExceptionDrawable.NOPICTURE));
						}
					}
				});

			}

			@Override
			public void onStart() {

			}
		});
		mContext.getHttpGroupaAsynPool().add(httpSetting);
	}
}
