package skytv_com.banking.enjoymovie;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import skytv_com.banking.enjoymovie.R;

import com.banking.xc.config.Configuration;
import com.banking.xc.utils.CommonUtil;
import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.ImageUtil;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpGroupSetting;
import com.banking.xc.utils.HttpGroup.HttpGroupaAsynPool;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpTaskListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 异常信息提交
 * 
 * 
 */
public class ErrorActivity extends Activity {
	// private final String TAG = ErrorActivity.class.getSimpleName();
	private TextView textView;
	private EditText editText;
	private CheckBox checkBox;
	private Button btnSubmit, btnCancel;
	private ProgressDialog loading;
	/** 异常信息 **/
	private String errorStr, msg, user;
	private boolean isRestart;
	private boolean isKill = true;

	// /** 机器ID**/
	// private String uuid;
	// /** 客户端版本号**/
	// private String clientVersion;
	// /** 系统版本号**/
	// private String osVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	private void onActivity() {
		final View.OnClickListener onSubmit = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myOnClick(0);
			}

		};

		final View.OnClickListener onCancel = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myOnClick(-1);
			}

		};
		btnSubmit.setOnClickListener(onSubmit);
		btnCancel.setOnClickListener(onCancel);
		textView.setText(textView.getText() + "||" + msg);
	}

	private void onDialog(View layout) {
		new AlertDialog.Builder(this).setView(layout).setMessage(msg).setTitle("").setPositiveButton("", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				myOnClick(0);
			}
		}).setNegativeButton("", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				myOnClick(-1);
			}
		}).setOnKeyListener(new DialogInterface.OnKeyListener() {

			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					return true;
				} else if (keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				} else {
					return false;
				}
			}
		}).show();
	}

	private void myOnClick(int type) {
		switch (type) {
		case 0:
			onSubmitError();

			break;
		case -1:
			killProcess();
		default:
			break;
		}
	}

	/**
	 * 是否重启应用
	 * 
	 * @return
	 */
	private boolean isRestart() {
		if (checkBox != null) {
			return isRestart;
		}
		return false;
	}

	/**
	 * 关闭当前进程
	 */
	private void killProcess() {
		finish();
		android.os.Process.killProcess(Process.myTid());
		System.exit(0);
	}

	/**
	 * 提交错误信息
	 */
	private void onSubmitError() {
		final String functionId = "crash";
		try {
			final JSONObject json = new JSONObject();
			if (errorStr.length() > 20000)
				errorStr = errorStr.substring(0, 20000);
			json.put("msg", errorStr);
			loading = ProgressDialog.show(this, null, "");
			loading.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						dialog.dismiss();
					}
					return false;
				}
			});
			new Thread() {
				public void run() {
					isKill = false;
					doPost(functionId, json);
				};
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
			// finish();
		} finally {
			if (isRestart) {
				Toast.makeText(ErrorActivity.this, "", 1).show();
			} else {
				Toast.makeText(ErrorActivity.this, "", 1).show();
			}
			finish();
		}
	}

	/**
	 * post 连接
	 */
	private void doPost(String functionId, JSONObject json) {
		try {
			HttpGroupSetting setting = new HttpGroupSetting();
			setting.setPriority(HttpGroupSetting.PRIORITY_JSON);
			setting.setType(HttpGroupSetting.TYPE_JSON);
			HttpSetting httpSetting = new HttpSetting();
			HttpGroupaAsynPool httpGroup = null;//new HttpGroupaAsynPool();//setting
			httpSetting.setListener(new HttpGroup.OnAllListener() {

				@Override
				public void onProgress(int max, int progress) {

				}

				@Override
				public void onError(HttpError error) {
					if (Log.D) {
						Log.d("ErrorActivity", " -->> onError() error:" + error.toString());
					}
					restart();
				}

				@Override
				public void onEnd(com.banking.xc.utils.HttpGroup.HttpResponse httpResponse) {
					if (Log.D) {
						Log.d("ErrorActivity", " -->> onEnd() code:" + httpResponse.getCode());
					}
					restart();
				}

				@Override
				public void onStart() {

				}

				private void restart() {
					if (isRestart()) {
						startActivity(new Intent(ErrorActivity.this, MainActivity.class));
					}
					killProcess();
				}
			});
			httpGroup.add(httpSetting);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// ArrayList<NameValuePair> pairList = new ArrayList<NameValuePair>();
		// if (json != null) {
		// String key = "body";
		// pairList.add(new BasicNameValuePair(key, json.toString()));
		// }
		// try {
		// HttpParams params = new BasicHttpParams();
		// HttpConnectionParams.setConnectionTimeout(params, 10000);// 设置超时
		// HttpClient httpClient = new DefaultHttpClient(params);
		// HttpPost httpPost = new HttpPost(mUrl + user);
		// HttpEntity httpEntity = new UrlEncodedFormEntity(pairList, "utf-8");// 设置编码
		// httpPost.setEntity(httpEntity);
		// HttpResponse httpResponse = httpClient.execute(httpPost);
		//
		// int status = httpResponse.getStatusLine().getStatusCode();
		// if (status == HttpStatus.SC_OK) {
		// HttpEntity entity = httpResponse.getEntity();
		// JSONObject obj = new JSONObject(EntityUtils.toString(entity));
		// String code = obj.get("code").toString();
		// if (Log.D) {
		// System.out.println(mUrl + user + "&body=" + json.toString());
		// System.out.println(">>>code:" + code + " len=" + errorStr.length());
		// }
		// }
		// } catch (ConnectTimeoutException e) {
		// e.printStackTrace();
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (JSONException e) {
		// e.printStackTrace();
		// } finally {
		// if (isRestart()) {
		// startActivity(new Intent(ErrorActivity.this, MainActivity.class));
		// }
		// killProcess();
		// }
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		killProcess();
		return false;
	}

	@Override
	protected void onStop() {
		if (isKill)
			killProcess();
		super.onStop();
	}
}
