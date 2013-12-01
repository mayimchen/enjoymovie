package com.banking.xc.database;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.banking.xc.database.table.CityTable;
import com.banking.xc.database.table.UserTable;
import com.banking.xc.database.table.UserTagsTable;
import com.banking.xc.utils.Log;


/**
 * 数据库帮助类
 * @author zhangyinhang
 *
 */
public class DatabaseHelper {

	private SQLiteDatabase db = null;
	private static int versionCode = 1;
	private Cursor c;

	private static final String DB_NAME = "banking.db";

	private Context mContext;
	private static OpenHelper mOpenHelper;
	
	private static final String TAG="DatabaseHelper";

	public static synchronized SQLiteDatabase getDatabase() {
		if (null == mOpenHelper) {
			try {
				versionCode = MyApplication.getInstance().getPackageManager().getPackageInfo(MyApplication.getInstance().getPackageName(), 0).versionCode;
			} catch (Exception e) {
				e.printStackTrace();
			}
			mOpenHelper = new OpenHelper(MyApplication.getInstance(), DB_NAME, null, versionCode);
		}
		try {
			SQLiteDatabase writableDatabase = mOpenHelper.getWritableDatabase();
			if (Log.D) {
				Log.d("Temp", "writableDatabase 1 -->> " + writableDatabase);
			}
			return writableDatabase;
		} catch (Exception e) {
			e.printStackTrace();
			MyApplication.getInstance().deleteDatabase(DB_NAME);
			SQLiteDatabase writableDatabase = mOpenHelper.getWritableDatabase();
			if (Log.D) {
				Log.d("Temp", "writableDatabase 2 -->> " + writableDatabase);
			}
			return writableDatabase;
		}
	}

	public static void closeDatabase() {
		// mOpenHelper.close();
	}

	public DatabaseHelper(Context context) {
		mContext = context;
	}


}

class OpenHelper extends SQLiteOpenHelper {

	public OpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		UserTagsTable.create(db);
		CityTable.create(db);
		UserTable.create(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (oldVersion < newVersion) {
			if (Log.I) {
				Log.i("onUpgrade", "oldVersion:" + oldVersion + "newVersion:" + newVersion);
			}
			UserTagsTable.upgrade(db);
			CityTable.upgrade(db);
			UserTable.upgrade(db);
			onCreate(db);
		}
	}

}
