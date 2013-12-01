package com.banking.xc.utils;

import skytv_com.banking.enjoymovie.R;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SelectedChangeListener implements CompoundButton.OnCheckedChangeListener{
	
	
	public SelectedChangeListener()
	{
		
	}
	/* (non-Javadoc)
	 * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
	 * 
	 */
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch(buttonView.getId())
		{
		case 0://R.id.show_password:
			break;
		}
	}
	
	private void handleShowPassword(boolean isChecked)
	{
		if(isChecked)
		{
			
		}
	}
}
