package com.banking.xc.utils.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import skytv_com.banking.enjoymovie.R;

public class JDImageView extends ImageView {

	private float ratio; //宽高比,width/height,Attention:not height/width

	private boolean isChildRequestLayout = false;
	
	public JDImageView(Context context) {
		super(context);
	}

	public JDImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.image);
		ratio = ta.getFloat(R.styleable.image_ratio, 1.0f);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int size = MeasureSpec.getSize(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		if (ratio >= 1) {
			setMeasuredDimension(size, Math.round(size / ratio));
		} else {
			setMeasuredDimension(Math.round(height * ratio), height);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		return false;
	}

	@Override
	public void setImageDrawable(Drawable drawable) {
		isChildRequestLayout = true;
		super.setImageDrawable(drawable);
		isChildRequestLayout = false;
	}

	@Override
	public void requestLayout() {
		if (getWidth() > 0 && getHeight() > 0 && isChildRequestLayout) {
			forceLayout();
			return;
		}
		super.requestLayout();
	}

}
