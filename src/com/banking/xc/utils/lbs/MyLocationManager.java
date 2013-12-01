package com.banking.xc.utils.lbs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.Log;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MyLocationManager implements LocationManagerInterface{
	private static final String TAG = "MyLocationManager";
	private static final String CONTEXT_SERVICE = Context.LOCATION_SERVICE;

	private LocationManager locationManager;
	private Location mLocation = null;
	private UpdateLocationListener globlListener;
	private static MyLocationManager myLocationManager;

	/**
	 * 构造器
	 * 
	 * @param context
	 */
	private MyLocationManager(Context context) {
		if (Log.D) {
			Log.d(TAG, " myLocationManager -->> 定位管理器");
		}
		if (context == null) {
			throw new IllegalArgumentException("Context can not be null ! ");
		}
		locationManager = (LocationManager) context.getSystemService(CONTEXT_SERVICE);
	}

	/**
	 * 单例模式
	 * 
	 * @return
	 */
	public static MyLocationManager getInstance() {
		if (myLocationManager == null) {
			myLocationManager = new MyLocationManager(MyApplication.getInstance().getApplicationContext());
		}
		return myLocationManager;
	}

	/**
	 * 请求获取位置信息
	 */
	@Override
	public void requestLocation() {
		final List<String> providers = locationManager.getAllProviders();
		if (Log.D) {
			Log.d(TAG, " requestLocation -->> 开始请求定位服务");
		}
		if (providers.contains(LocationManager.GPS_PROVIDER) //
				&& locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

			if (Log.D) {
				Log.d(TAG, " requestLocation -->> GPS_PROVIDER");
			}
			setLocationListener(LocationManager.GPS_PROVIDER);
		}

		if (providers.contains(LocationManager.NETWORK_PROVIDER) //
				&& locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			if (Log.D) {
				Log.d(TAG, " requestLocation -->> NETWORK_PROVIDER");
			}
			setLocationListener(LocationManager.NETWORK_PROVIDER);
		}
	}

	/**
	 * 设置监听器
	 * 
	 * @param provider
	 */
	private void setLocationListener(final String provider) {
		if (Log.D) {
			Log.d(TAG, " setLocationListener -->> provider:" + provider);
		}

		if (null != MyApplication.getInstance().getMainActivity() && null != MyApplication.getInstance().getMainActivity().getHandler()) {
			MyApplication.getInstance().getMainActivity().getHandler().post(new Runnable() {

				@Override
				public void run() {
					if (Log.D) {
						Log.d(TAG, " setLocationListener -->> requestLocationUpdates 请求定位间隔" + LOCATION_TIME_SPAN);
					}
					locationManager.requestLocationUpdates(provider, LOCATION_TIME_SPAN, 0, locationListener);
				}
			});
		}
	}

	/**
	 * 自定义监听器
	 */
	private LocationListener locationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			if (Log.D) {
				Log.d(TAG, " locationListener -->> onStatusChanged");
			}
			if (mLocation != null) {
				updateWithNewLocation(mLocation);
			}
		}

		@Override
		public void onProviderEnabled(String provider) {
			if (Log.D) {
				Log.d(TAG, " locationListener -->> onProviderEnabled");
			}
			if (mLocation != null) {
				updateWithNewLocation(mLocation);
			}
		}

		@Override
		public void onProviderDisabled(String provider) {

		}

		@Override
		public void onLocationChanged(Location location) {
			if (Log.D) {
				Log.d(TAG, " locationListener -->> onLocationChanged");
			}
			updateWithNewLocation(location);
		}
	};

	// 更新最新位置信息，回传给LbsUtil处理
	private synchronized void updateWithNewLocation(Location location) {
		if (location != null) {
			stopLocation();
			mLocation = location;
			if (Log.D) {
				Log.d(TAG, " updateWithNewLocation -->> 得到定位位置：" + mLocation.getLatitude() + "|" + mLocation.getLongitude());
			}
			Map<String, Double> locMap = new HashMap<String, Double>();
			
			locMap.put("lati", location.getLatitude());
			locMap.put("longi", location.getLongitude());

			if (globlListener != null) {
				if (Log.D) {
					Log.d(TAG, " updateWithNewLocation -->> 成功返回定位结果!");
				}
				globlListener.onFinish(locMap);
				globlListener = null;// 已回调，设计为空防止重复调用
			}
		}
	}

	/**
	 * 取得位置信息
	 * 
	 * @return
	 */
	@Override
	public void setUpdateLocationListener(UpdateLocationListener listener) {
		if (Log.D) {
			Log.d(TAG, " setUpdateLocationListener -->> listener:" + listener);
		}
		globlListener = listener;

	}

	// 取消监听
	@Override
	public void stopLocation() {
		if (Log.D) {
			Log.d(TAG, " stopLocation -->> ");
		}
		locationManager.removeUpdates(locationListener);
	}

	@Override
	public boolean isOpenGps() {
		try {
			return locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER) && //
					locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception e) {
			return false;
		}
	}

}
