package com.banking.xc.utils.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import skytv_com.banking.enjoymovie.R;

import com.banking.xc.utils.ui.TouchUtil;

public class GroupOrderPopupWindow implements MyPopupWindowInterface {

	private View view;
	private View parentView;
	private View viewMain;
	private PopupWindow mPopupWindow;
	private boolean loading = false;//正在发送网络请求
	public GroupOrderPopupWindow(View view, View parentView, View viewMain) {
		this.view = view;
		this.parentView = parentView;
		this.viewMain = viewMain;
		initPopupWindow();

	}
	
	public boolean isLoading() {
		return loading;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	private void initPopupWindow() {

		mPopupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setAnimationStyle(R.style.popup_anim_alpha_style);
		mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					if(loading){
						return false;
					}
					float x = event.getRawX();
					float y = event.getRawY();
					if (!TouchUtil.checkDownPointerInView(viewMain, x, y)) {
						mPopupWindow.dismiss();
						return true;
					}
				}
				return false;
			}

			
		});

	}

	@Override
	public void show() {
		mPopupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);

	}

	@Override
	public void dismiss() {
		mPopupWindow.dismiss();

	}

}
