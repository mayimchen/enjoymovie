package com.banking.xc.utils.lbs;

import java.util.Map;

public interface LocationManagerInterface {
	/**
	 * 一次定位请求内，定位重试间隔时间
	 */
	int LOCATION_TIME_SPAN = 5000;

	/**
	 * 定位监听
	 * 
	 */
	public interface UpdateLocationListener {
		void onFinish(Map<String, Double> map);
	}

	/**
	 * 设置 定位监听器
	 * 
	 * @param listener
	 */
	void setUpdateLocationListener(UpdateLocationListener listener);

	/**
	 * gps设备是否是打开状态了
	 * 
	 * @return
	 */
	boolean isOpenGps();

	/**
	 * 开始请求定位，定位成功，将执行UpdateLocationListener回调
	 */
	void requestLocation();

	/**
	 * 停止 定位
	 */
	void stopLocation();
}
