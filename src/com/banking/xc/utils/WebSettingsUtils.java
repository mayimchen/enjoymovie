package com.banking.xc.utils;

import android.webkit.WebSettings;

public class WebSettingsUtils {

	public void setGeolocationEnabled(WebSettings settings) {
		if (settings != null) {
			settings.setGeolocationEnabled(true);
		}
	}

}
