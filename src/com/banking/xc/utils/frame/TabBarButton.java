

package com.banking.xc.utils.frame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;

import com.banking.xc.utils.Log;

import android.widget.RadioButton;
import skytv_com.banking.enjoymovie.R;

public class TabBarButton extends RadioButton{
	
	private StateController stateController = new StateController();// 状态控制器
	
	Context context;
	public TabBarButton(Context context) {
		super(context);
		this.context = context;
	}
	
	public TabBarButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	public void setState(String label, int imageId)
	{
		RadioStateDrawable offDrawable = new RadioStateDrawable(context, imageId, label, false, RadioStateDrawable.SHADE_GRAY);
		RadioStateDrawable onDrawable = new RadioStateDrawable(context, imageId, label, true, RadioStateDrawable.SHADE_YELLOW);
		offDrawable.setStateController(stateController);
		onDrawable.setStateController(stateController);
		setStateImageDrawables(onDrawable,offDrawable);
	}
	
	public void setState(String label, int imageId, int offState, int onState)
	{
		RadioStateDrawable offDrawable = new RadioStateDrawable(context, imageId, label, false, offState);
		RadioStateDrawable onDrawable = new RadioStateDrawable(context, imageId, label, true, onState);
		offDrawable.setStateController(stateController);
		onDrawable.setStateController(stateController);
		setStateImageDrawables(onDrawable,offDrawable);
	}

	public void setState(String label, int onId, int offId)
	{
		Resources resource = this.getResources();
		Drawable offDrawable = resource.getDrawable(offId);
		Drawable onDrawable = resource.getDrawable(onId);
		setStateImageDrawables(onDrawable,offDrawable);
	}

	private void setStateImageDrawables(Drawable onDrawable, Drawable offDrawable)
	{

		StateListDrawable drawables = new StateListDrawable();
		
		int stateChecked = android.R.attr.state_checked;
		int stateFocused = android.R.attr.state_focused;
		int statePressed = android.R.attr.state_pressed;
		int stateWindowFocused = android.R.attr.state_window_focused;
		
		Resources resource = this.getResources();
		Drawable xDrawable = resource.getDrawable(R.drawable.bottom_bar_highlight);
		
		drawables.addState(new int[]{ stateChecked, -stateWindowFocused}, offDrawable);
		drawables.addState(new int[]{-stateChecked, -stateWindowFocused}, offDrawable);
		drawables.addState(new int[]{ stateChecked,  statePressed      }, onDrawable);
		drawables.addState(new int[]{-stateChecked,  statePressed      }, onDrawable);
		drawables.addState(new int[]{ stateChecked,  stateFocused      }, onDrawable);
		drawables.addState(new int[]{-stateChecked,  stateFocused      }, offDrawable);
		drawables.addState(new int[]{ stateChecked                     }, onDrawable);
		drawables.addState(new int[]{-stateChecked                     }, offDrawable);
		drawables.addState(new int[]{                  				   }, xDrawable);
		this.setButtonDrawable(drawables);
	}

	public StateController getStateController() {
		return stateController;
	}
	
	public class StateController {
		private Integer num;

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
			invalidate();
		}

		public void addNum() {
			setNum(null == num ? 1 : num + 1);
		}
	}

}
