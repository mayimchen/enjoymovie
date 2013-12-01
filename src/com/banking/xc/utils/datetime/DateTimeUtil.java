package com.banking.xc.utils.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.banking.xc.utils.CommonUtil;
import com.banking.xc.utils.Log;

import android.text.TextUtils;
import android.text.format.DateFormat;

public class DateTimeUtil {

	public static final String TAG = "DateTimeUtil";

	
	public static void setWantStartDate(String date){
		CommonUtil.getSharedPreferences().edit().putString(CommonUtil.START_DATE, date).commit();
	}
	/**
	 * 取得默认的出发日期
	 */
	public static String getWantStartDate(){
		String date = CommonUtil.getSharedPreferences().getString(CommonUtil.START_DATE, "");
		if(TextUtils.isEmpty(date)||outOfDate(date)){
			//如果这个日期比今天要早，那么算作过期的
			String defaultDate = getNDaysAfterToday(3);//TODO 急性子
			return defaultDate;
		}
		return date;
		
		
		
	}
	
	
	/**
	 * 返回当前
	 */
	public static String getNowTime() {
		Date date = new Date();
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		final String value = dateFormat.format(date);
		if (Log.D) {
			Log.d(TAG, "getNowTime()-->" + value);
		}
		return value;
		//return date;
	}

	public static String simpleDateToXcForm(String date) {
		StringBuffer sb = new StringBuffer(date);
		sb.append("T00:00:00.000+08:00");
		return sb.toString();
	}

	// public static String
	public static String getTodayDate() {
		final Date date = new Date();
		final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd中mm是分钟
		final String value = dateFormat.format(date);
		if (Log.D) {
			Log.d(TAG, "getNowTime()-->" + value);
		}
		return value;
	}
	public static String getNDaysAfterTheDay(int n,String theDay){
		if(n==0){
			return theDay;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(theDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, n);
			String value = dateFormat.format(calendar.getTime());
			return value;
		} else {
			return null;
		}
	}
	public static String getNDaysAfterToday(int n){
		if(n<1){
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String now = getTodayDate();
		Date date = null;
		try {
			date = dateFormat.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, n);
			String value = dateFormat.format(calendar.getTime());
			return value;
		} else {
			return null;
		}
	}
	public static String getTomorrowDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String now = getTodayDate();
		Date date = null;
		try {
			date = dateFormat.parse(now);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String value = dateFormat.format(calendar.getTime());
			return value;
		} else {
			return null;
		}
	}

	/**
	 * 根据当前时间，用户特征，返回适合的默认到店时间. 默认是当日6点
	 * 
	 * @param checkInDate yyyy-MM-dd
	 * @return yyyy-MM-dd hh-mm-ss
	 */
	public static String getDefaultArrivalTime(String checkInDate) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = dateFormat.parse(checkInDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 6);
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String value = dateFormat2.format(calendar.getTime());
		return value;
	}

	public static String getDefaultLateArrivalTime(String arrivalTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		Date date = null;
		try {
			date = dateFormat.parse(arrivalTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		String value = dateFormat.format(calendar.getTime());
		return value;
	}

	public static boolean outOfDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		final Date nowDate = new Date();
		try {
			date1 = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date1.after(nowDate))
		{
			return true;
		}else{
			return false;
		}
		
	}

}
