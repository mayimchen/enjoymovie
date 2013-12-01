package com.banking.xc.utils;

import skytv_com.banking.enjoymovie.MainActivity;
import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banking.xc.utils.cache.GlobalImageCache;

public class InflateUtil {
	public static View inflate(int resource, ViewGroup root) {
		try {
			return getLayoutInflater().inflate(resource, root);
		} catch (Throwable e) {
			GlobalImageCache.getLruBitmapCache().clean();
		}
		return getLayoutInflater().inflate(resource, root);
	}

	public static View inflate(int resource, ViewGroup root, boolean attachToRoot) {
		try {
			return getLayoutInflater().inflate(resource, root, attachToRoot);
		} catch (Throwable e) {
			GlobalImageCache.getLruBitmapCache().clean();
		}
		return getLayoutInflater().inflate(resource, root, attachToRoot);
	}

	private static LayoutInflater getLayoutInflater() {

		LayoutInflater li = null;
		MainActivity mainActivity = MyApplication.getInstance().getMainActivity();
		if (null != mainActivity) {
			li = LayoutInflater.from(mainActivity);
		} else {
			li = (LayoutInflater) MyApplication.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		return li;

	}
}
