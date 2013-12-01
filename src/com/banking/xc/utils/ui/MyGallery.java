package com.banking.xc.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class MyGallery extends Gallery {

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyGallery(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			initPressed();
			for (int i = getChildCount() - 1; i >= 0; i--) {
				getChildAt(i).onTouchEvent(event);
			}
			break;
		default:
			break;
		}
		return true;
	}

	private void initPressed() {
		for (int i = getChildCount() - 1; i >= 0; i--) {
			getChildAt(i).setPressed(false);
		}
		setPressed(false);
	}

}
