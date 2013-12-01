package skytv_com.banking.enjoymovie.bean;

import java.util.ArrayList;

import android.text.TextUtils;

import com.banking.xc.utils.JSONArrayPoxy;
import com.banking.xc.utils.JSONObjectProxy;
import com.banking.xc.utils.Log;

public class Movie {
	private static final String TAG = "Chapter";
	private String name;
	private String movieUrl;
	private String picUrl;
	private String director;
	private String starring;
	private String movieID;
	
	public Movie(JSONObjectProxy object){
		this.setName(object.getStringOrNull("name"));
		this.setMovieUrl(object.getStringOrNull("url"));
		this.setPicUrl(object.getStringOrNull("picture_url"));
		this.setDirector(object.getStringOrNull("director"));
		this.setStarring(object.getStringOrNull("starring"));
		this.setMovieID(object.getStringOrNull("id"));
	}
	
	public Movie(JSONObjectProxy object,String index) {
		this(object);
	}
	

	public static ArrayList<Movie> toList(JSONArrayPoxy array) {
		ArrayList<Movie> chapters = new ArrayList<Movie>();
		try {
			for (int i = 0, length = array.length(); i < length; i++) {
				final JSONObjectProxy object = array.getJSONObject(i);
				if(Log.D){
					Log.d(TAG, "Json object-->"+object);
				}
				final Movie chapter = new Movie(object);
				chapters.add(chapter);
			}
		} catch (Exception e) {
		}
		return chapters;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public static String getTag() {
		return TAG;
	}
	
	
	
	
}
