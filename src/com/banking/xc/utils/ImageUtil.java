package com.banking.xc.utils;

import java.io.File;
import java.io.InputStream;

import skytv_com.banking.enjoymovie.MainActivity;
import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.cache.GlobalImageCache.BitmapDigest;
import com.novoda.imageloader.core.bitmap.BitmapUtil;

public class ImageUtil {

	public static final int DEFAULT_ROUND = 6;

	public static final int IMAGE_MAX_WIDTH = 666;
	public static final int IMAGE_MAX_HEIGHT = 666;

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
			//GlobalImageCache.getLruBitmapCache().clean();
		}
		return getLayoutInflater().inflate(resource, root, attachToRoot);
	}

	private static LayoutInflater getLayoutInflater() {

		LayoutInflater li = null;

		MyActivity currentMyActivity = MyApplication.getInstance().getCurrentMyActivity();
		MainActivity mainActivity = MyApplication.getInstance().getMainActivity();
		if (null != currentMyActivity) {
			li = LayoutInflater.from(currentMyActivity);
		} else if (null != mainActivity) {
			li = LayoutInflater.from(mainActivity);
		} else {
			li = (LayoutInflater) MyApplication.getInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		return li;

	}

	/**
	 * 
	 * @param drawable
	 *            drawable图片
	 * @param roundPx
	 *            角度
	 * @return
	 * @Description:// 获得圆角图片的方法
	 */

	public static Bitmap getRoundedCornerBitmap(Drawable drawable, float roundPx) {
		Bitmap bitmap = drawableToBitmap(drawable);
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap

		.getHeight(), Config.ARGB_8888);

		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;

		final Paint paint = new Paint();

		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);

		paint.setColor(color);

		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;

	}

	/**
	 * 
	 * @param drawable
	 * @return
	 * @Description:将Drawable转化为Bitmap
	 */

	private static Bitmap drawableToBitmap(Drawable drawable) {

		int width = drawable.getIntrinsicWidth();

		int height = drawable.getIntrinsicHeight();

		Bitmap bitmap = Bitmap.createBitmap(width, height,

		drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

		: Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);

		drawable.setBounds(0, 0, width, height);

		drawable.draw(canvas);

		return bitmap;

	}

	/**
	 * 根据bitmapDigest得到bitmap
	 */
	public static Bitmap createBitmap(InputWay inputWay, BitmapDigest bd) {
		
		if(bd.isLarge()){
			if (Log.D) {
				Log.d(ImageUtil.class.getName(), "createBitmap() bitmapDigest isLarge let cleanMost  -->> ");
			}
			GlobalImageCache.getLruBitmapCache().cleanMost();
		}
		
		int width = bd.getWidth();
		int height = bd.getHeight();

		Bitmap bitmap = createBitmap(inputWay, width, height);

		if (null == bitmap) {
			return null;
		}

		if (0 != bd.getRound()) {
			bitmap = toRoundCorner(bitmap, bd.getRound());
		}

		return bitmap;
	}

	/**
	 * 根据宽高得到bitmap
	 */
	public static Bitmap createBitmap(InputWay inputWay, int width, int height) {

		if (Log.D) {
			Log.d(ImageUtil.class.getName(), "createBitmap() width=" + width + " height=" + height + " -->> ");
		}

		if (width > DPIUtil.dip2px(IMAGE_MAX_WIDTH)) {
			width = DPIUtil.dip2px(IMAGE_MAX_WIDTH);
		}

		if (height > DPIUtil.dip2px(IMAGE_MAX_HEIGHT)) {
			height = DPIUtil.dip2px(IMAGE_MAX_HEIGHT);
		}

		if (width == 0 && height == 0) {
			width = DPIUtil.dip2px(IMAGE_MAX_WIDTH);
			height = DPIUtil.dip2px(IMAGE_MAX_HEIGHT);
		}

		BitmapUtil bitmapUtil = new BitmapUtil();

		Bitmap bitmap = null;

		for (int i = 0; i < 2; i++) {

			if (0 != inputWay.getResourceId()) {
				bitmap = bitmapUtil.decodeResourceBitmapAndScale(MyApplication.getInstance(), width, height, inputWay.getResourceId(), false);
			} else if (null != inputWay.getFile()) {
				bitmap = bitmapUtil.decodeFileAndScale(inputWay.getFile(), width, height, false);
			} else if (null != inputWay.getInputStream()) {
				// TODO 重复尝试时肯定会出错
				/*Bitmap unscaledBitmap = bitmapUtil.decodeInputStream(inputWay.getInputStream());
				if (null == unscaledBitmap) {
					bitmap = null;
				} else {
					bitmap = bitmapUtil.scaleBitmap(unscaledBitmap, width, height, false);
				}*/
			} else if (null != inputWay.getByteArray()) {
				Bitmap unscaledBitmap = null;
				try {
					unscaledBitmap = BitmapFactory.decodeByteArray(inputWay.getByteArray(), 0, inputWay.getByteArray().length);
				} catch (Throwable e) {
				}
				if (null == unscaledBitmap) {
					bitmap = null;
				} else {
					bitmap = bitmapUtil.scaleBitmap(unscaledBitmap, width, height, false);
				}
			}

			// 内存不足就会返回null
			if (null == bitmap) {
				GlobalImageCache.getLruBitmapCache().clean();
			} else {
				break;
			}

		}

		if (Log.D) {
			if (null != bitmap) {
				Log.d(ImageUtil.class.getName(), "createBitmap() return width=" + bitmap.getWidth() + " height=" + bitmap.getHeight() + " -->> ");
			}
		}
		return bitmap;

	}

	/**
	 * 圆角
	 */
	public static Bitmap toRoundCorner(Bitmap inBitmap, int dp) {

		if (Log.D) {
			Log.d(ImageUtil.class.getName(), "toRoundCorner() dp -->> " + dp);
		}

		float px = DPIUtil.dip2px(dp);

		Bitmap outBitmap = Bitmap.createBitmap(inBitmap.getWidth(), inBitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(outBitmap);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, inBitmap.getWidth(), inBitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, px, px, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(inBitmap, rect, rect, paint);

		inBitmap.recycle();

		if (Log.D) {
			Log.d(ImageUtil.class.getName(), "toRoundCorner() bitmap -->> " + outBitmap);
		}

		return outBitmap;
	}

	/**
	 * 把三种输入方式封装起来
	 */
	public static class InputWay {

		private int resourceId;
		private File file;
		private InputStream inputStream;
		private byte[] byteArray;

		public static InputWay createInputWay(HttpResponse response) {
			InputWay inputWay = new InputWay();
			inputWay.setByteArray(response.getInputData());
			inputWay.setFile(response.getSaveFile());
			return inputWay;
		}

		public int getResourceId() {
			return resourceId;
		}

		public void setResourceId(int resourceId) {
			this.resourceId = resourceId;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public byte[] getByteArray() {
			return byteArray;
		}

		public void setByteArray(byte[] byteArray) {
			this.byteArray = byteArray;
		}

	}

}
