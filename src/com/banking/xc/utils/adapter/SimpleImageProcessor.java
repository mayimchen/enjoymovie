package com.banking.xc.utils.adapter;

import skytv_com.banking.enjoymovie.MyApplication;
import android.graphics.Bitmap;
import android.os.Handler;

import com.banking.xc.utils.ImageUtil;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.ImageUtil.InputWay;
import com.banking.xc.utils.MySimpleAdapter.ImageProcessor;
import com.banking.xc.utils.SimpleBeanAdapter.SubViewHolder;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.banking.xc.utils.cache.GlobalImageCache.ImageState;

public class SimpleImageProcessor implements ImageProcessor {

	@Override
	public Bitmap create(InputWay inputWay, BitmapDigest bd) {
		return ImageUtil.createBitmap(inputWay, bd);
	}

	@Override
	public void show(SubViewHolder subViewHolder, ImageState is) {

		if (Log.D) {
			Log.d(SimpleImageProcessor.class.getName(), "show() position = " + subViewHolder.getPosition() + " -->> ");
		}

		if (null == subViewHolder.getSubView() && // 从网络加载回来，
				subViewHolder.getAdapter().getAdapterHelper().getAdapterView().getChildCount() < 1// 界面还没绘画出来
		) {
			if (Log.D) {
				Log.d(SimpleImageProcessor.class.getName(), "show() sleep 200 -->> ");
			}
			try {
				Thread.sleep(200);// 睡眠200毫秒
			} catch (InterruptedException e) {
			}
		}

		Thread uiThread = MyApplication.getInstance().getUiThread();

		if (Thread.currentThread() == uiThread) {
			if (Log.D) {
				Log.d(SimpleImageProcessor.class.getName(), "show() uiThread true -->> ");
			}
			provideUIRunnable(subViewHolder, is).run();
		} else {
			if (Log.D) {
				Log.d(SimpleImageProcessor.class.getName(), "show() uiThread false -->> ");
			}
			subViewHolder.getAdapter().UIWorkCentralized(provideUIRunnable(subViewHolder, is));
		}

		boolean loaded = false;
		Object o = subViewHolder.getMoreParameter(SubViewHolder.MORE_PARAMETER_LOADED);
		if (null != o && o instanceof Boolean) {
			loaded = (Boolean) o;
		}
		
		if(loaded){// 用完需要马上重置
			subViewHolder.putMoreParameter(SubViewHolder.MORE_PARAMETER_LOADED, false);
		}

		if (Log.D) {
			Log.d(SimpleImageProcessor.class.getName(), "show() is.getState() -->> " + is.getState());
			Log.d(SimpleImageProcessor.class.getName(), "show() loaded -->> " + loaded);
		}
		if ((GlobalImageCache.STATE_NONE == is.getState() || GlobalImageCache.STATE_FAILURE == is.getState()) && !loaded) {
			if (Log.D) {
				Log.d(SimpleImageProcessor.class.getName(), "STATE_NONE or STATE_FAILURE position = " + subViewHolder.getPosition() + " -->> ");
			}
			loadImage(subViewHolder, is);
		}

	}

	protected void loadImage(SubViewHolder subViewHolder, ImageState is) {
		new ImageLoader(subViewHolder, is, this)//
				.load();
	}

	protected UIRunnable provideUIRunnable(SubViewHolder subViewHolder, ImageState imageState) {
		return new UIRunnable(subViewHolder, imageState);
	}

}
