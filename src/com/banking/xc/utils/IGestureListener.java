package com.banking.xc.utils;

import android.view.MotionEvent;
import android.view.GestureDetector.OnGestureListener;
import android.view.ViewParent;

public class IGestureListener implements OnGestureListener {

	private TouchFlingActionListener onListener;
	private ViewParent parent;

	public IGestureListener(ViewParent parent, TouchFlingActionListener listener) {
		onListener = listener;
		this.parent = parent;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		if (Log.D) {
			Log.d("IGestureListener", "onDown");
		}
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if (Log.D) {
			Log.d("IGestureListener", "onFling velocityX=" + velocityX + ",velocityY=" + velocityY);
		}
		if (e1 != null && e2 != null) {
			if (e1.getX() - e2.getX() > 5) {
				onListener.previous();
				return false;
			} else if (e1.getX() - e2.getX() < -5) {
				onListener.next();
				return false;
			}
		}
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		if (Log.D) {
			Log.d("IGestureListener", "onLongPress");
		}
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if (Log.D) {
			Log.d("IGestureListener", "onScroll distanceX=" + distanceX + ",distanceY=" + distanceY);
		}
		this.parent.requestDisallowInterceptTouchEvent(true);
		if (Math.abs(distanceX) > Math.abs(distanceY)) {
			if (e1 != null && e2 != null) {
				if (e1.getX() - e2.getX() > 50) {
					onListener.previous();
					return false;
				} else if (e1.getX() - e2.getX() < -50) {
					onListener.next();
					return false;
				}
			}
			System.out.println("横向");
		}else{
			System.out.println("纵向");
			return true;
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		if (Log.D) {
			Log.d("IGestureListener", "onShowPress");
		}
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		if (Log.D) {
			Log.d("IGestureListener", "onSingleTapUp");
		}
		onListener.startActivity();
		return false;
	}

	public interface TouchFlingActionListener {
		public void next();

		public void previous();

		public void startActivity();
	}
}
