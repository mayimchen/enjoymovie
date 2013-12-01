package com.banking.xc.database.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.banking.xc.database.DatabaseHelper;
import com.banking.xc.entity.CityInfoEntity;
import com.banking.xc.utils.Log;

/**
 * 手机内存不可能这么小，所以，把City对应还是放到内存中，不要每次都到数据库中去取
 * 
 * @author zhangyinhang
 * 
 */
public class CityTable {
	private static final String TAG = "CityTable";
	public static final String CITY_TABLE = "flight_city_table";
	public static final String COLUMN_CITYCODE = "cityCode"; //三字码，有些城市就是cityID
	public static final String COLUMN_CITYNAME = "cityName";
	public static final String COLUMN_CITYID = "cityID";//城市ID
	
	final static int max = 3786;
	static int nowSize = 0;
	
	public static synchronized boolean  isNeedSaveCity(){
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			if(Log.D){
				Log.d(TAG,"isNeedSaveCity()Thread"+Thread.currentThread());
			}
			db = DatabaseHelper.getDatabase();
			String[] queryColumn = { COLUMN_CITYCODE };
			cursor = db.query(CITY_TABLE, queryColumn, null, null, null, null, null);
			if(Log.D){
				Log.d(TAG, "现在数据库中城市个数 ：cursor.getCount()"+cursor.getCount());
			}
			if(cursor==null){
				return false;//异常情况
			}else{
				nowSize = cursor.getCount();
				if(nowSize<max){
					return true;
				}else{
					return false;
				}
			}
			//一共有3786结果,3225.可以拿2000做边界.不，直接判断
			/*if (cursor == null || cursor.getCount() == 0) {
				return true;
			}*/
		}catch(Exception e){
			if(Log.D){
				Log.d(TAG,"isNeedSaveCity() exce"+e);
			}
		}
		return false;
	}
	/**
	 * 插入多条数据
	 * 
	 * @param name
	 */
	public static  void insertManyCity(ArrayList<CityInfoEntity> cities) {
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			if(Log.D){
				Log.d(TAG,"Thread"+Thread.currentThread().getName());
			}
			db = DatabaseHelper.getDatabase();
			if(Log.D){
				Log.d(TAG, "saveManyCity()从第"+nowSize+"个城市开始写入数据库！");
			}
				//for (CityInfoEntity entity : cities) {
				for(int i =nowSize;i<max;i++){
					CityInfoEntity entity = cities.get(i);
					if(Log.D){
						Log.d(TAG, "save entity"+entity.getCityName()+entity.getCityCode()+" "+entity.getCityId());
					}
					ContentValues values = new ContentValues();
					values.put(COLUMN_CITYCODE, entity.getCityCode());
					values.put(COLUMN_CITYNAME, entity.getCityName());
					values.put(COLUMN_CITYID, entity.getCityId());
					db.insert(CITY_TABLE, null, values);
				}

		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
	}

	public static String getCityIdByName(String cityName){
		String returnid = "";
		SQLiteDatabase db = null;
		Cursor cursor = null;
		if (Log.D) {
			Log.e(TAG,"getCityIdByName-->"+cityName);
		}
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = { COLUMN_CITYID};
			String whereArgs = COLUMN_CITYNAME+"=?";
			String[] whereValues = {cityName};
			cursor = db.query(CITY_TABLE, columns, whereArgs, whereValues, null, null, null);
			if (Log.D) {
				Log.e(TAG,"getCityIdByName--> cursor"+cursor.getCount());
			}
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				String id = cursor.getString(cursor.getColumnIndex(COLUMN_CITYID));
				returnid = id;
					/*entity.setCityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));
					entity.setCityName(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));*/
			}
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.e(TAG,"getCities() Exception"+e);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
		if (Log.D) {
			Log.e(TAG,"getCityIdByName--> returnCode"+returnid);
		}
		return returnid;
	}
	
	public static  String getCityNameById(String cityId){
		String returnName = "";
		SQLiteDatabase db = null;
		Cursor cursor = null;
		if (Log.D) {
			Log.e(TAG,"getCityNameById-->"+cityId);
		}
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = {COLUMN_CITYNAME };
			String whereArgs = COLUMN_CITYID+"=?";
			String[] whereValues = {cityId};
			cursor = db.query(CITY_TABLE, columns, whereArgs, whereValues, null, null, null);
			if (Log.D) {
				Log.e(TAG,"getCityNameById--> cursor"+cursor.getCount());
			}
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				String name = cursor.getString(cursor.getColumnIndex(COLUMN_CITYNAME));
				returnName = name;
			}
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.e(TAG,"getCities() Exception"+e);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
		if (Log.D) {
			Log.e(TAG,"getCityNameById--> returnName"+returnName);
		}
		return returnName;
	}
	
	/**
	 * 根据城市名字得到三字码
	 * @param cityName
	 * @return
	 */
	public static String getCityCodeByName(String cityName){
		String returnCode = "";
		SQLiteDatabase db = null;
		Cursor cursor = null;
		if (Log.D) {
			Log.e(TAG,"getCityCodeByName-->"+cityName);
		}
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = { COLUMN_CITYCODE};
			String whereArgs = COLUMN_CITYNAME+"=?";
			String[] whereValues = {cityName};
			cursor = db.query(CITY_TABLE, columns, whereArgs, whereValues, null, null, null);
			if (Log.D) {
				Log.e(TAG,"getCityCodeByName--> cursor"+cursor.getCount());
			}
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				String code = cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE));
				returnCode = code;
					/*entity.setCityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));
					entity.setCityName(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));*/
			}
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.e(TAG,"getCities() Exception"+e);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
		if (Log.D) {
			Log.e(TAG,"getCityCodeByName--> returnCode"+returnCode);
		}
		return returnCode;
	}
	
	
	/**
	 * 根据城市名字得到三字码
	 * @param cityName
	 * @return
	 */
	public static String getCityNameByCode(String cityName){
		String returnName = "";
		SQLiteDatabase db = null;
		Cursor cursor = null;
		if (Log.D) {
			Log.e(TAG,"getCityNameByCode-->"+cityName);
		}
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = { COLUMN_CITYCODE};
			String whereArgs = COLUMN_CITYNAME+"=?";
			String[] whereValues = {cityName};
			cursor = db.query(CITY_TABLE, columns, whereArgs, whereValues, null, null, null);
			if (Log.D) {
				Log.e(TAG,"getCityNameByCode--> cursor"+cursor.getCount());
			}
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				String code = cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE));
				returnName = code;
					/*entity.setCityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));
					entity.setCityName(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));*/
			}
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.e(TAG,"getCities() Exception"+e);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
		if (Log.D) {
			Log.e(TAG,"getCityNameByCode--> returnCode"+returnName);
		}
		return returnName;
	}
	
	/**
	 * 获取所有数据
	 * 
	 * @param name
	 */
	public static synchronized ArrayList<CityInfoEntity> getCities() {
		final ArrayList<CityInfoEntity> citys = new ArrayList<CityInfoEntity>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = { COLUMN_CITYCODE, COLUMN_CITYNAME };
			cursor = db.query(CITY_TABLE, columns, null, null, null, null, null);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					final CityInfoEntity entity = new CityInfoEntity();
					entity.setCityCode(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));
					entity.setCityName(cursor.getString(cursor.getColumnIndex(COLUMN_CITYCODE)));
					citys.add(entity);
				}
			}
		} catch (Exception e) {
			if (Log.D) {
				e.printStackTrace();
				Log.e(TAG,"getCities() Exception"+e);
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
		if (Log.D) {
			Log.e(TAG,"getCities()-->citys.size()"+citys.size());
		}
		return citys;
	}

	/**
	 * 创建表
	 */
	public static void create(SQLiteDatabase db) {
		final String CREATE_TABLE_CITY = "CREATE TABLE "//
				+ CITY_TABLE//
				+ "('id' INTEGER PRIMARY KEY  NOT NULL ,"//
				+ COLUMN_CITYCODE + " TEXT," + COLUMN_CITYNAME + " TEXT," + COLUMN_CITYID + " TEXT"+")";
		db.execSQL(CREATE_TABLE_CITY);
	}

	/*
	 * 升级
	 */
	public static void upgrade(SQLiteDatabase db) {
		db.execSQL("drop table if exists " + CITY_TABLE);
	}

}
