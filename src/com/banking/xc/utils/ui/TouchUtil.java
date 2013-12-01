package com.banking.xc.utils.ui;

import android.view.View;

public class TouchUtil {
		public static boolean checkDownPointerInView(View view, float x, float y) {
			int[] location2 = new int[2];
			view.getLocationOnScreen(location2);
			if (x >= location2[0] && x <=  location2[0] + view.getWidth() && y >= location2[1] && y <= location2[1] + view.getHeight()) {
				return true;
			}
			return false;
		}
	
}
