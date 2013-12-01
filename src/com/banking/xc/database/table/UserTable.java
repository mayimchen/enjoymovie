package com.banking.xc.database.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.banking.xc.database.DatabaseHelper;
import com.banking.xc.entity.User;
import com.banking.xc.utils.Log;

/**
 * 需要存储用户的名字和UID
 * 暂时客户端仅支持一个user
 * @author zhangyinhang
 *
 */
public class UserTable {
	public static final String USER_TABLE = "user_table";
	public static final String COLUMN_NAME= "randomUserName";
	public static final String COLUMN_UID= "userUID";
	//没必要去记载userName，因为它时random生成的
	
	
	/**
	 * 插入一条用户数据，如果存在一跳，那么直接删掉原来的
	 * @param name
	 */
	public static synchronized void insertUser(String name,String uid){
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = {COLUMN_NAME};
			//String whereArgs = COLUMN_NAME+"=?";
			cursor =  db.query(USER_TABLE, columns, null, null, null, null, null);
			if(cursor == null || cursor.getCount() <= 0){
				System.out.println("database null cursor");
			}else{
				db.delete(USER_TABLE, null, null);
			}
			ContentValues values = new ContentValues();
			values.put(COLUMN_NAME, name);
			values.put(COLUMN_UID, uid);
			db.insert(USER_TABLE, null, values);
			System.out.println("database insert");
		} catch (Exception e) {
			if(Log.D){
				e.printStackTrace();
			}
		}finally{
			if(cursor != null){
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
	}
	
	public static synchronized User getUser(){
		SQLiteDatabase db = null;
		Cursor cursor = null;
		User user = null;
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = {COLUMN_NAME,COLUMN_UID};
			cursor =  db.query(USER_TABLE, columns, null, null, null, null, null);
			if(cursor == null || cursor.getCount() <= 0){
				System.out.println("database getUser null");
			}else{
				cursor.moveToFirst();
				cursor.moveToPosition(0);
				user = new User();
				user.setRandomName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
				user.setUniqueID(cursor.getString(cursor.getColumnIndex(COLUMN_UID)));
			}
		} catch (Exception e) {
			if(Log.D){
				e.printStackTrace();
			}
		}finally{
			if(cursor != null){
				cursor.close();
			}
			DatabaseHelper.closeDatabase();
		}
		return user;
	}
	/**
	 * 创建表
	 */
	public static void create(SQLiteDatabase db){
		final String CREATE_TABLE_USERNAMES = "CREATE TABLE "//
				+ USER_TABLE//
				+ "('id' INTEGER PRIMARY KEY  NOT NULL ,"//
				+ COLUMN_NAME + " TEXT," 
				+ COLUMN_UID +" TEXT)";
			db.execSQL(CREATE_TABLE_USERNAMES);
	}
	
	/*
	 * 升级
	 */
	public static void upgrade(SQLiteDatabase db){
		db.execSQL("drop table if exists " +USER_TABLE);
	}

	
}
