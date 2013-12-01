package skytv_com.banking.enjoymovie;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import skytv_com.banking.enjoymovie.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.banking.xc.utils.CommonUtil;
import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.FileService;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.NoImageUtils;
import com.banking.xc.utils.frame.ScrollableTabActivity;
import com.banking.xc.utils.frame.TabBarButton.StateController;
import com.banking.xc.utils.lbs.LbsUtil;

public class MainActivity extends ScrollableTabActivity {

	private Set<String> tokens = new HashSet<String>();
	private ViewGroup rootFrameLayout;
	private ViewGroup modal;
	private TextView stateTextView;
	private Runnable resume = null;// 
	private boolean showStateText = true;
	private Handler mHandler;
	AlertDialog alertDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		MyApplication.getInstance().setMainActivity(this);
		mHandler = new Handler();
		super.onCreate(savedInstanceState);
		Log.d("MainActivity", "OnCreate");
		if (Log.D) {
			Log.d("MainActivity", "OnCreate realy");
		}
		final SharedPreferences defaltPreference = CommonUtil.getSharedPreferences();

		if (!globalInit()) {
			return;
		}
		checkNetState();
		LbsUtil.getInstance().startLocationService();
	}
	
	@Override
	protected void onResume() {
		if (Log.D) {
			Log.d("Temp", "MainActivity onResume() -->> ");
		}
		try {
			super.onResume();
			if (null != resume) {
				resume.run();
				resume = null;
			}
		} catch (Exception e) {
			if (Log.D) {
				Log.d("Temp", " onResume()-->> " + e.getMessage());
			}
		}
	}

	
	private void initNetwork() {
		if (Log.D) {
			Log.d("Temp", "HomeActivity initNetwork() -->> ");
		}
		
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	public Handler getHandler() {
		return mHandler;
	}

	private int targetActivityState;



	
	public boolean hasTargetActivity() {
		if (Log.D) {
			Log.d("Temp", "hasTargetActivity() -->> ");
		}
		if (1 == targetActivityState) {
			return true;
		}
		if (0 == targetActivityState) {
			Bundle bundle = getIntent().getExtras();
			if (Log.D) {
				Log.d("Temp", "hasTargetActivity() bundle -->> " + bundle);
			}
			if (null != bundle) {

			}
		}
		return false;
	}


	public void setStateText(final String state) {
		if (stateTextView != null) {
			if (showStateText) {
				stateTextView.setText(state);
			} else {
				stateTextView.setVisibility(View.GONE);
			}
		}

	}

	private boolean globalInit() {
		rootFrameLayout = (ViewGroup) getWindow().peekDecorView();
		modal = new RelativeLayout(this);
		RelativeLayout.LayoutParams textViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		textViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		textViewLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		stateTextView = new TextView(this);
		stateTextView.setPadding(0, 0, 0, DPIUtil.percentHeight(0.25f));
		stateTextView.setTextColor(0xFFFFFFFF);
		modal.addView(stateTextView, textViewLayoutParams);

		modal.setBackgroundDrawable(getResources().getDrawable(R.drawable.start_image));//getResources().getDrawable(R.color.red)

		modal.setOnTouchListener(new ViewGroup.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		rootFrameLayout.addView(modal, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
		rootFrameLayout.invalidate();

		setStateText("加载中...");
		loadHomeActivity();
		return true;

	}

	protected void initNoImage(DialogInterface dialog, CheckBox cb) {
		if (cb.isChecked()) {
			NoImageUtils.setIfNeedAlertDialog(false);
		} else {
			NoImageUtils.setIfNeedAlertDialog(true);
		}
		dialog.cancel();
		loadHomeActivity();
	}

	private void loadHomeActivity() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				navigationInit();
			}
		}, 100);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				deleteToken("");
			}
		}, 3000);// 5000
	}

	private boolean checkNetState() {

		boolean result = CommonUtil.CheckNetWork();

		if (Log.D) {
			Log.d("Temp", "CommonUtil.checkNetWorkType() -->> " + CommonUtil.checkNetWorkType());
		}

		if (!result) {
			alertDialog = new Builder(this).create();
			alertDialog.setTitle("检查网络");
			alertDialog.setMessage("您当前网络状况不佳，请检查下您的网络吧！");
			alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"设置网络", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
						Intent intent = null;
						if (android.os.Build.VERSION.SDK_INT > 10) {
							// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
							intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
						} else {
							intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
						}
						alertDialog.dismiss();
						startActivity(intent);
				}
			});
			alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"取消", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					alertDialog.dismiss();
				}
			});

			alertDialog.show();

		}


		return result;

	}
	public void navigationInit() {

		{
			Intent intent = new Intent(this, MovieHomeActivity.class);
			intent.putExtra(ScrollableTabActivity.SINGLE_INSTANCE_FLAG, true);
		    intent.putExtra(NAVIGATION_FLAG, true);
			intent.putExtra(NAVIGATION_ID, NAVIGATION_HOME);
			this.addTab(new ButtonStyle("精品", R.drawable.icon_boutique_navi), new ButtonAction(intent, true, true));
			//this.addTab(new ButtonStyle("首页", R.drawable.icon_home), new ButtonAction(intent, true, true));
		}

		commit();

	}

	public String createToken(String token) {
		tokens.add(token);
		return token;
	}

	public void deleteToken(String token) {
		tokens.remove(token);
		notifyTokenChange();
	}

	private void notifyTokenChange() {
		if (tokens.size() <= 0) {
			rootFrameLayout.removeView(modal);
			rootFrameLayout.invalidate();

		}
	}

	public void checkSlideScreenState() {
		/*if (!isGuided) {
			isSlideScreenState = true;
			addGuideImage();
		}*/
	}
}
