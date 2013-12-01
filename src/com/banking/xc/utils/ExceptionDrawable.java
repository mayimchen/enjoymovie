package com.banking.xc.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import skytv_com.banking.enjoymovie.R;

/**
 * 异常图片类
 */
public class ExceptionDrawable extends Drawable {

	private final String text;
	private static Bitmap bitmapLoading = null;
	private static Bitmap bitmapNoPicture = null;
	private static int widthLoading;
	private static int heightLoading;
	private static int widthNoPic;
	private static int heightNoPic;
	public static final int LOADING  = 10;
	public static final int NOPICTURE  = 11;
	private int tag ;

	public ExceptionDrawable(Context context, String text,int tag) {
		this.text = text;
		this.tag = tag;
		switch(tag){
				case LOADING:
				if (bitmapLoading == null) {
					try {
						BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.loading_picture);
						bitmapLoading = drawable.getBitmap();
						widthLoading = bitmapLoading.getWidth();
						heightLoading = bitmapLoading.getHeight();
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
				break;
				case NOPICTURE:
					if (bitmapNoPicture == null) {
						try {
							BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.no_picture);
							bitmapNoPicture = drawable.getBitmap();
							widthNoPic = bitmapNoPicture.getWidth();
							heightNoPic = bitmapNoPicture.getHeight();
						} catch (Throwable e) {
							e.printStackTrace();
						}
					}
					break;	
		}
	}

	/**
	 * 无图构造方法
	 * @param context
	 * @param text
	 * @param i
	 */
	/*public ExceptionDrawable(Context context, String text,int i) {
		this.text = text;
		if (bitmap == null) {
			try {
				BitmapDrawable drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.no_picture);
				bitmap = drawable.getBitmap();
				width = bitmap.getWidth();
				height = bitmap.getHeight();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}*/
	private Paint paint = new Paint();

	{
		paint.setColor(Color.GRAY);
		paint.setStyle(Paint.Style.FILL);
		paint.setTextSize(DPIUtil.dip2px(12));
		paint.setTextAlign(Align.CENTER);
		paint.setAntiAlias(true);
	}

	@Override
	public void draw(Canvas canvas) {
		if (Log.D) {
			Log.d(ExceptionDrawable.class.getName(), "draw() -->> ");
		}
		Rect bounds = getBounds();
		float x = bounds.right - bounds.width() / 2;
		float y = bounds.bottom - bounds.height() / 2;
		canvas.drawText(text, x, y, paint);
		switch(tag){
		case LOADING:
			if (bitmapLoading != null) {
				canvas.drawBitmap(bitmapLoading, x - (widthLoading / 2), y - (heightLoading / 2) + DPIUtil.dip2px(10), paint);
			}
			break;
		case NOPICTURE:
			if (bitmapNoPicture != null) {
				canvas.drawBitmap(bitmapNoPicture, x - (widthNoPic / 2), y - (heightNoPic / 2) + DPIUtil.dip2px(10), paint);
			}
			break;
		}
		
	}

	@Override
	public int getOpacity() {
		return 0;
	}

	@Override
	public void setAlpha(int alpha) {
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
	}

}
