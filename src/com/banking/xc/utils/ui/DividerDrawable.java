package com.banking.xc.utils.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

public class DividerDrawable extends Drawable {

	public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
	public static final int VERTICAL = LinearLayout.VERTICAL;
	
	private int orientation;

	public DividerDrawable(int orientation) {
		this.orientation  = orientation;
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		Rect clipBounds = canvas.getClipBounds();
		Paint paint = new Paint();
		if(orientation == VERTICAL) {
			Shader mShader1 = new LinearGradient(clipBounds.left, clipBounds.top, clipBounds.right, clipBounds.top + 1, new int[] { 0x00090909, 0xff090909, 0x00090909 }, null, Shader.TileMode.REPEAT);
			paint.setShader(mShader1);
			canvas.drawRect(clipBounds.left, clipBounds.top, clipBounds.right, clipBounds.top + 1, paint);

			Shader mShader2 = new LinearGradient(clipBounds.left, clipBounds.top + 1, clipBounds.right, clipBounds.top + 2, new int[] { 0x007A7A7A, 0xff7A7A7A, 0x007A7A7A }, null, Shader.TileMode.REPEAT);
			paint.setShader(mShader2);

			canvas.drawRect(clipBounds.left, clipBounds.top + 1, clipBounds.right, clipBounds.top + 2, paint);
		} else {
			Shader mShader1 = new LinearGradient(clipBounds.left, clipBounds.top, clipBounds.left + 1, clipBounds.bottom, new int[] { 0x00090909, 0xff090909, 0x00090909 }, null, Shader.TileMode.REPEAT);
			paint.setShader(mShader1);
			canvas.drawRect(clipBounds.left, clipBounds.top, clipBounds.left + 1, clipBounds.bottom, paint);

			Shader mShader2 = new LinearGradient(clipBounds.left + 1, clipBounds.top, clipBounds.left + 2, clipBounds.bottom, new int[] { 0x007A7A7A, 0xff7A7A7A, 0x007A7A7A }, null, Shader.TileMode.REPEAT);
			paint.setShader(mShader2);

			canvas.drawRect(clipBounds.left + 1, clipBounds.top, clipBounds.left + 2, clipBounds.bottom, paint);
		}

		

	}

	@Override
	public void setAlpha(int alpha) {
	}

	@Override
	public void setColorFilter(ColorFilter cf) {
	}

	@Override
	public int getOpacity() {
		return 0;
	}

}