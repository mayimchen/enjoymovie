package skytv_com.banking.enjoymovie;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import skytv_com.banking.enjoymovie.bean.Movie;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;

import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.HttpGroupUtils;
import com.banking.xc.utils.JSONArrayPoxy;
import com.banking.xc.utils.JSONObjectProxy;
import com.banking.xc.utils.MyActivity;
import com.banking.xc.utils.MySimpleAdapter;

public class VideoListActivity extends MyActivity{

	    /** Called when the activity is first created. */
		public static String VIDEO_KEYWORD = "keyword";
		GridView videoListView;
		MySimpleAdapter mChapterAdapters;
		ArrayList<Movie> movies;
		
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.video_list);
	        
	        videoListView = (GridView)this.findViewById(R.id.videoList);
	        if(getIntent().getExtras()!=null){
	        	String keyword = getIntent().getExtras().getString(VIDEO_KEYWORD);
	        	getVideoList(keyword);
	        }
	        
	    }
	    public static String toUinCode(String s){
	    	String as[] = new String[s.length()];
	    	String s1 = "";
	    	for(int i = 0;i<s.length();i++){
	    		as[i] = Integer.toHexString(s.charAt(i)&0xffff);
	    		s1 = s1 + "\\u" + as[i];
	    	}
	    	return s1;
	    }
	    public static String toUtf8Code(String s){
	    	StringBuffer sb = new StringBuffer();
	    	for(int i=0;i<s.length();i++){
	    		char c = s.charAt(i);
	    		if(c>=0&&c<=255){
	    			sb.append(c);
	    		}else{
	    			byte[] b;
	    			try{
	    				b = String.valueOf(c).getBytes("utf-8");
	    			}catch(Exception e){
	    				b = new byte[0];
	    			}
	    			for(int j = 0;j<b.length;j++){
	    				int k = b[j];
	    				if(k<0){
	    					k += 256;
	    				}
	    				sb.append("%"+Integer.toHexString(k).toUpperCase());
	    			}
	    			
	    		}
	    	}
	    	return sb.toString();
	    }
	    private void getVideoList(String keyword){
	    	final HttpSetting httpSetting = new HttpSetting();
	    	String uniCodeKeyword = toUtf8Code(keyword);
	    	Log.d("","uniCodeKeyword"+uniCodeKeyword);
			httpSetting.setFinalUrl("http://movie.zoneke.com/suggestion/?keyword="+uniCodeKeyword);
			Log.d("", "keyword"+keyword);
			httpSetting.setEffect(HttpSetting.EFFECT_NO);
			httpSetting.setNotifyUser(true);
			httpSetting.setListener(new HttpGroup.OnCommonListener() {
				@Override
				public void onEnd(HttpResponse httpResponse) {
					
					String chapterData = httpResponse.getString();
					Log.d("", "on End"+chapterData);
					try {
						//JSONObjectProxy jsonObject = new JSONObjectProxy(new JSONObject(chapterData));
						JSONArrayPoxy jsonArray = new JSONArrayPoxy(chapterData);
						movies = Movie.toList(jsonArray);
						adapterMovies();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void onError(HttpError error) {
					Log.d("", "on Error");
				}

				@Override
				public void onReady(HttpSettingParams httpSettingParams) {
				}

			});
			HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
	    }
	    public void adapterMovies(){
			
			mChapterAdapters = new MySimpleAdapter(VideoListActivity.this, movies,
					R.layout.movie_grid_item, new String[] { "name","picUrl"},//,"director","starring"
					new int[] { R.id.movie_item_name,R.id.movie_item_image}) {//"picUrl", R.id.movie_item_image,, R.id.movie_item_director,
				//R.id.movie_item_stars
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {

					View view = super.getView(position, convertView, parent);
					if (position % 2 == 1) {
						view.setBackgroundResource(R.drawable.list_row_even);
					} else {
						view.setBackgroundResource(R.drawable.list_row_odd);
					}
					return view;
				}

			};
			videoListView.post(new Runnable() {
				@Override
				public  void run() {
					videoListView.setAdapter(mChapterAdapters);
					videoListView.setVisibility(View.VISIBLE);
					
				}
			});
			videoListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
						final Movie movie = movies.get(position);
						final String movieId = movie.getMovieID();
						Intent intent = new Intent(VideoListActivity.this,WebActivity.class);//list
						intent.putExtra("url", "http://112.124.26.176:7777/testflash");//http://player.youku.com/player.php/sid/XMzQzNzI0MDky/v.swf
						VideoListActivity.this.startActivity(intent);
				}
			});
		}
}
