package skytv_com.banking.enjoymovie;

import java.util.Properties;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;


import skytv_com.banking.enjoymovie.R;

import com.banking.xc.config.Configuration;
import com.banking.xc.database.table.CityTable;
import com.banking.xc.utils.CommonUtil;
import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.MyActivity;
import com.banking.xc.utils.staticinfo.CityUtil;
import com.banking.xc.utils.staticinfo.FlightAirLineUtil;
import com.banking.xc.utils.staticinfo.FlightCityUtil;
import com.banking.xc.utils.user.UserUtil;

public class MyApplication extends Application {


	
	public static boolean isShowActivity;

	private static MyApplication instance;
	private MainActivity mainActivity;
	private Handler handler;
	private Thread uiThread;

	public int networkInitializationState = 0;// 0:未曾初始化,1:正在初始化,2:已经初始化

	private static ActivityManager activityManager;

	public static MyApplication getInstance() {
		return instance;
	}

	@Override
	public void onCreate() {
		if (Log.D) {
			Log.d("Temp", "MyApplication onCreate() -->> Process.myPid() " + Process.myPid());
		}
		super.onCreate();
		instance = this;
		//Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));// 处理未捕获异常
		//UserUtil.getUserByUniqueID("banking17173@126.com");//
		/*UserUtil.initializeUser();
		CityUtil.saveCityToFile();*/
		
		DPIUtil.setDensity(this.getResources().getDisplayMetrics().density);

	
		handler = new Handler();
		uiThread = Thread.currentThread();

		activityManager = (ActivityManager) instance.getSystemService(Context.ACTIVITY_SERVICE);
		dataInit();
	}

	private void dataInit() {
		//FlightAirLineUtil.initializeFlightAirline();
		//FlightCityUtil.initializeFlightCity();
	}

	public MainActivity getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public MyActivity getCurrentMyActivity() {
		if (Log.D) {
			Log.d("Temp", "xxx mainActivity -->> " + mainActivity);
		}
		if (null != mainActivity) {
			Activity activity = mainActivity.getCurrentActivity();
			if (Log.D) {
				Log.d("Temp", "xxx activity -->> " + activity);
			}
			if (activity instanceof MyActivity) {
				return (MyActivity) activity;
			}
		}
		return null;
	}

	/**
	 * 退出对话框
	 */
	public static void exitDialog() {
		if (Log.D) {
			Log.d("Temp", "exitDialog() -->> ");
		}

		final boolean hasBackground = true;

		if (Log.D) {
			Log.d("Temp", "exitDialog() hasBackground -->> " + hasBackground);
		}

		final MainActivity mainActivity = getInstance().getMainActivity();

			AlertDialog alertDialog = new Builder(mainActivity).create();
			alertDialog.setMessage(mainActivity.getText(R.string.exit_confrim_string));
			alertDialog.setTitle(R.string.menu_exit);
			OnClickListener listener = new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:// 退出（左）
						if (hasBackground) {// 如果存在后台
							exitAll();
						} else {
							exit();
						}
						break;
					case DialogInterface.BUTTON_NEGATIVE:// 取消（右）
						dialog.dismiss();
						break;

					default:
						break;
					}
				}

			};

			alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,//
					mainActivity.getString(R.string.exit),// 退出（左）
					listener);

			alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, //
					mainActivity.getString(R.string.cancel), // 取消（右）
					listener);
			alertDialog.show();
	}

	

	/**
	 * 正常退出（根据当前应用设置情况，自动判断是否保留后台进程）
	 */
	public static void exit() {
		if (Log.D) {
			Log.d("Temp", "MyApplication exit() -->> ");
		}
		// 强制杀掉前台进程
		killStage();

	}

	/**
	 * 强制退出（杀后台进程，不更新widget和message）
	 */
	public static void exitAll() {
		if (Log.D) {
			Log.d("Temp", "MyApplication exitAll() -->> ");
		}

		// // 让后台进程去清理缓存文件
		// clearCache();

		// 强制杀掉后台进程
		killBackground();

		// 强制杀掉前台进程
		killStage();

	}

	/**
	 * 强制杀掉前台进程
	 */
	public static void killStage() {
		if (Log.D) {
			Log.d("Temp", "MyApplication killStage() -->> ");
		}

		// 结束界面
		// mainActivity.finishThis();（改为杀进程，因此注释掉。2011-06-08）
		instance.setMainActivity(null);// 无论是否杀界面进程都必须清理，因为即使杀界面进程仍存在
		instance.networkInitializationState = 0;// 无论是否杀界面进程都必须清理，因为即使杀界面进程仍存在
		Process.killProcess(Process.myPid());

	}

	public synchronized static void killStageNoUI() {
		if (Log.D) {
			Log.d("Temp", "MyApplication killStageNoUI() -->> ");
		}
		if (null == MyApplication.getInstance().getMainActivity()) {
			if (Log.D) {
				Log.d("Temp", " killSelfMethod -->> kell!");
			}
			instance.setMainActivity(null);// 无论是否杀界面进程都必须清理，因为即使杀界面进程仍存在
			instance.networkInitializationState = 0;// 无论是否杀界面进程都必须清理，因为即使杀界面进程仍存在
			activityManager.restartPackage(instance.getPackageName());// 只杀后台进程
		}
	}

	/**
	 * 强制杀掉后台进程（如果正在清理缓存文件，那么会在清理缓存文件后自杀）
	 */
	public static void killBackground() {
		if (Log.D) {
			Log.d("Temp", "MyApplication killBackground() -->> ");
		}
		Intent i = new Intent();
		
		MyApplication.getInstance().startService(i);
	}

	public static String getAId() {
		String ret = android.provider.Settings.System.getString(instance.getContentResolver(), //
				android.provider.Settings.System.ANDROID_ID);
		return ret;
	}


	public Handler getHandler() {
		return handler;
	}

	public Thread getUiThread() {
		return uiThread;
	}

}