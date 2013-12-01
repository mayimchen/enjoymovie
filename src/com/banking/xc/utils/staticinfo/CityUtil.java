package com.banking.xc.utils.staticinfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import skytv_com.banking.enjoymovie.MyApplication;
import android.text.TextUtils;

import com.banking.xc.database.table.CityTable;
import com.banking.xc.entity.CityInfoEntity;
import com.banking.xc.entity.CityInfosResponse;
import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.HttpGroupUtils;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.webService.request.flight.flightproduct.FltGetCityInfoRequest;
import com.banking.xc.utils.xml.flight.FlightCityInfoResponseXmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class CityUtil {
	private final static String TAG = "CityUtil";
	public static ArrayList<CityInfoEntity> cityList;
	
	public  ArrayList<String> cityNames;
	public  Map<String,String> cityNameCodeMap;
	/*public static ArrayList<CityInfoEntity> getCityList(){
		return cityList;
	}*/
	public void initializeFlightCity(){
		
	}
	/**
	 * 方法可能返回为空
	 * @return
	 */
	public ArrayList<String> getCityNameList(){
		return cityNames;
	}
	/**
	 * 可能返回为空
	 * @return
	 */
	public Map<String,String> getCityNameCodeMap(){
		return cityNameCodeMap;
	}

	
	public static void saveCityToFile(){
		
		if(!CityTable.isNeedSaveCity()){
			return;
		}
		
		InputStream is = null;
		if(Log.D){
			Log.d(TAG,"saveCityToFile()");
		}
		try {
			is = MyApplication.getInstance().getResources().getAssets().open("city.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(is==null){
			return;
		}
		final FlightCityInfoResponseXmlParseHandler parseHandler = new FlightCityInfoResponseXmlParseHandler(new XmlParseListener() {
			@Override
			public void onParseStart() {

			}

			@Override
			public void onParseError(String reason) {
				if (Log.D) {
					Log.d(TAG, "getFlightList() Parse error");
				}
			}

			@Override
			public void onParseSuccess(Object o) {
				if(Log.D){
					Log.d(TAG,"Thread"+Thread.currentThread().getName());
				}
				 cityList = (ArrayList<CityInfoEntity>)o;
				 CityTable.insertManyCity(cityList);
				 if(Log.D&&cityList!=null){
					 Log.d(TAG, "cityList size()"+cityList.size());
				 }
			}

		}, is);
		new Thread(new Runnable() {
			@Override
			public void run() {
				parseHandler.parse();
			}
		}).start();
		

	}
	
	public String getCityCodeByCityName(String cityName){
		for(Entry<String,String>set:cityNameCodeMap.entrySet()){
			if(TextUtils.equals(cityName,set.getKey())){
				return set.getValue();
			}
		}
		return null;
		/*for(CityInfoEntity c :cityList){
			if(TextUtils.equals(c.getCityName(), cityName)){
				return c.getCityCode();
			}
		}
		return "";*/
	}
	public String getCityNameByCityCode(String cityCode){
		for(Entry<String,String>set:cityNameCodeMap.entrySet()){
			if(TextUtils.equals(cityCode,set.getValue())){
				return set.getKey();
			}
		}
		return null;
		/*if(cityList==null){
			return "";
		}
		for(CityInfoEntity c :cityList){
			if(TextUtils.equals(c.getCityCode(), cityCode)){
				return c.getCityName();
			}
		}
		return "";*/	
	}
	/**
	 * 相对于枚举实现，asset中导入数据库,这种方法最优化.
	 * 这个方法没必要，只需要根据城市汉字得到.可以记住热门的
	 */
	public void productCityMapByCode(){
		cityNameCodeMap = null;
		cityNameCodeMap = new HashMap<String, String>();
		cityNameCodeMap.put("北京","BJS");
		cityNameCodeMap.put("上海","SHA");
		cityNameCodeMap.put("天津","TSN");
		cityNameCodeMap.put("重庆","CKG");
		cityNameCodeMap.put("哈尔滨","HRB");
		cityNameCodeMap.put("西安","SIA");
		cityNameCodeMap.put("昆明","KMG");
		cityNameCodeMap.put("南京","NKG");
		cityNameCodeMap.put("苏州","SZV");
		cityNameCodeMap.put("杭州","HGH");
		cityNameCodeMap.put("扬州","YTY");
		cityNameCodeMap.put("长沙","CSX");//
		cityNameCodeMap.put("厦门","XMN");//
		cityNameCodeMap.put("张家界","DYG");
		cityNameCodeMap.put("成都","CTU");
		cityNameCodeMap.put("广州","CAN");
		cityNameCodeMap.put("深圳","SZX");
		cityNameCodeMap.put("郑州","CGO");//
		cityNameCodeMap.put("三亚","SYX");//
		cityNameCodeMap.put("济南","TNA");//
		cityNameCodeMap.put("福州","FOC");//
		cityNameCodeMap.put("九寨沟","JZH");//
		cityNameCodeMap.put("泰安","454");//？
		cityNameCodeMap.put("泰山","145");//？
		
		cityNameCodeMap.put("黄山","TXN");//？
		cityNameCodeMap.put("林芝","LZY");//？
		
		cityNameCodeMap.put("大连","DLC");
		cityNameCodeMap.put("青岛","TAO");
		cityNameCodeMap.put("敦煌","DNH");
		cityNameCodeMap.put("曲阜","143");
		
	}
	public void productCityNameListByCode(){
		cityNames = null;
		cityNames = new ArrayList<String>();
		cityNames.add("北京");
		cityNames.add("上海");
		cityNames.add("天津");
		cityNames.add("重庆");
		cityNames.add("哈尔滨");
		cityNames.add("西安");
		cityNames.add("昆明");
		cityNames.add("南京");
		cityNames.add("苏州");
		cityNames.add("扬州");
		cityNames.add("杭州");
		cityNames.add("长沙");
		cityNames.add("厦门");
		cityNames.add("张家界");
		cityNames.add("成都");
		cityNames.add("广州");
		cityNames.add("深圳");
		cityNames.add("郑州");
		cityNames.add("三亚");
		cityNames.add("济南");
		cityNames.add("福州");
		cityNames.add("九寨沟");
		cityNames.add("泰山");
		cityNames.add("泰安");
		cityNames.add("黄山");
		cityNames.add("林芝");
		
		cityNames.add("大连");
		cityNames.add("青岛");
		cityNames.add("敦煌");
		cityNames.add("曲阜");
		
	}
}
