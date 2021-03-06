package com.banking.xc.utils;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class DPIUtil {

	private static float mDensity;
	private static Display defaultDisplay;

	public static void setDensity(float density) {
		mDensity = density;
		if(Log.D){
			Log.d("DPIUtil", " -->> density=" + density);
		}
	}
	public static float getDensity() {
		return mDensity;
	}
	public static Display getDefaultDisplay() {
		if (null == defaultDisplay) {
			WindowManager systemService = (WindowManager) MyApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
			defaultDisplay = systemService.getDefaultDisplay();
		}
		return defaultDisplay;
	}

	public static int percentWidth(float percent) {
		return (int) (getWidth() * percent);
	}

	public static int percentHeight(float percent) {
		return (int) (getHeight() * percent);
	}

	public static int dip2px(float dipValue) {
		return (int) (dipValue * mDensity + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		return (int) (pxValue / mDensity + 0.5f);
	}

	public static int getWidth() {
		return getDefaultDisplay().getWidth();
	}

	public static int getHeight() {
		return getDefaultDisplay().getHeight();
	}

}
