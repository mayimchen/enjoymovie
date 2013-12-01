package skytv_com.banking.enjoymovie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView.BufferType;
import android.widget.TextView;
import android.widget.Toast;
import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.protocol.ProtocolTag;

import com.banking.xc.utils.DPIUtil;
import com.banking.xc.utils.GeoWebChromeClient;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.MyActivity;
import com.banking.xc.utils.WebSettingsUtils;



public class WebActivity extends MyActivity {

	
	public static final int TYPE_TURN_RIGHT = 10;
	public static final int TYPE_TURN_LEFT = 11;
	
	private final String TAG = "WebActivity";
	private WebView webView;

	private RelativeLayout headLayout;
	private TextView titleView;
	private Button closeButton;
	private ImageView progressImage;

	private AlphaAnimation hideProgressAnimation;

	private String url;
	private int pageType;
	public static final String TYPE_TAG = "pagetag";
	public static final int DETAIL = 1;
	public static final int LIST = 2;


	private float perWidth = (float) DPIUtil.getWidth() / 100f;



	private ProgressBar loadingBar;
	// 图片的uri
	private Uri photoUri;
	private Handler mHandler;
	CustomViewCallback  myCallback;
	/**
	 * sdk2.1 的版本号 7
	 */
	private final static int SDK_2_1 = 7;

	private void findView() {
		if(pageType==DETAIL){
			headLayout = (RelativeLayout)findViewById(R.id.webview_title);
			headLayout.setVisibility(View.GONE);
		}
		closeButton = (Button) findViewById(R.id.titleRightButton);
		titleView = (TextView) findViewById(R.id.titleText);
		progressImage = (ImageView) findViewById(R.id.progressImage);

	}

	private void initData() {
		url = getIntent().getExtras().getString("url");
		pageType = getIntent().getExtras().getInt(TYPE_TAG, LIST);
	}

	public static void startWebActivity(Context context,String url){
		Intent intent = new Intent(context,WebActivity.class);//VideoListActivity
		intent.putExtra("url", url);
		context.startActivity(intent);
	}
	public static void startWebActivity(Context context,String url,int type){
		Intent intent = new Intent(context,WebActivity.class);//VideoListActivity
		intent.putExtra("url", url);
		intent.putExtra(TYPE_TAG, type);
		context.startActivity(intent);
	}
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.web_activity);

		initData();
		findView();
		
		mHandler = new MyWebViewHandler();
		// 右上角关闭按钮的文字与事件
		closeButton.setText("关闭");
		closeButton.setOnClickListener(new Button.OnClickListener() {
			@Override  
			public void onClick(View v) {
				finish();
				//mHandler.sendEmptyMessage(TYPE_TURN_LEFT);
			}
		});
		closeButton.setVisibility(View.VISIBLE);

		webView = new WebView(this);// (WebView) this.findViewById(R.id.webView);//
		// 设置不显示滚动条
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		// 设置javascript可用
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setPluginState(PluginState.ON);

		webView.addJavascriptInterface(new AndroidBridge(), "android");
		// 获得焦点
		webView.requestFocus();
		// 缩放把柄
		webView.getSettings().setBuiltInZoomControls(false);


		// 初始页面
		if (Log.D) {
			Log.d(TAG, "url -->> " + url);
		}
		post(new Runnable() {
			@Override
			public void run() {
				webView.loadUrl(url);
				/*if(check()){
					webView.loadUrl(url);
				}else{
					webView.loadUrl("file:///android_asset/download.html");
				}*/
				
			}
		});
		
		String sdk = Build.VERSION.SDK;
		if (Integer.valueOf(sdk) >= 5) {
			// 地理位置可用
			WebSettingsUtils utils = new WebSettingsUtils();
			utils.setGeolocationEnabled(webView.getSettings());
			
			webView.setWebChromeClient(new GeoWebChromeClient() {
				
				@SuppressLint("NewApi")
				public void onShowCustomView(View view, CustomViewCallback callback) {  
		            Log.e("Media", "onShowCustomView ............ ");  
		  
		  
		                if (myCallback != null) {  
		                      myCallback.onCustomViewHidden();
		                      myCallback = null ;  
		                      Log.e("Media", "myCallback.onCustomViewHidden()...");  
		                      return;  
		               }  
		  
		  
		                long id = Thread.currentThread().getId();  
		              
		               ViewGroup parent = (ViewGroup) webView.getParent();  
		               String s = parent.getClass().getName();  
		               Log.v("WidgetChromeClient", "rong debug Ex: " + s);  
		               parent.removeView(webView);  
		               LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		       		   params.addRule(RelativeLayout.BELOW, R.id.webview_title);
		       		   view.setLayoutParams(params);
		               parent.addView(view);  
		               myView = view;  
		               myCallback = callback;  
		  
		  
		                  
		                  
		        }  
		  
		  
		         private View myView = null;  
		         private CustomViewCallback myCallback = null;  
		          
		          
		         @SuppressLint("NewApi")
				public void onHideCustomView() {  
		                 
		                long id = Thread.currentThread().getId();  
		                Log.v("Media", "onrong debug in hideCustom Ex: " + id);  
		                 
		                 
		                if (myView != null) {  
		                       
		                      if (myCallback != null) {  
		                             myCallback.onCustomViewHidden(); 
		                             myCallback = null ;  
		                     }  
		                       
		                     ViewGroup parent = (ViewGroup) myView.getParent();  
		                     parent.removeView( myView);
		                     LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		             		 params.addRule(RelativeLayout.BELOW, R.id.webview_title);
		             		 myView.setLayoutParams(params);
		                     parent.addView(webView);  
		                     myView = null;  
		               }  
		         }  

				@Override
				public void onReceivedTitle(WebView view, String title) {
					if (Log.D) {
						Log.d(TAG, "onReceivedTitle() title -->> " + title);
					}
					if (!TextUtils.isEmpty(title)) {
						titleView.setText(title);
					} else {
						titleView.setText("");
					}
					super.onReceivedTitle(view, title);
				}

				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					super.onProgressChanged(view, newProgress);
					setImageProgress(newProgress);
				}
				
			});
		} 
		
		webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.d(TAG, "shouldOverrideUrlLoading url -->> " + url);
                
                return true;
            }
            @Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				if(url.contains("list#")){
					webView.postDelayed(new Runnable(){
						@Override
						public void run() {
							String js="javascript: var v=document.getElementsByTagName('video')[0]; "  
							         +"v.play(); ";  
							webView.loadUrl(js);
						}
					}, 200);
					webView.postDelayed(new Runnable(){
						@Override
						public void run() {
							String js="javascript: var v=document.getElementsByTagName('video')[0]; "+"v.webkitEnterFullscreen(); ";  
							webView.loadUrl(js);
						}
					}, 500);
				}
				
				if(url.contains("v.play()")){
					
				}
				
			}
        });

		ViewGroup group = ((ViewGroup) findViewById(R.id.webview_layout));

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.addRule(RelativeLayout.BELOW, R.id.webview_title);
		webView.setLayoutParams(params);
		group.addView(webView);

		loadingBar = new ProgressBar(this);
		LayoutParams loadingParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		loadingParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		loadingBar.setLayoutParams(loadingParams);
		loadingBar.setVisibility(View.GONE);
		group.addView(loadingBar);
		
		mHandler.postDelayed(new Runnable(){

			@Override
			public void run() {
				//mHandler.sendEmptyMessage(TYPE_TURN_RIGHT);
			}
			
		}, 5000);
		
		
	}
	class MyWebViewHandler extends Handler {
        public MyWebViewHandler() {
       	 
        }

        public MyWebViewHandler(Looper L) {
            super(L);
        }

       // 子类必须重写此方法,接受数据
        @Override
        public void handleMessage(Message msg) {
       	 Log.d("yinhang", "handleMessage"+msg.what);
			switch(msg.what){
			case TYPE_TURN_RIGHT: //非通讯
				webView.loadUrl("javascript:turnRight()");
				break;
			case TYPE_TURN_LEFT:
				webView.loadUrl("javascript:turnLeft()");
				break;
			}
			super.handleMessage(msg);

        }
	}
	private boolean check(){
		PackageManager pm = getPackageManager();
		List<PackageInfo> infoList = pm.getInstalledPackages(PackageManager.GET_SERVICES);
		for(PackageInfo info : infoList){
			if("com.adobe.flashplayer".equals(info.packageName)){
				return true;
			}
		}
		return false;
	}
	private class AndroidBridge{
		public void goMarket(){
			mHandler.post(new Runnable(){
				@Override
				public void run() {
					Intent installIntent = new Intent("android.intent.action.VIEW");
					installIntent.setData(Uri.parse("market://details?id=com.adobe.flashplayer"));
					startActivity(installIntent);
				}
				
			});
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}


	/**
	 * 隐藏title的加载条
	 */
	protected void hideImageProgress() {
		setImageProgress(100);
		progressImage.startAnimation(getHideProgressAnimation());
		hideLoadingBar();
	}

	/**
	 * 显示title的加载条
	 */
	protected void showImagePregress() {
		setImageProgress(0);
		progressImage.setBackgroundResource(R.drawable.android_title_bg_progress);
		progressImage.setVisibility(View.VISIBLE);
		showLoadingBar();
	}

	/**
	 * 获取隐藏进度的动画效果
	 * 
	 * @return
	 */
	private Animation getHideProgressAnimation() {
		if (hideProgressAnimation == null) {
			hideProgressAnimation = new AlphaAnimation(1f, 0.1f);
			hideProgressAnimation.setDuration(1000);
			hideProgressAnimation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationStart(Animation animation) {
				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}

				@Override
				public void onAnimationEnd(Animation animation) {
					progressImage.setVisibility(View.GONE);
				}
			});
		}
		return hideProgressAnimation;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public void setImageProgress(int progress) {
		int width = (int) (perWidth * progress);
		progressImage.setLayoutParams(new RelativeLayout.LayoutParams(width, RelativeLayout.LayoutParams.FILL_PARENT));
	}

	/**
	 * 刷新
	 */
	public void reload() {
		webView.reload();
	}


	private void showLoadingBar() {
		post(new Runnable() {
			@Override
			public void run() {
				if (loadingBar != null) {
					loadingBar.setVisibility(View.VISIBLE);
				}
			}
		});

	}

	private void hideLoadingBar() {
		post(new Runnable() {
			@Override
			public void run() {
				if (loadingBar != null) {
					loadingBar.setVisibility(View.GONE);
				}
			}
		});

	}



}
