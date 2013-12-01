package com.banking.xc.utils.staticinfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import skytv_com.banking.enjoymovie.MyApplication;
import android.text.TextUtils;

import com.banking.xc.entity.AirlineInfoEntity;
import com.banking.xc.entity.AirlineInfosResponse;
import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.HttpGroupUtils;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.request.flight.flightproduct.FltGetArilineRequest;
import com.banking.xc.utils.xml.flight.FlightAirLineInfoResponseXmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightAirLineUtil {
	private final static String TAG = "FlightAirLineUtil";
	public static ArrayList<AirlineInfoEntity> airlineList;
	public Map<String,String> airLineMap;
	public List<String> airLineNameList;
	
	/*public static ArrayList<AirlineInfoEntity> getAirlineList(){
		return airlineList;
	}*/
	public Map<String,String> getAirLineMap(){
		return airLineMap;
	}
	public List<String> getAirLineList(){
		return airLineNameList;
	}
	public void initializeFlightAirline(){
		
		//getFlightAirlineByHttp();
		productCityMapByCode();
		productCityNameListByCode();
	}
	/**
	 * 相对于枚举实现，asset中导入数据库,这种方法最优化.
	 * 这个方法没必要，只需要根据城市汉字得到.可以记住热门的
	 */
	public void productCityMapByCode(){
		airLineMap = null;
		airLineMap = new HashMap<String, String>();
		airLineMap.put("四川航空","3U");
		//airLineMap.put("宿雾航空","5J");
		airLineMap.put("东星航空","8C");
		airLineMap.put("华信航空","AE");
		airLineMap.put("中国国航","CA");
		airLineMap.put("中华航空","CI");
		airLineMap.put("大新华","CN");
		airLineMap.put("国泰航空","CX");
		airLineMap.put("南方航空","CZ");
		airLineMap.put("远东航空","EF");//FE,H8
		airLineMap.put("成都航空","EU");
		airLineMap.put("上海航空","FM");//
		airLineMap.put("华夏航空","G5");//
		airLineMap.put("天津航空","GS");
		airLineMap.put("海南航空","HU");
		airLineMap.put("香港航空","HX");
		airLineMap.put("福建航空","IV");
		airLineMap.put("首都航空","JD");//
		airLineMap.put("蓝天航空","KF");//
		airLineMap.put("昆明航空","KY");//
		airLineMap.put("厦门航空","MF");//
		airLineMap.put("东方航空","MU");//
		airLineMap.put("河北航空","NS");//
		airLineMap.put("澳门航空","NX");//
		airLineMap.put("山东航空","SC");//
		airLineMap.put("联合航空","UA");//
		airLineMap.put("香港快运航空","UO");//
		airLineMap.put("深圳航空","ZH");//
		airLineMap.put("中国航空","F6");//
		airLineMap.put("中西部航空","YX");//
		airLineMap.put("亚洲航空","AK");//
		airLineMap.put("西南航空","WN");//
		airLineMap.put("重庆航空","OQ");//
		
	}
	public void productCityNameListByCode(){
		airLineNameList = null;
		airLineNameList = new ArrayList<String>();
		airLineNameList.add("四川航空");
		airLineNameList.add("东星航空");
		airLineNameList.add("华信航空");
		airLineNameList.add("中国国航");
		airLineNameList.add("中华航空");
		airLineNameList.add("大新华");
		airLineNameList.add("国泰航空");
		airLineNameList.add("南方航空");
		airLineNameList.add("天津航空");
		airLineNameList.add("上海航空");
		//可以了
	}
	public String getAirLineNameByCode(String code){
		for(Entry<String, String> set: airLineMap.entrySet()){
			if(TextUtils.equals(set.getValue(),code)){
				return set.getKey();
			}
		}
		return null;
	}
	public String getAirLineCodeByName(String name){
		for(Entry<String, String> set: airLineMap.entrySet()){
			if(TextUtils.equals(set.getKey(),name)){
				return set.getValue();
			}
		}
		return null;
	}
	/**
	 * 这个居然就有21段
	 */
	public static void getFlightAirlineByHttp(){
		FltGetArilineRequest request = new FltGetArilineRequest();
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
				System.out.println("getFlightAirlineList()-->on End");
				if (Log.D) {
					//Log.dAll(TAG, "getFlightAirlineList()-->", result);
				}

				InputStream inputStream = new ByteArrayInputStream(result.getBytes());
				final FlightAirLineInfoResponseXmlParseHandler parseHandler = new FlightAirLineInfoResponseXmlParseHandler(new XmlParseListener() {
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
						airlineList = ((AirlineInfosResponse)o).getAirlineInfoEntityList();
						if(Log.D){
							Log.d(TAG, "airlineList.size"+airlineList.size());
						}
						for(int i =0;i<airlineList.size();i++){
							if(Log.D){
								Log.d(TAG, "airlineList:"+airlineList.get(i).getShortName()+" "+airlineList.get(i).getAirLine());
							}
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
	
}
