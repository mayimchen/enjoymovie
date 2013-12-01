package com.banking.xc.utils;

import com.banking.xc.utils.HttpGroup.HttpGroupSetting;

public class HttpGroupUtils {

	public static HttpGroup getHttpGroupaAsynPool() {
		return getHttpGroupaAsynPool(HttpGroupSetting.TYPE_JSON);
	}

	public static HttpGroup getHttpGroupaAsynPool(int type) {
		HttpGroupSetting setting = new HttpGroupSetting();
		setting.setType(type);
		return getHttpGroupaAsynPool(setting);
	}

	public static HttpGroup getHttpGroupaAsynPool(HttpGroupSetting setting) {
		HttpGroup httpGroup = new HttpGroup.HttpGroupaAsynPool(setting);
		return httpGroup;
	}
	
}
