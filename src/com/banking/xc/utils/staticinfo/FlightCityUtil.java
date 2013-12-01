package com.banking.xc.utils.staticinfo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.text.TextUtils;

import com.banking.xc.database.table.CityTable;
import com.banking.xc.entity.CityInfoEntity;
import com.banking.xc.entity.CityInfosResponse;
import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.HttpGroupUtils;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.request.flight.flightproduct.FltGetCityInfoRequest;
import com.banking.xc.utils.xml.flight.FlightCityInfoResponseXmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

/**
 * 这个util对象依赖于flight页面
 * @author zhangyinhang
 *
 */
public class FlightCityUtil {
	private final static String TAG = "FlightCityUtil";
	public static ArrayList<CityInfoEntity> cityList;
	
	public  ArrayList<String> cityNames;
	public  Map<String,String> cityNameCodeMap;
	/*public static ArrayList<CityInfoEntity> getCityList(){
		return cityList;
	}*/
	public void initializeFlightCity(){
		productCityMapByCode();
		productCityNameListByCode();
		/*cityList = FlightCityTable.getCities();
		if(cityList.size()<1){
			getFlightCityByHttp();
		}else{
			
		}*/
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
	/**
	 * 如果使用全部获取，会引起内存溢出。一个技术问题
	 * @param cityName
	 * @return
	 */
	@Deprecated
	public static void getFlightCityByHttp(){
		FltGetCityInfoRequest request = new FltGetCityInfoRequest();
		//request.set
		//request.setCityId("105");
		//request.set
		final HttpSetting httpSetting = new HttpSetting(request);
		httpSetting.setEffect(HttpSetting.EFFECT_NO);
		httpSetting.setNotifyUser(true);

		httpSetting.setListener(new HttpGroup.OnCommonListener() {
			@Override
			public void onEnd(HttpResponse httpResponse) {
				String result = httpResponse.getString();
				System.out.println("getFlightCityList()-->on End");
				if (Log.D) {
					Log.dAll(TAG, "getFlightCityList()-->", result);
				}

				InputStream inputStream = new ByteArrayInputStream(result.getBytes());
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
						 cityList = ((CityInfosResponse)o).getCityInfoEntiyList();
						 CityTable.insertManyCity(cityList);
						 if(Log.D&&cityList!=null){
							 Log.d(TAG, "cityList.size()"+cityList.size());
						 }
					}

				}, inputStream);
				parseHandler.parse();
			}

			@Override
			public void onError(HttpError error) {
				System.out.println("getFlightList()-->on End");
			}

			@Override
			public void onReady(HttpSettingParams httpSettingParams) {
			}

		});
		HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);

	}
	
	public String getCityCodeByCityName(String cityName){
		for(Entry<String,String>set:cityNameCodeMap.entrySet()){
			if(TextUtils.equals(cityName,set.getKey())){
				return set.getValue();
			}
		}
		return CityTable.getCityCodeByName(cityName);
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
		return CityTable.getCityNameByCode(cityCode);
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
