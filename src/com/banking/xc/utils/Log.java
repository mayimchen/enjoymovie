package com.banking.xc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;

import android.os.Environment;
import android.os.Process;

import com.banking.xc.config.Configuration;

public class Log {

	private static boolean printLog = Boolean.parseBoolean(Configuration.getProperty(Configuration.PRINT_LOG, "false"));

	public static boolean D = printLog ? Boolean.parseBoolean(Configuration.getProperty(Configuration.DEBUG_LOG, "false")) : false;
	public static boolean V = printLog ? Boolean.parseBoolean(Configuration.getProperty(Configuration.VIEW_LOG, "false")) : false;
	public static boolean I = printLog ? Boolean.parseBoolean(Configuration.getProperty(Configuration.INFO_LOG, "false")) : false;
	public static boolean W = printLog ? Boolean.parseBoolean(Configuration.getProperty(Configuration.WARN_LOG, "false")) : false;
	public static boolean E = printLog ? Boolean.parseBoolean(Configuration.getProperty(Configuration.ERROR_LOG, "false")) : false;
	private static final int LOG_CAT_DATA_LENGTH = 1500;//经测试，logcat中打印日志一条最多为3300+个字符,3000->2000
	
	// 日志存储目录
	public static final String LOG_FILE = Environment.getExternalStorageDirectory() + "/banking_log.txt";
	// 是否是测试模式

	private static RandomAccessFile accessFile;

	public static void d(String tag, String msg) {
		if (!printLog) {
			return;
		}
		android.util.Log.d(tag, "tid:" + Process.myTid() + "; -->" + msg);
	}

	public static void d(String tag, String msg, Throwable tr) {
		if (!printLog) {
			return;
		}
		android.util.Log.d(tag, msg, tr);
	}
	public static void dAll(String tag,String msgIns,String msg){
		if (!printLog) {
			return;
		}
		if(msg.length() <= LOG_CAT_DATA_LENGTH){
			d(tag,msgIns+msg);
			return;
		}
		float length1 = msg.length()/LOG_CAT_DATA_LENGTH;
		if(length1>(int)length1){
			length1 += 1;
		}
		final int length = (int)length1;
		int i = 0;
		for(;i<length-1;i++){
			d(tag,msgIns+"第"+i+"段"+msg.substring(i*LOG_CAT_DATA_LENGTH,(i+1)*LOG_CAT_DATA_LENGTH));
		}
		d(tag,msgIns+"第"+i+"段"+msg.substring(i*LOG_CAT_DATA_LENGTH,msg.length()));
	}

	public static void v(String tag, String msg) {
		if (!printLog) {
			return;
		}
		android.util.Log.v(tag, msg);
	}

	public static void v(String tag, String msg, Throwable tr) {
		if (!printLog) {
			return;
		}
		android.util.Log.v(tag, msg, tr);
	}

	public static void i(String tag, String msg) {
		if (!printLog) {
			return;
		}
		android.util.Log.i(tag, msg);
	}

	public static void i(String tag, String msg, Throwable tr) {
		if (!printLog) {
			return;
		}
		android.util.Log.i(tag, msg, tr);
	}

	public static void w(String tag, String msg) {
		if (!printLog) {
			return;
		}
		android.util.Log.w(tag, msg);
	}

	public static void w(String tag, Throwable tr) {
		if (!printLog) {
			return;
		}
		android.util.Log.w(tag, tr);
	}

	public static void w(String tag, String msg, Throwable tr) {
		if (!printLog) {
			return;
		}
		android.util.Log.w(tag, msg, tr);
	}

	public static void e(String tag, String msg) {
		if (!printLog) {
			return;
		}
		android.util.Log.e(tag, msg);
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (!printLog) {
			return;
		}
		android.util.Log.e(tag, msg, tr);
	}
	public static void saveLog(String msg){
		try {
			if (accessFile == null) {
				accessFile = new RandomAccessFile(LOG_FILE, "rw");
			}
			long fileLength = accessFile.length();
			// 将写文件指针移到文件尾。
			//accessFile.
			accessFile.seek(fileLength);
			//accessFile.writeBytes(FormatUtils.formatDate(new Date()) + ":");
			accessFile.writeBytes(msg);
			//accessFile.writeChars(msg);
			accessFile.writeBytes("\r\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
