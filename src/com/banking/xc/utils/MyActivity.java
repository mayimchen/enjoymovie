package com.banking.xc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.utils.HttpGroup.HttpGroupSetting;
import com.banking.xc.utils.cache.GlobalImageCache;
import com.banking.xc.utils.frame.ScrollableTabActivity;
import com.banking.xc.utils.frame.TabBarButton.StateController;

public class MyActivity extends FragmentActivity {

	private static final String TAG = "MyActivity";

	private Thread mUiThread;

	private ArrayList<DestroyListener> destroyListenerList = new ArrayList<DestroyListener>();
	private ArrayList<PauseListener> pauseListenerList = new ArrayList<PauseListener>();
	private ArrayList<ResumeListener> resumeListenerList = new ArrayList<ResumeListener>();
	private Handler handler;

	private boolean isCanResend = true;

	
	
	
	
	public void canNotResend() {
		isCanResend = false;
	}

	public void noShowAgain() {
		((ScrollableTabActivity) getParent()).markJump();
	}

	/**
	 * 显示遮罩层时
	 */
	public void onShowModal() {

	}

	/**
	 * 隐藏遮罩层时
	 */
	public void onHideModal() {

	}

	@Override
	public void setContentView(int layoutResID) {
		try {
			super.setContentView(layoutResID);
		} catch (Throwable e) {
			GlobalImageCache.getLruBitmapCache().clean();
		}
		super.setContentView(layoutResID);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Log.D) {
			Log.d("MyActivity", "onCreate() -->> " + getClass().getName());
		}

		mUiThread = Thread.currentThread();

		// 全局解决键盘不隐藏的问题
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

		handler = new Handler();

		super.onCreate(savedInstanceState);

		// 如果是单例Activity就不要去持有对象
		Intent intent = getIntent();
		if (ScrollableTabActivity.isSingleInstance(intent)) {
			destroyListenerList = null;
		}

		

	}
	
	public Map<String,String> getParams(){
		return null;
	}

	@Override
	protected void onResume() {
		if (Log.D) {
			Log.d("MyActivity", "onResume() -->> " + getClass().getName());
		}
		super.onResume();	
		try {
			isCanResend = true;
			int beforeSize = resumeListenerList.size();
			int currIndex = 0;
			for (int i = 0; i < beforeSize; i++) {
				int oldSize = resumeListenerList.size();
				ResumeListener listener = resumeListenerList.get(currIndex);
				listener.onResume();
				if (null == resumeListenerList) {// 为空的话，直接返回了
					return;
				}
				if (oldSize == resumeListenerList.size()) {// 没有减少元素
					currIndex++;
				}
			}
		} catch (Exception e) {
			if (Log.D) {
				Log.e(TAG, "myActivity onResume -->> Exception:" + e.getMessage());
			}
		}
	}

	@Override
	protected void onStop() {
		if (Log.D) {
			Log.d("MyActivity", "onStop() -->> " + getClass().getName());
		}
		super.onStop();
	}

	@Override
	protected void onRestart() {
		if (Log.D) {
			Log.d("MyActivity", "onRestart() -->> " + getClass().getName());
		}
		super.onRestart();
	}

	@Override
	protected void onPause() {
		if (Log.D) {
			Log.d("MyActivity", "onPause() -->> " + getClass().getName());
		}
		super.onPause();
		for (PauseListener listener : pauseListenerList) {
			listener.onPause();
		}
	}

	@Override
	protected void onDestroy() {
		if (Log.D) {
			Log.d("MyActivity", "onDestroy() -->> " + getClass().getName());
		}
		super.onDestroy();
		if (destroyListenerList != null) {
			for (DestroyListener listener : destroyListenerList) {
				listener.onDestroy();
			}
			destroyListenerList = null;
			pauseListenerList = null;
			resumeListenerList = null;
		}
	}

	public void addPauseListener(PauseListener listener) {
		if (null != pauseListenerList) {
			pauseListenerList.add(listener);
		}
	}

	public void addResumeListener(ResumeListener listener) {
		if (null != resumeListenerList) {
			resumeListenerList.add(listener);
		}
	}

	public void addDestroyListener(DestroyListener listener) {
		if (null != destroyListenerList) {
			destroyListenerList.add(listener);
		}
	}

	public void removePauseListener(PauseListener listener) {
		if (null != pauseListenerList) {
			pauseListenerList.remove(listener);
		}
	}

	public void removeResumeListener(ResumeListener listener) {
		if (null != resumeListenerList) {
			resumeListenerList.remove(listener);
		}
	}

	public void removeDestroyListener(DestroyListener listener) {
		if (null != destroyListenerList) {
			destroyListenerList.remove(listener);
		}
	}

	private static AlertDialog.Builder hintDialogBuilder;

	/**
	 * 弹出提示
	 */
	public void alert(int messageId) {
		if (null == hintDialogBuilder) {
			hintDialogBuilder = new AlertDialog.Builder(this);
			hintDialogBuilder.setTitle(R.string.prompt);
			hintDialogBuilder.setMessage(messageId);
			hintDialogBuilder.setPositiveButton(R.string.ok, new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}
		hintDialogBuilder.show();
	}

	/**
	 * Description: 得到连接组
	 * 
	 * @return
	 * 
	 */
	public HttpGroup getHttpGroupaAsynPool() {
		return getHttpGroupaAsynPool(HttpGroupSetting.TYPE_JSON);
	}

	public HttpGroup getHttpGroupaAsynPool(int type) {
		HttpGroupSetting setting = new HttpGroupSetting();
		setting.setMyActivity(this);
		setting.setType(type);
		return getHttpGroupaAsynPool(type);
	}

	/*public HttpGroup getHttpGroupaAsynPool(HttpGroupSetting setting) {
		HttpGroup httpGroup = new HttpGroup.HttpGroupaAsynPool(setting);
		addDestroyListener(httpGroup);
		return httpGroup;
	}*/

	/**
	 * Description: 单例模式打开Activity
	 * @param intent
	 * 
	 */
	public void startSingleActivityInFrame(Intent intent) {
		if (Log.D) {
			Log.d("MyActivity", "startSingleActivityInFrame() -->> ");
		}
		intent.putExtra(ScrollableTabActivity.SINGLE_INSTANCE_FLAG, true);
		((ScrollableTabActivity) getParent()).startSubActivity(intent);
	}

	/**
	 * 
	 *Description: 多例模式打开Activity
	 * @param intent
	 * 
	 */
	public void startActivityInFrame(final Intent intent) {
		if (Log.D) {
			Log.d("MyActivity", "startActivityInFrame() -->> ");
		}
		String sdk = Build.VERSION.SDK;
		if (Integer.valueOf(sdk) > 10) {
			post(new Runnable() {// 抽离本调用链。为了解决一个Android 3.0的问题。
				public void run() {
					((ScrollableTabActivity) getParent()).startSubActivity(intent);
				}
			});
		} else {
			((ScrollableTabActivity) getParent()).startSubActivity(intent);
		}

	}
	
	public void removeAllHistory() {
		if (Log.D) {
			Log.d("MyActivity", "removeAllHistory() -->> ");
		}
		((ScrollableTabActivity) getParent()).removeAllRecords(true);
	}
	
	/**
	 * 清除单例历史，只留第一个 
	 */
	public void removeAllSingleHistory() {
		if (Log.D) {
			Log.d("MyActivity", "removeAllSingleHistory() -->> ");
		}
		((ScrollableTabActivity) getParent()).removeAllSingleInstances();
	}

	
	public void resendActivityInFrame(final Intent intent) {
		if (!isCanResend) {
			return;
		}
		if (Log.D) {
			Log.d("MyActivity", "resendActivityInFrame() -->> " + intent);
		}
		final ScrollableTabActivity parent = (ScrollableTabActivity) getParent();
		parent.pushResendRequest(new Runnable() {
			public void run() {
				intent.putExtra(ScrollableTabActivity.RESEND_FLAG, true);
				parent.startSubActivity(intent);
			}
		});
	}
	public void startTaskActivityInFrame(Intent intent) {
		if (Log.D) {
			Log.d("MyActivity", "startTaskActivityInFrame() -->> " + intent);
		}

		// 添加 task id
		HashMap<String, Object> taskId = createTaskId(intent);

		startTaskActivityInFrame(intent, taskId);
	}
	public void startTaskActivityInFrame(Intent intent, HashMap<String, Object> taskId) {
		if (Log.D) {
			Log.d("MyActivity", "startTaskActivityInFrame() -->> " + intent + "|" + taskId);
		}

		// 添加 task id
		intent.putExtra(ScrollableTabActivity.TASK_ID_FLAG, taskId);

		// 隐藏导航栏
		intent.putExtra(ScrollableTabActivity.NAVIGATION_DISPLAY_FLAG, ScrollableTabActivity.NAVIGATION_DISPLAY_HIDE);

		startActivityInFrame(intent);
	}
	public HashMap<String, Object> createTaskId(Intent intent) {
		HashMap<String, Object> taskId = new HashMap<String, Object>();
		taskId.put("className", intent.getComponent().getClassName());
		return taskId;
	}
	
	public Handler getHandler() {
		return handler;
	}

	/**
	 * 在非UI线程中引用MyActivity对象更新UI
	 */
	public void post(final Runnable action) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (MyActivity.this.isFinishing()) {
					return;
				}
				action.run();
			}
		});
	}

	/**
	 * 统一 post 接口
	 */
	public void post(final Runnable action, int delayMillis) {
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (MyActivity.this.isFinishing()) {
					return;
				}
				action.run();
			}
		}, delayMillis);
	}

	/**
	 * 如果正在UI线程则马上执行，否则就加入到任务队列
	 */
	public void attemptRunOnUiThread(final Runnable action) {
		if (Thread.currentThread() != getUiThread()) {// 判断UI线程
			post(action);
		} else {
			action.run();
		}
	}

	@Override
	public void finish() {
		super.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	public interface DestroyListener {
		void onDestroy();
	}

	public interface PauseListener {
		void onPause();
	}

	public interface ResumeListener {
		void onResume();
	}

	private Thread getUiThread() {
		return mUiThread;
	}
	/**
	 * 在autoMode的Activity需要自己重写该方法。还是不要抽象出接口了
	 * 这时候需要确认
	 */
	public void stopAutoMode(){
	}
	
	/**
	 * 已经取消了自动发现操作
	 * 每个Activity差异化执行
	 */
	public void onStopAutoMode(){
		//其实可以抽象出  title的方法处理
	}
	
	/*interface AutoModeInterface{
		*//**
		 * 在autoMode的Activity需要自己重写该方法。
		 *//*
		public void stopAutoMode();
	}*/
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if(Log.D){
				Log.d("MyActivity", " -->>onKeyDown : ");
			}
			if (getParent() instanceof ScrollableTabActivity) {
				((ScrollableTabActivity) getParent()).clearJump();
			}
		}
		return super.onKeyDown(keyCode, event);
	}
    
}
