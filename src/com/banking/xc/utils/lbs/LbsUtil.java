package com.banking.xc.utils.lbs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.text.TextUtils;

import com.banking.xc.utils.CommonUtil;
import com.banking.xc.utils.JSONObjectProxy;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.lbs.LocationManagerInterface.UpdateLocationListener;
import com.banking.xc.utils.staticinfo.VacationHotCityUtil;

public class LbsUtil {

	public static final String TAG = "LbsUtil";
	private static MyLocationListener mLocationListener;
	private static LocationManagerInterface mLocationManager;// 定位管理器，根据服务器下发配置，得到不同的实现
	public static final int GPS_TIMEOUT_TIME = 60 * 1000;// GPS定位超时时间（毫秒）
	public static final int RELOCATION_INTERVAL_TIME = 30 * 60 * 1000;// 重复定位间隔时间（毫秒）
	private static LbsUtil mLbsUtil;
	private static Context mContext;
	private static String mName;
	private static String DEFAULT_CITY = "北京";

	public static double lati, longi; // 经纬度
	public static int provinceId = 1; // 省id
	public static int cityId; // 市id
	public static int districtId; // 区id
	static VacationHotCityUtil util;
	/**
	 * 对外方法，获取所在城市 逻辑：如果用户自己选择了某个城市，那么所有出发城市都是这个。如果没有选择，那么定位。定位失败，默认北京
	 * 
	 * @return
	 */
	public static String getStartCityName() {
		String gotName;
		gotName = CommonUtil.getSharedPreferences().getString(CommonUtil.START_CITY, "");
		
		if(TextUtils.isEmpty(gotName)){
			gotName = mName;
			if(Log.D){
				Log.d(TAG, "getStartCityName()2"+mName);
			}
			if(util==null){
				util = new VacationHotCityUtil();
				util.initializeVacationHotCity();
			}
			
			String code = util.getCityCodeByCityName(gotName);
			if(Log.D){
				Log.d(TAG, "code"+code);
			}
			if(!TextUtils.isEmpty(gotName)&&!TextUtils.isEmpty(code)){
				changeStartCity(gotName);
			}else{
				gotName = DEFAULT_CITY;
				changeStartCity(gotName);
			}
		}
		if(Log.D){
			Log.d(TAG, "getStartCityName()"+gotName);
		}
		
		return gotName;
	}

	/**
	 * 更改出发城市的名字
	 * 
	 * @param cityName
	 */
	public static void changeStartCity(String cityName) {
		CommonUtil.getSharedPreferences().edit().putString(CommonUtil.START_CITY, cityName).commit();
	}

	public static String getDestCityName() {
		return CommonUtil.getSharedPreferences().getString(CommonUtil.DEST_CITY, "全部");
	}

	public static void changeDestCity(String cityName) {
		CommonUtil.getSharedPreferences().edit().putString(CommonUtil.DEST_CITY, cityName).commit();
	}

	private LbsUtil(Context context) {
		mContext = context;
	}

	// 取消定位
	public void removeUpdateLocation() {
		if (mLocationManager != null) {
			mLocationManager.stopLocation();
		}
	}

	public static LbsUtil getInstance() {
		if (Log.D) {
			Log.d(TAG, " getInstance -->>  ");
		}

		if (null == mLbsUtil) {
			mLbsUtil = new LbsUtil(MyApplication.getInstance().getApplicationContext());
		}
		if (Log.D) {
			Log.d(TAG, " LocManager -->> 使用gps定位 ");
		}
		mLocationManager = MyLocationManager.getInstance();
		// }
		return mLbsUtil;
	}

	// 定时更新位置信息
	public void startLocationService() {

		if (Log.D) {
			Log.d(TAG, "startLocationService -->> ");
		}

		new Thread(new Runnable() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					if (Log.D) {
						Log.d(TAG, "startLocationService -->> location count: " + (++count));
					}
					queryInfoByLocation(); //
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							// 查询n分钟后 无论成功与否都关闭gps
							if (Log.D) {
								Log.d(TAG, "startLocationService -->> gps auto off  ");
							}
							LbsUtil.getInstance().removeUpdateLocation();
						}
					}, GPS_TIMEOUT_TIME);

					try {
						if (Log.D) {
							Log.d(TAG, "startLocationService -->> location count: " + count + "等待下次定位 sleep");
						}
						Thread.sleep(RELOCATION_INTERVAL_TIME);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

	}

	/**
	 * 这里进行更新
	 * 
	 * @param map
	 */
	public static void updateLocation(Map<String, Double> map) {
		double result;
		lati = map.get("lati");
		longi = map.get("longi");
		CommonUtil.getSharedPreferences().edit().putString(CommonUtil.LATI, String.valueOf(lati)).commit();
		CommonUtil.getSharedPreferences().edit().putString(CommonUtil.LONGI, String.valueOf(longi)).commit();
		getCityNameByGoogleApi();
		/*
		String addressStr = "no address \n";
		Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
		try {
			List<Address> addresses = geocoder.getFromLocation(lati, longi, 1);
			StringBuilder sb = new StringBuilder();
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
					sb.append(address.getAddressLine(i)).append("\n");
				}
				sb.append(address.getLocality()).append("\n");
				sb.append(address.getLocality()).append("\n");
				sb.append(address.getCountryName());
				addressStr = sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (Log.D) {
			Log.d(TAG, "addressStr" + addressStr);
			Log.d(TAG, "lati" + lati);
			Log.d(TAG, "longi" + longi);
		}*/
		// result = calculateDistance(map.get("lati"), map.get("longi"), lati, longi);
		/*if (result > DISTANCE) {
			lati = map.get("lati");
			longi = map.get("longi");
		}*/
	}

	/**
	 * 该网络请求未加入框架
	 */
	public static void getCityNameByGoogleApi() {
		String sTmp = "http://maps.google.com/maps/api/geocode/json?latlng=" + lati + "," + longi + "&language=zh-CN&sensor=true";
		if (Log.D) {
			Log.d(TAG, "sTmp" + sTmp);
		}
		try {
			URL url = new URL(sTmp);
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			connect.setDoInput(true);
			BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			String line = null;
			StringBuffer content = new StringBuffer();
			while ((line = in.readLine()) != null) {
				if (Log.D) {
					Log.d(TAG, "readLine()" + line);
				}
				content.append(line);
			}
			in.close();
			if (Log.D) {
				Log.d(TAG, "content" + content);
			}
			JSONObject jsonObject = new JSONObject(content.toString());
			if (Log.D) {
				Log.d(TAG, "ok1");
			}
			JSONArray array1 = jsonObject.getJSONArray("results");
			if (Log.D) {
				Log.d(TAG, "ok2");
			}

			JSONObject array2 = array1.getJSONObject(0);
			if (Log.D) {
				Log.d(TAG, "ok3");
			}
			JSONArray array3 = array2.getJSONArray("address_components");
			JSONObject object = array3.getJSONObject(3);
			if (Log.D) {
				Log.d(TAG, "ok4");
			}
			String name = object.getString("short_name");
			if (Log.D) {
				Log.d(TAG, "name" + name);
			}

			// String cityName = (String) jsonObject.getJSONArray("results").getJSONArray(0).getJSONObject(3).get("short_name");
			mName = name;
			// 判断是否合法
			if(util==null){
				util = new VacationHotCityUtil();
				util.initializeVacationHotCity();
			}
			String code = util.getCityCodeByCityName(mName);
			if(Log.D){
				Log.d(TAG, "code"+code);
			}
			if(!TextUtils.isEmpty(mName)&&!TextUtils.isEmpty(code)){
				CommonUtil.getSharedPreferences().edit().putString(CommonUtil.START_CITY, name).commit();
			}
			/*if (Log.D) {
				Log.d(TAG, "cityName" + cityName);
			}*/
		} catch (IOException ex) {
			if (Log.D) {
				Log.d(TAG, "ex" + ex.getStackTrace());
			}
			ex.printStackTrace();
		} catch (Exception e) {

			if (Log.D) {
				Log.d(TAG, "e" + e.getStackTrace());
			}

		}

	}

	// 省市区信息保存在本地
	public void queryInfoByLocation() {
		if (Log.D) {
			Log.d(TAG, "queryInfoByLocation -->> ");
		}

		mLocationManager.setUpdateLocationListener(new UpdateLocationListener() {

			@Override
			public void onFinish(Map<String, Double> map) {

				if (!map.isEmpty()) { // 查询到位置信息
					if (Log.D) {
						Log.d(TAG, " queryInfoByLocation -->> map:" + map);
					}
					updateLocation(map);
				} else {
					// 出错处理
					// mLocationListener.onFinish(null, MyApplication.getInstance().getApplicationContext().getString(R.string.gps_location_fail));
				}
			}
		});
		mLocationManager.requestLocation();
	}

	public interface MyLocationListener {
		void onFinish(String msg);
	}

}
