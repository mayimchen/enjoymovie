package com.banking.xc.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import skytv_com.banking.enjoymovie.MyApplication;
import skytv_com.banking.enjoymovie.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.banking.xc.config.CacheTimeConfig;
import com.banking.xc.config.Configuration;
import com.banking.xc.utils.FileService.Directory;
import com.banking.xc.utils.MyActivity.DestroyListener;
import com.banking.xc.utils.thread.PooledThread;
import com.banking.xc.utils.ui.DialogController;
import com.banking.xc.utils.webService.request.XCRequest;
import com.banking.xc.utils.webService.util.WebServiceConnect;

public abstract class HttpGroup implements DestroyListener {

	private static int httpIdCounter = 0;
	private static final int connectTimeout = Integer.parseInt(Configuration
			.getProperty(Configuration.CONNECT_TIMEOUT));// 连接超时
	private static final int connectTimeoutForWIFI = Integer
			.parseInt(Configuration
					.getProperty(Configuration.CONNECT_TIMEOUT_FOR_WIFI));// 连接超时
	private static final int readTimeout = Integer.parseInt(Configuration
			.getProperty(Configuration.READ_TIMEOUT));// 读取超时
	private static final int readTimeoutForWIFI = Integer
			.parseInt(Configuration
					.getProperty(Configuration.READ_TIMEOUT_FOR_WIFI));// 读取超时
	private static String charset = "GBK";// 编码 UTF-8
	private static final int attempts = Integer.parseInt(Configuration
			.getProperty(Configuration.ATTEMPTS));// 尝试次数
	private static final int attemptsTime = Integer.parseInt(Configuration
			.getProperty(Configuration.ATTEMPTS_TIME));// 尝试的间隔时间

	protected int httpCount = 0;// 组的任务数量
	protected HttpGroupSetting httpGroupSetting;
	protected int priority = 1;
	protected int type = 1;
	AlertDialog alertDialog;

	// 设置是否带上client的访问信息
	private static final HashMap<MyActivity, ArrayList<HttpRequest>> alertDialogStateMap = new HashMap<MyActivity, ArrayList<HttpRequest>>();

	public HttpGroup(HttpGroupSetting setting) {
		Log.d("HttpGroup", "HttpGroup cons");
		this.httpGroupSetting = setting;
		this.priority = setting.getPriority();
		this.type = setting.getType();
	}

	abstract protected void execute(HttpRequest httpRequest);

	/**
	 * 每次提供的httpSetting都应该是新的，不要同一个httpSetting多次提供给网络层
	 */
	public HttpRequest add(final HttpSetting httpSetting) {

		// 给每个网络请求派发一个运行时标识符
		httpIdCounter = httpIdCounter + 1;
		httpSetting.setId(httpIdCounter);

		tryEffect(httpSetting);
		final HttpRequest httpRequest = new HttpRequest(httpSetting);

		final OnReadyListener onReadyListener = httpSetting
				.getOnReadyListener();
		if (null != onReadyListener) {
			new Thread() {
				@Override
				public void run() {
					onReadyListener.onReady(httpSetting);

					if (httpSetting.isReady()) {
						executeAddedRequest(httpRequest);// 准备好参数才继续

					} else {// 否则没有准备好，抛出一个error，给调用者
						final HttpError httpError = new HttpError(
								new Exception(
										HttpError.EXCEPTION_MESSAGE_NO_READY));
						httpSetting.onError(httpError);
					}
				}
			}.start();
		} else {
			executeAddedRequest(httpRequest);// 直接继续
		}

		return httpRequest;

	}

	public void executeAddedRequest(HttpRequest httpRequest) {

		HttpSetting httpSetting = httpRequest.getHttpSetting();

		if (Log.I && null != httpSetting.getFunctionId()) {
			Log.i("HttpGroup", "id:" + httpSetting.getId()
					+ "- functionId -->> " + httpSetting.getFunctionId());
		}
		// 数据类型（因为优先级提前，因此数据类型也要提前）
		if (httpSetting.getType() == 0) {
			httpSetting.setType(type);
		}

		// 优先级（一定要提前到这里处理，因为要赶在加入线程池之前）
		if (httpSetting.getPriority() == 0) {
			httpSetting.setPriority(priority);
		}

		// 默认优先级
		if (httpSetting.getPriority() == 0) {// 可继承
			switch (httpSetting.getType()) {
			case HttpGroupSetting.TYPE_JSON:// 如果是 JSON
				httpSetting.setPriority(HttpGroupSetting.PRIORITY_JSON);
				break;
			case HttpGroupSetting.TYPE_IMAGE:// 如果是图片
				httpSetting.setPriority(HttpGroupSetting.PRIORITY_IMAGE);
				break;
			case HttpGroupSetting.TYPE_FILE:// 如果是文件
				httpSetting.setPriority(HttpGroupSetting.PRIORITY_FILE);
				break;
			}
		}

		execute(httpRequest);// 马上交给线程处理。在此之前还是UI线程。

	}

	/**
	 * 如果要求默认效果，而且效果状态为未处理，并且所关联 activity 非空，才加上效果。
	 */
	private void tryEffect(HttpSetting httpSetting) {
		MyActivity myActivity = httpSetting.getMyActivity();// httpGroupSetting.getMyActivity();
		Activity activity = httpSetting.getActivity();// httpGroupSetting.getMyActivity();
		if (Log.D) {
			Log.d("", "myActivity==null" + (myActivity == null));
		}
		if (HttpSetting.EFFECT_DEFAULT == httpSetting.getEffect() && // 需要默认效果
				HttpSetting.EFFECT_STATE_NO == httpSetting.getEffectState()// 而且效果状态为未处理
		) {// 并且所关联 activity 非空
			if (null != myActivity) {
				if (Log.D) {
					Log.d("", "doeffect");
				}
				DefaultEffectHttpListener effectListener = new DefaultEffectHttpListener(
						httpSetting, myActivity);
				httpSetting.setListener(effectListener);
			} else {
				if (activity != null) {
					if (Log.D) {
						Log.d("", "doeffect 2");
					}
					DefaultEffectHttpListener effectListener = new DefaultEffectHttpListener(
							httpSetting, activity);
					httpSetting.setListener(effectListener);
				}
			}
		}
	}

	@Override
	public void onDestroy() {
	}

	/**
	 * 异步池组
	 */
	public static class HttpGroupaAsynPool extends HttpGroup {
		//

		public HttpGroupaAsynPool(HttpGroupSetting setting) {
			super(setting);
		}

		/*
		 * public HttpGroupaAsynPool(int type,int j) { super(type); }
		 */

		@Override
		public void execute(final HttpRequest httpRequest) {

			// 先放到第一线程池中，避免同时处理多个任务引起手机速度降低，这个池里也区分线
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					httpCount++;
					// onReady
					if (httpCount < 1) {// 通知组开始
						HttpGroupaAsynPool.this.onStart();
					}

					httpRequest.noNeedConnectionHandler();// 先执行不需要连网的任务

					if (httpRequest.isNeedConnection) {// 需要连网取数据

						final Runnable taskRunnable = new Runnable() {
							@Override
							public void run() {
								httpRequest.needConnectionHandler();
							}
						};

						if (httpRequest.getHttpSetting().isTopPriority()) {
							new Thread(taskRunnable).start();
						} else {
							if (httpRequest.getHttpSetting().getPriority() == HttpGroupSetting.PRIORITY_JSON) {// json放到第二个线程池里
								PooledThread.getSecondThreadPool().offerTask(
										taskRunnable,
										httpRequest.getHttpSetting()
												.getPriority());
							} else {// 图片和file放第三线程池里
								PooledThread.getThirdThreadPool().offerTask(
										taskRunnable,
										httpRequest.getHttpSetting()
												.getPriority());
							}
						}
					}
				}
			};
			if (httpRequest.getHttpSetting().isTopPriority()) {
				new Thread(runnable).start();
			} else {
				PooledThread.getFirstThreadPool().offerTask(runnable,
						httpRequest.getHttpSetting().getPriority());
			}
		}
	}

	interface Handler {
		void run();
	}

	/**
	 * 停止控制器
	 */
	public interface StopController {
		void stop();

		boolean isStop();
	}

	public interface CompleteListener {
		void onComplete(Bundle bundle);
	}

	/**
	 * 请求
	 */
	public class HttpRequest implements StopController {

		// 停止控制器
		private boolean stopFlag;

		public boolean isStop() {
			return stopFlag;
		}

		public void stop() {
			stopFlag = true;
		}

		// 停止控制器

		protected HttpSetting httpSetting;

		protected HttpURLConnection conn;
		protected InputStream inputStream;

		protected HttpResponse httpResponse = new HttpResponse();
		// 对象实例化，较大改动

		protected ArrayList<HttpError> errorList;

		protected boolean manualRetry;

		/**
		 * 代表着本次连接是失败的，不可用。
		 */
		protected boolean connectionRetry;

		private int currentHandlerIndex = 0;

		private String thirdHost;

		private ArrayList<HttpError> getErrorList() {
			if (null == errorList) {
				errorList = new ArrayList<HttpError>();
			}
			return errorList;
		}

		private HttpError getLastError() {
			ArrayList<HttpError> errorList = getErrorList();
			int size = errorList.size();
			if (size > 0) {
				return errorList.get(size - 1);
			}
			return null;
		}

		private void clearErrorList() {
			getErrorList().clear();
		}

		public boolean isLastError() {// 判断是否多次尝试失败
			boolean result = null != errorList
					&& !(errorList.size() < httpSetting.getAttempts());// 需要与httpSetting的重试资料来判断
			if (!result) {
				HttpError lastError = getLastError();
				if (null != lastError && lastError.isNoRetry()) {
					result = true;
				}
			}
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- isLastError() -->> " + result);
			}
			return result;
		}

		public void throwError(HttpError error) {
			ArrayList<HttpError> errorList = getErrorList();
			errorList.add(error);
			error.setTimes(errorList.size());
			if (Log.I) {
				Log.i("HttpGroup", "id:" + httpSetting.getId()
						+ "- HttpError -->> " + error);
			}
			// 检查用户交互
			checkErrorInteraction();
		}

		/**
		 * 检查用户交互
		 */
		public void checkErrorInteraction() {
			/*
			 * 存在2种需要与用户交互的异常
			 */
			HttpError lastError = getLastError();
			if (null != lastError && // 认证WIFI
					HttpError.EXCEPTION == lastError.getErrorCode() && //
					HttpError.EXCEPTION_MESSAGE_ATTESTATION_WIFI
							.equals(lastError.getException().getMessage())) {
				alertAttestationWIFIDialog();
			} else if (isLastError()) {// 如果已经达到自动尝试次数就弹出通知窗口
				alertErrorDialog();
			}
		}

		/**
		 * 非线程安全
		 */
		class HttpDialogController extends DialogController {

			protected ArrayList<HttpRequest> httpRequestList;
			protected MyActivity myActivity;

			private boolean isSynchronizHTTP = true;// 弹框时，是否阻塞网络请求

			/**
			 * 初始化
			 */
			public void init(ArrayList<HttpRequest> httpRequestList,
					MyActivity myActivity) {
				this.myActivity = myActivity;
				this.httpRequestList = httpRequestList;
				init(myActivity);
			}

			/**
			 * 重试
			 */
			protected void actionRetry() {
				actionCommon(true);
			}

			/**
			 * 取消
			 */
			protected void actionCancel() {
				actionCommon(false);
			}

			/**
			 * 是否阻塞网络请求
			 * 
			 * @param isSynchronizHTTP
			 */
			public void setSynchronizHTTP(boolean isSynchronizHTTP) {
				this.isSynchronizHTTP = isSynchronizHTTP;
			}

			/**
			 * 是否阻塞网络请求
			 * 
			 * @return
			 */
			public boolean isSynchronizHTTP() {
				return isSynchronizHTTP;
			}

			protected void actionCommon(boolean isRetry) {
				if (null != alertDialog) {
					alertDialog.dismiss();
				}
				if (Log.D) {
					Log.d("HttpGroup",
							"id:"
									+ httpSetting.getId()
									+ "- notifyUser() retry -->> httpRequestList.size() = "
									+ httpRequestList.size());
				}
				synchronized (alertDialogStateMap) {
					for (int i = 0; i < httpRequestList.size(); i++) {
						HttpRequest httpRequest = httpRequestList.get(i);
						if (isRetry) {
							httpRequest.manualRetry = true;
						}
						synchronized (httpRequest) {
							httpRequest.notify();
						}
					}
					alertDialogStateMap.remove(myActivity);
				}
			}

		}

		/**
		 * 弹出对话窗
		 */
		private void notifyUser(final HttpDialogController httpDialogController) {

			final MyActivity myActivity = null;// httpGroupSetting.getMyActivity();
			if (null == myActivity) {// 跟界面无关的连接不弹窗
				return;
			}

			boolean result = false;// 用于控制不要同一界面连续弹窗
			ArrayList<HttpRequest> httpRequestList = null;
			synchronized (alertDialogStateMap) {
				httpRequestList = alertDialogStateMap.get(myActivity);// 该页面所关联的需弹窗网络异常通知
				if (null == httpRequestList) {// 如果没有任何需弹窗网络异常通知
					httpRequestList = new ArrayList<HttpRequest>();
					alertDialogStateMap.put(myActivity, httpRequestList);
					result = true;
				}
				httpRequestList.add(this);
			}

			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- notifyUser() -->> result = " + result);
			}

			if (result) {
				// 弹出对话框
				// 初始化
				httpDialogController.init(httpRequestList, myActivity);

				myActivity.post(new Runnable() {
					@Override
					public void run() {
						httpDialogController.show();
					}
				});

			}

			if (httpDialogController.isSynchronizHTTP) {
				// 本线程工作暂停，等待UI线程接受用户选择。
				synchronized (HttpRequest.this) {
					try {
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- dialog wait start -->> ");
						}
						HttpRequest.this.wait();
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- dialog wait end -->> ");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

		/**
		 * s
		 */
		private void alertResettingDialog() {

			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- alertErrorDialog() -->> ");
			}

			// 是否禁止通知用户处理
			if (!httpSetting.isNotifyUser()) {
				return;
			}
			if (Log.D) {
				Log.d("HttpGroup", "ResettingDialog() -->> ");
			}
			final MyActivity myActivity = MyApplication.getInstance()
					.getCurrentMyActivity();
			if (myActivity == null) {
				return;
			}
			myActivity.post(new Runnable() {
				@Override
				public void run() {
					alertDialog = new Builder(myActivity).create();
					alertDialog.setTitle("检查网络");
					alertDialog.setMessage("您当前网络状况不佳，请检查下您的网络吧！");
					alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "设置网络",
							new AlertDialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = null;
									if (android.os.Build.VERSION.SDK_INT > 10) {
										// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
										intent = new Intent(
												android.provider.Settings.ACTION_SETTINGS);
									} else {
										intent = new Intent(
												android.provider.Settings.ACTION_WIRELESS_SETTINGS);
									}
									alertDialog.dismiss();
									myActivity.startActivity(intent);
								}
							});
					alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消",
							new AlertDialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									myActivity.finish();
									alertDialog.dismiss();
								}
							});

					alertDialog.show();
				}
			});
		}

		/**
		 * 一般异常对话框
		 */
		private void alertErrorDialog() {

			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- alertErrorDialog() -->> ");
			}

			// 是否禁止通知用户处理
			if (!httpSetting.isNotifyUser()) {
				return;
			}

			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- alertErrorDialog() -->> true");
			}

			// 弹窗通知用户
			HttpDialogController httpDialogController = new HttpDialogController() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case AlertDialog.BUTTON_POSITIVE:// （左边的按钮）重试
						if (httpSetting.getAlertErrorDialogType() == HttpSetting.ERROR_DIALOG_TYPE_SETUP_CANCEL) {
							actionCancel();
							final MyActivity myActivity = MyApplication
									.getInstance().getCurrentMyActivity();
							if (null != myActivity) {
								Intent intent = null;
								if (android.os.Build.VERSION.SDK_INT > 10) {
									// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
									intent = new Intent(
											android.provider.Settings.ACTION_SETTINGS);
								} else {
									intent = new Intent(
											android.provider.Settings.ACTION_WIRELESS_SETTINGS);
								}

								// myActivity.startActivityNoException(intent);
							}
						} else {
							actionRetry();
						}
						break;
					case AlertDialog.BUTTON_NEGATIVE:// （右边的按钮）取消或退出
						if (httpSetting.getAlertErrorDialogType() == HttpSetting.ERROR_DIALOG_TYPE_BACK_RETRY) {
							final MyActivity myActivity = MyApplication
									.getInstance().getCurrentMyActivity();
							if (null != myActivity) {
								myActivity.finish();
							}
						} else {
							actionCancel();
						}
						break;
					}
				}

			};

			httpDialogController.setCanceledOnTouchOutside(false);

			httpDialogController.setMessage("网络不佳");

			if (httpSetting.getAlertErrorDialogType() == HttpSetting.ERROR_DIALOG_TYPE_DEFAULT) {
				// 重试按钮
				httpDialogController.setPositiveButton(MyApplication
						.getInstance().getText(R.string.retry));
				// 退出或取消按钮
				httpDialogController
						.setNegativeButton(MyApplication
								.getInstance()
								.getText(
										httpSetting.isNotifyUserWithExit() ? R.string.exit
												: R.string.cancel));
			} else if (httpSetting.getAlertErrorDialogType() == HttpSetting.ERROR_DIALOG_TYPE_ONLY_CANCEL) {
				// 确定：取消
				httpDialogController.setNegativeButton(MyApplication
						.getInstance().getText(R.string.ok));
			} else if (httpSetting.getAlertErrorDialogType() == HttpSetting.ERROR_DIALOG_TYPE_BACK_RETRY) {
				// 重试
				httpDialogController.setPositiveButton(MyApplication
						.getInstance().getText(R.string.retry));
				// 返回上一页
				httpDialogController.setNegativeButton(MyApplication
						.getInstance().getText(R.string.back_page));
			} else if (httpSetting.getAlertErrorDialogType() == HttpSetting.ERROR_DIALOG_TYPE_SETUP_CANCEL) {
				httpDialogController.setSynchronizHTTP(false);// 网络线程可直接结束掉
				// 设置网络
				httpDialogController.setPositiveButton(MyApplication
						.getInstance().getText(R.string.go_setup));
				// 取消
				httpDialogController.setNegativeButton(MyApplication
						.getInstance().getText(R.string.cancel));
			}

			notifyUser(httpDialogController);

		}

		/**
		 * 认证 WIFI 对话框
		 */
		private void alertAttestationWIFIDialog() {
			// 弹出窗口
			HttpDialogController httpDialogController = new HttpDialogController() {

				private int state;

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case AlertDialog.BUTTON_POSITIVE:// （左边的按钮）确定
						switch (state) {
						case 0:// 第一次
							if (Log.D) {
								Log.d("HttpGroup",
										"http dialog BUTTON_POSITIVE -->> " + 1);
							}
							// 改变界面和功能
							state = 1;
							myActivity.post(new Runnable() {// 让窗口关闭后重新显示
										public void run() {
											if (Log.D) {
												Log.d("HttpGroup",
														"http dialog change -->> ");
											}
											setMessage("现在是否重试？");
											setPositiveButton("重试");
											if (!alertDialog.isShowing()) {
												alertDialog.show();
											}
											// 打开浏览器（这里要确保在所有UI操作之后执行）
											Intent intent = new Intent(
													Intent.ACTION_VIEW,
													Uri.parse("http://app.360buy.com/"));
											intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
											// myActivity.startActivityNoException(intent);
										}
									});
							break;
						case 1:// 第二次
							if (Log.D) {
								Log.d("HttpGroup",
										"http dialog BUTTON_POSITIVE -->> " + 2);
							}
							actionRetry();
							break;
						}
						break;
					case AlertDialog.BUTTON_NEGATIVE:// （右边的按钮）取消
						if (Log.D) {
							Log.d("HttpGroup",
									"http dialog BUTTON_NEGATIVE -->> " + 1);
						}
						actionCancel();
						break;
					}
				}
			};
			httpDialogController.setTitle("WIFI认证");
			httpDialogController.setMessage("您所连接的网络可能需要验证，现在打开浏览器进行验证？");
			// （左边的按钮）重试
			httpDialogController.setPositiveButton("确定");
			// （右边的按钮）取消或退出
			httpDialogController.setNegativeButton("取消");
			notifyUser(httpDialogController);
		}

		private ArrayList<Handler> handlers = new ArrayList<Handler>();

		public HttpRequest(HttpSetting httpSetting) {
			this.httpSetting = httpSetting;
		}

		public boolean isNeedConnection = false;// 该请求是否要网络，并进入到线程池中

		/**
		 * 不需要连网可执行的责任链
		 */
		public void noNeedConnectionHandler() {
			handlers.add(firstHandler);
			handlers.add(testHandler);
			handlers.add(cacheHandler);
			nextHandler();
		}

		/**
		 * 需要连网操作执行的责任链，需要被放入到线程池中。
		 */
		public void needConnectionHandler() {
			if (isNeedConnection) {
				handlers.clear();
				handlers.add(connectionThreadPoolsHandler);
				handlers.add(connectionHandler);
				handlers.add(contentHandler);
				nextHandler();
			}
		}

		public HttpSetting getHttpSetting() {
			return httpSetting;
		}

		private void nextHandler() {
			int i = currentHandlerIndex;
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- nextHandler() i -->> " + currentHandlerIndex);
			}
			currentHandlerIndex++;
			if (i < handlers.size()) {
				handlers.get(i).run();
				currentHandlerIndex = i;// 恢复层次指针到本层
			}
		}

		private File findCachesFileByMd5() {
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- findCachesFileByMd5() -->> ");
			}

			Directory directory = null;
			String fileName = httpSetting.getMd5();

			// 1. 确定类型
			switch (httpSetting.getType()) {

			case HttpGroupSetting.TYPE_JSON: {// JSON
				directory = FileService.getDirectory(FileService.JSON_DIR);
				fileName += FileService.CACHE_EXT_NAME_JSON;
				break;
			}

			case HttpGroupSetting.TYPE_IMAGE: {// IMAGE
				directory = FileService.getDirectory(FileService.IMAGE_DIR);
				fileName += FileService.CACHE_EXT_NAME_IMAGE;
				break;
			}

			}

			// 2. 查找文件
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- findCachesFileByMd5() directory -->> " + directory);
			}
			if (null == directory) {
				return null;
			}
			File dir = directory.getDir();
			if (Log.D) {
				Log.d("HttpGroup",
						"id:" + httpSetting.getId()
								+ "- findCachesFileByMd5() dir.exists() -->> "
								+ dir.exists());
			}
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- findCachesFileByMd5() dir.isDirectory() -->> "
						+ dir.isDirectory());
			}
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- findCachesFileByMd5() dir -->> " + dir);
			}

			if (null != directory.getPath()) {
				final String filePath = directory.getPath()
						+ File.separatorChar + fileName;

				final File file = new File(filePath);
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- findCachesFileByMd5() filePath -->> "
							+ filePath);
				}
				if (file.exists()) {
					if (Log.D) {
						Log.d("HttpGroup", "id:" + httpSetting.getId()
								+ "- can find caches file by md5 -->> ");
					}
					return file;
				}
			}

			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- canot find caches file by md5 -->> ");
			}

			return null;

		}

		/**
		 * 设置纠正
		 */
		private Handler firstHandler = new Handler() {
			@Override
			public void run() {
				// System.out.println("XC--> firstHandler");
				// 继承组设置：
				// 重试次数
				if (httpSetting.getAttempts() == 0) {
					httpSetting.setAttempts(attempts);
				}
				// 连接等待时间
				if (httpSetting.getConnectTimeout() == 0) {
					if (NetUtils.isWifi()) {
						httpSetting.setConnectTimeout(connectTimeoutForWIFI);
					} else {
						httpSetting.setConnectTimeout(connectTimeout);
					}
				}
				// 读取等待时间
				if (httpSetting.getReadTimeout() == 0) {
					if (NetUtils.isWifi()) {
						httpSetting.setReadTimeout(readTimeoutForWIFI);
					} else {
						httpSetting.setReadTimeout(readTimeout);
					}
				}

				// 默认缓存
				if (httpSetting.getType() == HttpGroupSetting.TYPE_IMAGE) {// 如果是图片
					httpSetting.setLocalFileCache(true);
					httpSetting.setLocalFileCacheTime(CacheTimeConfig.IMAGE);// 图片默认缓存一天
				}

				// 全局初始化
				if (httpSetting.getType() == HttpGroupSetting.TYPE_IMAGE) {// 如果是图片
					httpSetting.setNeedGlobalInitialization(false);
				}

				// 组里面的连接累计量
				addMaxStep(1);

				// System.out.println("httpSetting.getPayUrl()"+httpSetting.getPayUrl());
				// 这里是图片处理
				if ((TextUtils.isEmpty(httpSetting.getWholeUrl())
						&& TextUtils.isEmpty(httpSetting.getImageUrl()) && TextUtils
						.isEmpty(httpSetting.getFinalUrl()))) {
					// System.out.println("XC--> Error because empty Url");
					HttpError error = new HttpError();
					error.setErrorCode(HttpError.RESPONSE_CODE);
					error.setResponseCode(404);// 目前就当做404处理
					throwError(error);
					httpSetting.onError(getLastError());// 通知失败
					return;// 根本没执行后来的？
				} else {
					nextHandler();

					callBack();
				}
			}
		};

		// 回调
		private void callBack() {

			if (isNeedConnection) {// 本请求没有完成，还需要进入到线程池中连接网络，不执行以下回调
				return;
			}

			addCompletesCount();
			if (isLastError()) {
				if (Log.I) {
					Log.i("HttpGroup", "id:" + httpSetting.getId()
							+ "- onError -->> ");
				}
				httpSetting.onError(getLastError());// 通知失败
			} else {
				if (Log.I) {
					Log.i("HttpGroup", "id:" + httpSetting.getId()
							+ "- onEnd -->> ");
				}
				addStep(1);
				httpSetting.onEnd(httpResponse);// 通知成功
			}
		};

		// interface HttpTestMappers {
		//
		// boolean
		//
		// }

		/**
		 * 测试
		 */
		private Handler testHandler = new Handler() {
			@Override
			public void run() {
				nextHandler();
			}
		};

		/**
		 * 缓存
		 */
		private Handler cacheHandler = new Handler() {
			@Override
			public void run() {
				// System.out.println("XC--> cachehandler");
				File cachesFile = null;
				if (httpSetting.getCacheMode() != HttpSetting.CACHE_MODE_ONLY_NET
						&& httpSetting.isLocalFileCache()
						&& null != (cachesFile = findCachesFileByMd5())) {// 如果有缓存文件就走缓存

					long localFileCacheTime = httpSetting
							.getLocalFileCacheTime();

					if (localFileCacheTime != 0) {// 超出有效期
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- local file cache time out -->> ");
						}
						doNetAndCache();
						return;
					}

					httpResponse = new HttpResponse();

					switch (httpSetting.getType()) {

					case HttpGroupSetting.TYPE_JSON: {

						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- read json file -->> ");
						}
						FileInputStream inputStream = null;
						try {
							inputStream = new FileInputStream(cachesFile);
							httpResponse.setString(IOUtil.readAsString(
									inputStream, charset));
						} catch (Exception e) {
							e.printStackTrace();
							cachesFile.delete();
							httpResponse = null;
							doNetAndCache();
						} finally {
							if (null != inputStream) {
								try {
									inputStream.close();
								} catch (Exception e) {
								}
							}
						}

						break;
					}

					case HttpGroupSetting.TYPE_IMAGE: {// IMAGE

						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- read image file -->> ");
						}
						try {
							httpResponse.setLength(cachesFile.length());

							httpResponse.setSaveFile(cachesFile);// 图片需要保存File对象，用于取图片存储目录

							if (httpSetting.isNeedShareImage()) {// 需要将缓存图片存成共享
								final String shareImagePath = FileService
										.saveShareImage(cachesFile);
								httpResponse.setShareImagePath(shareImagePath);
							}
						} catch (Throwable e) {
							cachesFile.delete();
							httpResponse = null;
							doNetAndCache();
						}

						break;
					}

					}

				} else {
					doNetAndCache();
				}
			}
		};

		/**
		 * 通知需要访问网络
		 */
		private void doNetAndCache() {

			// System.out.println("XC--> doNetAndCache()");
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- doNetAndCache() -->> ");
			}

			// 如果要求只依靠缓存，那么直接走onError。
			if (HttpSetting.CACHE_MODE_ONLY_CACHE == httpSetting.getCacheMode()) {
				HttpError httpError = new HttpError(new Exception(
						HttpError.EXCEPTION_MESSAGE_NO_CACHE));
				httpError.setNoRetry(true);
				throwError(httpError);
				return;
			}

			isNeedConnection = true;// 需要进入到线程池中网络取数据
			if (Log.I) {
				Log.i("HttpGroup", "id:" + httpSetting.getId()
						+ "- onStart -->> ");
			}
			// 开始访问网络了，真正开始了，才打开遮罩
			httpSetting.onStart();// 通知开始（遮罩要在）
		}

		/**
		 * 保存
		 */
		private void saveCache() {
			if (isLastError()) {
				return;
			}

			// 存储
			if (httpSetting.isLocalFileCache()) {
				switch (httpSetting.getType()) {

				case HttpGroupSetting.TYPE_JSON: {// JSON

					if (Log.D) {
						Log.d("HttpGroup", "id:" + httpSetting.getId()
								+ "- save json file start -->> ");
					}
					Directory directory = FileService
							.getDirectory(FileService.JSON_DIR);
					if (null != directory) {
						String fileName = httpSetting.getMd5()
								+ FileService.CACHE_EXT_NAME_JSON;
						if (null == httpResponse) {
							return;
						}
						String fileContent = httpResponse.getString();
						boolean result = FileService.saveToSDCardWithType(
								directory, fileName, fileContent,
								FileService.JSON_DIR);
						if (result) {

						}
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- save json file -->> " + result);
						}
					}
					break;
				}

				case HttpGroupSetting.TYPE_IMAGE: {// IMAGE

					if (Log.D) {
						Log.d("HttpGroup", "id:" + httpSetting.getId()
								+ "- save image file start -->> ");
					}
					Directory directory = FileService
							.getDirectory(FileService.IMAGE_DIR);
					if (null != directory) {
						String fileName = httpSetting.getMd5()
								+ FileService.CACHE_EXT_NAME_IMAGE;
						if (null == httpResponse) {
							return;
						}
						byte[] fileContent = httpResponse.getInputData();
						boolean result = FileService.saveToSDCardWithType(
								directory, fileName, fileContent,
								FileService.IMAGE_DIR);
						if (result) {

							httpResponse.setSaveFile(null);// 图片需要保存File对象，用于取图片存储目录

							if (httpSetting.isNeedShareImage()) {// 需要将缓存图片存成共享
								final String shareImagePath = FileService
										.saveShareImage(null);
								httpResponse.setShareImagePath(shareImagePath);
							}
						}
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "- save image file -->> " + result);
						}
					}
					break;
				}

				}
			}
		}

		/**
		 * 进入线程池的第一个handler
		 */
		private Handler connectionThreadPoolsHandler = new Handler() {

			@Override
			public void run() {
				// System.out.println("XC--> connectionThreadPoolsHandler");
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- connectionThreadPoolsHandler -->> ");
				}

				isNeedConnection = false;// 已进入到线程池，还原为false

				nextHandler();

				// saveCache();// 保存缓存文件

				callBack();// 回调
			}

		};

		/**
		 * 这里要加入重试，真正的网络请求开始
		 */
		private Handler connectionHandler = new Handler() {

			@Override
			public void run() {
				if (Log.D) {
					Log.d("HttpGroup", "connectionHandler");
				}
				try {
					if (httpSetting.getType() == HttpGroupSetting.TYPE_JSON) {
						if (Log.D) {
							Log.d("HttpGroup",
									"httpSetting.getType()==HttpGroupSetting.TYPE_JSON");
						}
						handleBookByHttp();
					} else {
						handleImageByHttp();
					}
				} catch (Exception e) {

				}
			}
		};

		public void handleImageByHttp() {

			String urlStr = httpSetting.getImageUrl();
			try {
				URL url = new URL(urlStr);
				conn = (HttpURLConnection) url.openConnection();
				handleGetOrPost();
			} catch (Exception e) {
			}
		}

		public void handleBookByHttp() {

			String urlStr = httpSetting.getFinalUrl();
			Log.d("HttpGroup", "handleBookByHttp url:"+urlStr);
			try {
				URL url = new URL(urlStr);
				conn = (HttpURLConnection) url.openConnection();
				handleGetOrPost();
			} catch (Exception e) {

			}
		}

		private void handleGetOrPost() throws Exception {
			get();
			conn.setConnectTimeout(httpSetting.getConnectTimeout());
			conn.setReadTimeout(httpSetting.getReadTimeout());
			conn.setRequestProperty("Charset", charset);
			conn.setRequestProperty("Accept-Encoding", "gzip,deflate");// 客户端支持gzip
			connectionHandler2();
		}

		/**
		 * GET 请求
		 */
		private void get() throws Exception {
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- get() -->> ");
			}
			httpResponse = new HttpResponse(conn);
			conn.setRequestMethod("GET");
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- get() -->> ok");
			}
		}

		/**
		 * POST 请求
		 */
		private void post() throws Exception {
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- post() -->> ");
			}
			httpResponse = new HttpResponse(conn);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			byte[] data = null;
			if (this.httpSetting.getMapParams() == null) {
				data = ((String) "body=").getBytes();
			} else {

				StringBuilder sb = new StringBuilder();
				// sb.append("<soap:Body><Request xmlns=\"http://ctrip.com/\"><requestXML>");
				// sb.append("<Request><Header AllianceID=\"5208\" SID=\"123887\" TimeStamp=\"1363920604\" RequestType=\"GroupProductList\" Signature=\"52CC356E6308F116AC4D4CBCD3EFAC3E\" />");
				sb.append("<PaymentEntryRequest>");

				Map<String, String> mapParams = this.httpSetting.getMapParams();
				Set<String> keySet = mapParams.keySet();
				for (Iterator<String> iterator = keySet.iterator(); iterator
						.hasNext();) {
					String key = (String) iterator.next();
					/*
					 * if ("functionId".equals(key)) { continue; }
					 */
					String value = mapParams.get(key);
					if (Log.I) {
						Log.i("HttpGroup", "id:" + httpSetting.getId()
								+ "- param key and value -->> " + key + "："
								+ value);
					}
					/*
					 * sb.append(key).append("=").append(value); if
					 * (iterator.hasNext()) { sb.append("&"); }
					 */
					sb.append("<" + key + ">");
					sb.append(value);
					sb.append("</" + key + ">");
				}
				sb.append("</PaymentEntryRequest>");
				// sb.append("</Request>");
				// sb.append("</requestXML></Request></soap:Body>");
				if (Log.D) {
					Log.d("httpGroup", "id:" + httpSetting.getId()
							+ "sb.toString()" + sb.toString());
				}
				data = sb.toString().getBytes();
			}
			conn.setRequestProperty("Content-Length",
					String.valueOf(data.length));
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- post() -->> 1");
			}
			DataOutputStream outStream = new DataOutputStream(
					conn.getOutputStream());
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- post() -->> 2");
			}
			outStream.write(data);
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- post() -->> ready");
			}
			outStream.flush();
			if (Log.D) {
				Log.d("HttpGroup", "id:" + httpSetting.getId()
						+ "- post() -->> ok");
			}
		}

		/**
		 * 
		 */
		protected void connectionHandler2() {
			try {
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- connectionHandler2() -->> ");
				}
				conn.connect();
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- ResponseCode() -->> ");
				}
				// 保存头字段
				//httpResponse.setHeaderFields(conn.getHeaderFields());
				// 打印所有头字段
				/*if (Log.D) {
					try {
						Map<String, List<String>> headerFields = conn
								.getHeaderFields();// TODO:headerFields有可能为null
						Set<Entry<String, List<String>>> entrySet = headerFields
								.entrySet();
						JSONObject jsonObject = new JSONObject();
						for (Entry<String, List<String>> entry : entrySet) {
							String name = (null == entry.getKey() ? "<null>"
									: entry.getKey());
							String value = new JSONArray(entry.getValue())
									.toString();
							jsonObject.put(name, value);
						}
						Log.d("HttpGroup",
								"id:" + httpSetting.getId()
										+ "- headerFields -->> "
										+ jsonObject.toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}*/
				httpResponse.setCode(conn.getResponseCode());
				/*String inputStreamString = "null";
				try {
					inputStreamString = IOUtil.readAsString(conn.getInputStream(),
							charset, ioProgressListener);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("HttpGroup","inputString init...->"+inputStreamString);*/
				
				// 长度
				httpResponse.setLength(conn.getContentLength());
				HttpGroup.this.addMaxProgress(Long.valueOf(
						httpResponse.getLength()).intValue());// 更新组进度上限
				// 类型
				httpResponse.setType(conn.getContentType());
				if (httpResponse.getCode() != HttpURLConnection.HTTP_OK) {
					HttpError error = new HttpError();
					error.setErrorCode(HttpError.RESPONSE_CODE);
					error.setResponseCode(httpResponse.getCode());
					throwError(error);
					connectionRetry = true;// 重试
					return;
				}
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- ResponseCode() -->> ok");
				}
				// 输入流
				InputStream is = null;
				// 支持gzip
				String encoding = conn.getHeaderField("Content-Encoding");
				if ("gzip".equals(encoding)) {
					is = new GZIPInputStream(conn.getInputStream());
				} else {
					is = conn.getInputStream();
				}
				if (is != null) {
					// System.out.println("XC is  is not null");
				} else {
					// System.out.println("XC is  is null");
				}
				httpResponse.setInputStream(is);
				// try 为了保证释放 InputStream
				try {
					// 下一步
					if (Log.D) {
						Log.d("HttpGroup", "id:" + httpSetting.getId()
								+ "- ResponseCode() -->> ok nextHandler()");
					}
					nextHandler();
				} finally {
					try {
						if (null != httpResponse.getInputStream()) {
							httpResponse.getInputStream().close();
							httpResponse.setInputStream(null);// 去掉这个唯一的
							// InputStream
							// 持有
						}
						if (null != conn) {
							conn.disconnect();
							conn = null;
							// HttpResponse 里的 conn 暂时留着，用于方便事后排错或查询里面设定的属性 TODO
						}
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof SocketTimeoutException) {// 连接超时
					HttpError error = new HttpError();
					error.setErrorCode(HttpError.TIME_OUT);
					throwError(error);
				} else {// 其它
					HttpError httpError = new HttpError(e);
					throwError(httpError);
				}
				connectionRetry = true;// 重试
				return;
			}
		}

		/**
		 * 派发内容处理
		 */
		private Handler contentHandler = new Handler() {
			@Override
			public void run() {
				// if()
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- contentHandler -->>");
					Log.d("HttpGroup",
							"httpSetting.getType()==:" + httpSetting.getType());
				}
				try {
					if (httpSetting.getType() == HttpGroupSetting.TYPE_JSON) {
						InputStream is = httpResponse.getInputStream();
						String inputStreamString = "null";
						try {
							inputStreamString = IOUtil.readAsString(is,
									charset, ioProgressListener);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "-httpGroup inputStreamString my -->> "
									+ inputStreamString);
						}
						httpResponse.setString(inputStreamString);
						
						/*try {
							JSONObjectProxy jsProxy = new JSONObjectProxy(
									new JSONObject(httpResponse.getString()));
							httpResponse.setJsonObject(jsProxy);
						} catch (JSONException e) {// 根本不是 json 格式
							if (Log.D) {
								Log.d("HttpGroup", "id:" + httpSetting.getId()
										+ "- Can not format json -->> ", e);
							}
							HttpError httpError = new HttpError(e);
							throwError(httpError);
							connectionRetry = true;// 重试
							return;
						}*/
						
						return;

					} else if (httpSetting.getType() == HttpGroupSetting.TYPE_IMAGE) {
						imageContent();
					} else if (httpSetting.getType() == HttpGroupSetting.TYPE_FILE) {
						// fileContent();
					}
					httpResponse.clean();
				} catch (Exception e) {
					HttpError httpError = new HttpError(e);
					throwError(httpError);
					connectionRetry = true;// 重试
					return;
				}
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- contentHandler -->> ok");
				}
			}
		};

		// 读取进度监听器
		private IOUtil.ProgressListener ioProgressListener = new IOUtil.ProgressListener() {
			@Override
			public void notify(int incremental, int cumulant) {
				addProgress(incremental);// 组进度
				httpSetting.onProgress(Long.valueOf(httpResponse.getLength())
						.intValue(), cumulant);// 请求进度
			}
		};

		// 连接完毕继续监听器
		private HttpGroup.CompleteListener continueListener = new HttpGroup.CompleteListener() {
			@Override
			public void onComplete(Bundle bundle) {
				synchronized (HttpRequest.this) {
					HttpRequest.this.notify();
				}
			}
		};

		/**
		 * image 内容处理
		 */
		private void imageContent() throws Exception {
			// 头字段所示类型与期望不符时作以下处理：
			if (null == httpResponse.getType()
					|| !httpResponse.getType().contains("image/")) {
				HttpError error = new HttpError();
				error.setErrorCode(HttpError.RESPONSE_CODE);
				error.setResponseCode(404);
				throwError(error);
				connectionRetry = true;// 重试
				return;
			}
			// 走网络
			try {
				if (Log.D) {
					Log.d("setInputData", "setInputData");
				}
				httpResponse.setInputData(IOUtil.readAsBytes(
						httpResponse.getInputStream(), ioProgressListener));
			} catch (Throwable e) {// 读取过程出错
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- image content connection read error -->> ", e);
				}
				HttpError httpError = new HttpError(e);
				throwError(httpError);
				connectionRetry = true;// 重试
				return;
			}
			// 走缓存
		}

		/**
		 * file 内容处理
		 */
		private void fileContent() {
			// 所示类型与期望不符时作以下处理：
			// 管它是什么东西都尝试写到文件里面去
			try {
				FileGuider savePath = httpSetting.getSavePath();

				if (null != savePath) {
					// 确定保存路径
				}

				// TODO 应该判断如果 savePath 为 null
				// TODO 可以而提供绝对路径，也可以提供相对路径，应该有多种方式。
				savePath.setAvailableSize(httpResponse.getLength());// 所需空间大小
				FileOutputStream fileOutputStream = FileService
						.openFileOutput(savePath);
				IOUtil.readAsFile(httpResponse.getInputStream(),
						fileOutputStream, ioProgressListener, this);

				File dir = MyApplication.getInstance().getFilesDir();
				File apkFilePath = new File(dir, savePath.getFileName());
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- download() apkFilePath -->> " + apkFilePath);
				}
				if (isStop()) {
					apkFilePath.delete();
				}
				httpResponse.setSaveFile(apkFilePath);
			} catch (Exception e) {// 读取过程出错
				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "- file content connection read error -->> ", e);
				}
				HttpError httpError = new HttpError(e);
				throwError(httpError);
				connectionRetry = true;// 重试
				return;
			}
		}

		/**
		 * 类型定位
		 */
		public void typeHandler() {
			nextHandler();
		}


		/**
		 * @Title: getResponseJson
		 * @Description: 获取解密后的下发数据
		 * @param @param inputStreamString
		 * @param @return
		 * @return JSONObjectProxy
		 * @throws
		 */
		private JSONObjectProxy getResponseJson(String inputStreamString) {
			JSONObjectProxy responseJsonObject = null;
			try {
				responseJsonObject = new JSONObjectProxy(new JSONObject(
						inputStreamString));
				String codeString = responseJsonObject.getStringOrNull("code");

				if (Log.D) {
					Log.d("HttpGroup", "id:" + httpSetting.getId()
							+ "-codeString -->> " + codeString);
				}

				if (codeString != null && codeString.length() > 0) {
					String encContent = "";

					if ("6".equals(codeString)) {
						String desContent = responseJsonObject
								.getStringOrNull("des");
						if (Log.D) {
							Log.d("HttpGroup", "id:" + httpSetting.getId()
									+ "-通讯加密 desContent -->> " + desContent);
						}
					} else {
						if ("8".equals(codeString) || "11".equals(codeString)
								|| "7".equals(codeString)) {// 8:缺少sessionKey
															// 11:解密请求信息失败
							rsaConnectionRetry();
							return null;
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseJsonObject;
		}

		private void rsaConnectionRetry() {
			HttpError httpError = new HttpError(new Exception(
					HttpError.EXCEPTION_MESSAGE_ATTESTATION_RSA));
			httpError.setNoRetry(true);
			throwError(httpError);
			connectionRetry = true;// 重试
		}

		protected static final int MODULE_STATE_DISABLE = 0;// 禁用
		protected static final int MODULE_STATE_ENCRYPT = 3;// 加密

	}

	/**
	 * 响应封装类
	 */
	public class HttpResponse {

		private InputStream inputStream;
		private byte[] inputData;
		private SoftReference<byte[]> softReferenceInputData;
		private File saveFile;
		private String string;
		private JSONObjectProxy jsonObject;
		private HttpURLConnection httpURLConnection;
		private Map<String, List<String>> headerFields;

		private int code;// 响应码
		private long length;// 数据量
		private String type;// 媒体类型
		private String shareImagePath;// 共享图片路径名

		// 内存回收
		private void gc() {
			softReferenceInputData = new SoftReference<byte[]>(inputData);
			inputData = null;
		}

		/**
		 * 当直接从缓存中取得数据而无需网络连接时，可能会使用此构造函数
		 */
		public HttpResponse() {
		}

		public HttpResponse(HttpURLConnection httpConnection) {
			this.httpURLConnection = httpConnection;
		}

		public void clean() {
			this.httpURLConnection = null;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		/*public void setJsonObject(JSONObjectProxy jsonObject) {
			this.jsonObject = jsonObject;
		}

		public JSONObjectProxy getJSONObject() {
			return jsonObject;
		}*/

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public long getLength() {
			return length;
		}

		public void setLength(long length) {
			this.length = length;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public byte[] getInputData() {
			byte[] iData = inputData;
			gc();
			return iData;
		}

		public void setInputData(byte[] inputData) {
			this.inputData = inputData;
		}

		public File getSaveFile() {
			return saveFile;
		}

		public void setSaveFile(File saveFile) {
			this.saveFile = saveFile;
		}

		public Map<String, List<String>> getHeaderFields() {
			return headerFields;
		}

		public void setHeaderFields(Map<String, List<String>> headerFields) {
			this.headerFields = headerFields;
		}

		public String getHeaderField(String key) {
			if (null == headerFields) {
				return null;
			}
			List<String> listStr = headerFields.get(key);
			if (null == listStr || listStr.size() < 1) {
				return null;
			}
			return listStr.get(0);
		}

		public void setShareImagePath(String shareImagePath) {
			this.shareImagePath = shareImagePath;
		}

		public String getShareImagePath() {
			return shareImagePath;
		}

	}

	/* 组的被子调用事件 */
	protected void onStart() {
		if (null != onGroupStartListener)
			onGroupStartListener.onStart();
	}

	protected void onComplete() {
		if (null != onGroupCompleteListener)
			onGroupCompleteListener.onComplete();
	}

	private int completesCount = 0;

	/**
	 * httpSetting的onEnd 和 onError 都调用这一个，通知调用方各请求完成
	 */
	protected void addCompletesCount() {
		this.completesCount += 1;
		if (Log.I) {
			Log.i("HttpGroup", "addCompletesCount -->> " + this
					+ "completesCount:" + completesCount + ", httpCount:"
					+ httpCount);
		}
		if (completesCount == httpCount) {
			onComplete();
		}
	}

	// 为了 progress -->>
	private void onProgress(int maxProgress, int progress) {
		if (null != onGroupProgressListener)
			onGroupProgressListener.onProgress(maxProgress, progress);
	}

	private int maxProgress = 0;
	private int progress = 0;

	protected void addMaxProgress(int maxProgress) {// TODO 多线程调用这个方法，这里可能会有线程问题
		this.maxProgress += maxProgress;
		onProgress(this.maxProgress, this.progress);

	}

	protected void addProgress(int progress) {// TODO 多线程调用这个方法，这里可能会有线程问题
		this.progress += progress;
		onProgress(this.maxProgress, this.progress);
	}

	// <<-- 为了 progress

	// 为了 step -->>
	private void onStep(int maxStep, int step) {
		if (null != onGroupStepListener)
			onGroupStepListener.onStep(maxStep, step);
	}

	private int maxStep = 0;
	private int step = 0;

	protected void addMaxStep(int maxStep) {// TODO 多线程调用这个方法，这里可能会有线程问题
		this.maxStep += maxStep;
		onStep(this.maxStep, this.step);
	}

	protected void addStep(int step) {// TODO 多线程调用这个方法，这里可能会有线程问题
		this.step += step;
		onStep(this.maxStep, this.step);
	}

	// <<-- 为了 step

	/* 组监听器 - 存放 */
	private OnGroupStartListener onGroupStartListener;
	private OnGroupCompleteListener onGroupCompleteListener;
	private OnGroupProgressListener onGroupProgressListener;
	private OnGroupStepListener onGroupStepListener;

	public void setOnGroupStartListener(
			OnGroupStartListener onGroupStartListener) {
		this.onGroupStartListener = onGroupStartListener;
	}

	public void setOnGroupCompleteListener(
			OnGroupCompleteListener onGroupCompleteListener) {
		this.onGroupCompleteListener = onGroupCompleteListener;
	}

	public void setOnGroupProgressListener(
			OnGroupProgressListener onGroupProgressListener) {
		this.onGroupProgressListener = onGroupProgressListener;
	}

	public void setOnGroupStepListener(OnGroupStepListener onGroupStepListener) {
		this.onGroupStepListener = onGroupStepListener;
	}

	/* 组监听器 - 定义 */
	public interface OnGroupStartListener {
		void onStart();
	}

	public interface OnGroupCompleteListener {
		void onComplete();
	}

	public interface OnGroupProgressListener {
		void onProgress(int max, int progress);
	}

	public interface OnGroupStepListener {
		void onStep(int max, int step);
	}

	/* HttpTask监听器 - 定义 */
	public interface HttpTaskListener {

	}

	public interface OnStartListener extends HttpTaskListener {

		void onStart();

	}

	public interface OnEndListener extends HttpTaskListener {

		void onEnd(HttpResponse httpResponse);

	}

	public interface OnErrorListener extends HttpTaskListener {

		void onError(HttpError error);

	}

	public interface OnReadyListener extends HttpTaskListener {

		void onReady(HttpSettingParams httpSettingParams);

	}

	public interface OnProgressListener extends HttpTaskListener {

		void onProgress(int max, int progress);

	}

	public interface OnCommonListener extends OnEndListener, OnErrorListener,
			OnReadyListener {

	}

	public interface OnAllListener extends OnStartListener, OnEndListener,
			OnErrorListener, OnProgressListener {

	}

	public interface CustomOnAllListener extends OnAllListener {

	}

	/**
	 * 错误信息封装
	 */
	public static class HttpError {

		public static final int EXCEPTION = 0;
		public static final int TIME_OUT = 1;
		public static final int RESPONSE_CODE = 2;
		public static final int JSON_CODE = 3;

		public static final String EXCEPTION_MESSAGE_ATTESTATION_WIFI = "attestation WIFI";
		public static final String EXCEPTION_MESSAGE_NO_CACHE = "no cache";
		public static final String EXCEPTION_MESSAGE_ATTESTATION_RSA = "attestation RSA";
		public static final String EXCEPTION_MESSAGE_NO_READY = "no ready";

		/**
		 * 出错的方向
		 */
		private int errorCode;

		/**
		 * 被捕获的responseCode
		 */
		private int responseCode;

		/**
		 * 被捕获的jsonCode
		 */
		private int jsonCode;

		/**
		 * 备用
		 */
		private String message;

		/**
		 * 被捕获的异常
		 */
		private Throwable exception;

		/**
		 * 第几次尝试
		 */
		private int times;

		/**
		 * 无需重试
		 */
		private boolean noRetry;

		private HttpResponse httpResponse;

		public HttpError() {

		}

		public HttpError(Throwable exception) {
			this.errorCode = EXCEPTION;
			this.exception = exception;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public String getErrorCodeStr() {
			switch (errorCode) {
			case EXCEPTION:
				return "EXCEPTION";
			case TIME_OUT:
				return "TIME_OUT";
			case RESPONSE_CODE:
				return "RESPONSE_CODE";
			case JSON_CODE:
				return "JSON_CODE";
			default:
				return "UNKNOWN";
			}
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public int getResponseCode() {
			return responseCode;
		}

		public void setResponseCode(int responseCode) {
			this.responseCode = responseCode;
		}

		public int getJsonCode() {
			return jsonCode;
		}

		public void setJsonCode(int jsonCode) {
			this.jsonCode = jsonCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Throwable getException() {
			return exception;
		}

		public void setException(Throwable exception) {
			this.exception = exception;
		}

		public int getTimes() {
			return times;
		}

		public void setTimes(int times) {
			this.times = times;
		}

		public HttpResponse getHttpResponse() {
			return httpResponse;
		}

		public void setHttpResponse(HttpResponse httpResponse) {
			this.httpResponse = httpResponse;
		}

		@Override
		public String toString() {
			if (null != getException()) {
				if (Log.D) {
					Log.d("HttpGroup", "HttpError Exception -->> ",
							getException());
				}
			}
			return "HttpError [errorCode=" + getErrorCodeStr() + ", exception="
					+ exception + ", jsonCode=" + jsonCode + ", message="
					+ message + ", responseCode=" + responseCode + ", time="
					+ times + "]";
		}

		/**
		 * 是否无需重试
		 */
		public boolean isNoRetry() {
			return noRetry;
		}

		/**
		 * 设置是否无需重试
		 */
		public void setNoRetry(boolean noRetry) {
			this.noRetry = noRetry;
		}

	}

	/**
	 * Copyright 2011 Jingdong Android Mobile Application
	 * 
	 * @author lijingzuo
	 * 
	 *         Time: 2011-1-10 下午12:52:06
	 * 
	 *         Name:
	 * 
	 *         Description: 连接组设置封装
	 */
	public static class HttpGroupSetting {

		public static final int PRIORITY_FILE = 500;
		public static final int PRIORITY_JSON = 1000;
		public static final int PRIORITY_IMAGE = 5000;

		public static final int TYPE_FILE = 500;
		public static final int TYPE_JSON = 1000;
		public static final int TYPE_IMAGE = 5000;

		private MyActivity myActivity;
		private int priority;
		private int type;

		public MyActivity getMyActivity() {
			return myActivity;
		}

		public void setMyActivity(MyActivity myActivity) {
			this.myActivity = myActivity;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
			if (0 == priority) {
				switch (type) {
				case TYPE_JSON:
					setPriority(PRIORITY_JSON);
					break;
				case TYPE_IMAGE:
					setPriority(PRIORITY_IMAGE);
					break;
				}
			}
		}

	}

	public interface HttpSettingParams {

		void putJsonParam(String key, Object value);

		void putMapParams(String key, String value);

		void setReady(boolean isReady);

	}

	public static class HttpSetting implements HttpSettingParams {

		public static final int EFFECT_NO = 0;// 不要效果
		public static final int EFFECT_DEFAULT = 1;// 默认效果

		public static final int EFFECT_STATE_NO = 0;
		public static final int EFFECT_STATE_YES = 1;

		public static final int CACHE_MODE_AUTO = 0;
		public static final int CACHE_MODE_ONLY_CACHE = 1;
		public static final int CACHE_MODE_ONLY_NET = 2;
		public static final int CACHE_MODE_ASSETS = 3;

		public static final int ERROR_DIALOG_TYPE_DEFAULT = 0;// 默认为0
		public static final int ERROR_DIALOG_TYPE_ONLY_CANCEL = 1;// 只有取消按钮
		public static final int ERROR_DIALOG_TYPE_BACK_RETRY = 2;// 返回上一页和重试
		public static final int ERROR_DIALOG_TYPE_SETUP_CANCEL = 3;// 设置网络和取消

		private XCRequest request;
		private int id;

		private String imageUrl;
		private String finalUrl;
		private FileGuider savePath;
		private JSONObject jsonParams;
		private Map<String, String> mapParams;
		private OnStartListener onStartListener;
		private OnProgressListener onProgressListener;
		private OnEndListener onEndListener;
		private OnErrorListener onErrorListener;
		private OnReadyListener onReadyListener;
		private int connectTimeout;
		private int readTimeout;
		private String md5;
		private int type;
		private int priority;// 0:继承
		private boolean notifyUser = false;
		private boolean notifyUserWithExit = false;// 仅仅控制了一下文字，退出的逻辑由监听器处理。
		private boolean localMemoryCache = false;
		private boolean localFileCache = false;
		private long localFileCacheTime = CacheTimeConfig.DEFAULT;// 0:永久保存（不允许出现，因为有无限膨胀的危险）
		private boolean needGlobalInitialization = true;
		private int effect = EFFECT_DEFAULT;// 0:不要效果,1:默认效果
		private int effectState = EFFECT_STATE_NO;// 0:未处理,1:已处理
		private int cacheMode = CACHE_MODE_AUTO;// 缓存模式。0:自动模式（有缓存用缓存，没缓存用网络）,1:只使用缓存,2:只使用网络,3:打包模式(有缓存用缓存，没缓存用打包数据，没打包数据用网络)
		private int attempts;// 重试次数
		private String postFunctionId;
		private MyActivity myActivity;
		private Activity activity;

		private boolean needShareImage;// 是否将缓存图片存成共享图片

		private boolean isThisFunctionMustBeExcute = false; // 这个请求是否必须被执行，如果没有执行成功（即走了Error），那么将在网络情况重新连接的时候，重新发送这个请求

		private boolean isTopPriority = false;// 首要任务，不会放到线程池中执行

		private boolean isReady = true;// 是否真的准备好要发网络请求

		private int alertErrorDialogType = ERROR_DIALOG_TYPE_DEFAULT;// 错误时的弹出框类型

		public HttpSetting(XCRequest request) {
			this.request = request;
		}

		@Deprecated
		public HttpSetting() {

		}

		public MyActivity getMyActivity() {
			return myActivity;
		}

		public void setMyActivity(MyActivity myActivity) {
			this.myActivity = myActivity;
		}

		public Activity getActivity() {
			return activity;
		}

		public void setActivity(Activity activity) {
			this.activity = activity;
		}

		/**
		 * 仅仅为了保存图片http地址
		 */
		public void setUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getImageUrl() {
			return this.imageUrl;
		}

		public XCRequest getXCRequest() {
			return this.request;
		}

		public void setXCRequest(XCRequest request) {
			this.request = request;
		}

		// public
		public int getAlertErrorDialogType() {
			return alertErrorDialogType;
		}

		public void setAlertErrorDialogType(int alertErrorDialogType) {
			this.alertErrorDialogType = alertErrorDialogType;
		}

		public boolean isReady() {
			return isReady;
		}

		@Override
		public void setReady(boolean isReady) {
			this.isReady = isReady;
		}

		public boolean isThisFunctionMustBeExcute() {
			return isThisFunctionMustBeExcute;
		}

		public void setThisFunctionMustBeExcute(
				boolean isThisFunctionMustBeExcute) {
			this.isThisFunctionMustBeExcute = isThisFunctionMustBeExcute;
		}

		public int getAttempts() {
			return attempts;
		}

		// 重试次数追加1次
		public void appendOneAttempts() {
			attempts++;
		}

		public void setAttempts(int attempts) {
			this.attempts = attempts;
		}

		public String getFunctionId() {
			if (request == null) {
				return "";
			}
			return request.getRequestType();

		}

		public void setPostFunctionId(String s) {
			postFunctionId = s;
		}

		public String getPostFunctionId() {
			return postFunctionId;
		}

		public String getFinalUrl() {
			return finalUrl;
		}

		public void setFinalUrl(String finalUrl) {
			this.finalUrl = finalUrl;
		}

		public JSONObject getJsonParams() {
			if (null == jsonParams) {
				jsonParams = new JSONObject();
			}
			return jsonParams;
		}

		public void putJsonParam(String key, Object value) {
			if (null == this.jsonParams) {
				this.jsonParams = new JSONObject();
			}
			try {
				this.jsonParams.put(key, value);
			} catch (JSONException e) {
				if (Log.D) {
					Log.d("HttpGroup", "JSONException -->> ", e);
				}
			}
		}

		public Map<String, String> getMapParams() {
			return mapParams;
		}

		/**
		 * 批量添加参数
		 */
		@Deprecated
		public void setMapParams(Map<String, String> mapParams) {
			if (null == mapParams) {
				return;
			}
			Set<String> keySet = mapParams.keySet();
			for (String key : keySet) {
				putMapParams(key, mapParams.get(key));
			}
		}

		public void putMapParams(String key, String value) {
			if (null == this.mapParams) {
				this.mapParams = new URLParamMap(charset);
			}
			this.mapParams.put(key, value);
		}

		public int getConnectTimeout() {
			return connectTimeout;
		}

		public void setConnectTimeout(int connectTimeout) {
			this.connectTimeout = connectTimeout;
		}

		public OnStartListener getOnStartListener() {
			return onStartListener;
		}

		public OnProgressListener getOnProgressListener() {
			return onProgressListener;
		}

		public OnEndListener getOnEndListener() {
			return onEndListener;
		}

		public OnErrorListener getOnErrorListener() {
			return onErrorListener;
		}

		public OnReadyListener getOnReadyListener() {
			return onReadyListener;
		}

		public void setListener(HttpTaskListener httpTaskListener) {
			if (httpTaskListener instanceof CustomOnAllListener) {
				setEffect(0);// 没有效果
			}
			if (httpTaskListener instanceof DefaultEffectHttpListener) {
				setEffectState(1);// 已处理
			}
			if (httpTaskListener instanceof OnErrorListener) {
				this.onErrorListener = (OnErrorListener) httpTaskListener;
			}
			if (httpTaskListener instanceof OnStartListener) {
				this.onStartListener = (OnStartListener) httpTaskListener;
			}
			if (httpTaskListener instanceof OnProgressListener) {
				this.onProgressListener = (OnProgressListener) httpTaskListener;
			}
			if (httpTaskListener instanceof OnEndListener) {
				this.onEndListener = (OnEndListener) httpTaskListener;
			}
			if (httpTaskListener instanceof OnReadyListener) {
				this.onReadyListener = (OnReadyListener) httpTaskListener;
			}
		}

		public void onStart() {
			if (null != onStartListener) {
				onStartListener.onStart();
			}
		}

		public void onEnd(HttpResponse httpResponse) {
			if (null != onEndListener) {
				if (httpResponse != null) {
					onEndListener.onEnd(httpResponse);
				} else {

				}

			}
		}

		public void onError(HttpError httpError) {
			if (null != onErrorListener) {
				onErrorListener.onError(httpError);
			}
		}

		public void onProgress(int max, int progress) {
			if (null != onProgressListener) {
				onProgressListener.onProgress(max, progress);
			}
		}

		public String getWholeUrl() {
			if (request == null) {
				return "";
			}
			return request.getWholeUrl();

		}

		// 这儿和图片存储和获取有关
		public String getMd5() {
			if (null == md5) {
				String urlTempStr = getWholeUrl();
				if (null == urlTempStr) {
					return null;
				}
				int start = 0;
				for (int i = 0; i < 3; i++) {
					start = urlTempStr.indexOf("/", start + 1);
				}
				if (start == -1) {
					return null;
				}
				String urlPath = getWholeUrl().substring(start);
				// if (isPost()) {
				// md5 = Md5Encrypt.md5(urlPath + getJsonParams());
				// } else {
				md5 = Md5Encrypt.md5(urlPath);
				// }
				if (Log.D) {
					Log.d("HttpGroup", "urlPath -->> " + urlPath + " md5 -->> "
							+ md5);
				}
			}
			return md5;
		}

		public void setMd5(String md5) {
			this.md5 = md5;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public int getReadTimeout() {
			return readTimeout;
		}

		public void setReadTimeout(int readTimeout) {
			this.readTimeout = readTimeout;
		}

		public boolean isNotifyUser() {
			return notifyUser;
		}

		public void setNotifyUser(boolean notifyUser) {
			this.notifyUser = notifyUser;
		}

		public boolean isLocalMemoryCache() {
			return localMemoryCache;
		}

		public void setLocalMemoryCache(boolean localMemoryCache) {
			this.localMemoryCache = localMemoryCache;
		}

		public boolean isLocalFileCache() {
			return localFileCache;
		}

		public void setLocalFileCache(boolean localFileCache) {
			this.localFileCache = localFileCache;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public long getLocalFileCacheTime() {
			return localFileCacheTime;
		}

		public void setLocalFileCacheTime(long localFileCacheTime) {
			this.localFileCacheTime = localFileCacheTime;
		}

		public FileGuider getSavePath() {
			return savePath;
		}

		/**
		 * 注意不要把同一个对象给多个网络连接
		 */
		public void setSavePath(FileGuider savePath) {
			this.savePath = savePath;
		}

		public boolean isNotifyUserWithExit() {
			return notifyUserWithExit;
		}

		public void setNotifyUserWithExit(boolean notifyUserOrExit) {
			this.notifyUserWithExit = notifyUserOrExit;
		}

		public boolean isNeedGlobalInitialization() {
			return needGlobalInitialization;
		}

		public void setNeedGlobalInitialization(boolean needGlobalInitialization) {
			this.needGlobalInitialization = needGlobalInitialization;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getEffect() {
			return effect;
		}

		public void setEffect(int effect) {
			this.effect = effect;
		}

		public int getEffectState() {
			return effectState;
		}

		public void setEffectState(int effectState) {
			this.effectState = effectState;
		}

		public int getCacheMode() {
			return cacheMode;
		}

		/**
		 * 缓存模式。0:自动模式（有缓存用缓存，没缓存用网络）,1:只使用缓存,2:只使用网络
		 * CACHE_MODE_AUTO、CACHE_MODE_ONLY_CACHE、CACHE_MODE_ONLY_NET
		 */
		public void setCacheMode(int cacheMode) {
			this.cacheMode = cacheMode;
		}

		public void setNeedShareImage(boolean needShareImage) {
			this.needShareImage = needShareImage;
		}

		public boolean isNeedShareImage() {
			return needShareImage;
		}

		public boolean isTopPriority() {
			return isTopPriority;
		}

		public void setTopPriority(boolean isTopPriority) {
			this.isTopPriority = isTopPriority;
		}

	}

	public static String mergerUrlAndParams(String urlStr,
			Map<String, String> params) {

		if (null == params) {
			return urlStr;
		}

		Set<String> keySet = params.keySet();
		if (null == keySet || keySet.isEmpty()) {
			return urlStr;
		}

		StringBuilder url = new StringBuilder(urlStr);
		int i = urlStr.indexOf("?");
		if (i == -1) {
			url.append("?");
		} else {
			String queryString = urlStr.substring(i + 1);
			if (!TextUtils.isEmpty(queryString) && !queryString.endsWith("&")) {
				url.append("&");
			}
		}

		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String value = params.get(key);
			url.append(key).append("=").append(value);
			if (iterator.hasNext()) {
				url.append("&");
			}
		}

		return url.toString();

	}

}
