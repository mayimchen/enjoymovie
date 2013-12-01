package com.banking.xc.utils;

import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpGroupSetting;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.HttpGroup.OnCommonListener;
import com.banking.xc.utils.ImageUtil.InputWay;
import com.banking.xc.utils.MyActivity.ResumeListener;

/**
 * 无图版工具类
 * 
 * 
 */
public class NoImageUtils {

	/**
	 * 获取无图版的开关
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean needNoImage() {
		String key = "";
		boolean isNeedNoImage = CommonUtil.getSharedPreferences().getBoolean(key, false);

		return isNeedNoImage && !NetUtils.isWifi();

	}

	public static void setIfNeedNoImage(boolean isNeedNoImage) {
		Editor editor = CommonUtil.getSharedPreferences().edit();
		String key = "";
		editor.putBoolean(key, isNeedNoImage);
		editor.commit();
	}

	public static void initImageView(final MyActivity myActivity, final HttpGroup imageHttpGroup, final ImageView imageView, final String url, boolean isFirst) {

		if (isFirst) {
			myActivity.addResumeListener(new ResumeListener() {

				@Override
				public void onResume() {
					initImageView(myActivity, imageHttpGroup, imageView, url, false);
				}
			});
		}

		if (needNoImage()) {
			imageView.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					loadImage(true, myActivity, imageHttpGroup, imageView, url);
					return true;
				}
			});
		} else {
			imageView.setOnLongClickListener(null);
			imageView.setLongClickable(false);
		}

		// 无论是否无图版都先得尝试加载
		loadImage(false, myActivity, imageHttpGroup, imageView, url);

	}

	public static void loadImage(boolean mustLoad, final MyActivity myActivity, HttpGroup imageHttpGroup, final ImageView imageView, String url) {

		final boolean useCache = (!mustLoad && needNoImage());

		OnCommonListener onCommonListener = new OnCommonListener() {

			@Override
			public void onReady(HttpSettingParams httpSettingParams) {
			}

			@Override
			public void onEnd(final HttpResponse httpResponse) {
				myActivity.post(new Runnable() {
					@Override
					public void run() {
						Bitmap bitmap = ImageUtil.createBitmap(InputWay.createInputWay(httpResponse) , 0, 0);
						imageView.setImageDrawable(new BitmapDrawable(bitmap));
						imageView.invalidate();
					}
				});

			}

			@Override
			public void onError(HttpError error) {
				myActivity.post(new Runnable() {
					@Override
					public void run() {
						imageView.setImageDrawable(new ExceptionDrawable(myActivity, "暂无图片" ,ExceptionDrawable.NOPICTURE));// 暂无图片
					}
				});
			}

		};

		HttpSetting httpSetting = new HttpSetting();
		httpSetting.setType(HttpGroupSetting.TYPE_IMAGE);
		httpSetting.setUrl(url);
		httpSetting.setListener(onCommonListener);
		if (useCache) {
			httpSetting.setCacheMode(HttpSetting.CACHE_MODE_ONLY_CACHE);
		}

		imageHttpGroup.add(httpSetting);

	}

	public static void setIfNeedAlertDialog(boolean isNeedAlertDialog) {
		Editor editor = CommonUtil.getSharedPreferences().edit();
		String key = "";
		editor.putBoolean(key, isNeedAlertDialog);
		editor.commit();
	}

	public static boolean isNeedAlertDialog() {
		String key = "";
		return CommonUtil.getSharedPreferences().getBoolean(key, true);
	}

}
