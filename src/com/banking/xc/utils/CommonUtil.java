package com.banking.xc.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.banking.xc.constant.NetworkConstant;
import com.banking.xc.entity.recommend.VacationFilter;

public final class CommonUtil {

	private static final String TAG = "CommonUtil";
	public static SharedPreferences sharedPreferences;
	public final static String SHARE_PREFERENCE = "bankingAndroid";
	
	public final static String NO_SHOW_DEL_TAG = "noShowDelTag";
	public final static String LATI = "lati"; //经度
	public final static String LONGI = "longi"; //维度
	public final static String START_CITY = "start_city";//定位的城市,出发和团购城市
	public final static String DEST_CITY = "dest_city";//目标城市，作为搜索关键字.如果不为空就用它作为搜索关键字
	public final static String START_DATE = "start_date";//出发日期
	
	public final static String FILTER_TIP = "filter_tip";
	public final static String GUIDE = "guide";
	
	
	
	public static LayoutInflater getLayoutInflater() {
		return LayoutInflater.from(MyApplication.getInstance());
	}

	public static boolean isShowFilterTip(){
		return getSharedPreferences().getBoolean(FILTER_TIP, true);
	}
	
	public static void setIsShowFilterTip(Boolean b){
		getSharedPreferences().edit().putBoolean(FILTER_TIP, b).commit();
	}
	
	public static void setIsGuide(Boolean b){
		getSharedPreferences().edit().putBoolean(GUIDE, b).commit();
	}
	public static Boolean isGuide(){
		return getSharedPreferences().getBoolean(GUIDE, false);
	}
	
	public static int getVacationMan(){
		return getSharedPreferences().getInt(VacationFilter.VACATION_MAN, 1);
	}
	public static void setVacationMan(int man){
		getSharedPreferences().edit().putInt(VacationFilter.VACATION_MAN, man).commit();
	}
	public static int getVacationWoman(){
		return getSharedPreferences().getInt(VacationFilter.VACATION_WOMAN, 0);
	}
	public static void setVacationWoman(int woman){
		getSharedPreferences().edit().putInt(VacationFilter.VACATION_WOMAN, woman).commit();
	}
	public static int getVacationCouple(){
		return getSharedPreferences().getInt(VacationFilter.VACATION_COUPLE, 0);
	}
	public static void setVacationCouple(int couple){
		getSharedPreferences().edit().putInt(VacationFilter.VACATION_COUPLE, couple).commit();
	}
	
	public static int getVacationKindTag(){
		return getSharedPreferences().getInt(VacationFilter.VACATION_KIND, 1);
	}
	public static void setVacationKindTag(int tag){
		getSharedPreferences().edit().putInt(VacationFilter.VACATION_KIND, tag).commit();
	}
	
	/**
	 * 兴趣串0-2-1-4
	 * @return
	 */
	public static String getVacationInterest(){
		return getSharedPreferences().getString(VacationFilter.VACATION_INTERE, "");
	}
	public static void setVacationInterest(String interest){
		getSharedPreferences().edit().putString(VacationFilter.VACATION_INTERE, interest).commit();
	}
	
	/**
	 * 检测网络连接是否可用
	 * 
	 * @param ctx
	 * @return true 可用; false 不可用
	 */
	public static boolean CheckNetWork() {
		// 连接管理器
		ConnectivityManager cm = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		}
		NetworkInfo[] netinfo = cm.getAllNetworkInfo();
		if (netinfo == null) {
			return false;
		}
		for (int i = 0; i < netinfo.length; i++) {
			if (netinfo[i].isConnected()) {
				return true;
			}
		}
		return false;
	}




	
	/**
	 * 检测当前网络类型
	 * 
	 * @return 1:cmnet,2:cmwap
	 */
	public static int checkNetWorkType() {
		String proxyHost = android.net.Proxy.getDefaultHost();
		if (proxyHost != null) {
			return NetworkConstant.NetworkState.CMWAP;
		} else {
			return NetworkConstant.NetworkState.CMNET;
		}
	}

	public String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			if (Log.V) {
				Log.v("WifiPreference IpAddress", ex.toString());
			}
		}
		return null;
	}

	/**
	 * 获取主配置文件
	 */
	public static SharedPreferences getSharedPreferences() {
		if (null == sharedPreferences) {
			if (Log.D) {
				Log.d("CommonUtil", " -->> sharedPreferences:" + sharedPreferences);
			}
			sharedPreferences = MyApplication.getInstance()//
					.getSharedPreferences(CommonUtil.SHARE_PREFERENCE, Context.MODE_PRIVATE);
		}
		if (Log.D) {
			Log.d("CommonUtil", " -->> size:" + sharedPreferences.getAll().size());
		}

		return sharedPreferences;
	}
	/**
	 * 取得DeviceId
	 */
	public static String getDeviceId() {
		TelephonyManager tm = (TelephonyManager) MyApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
	/**
	 * 检测SDcard是否存在
	 * 
	 * @return true:存在、false:不存在
	 */
	public static boolean checkSDcard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}




    public static void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                    // pre-condition
                    return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                    View listItem = listAdapter.getView(i, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
    } 
	

}
