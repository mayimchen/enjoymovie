package com.banking.xc.database.table;

import java.util.ArrayList;

import com.banking.xc.database.DatabaseHelper;
import com.banking.xc.utils.Log;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 需要存储用户的标签
 * @author zhangyinhang
 *
 */
public class UserTagsTable {
	public static final String TAGS_TABLE = "tags_table";
	public static final String COLUMN_TAGSTR= "tagStr";
	//public static final String COLUMN_TAGID = "tagId"; //先不加入tagId
	
	
	/**
	 * 插入一条数据
	 * @param name
	 */
	public static synchronized void insertTag(String name){
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = {COLUMN_TAGSTR};
			String whereArgs = COLUMN_TAGSTR+"=?";
			String[] whereValues = {name};
			cursor =  db.query(TAGS_TABLE, columns, whereArgs, whereValues, null, null, null);
			if(cursor == null || cursor.getCount() <= 0){
				ContentValues values = new ContentValues();
				values.put(COLUMN_TAGSTR, name);
				db.insert(TAGS_TABLE, null, values);
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
	}
	/**
	 * 删除一条数据
	 * @param name
	 */
	public static synchronized void delTag(String name){
		SQLiteDatabase db = null;
		try {
			db = DatabaseHelper.getDatabase();
			String whereArgs = COLUMN_TAGSTR+"=?";
			String[] values = {name};
			db.delete(TAGS_TABLE, whereArgs, values);
		} catch (Exception e) {
			if(Log.D){
				e.printStackTrace();
			}
		}finally{
			DatabaseHelper.closeDatabase();
		}
	}
	/**
	 * 获取所有数据
	 * @param name
	 */
	public static synchronized ArrayList<String> getTags(){
		ArrayList<String> names = new ArrayList<String>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		try {
			db = DatabaseHelper.getDatabase();
			String[] columns = {COLUMN_TAGSTR};
			cursor = db.query(TAGS_TABLE, columns, null, null, null, null, null);
			if(cursor != null && cursor.getCount() > 0){
				cursor.moveToFirst();
				for (int i = 0; i < cursor.getCount(); i++) {
					cursor.moveToPosition(i);
					names.add(cursor.getString(cursor.getColumnIndex(COLUMN_TAGSTR)));
				}
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
		
		return names;
	}
	
	/**
	 * 创建表
	 */
	public static void create(SQLiteDatabase db){
		final String CREATE_TABLE_USERNAMES = "CREATE TABLE "//
				+ TAGS_TABLE//
				+ "('id' INTEGER PRIMARY KEY  NOT NULL ,"//
				+ COLUMN_TAGSTR + " TEXT)";
			db.execSQL(CREATE_TABLE_USERNAMES);
	}
	
	/*
	 * 升级
	 */
	public static void upgrade(SQLiteDatabase db){
		db.execSQL("drop table if exists " +TAGS_TABLE);
	}

	
}
