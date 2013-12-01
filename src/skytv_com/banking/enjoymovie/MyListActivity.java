package skytv_com.banking.enjoymovie;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.MyActivity;

import skytv_com.banking.enjoymovie.R;

public abstract class MyListActivity extends MyActivity{
	
	private ProgressBar mLoadingProgressbar;
	private FrameLayout mLoadingErrorLayout;
	//private ListView mListView;
	private LinearLayout nodataLayout;
	private String noDataHint = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (Log.D) {
			Log.d("MyListActivity", "onCreate() -->> " + getClass().getName());
		}
		super.onCreate(savedInstanceState);

	}
	
	public abstract ProgressBar  setLoadingBar();
	public abstract FrameLayout setLoadingError();
	public abstract LinearLayout setNodataLayout();
	
	public void setNoDataHint(String hint){
		this.noDataHint = hint;
	}
	public void showLoadingLayout(){
		if(mLoadingProgressbar==null){
			mLoadingProgressbar = setLoadingBar();
		}
		attemptRunOnUiThread(new Runnable() {
			@Override
			public void run() {
				mLoadingProgressbar.setVisibility(View.VISIBLE);
			}
		});
	}
	public void hindLoadingLayout(){
		if(mLoadingProgressbar==null){
			mLoadingProgressbar = setLoadingBar();
			if(mLoadingProgressbar==null){
				mLoadingProgressbar = (ProgressBar) this.findViewById(R.id.list_loading_bar);
			}
		}
		attemptRunOnUiThread(new Runnable() {
			@Override
			public void run() {
				mLoadingProgressbar.setVisibility(View.GONE);
			}
		});
	}
	/**
	 * 处理列表类中遇到网络错误，解析错误等
	 */
	public void showErrorLayout(){
		if(mLoadingErrorLayout==null){
			mLoadingErrorLayout = setLoadingError();
			if(mLoadingErrorLayout==null){
				mLoadingErrorLayout = (FrameLayout) this.findViewById(R.id.loading_error_tips_layout);
			}
		}
		attemptRunOnUiThread(new Runnable() {
			@Override
			public void run() {
				mLoadingErrorLayout.setVisibility(View.VISIBLE);
				//触摸事件处理
			}
		});
	}
	/**
	 * 处理列表中遇到没有数据返回情况
	 */
	public void showNodataLayout(){
		if(nodataLayout == null){
			nodataLayout = setNodataLayout();
			if(nodataLayout == null){
				nodataLayout = (LinearLayout)this.findViewById(R.id.loading_nodata_tips_layout);
			}
		}
		attemptRunOnUiThread(new Runnable() {
			@Override
			public void run() {
				if(!TextUtils.isEmpty(noDataHint)){
					final TextView nodataTv = (TextView)(nodataLayout.findViewById(R.id.no_data_hint));
					nodataTv.setText(noDataHint);
				}
				nodataLayout.setVisibility(View.VISIBLE);
				//触摸事件处理
			}
		});
		/*if(mListView==null){
			mListView = setListView();
		}
		final TextView textView = new TextView(this);
		textView.setGravity(Gravity.CENTER);
		String text;
		if (!TextUtils.isEmpty(this.noDataHint)){
			text = noDataHint;
		} else {
			text = getString(R.string.no_data);
		}
		textView.setText("dddd");
		textView.setTextSize(17);
		textView.setPadding(0, 20, 0, 20);
		final String text2 = text;
		attemptRunOnUiThread(new Runnable() {
			@Override
			public void run() {
				mListView.addHeaderView(textView, "", false);
			}
		});*/
		
	}
	
	
	/**
	 * 可以重写
	 */
	public void refreshList(){
		
	}
	
}
