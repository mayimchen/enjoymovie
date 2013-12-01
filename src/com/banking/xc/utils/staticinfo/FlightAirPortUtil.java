package com.banking.xc.utils.staticinfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;
import android.view.View;
import skytv_com.banking.enjoymovie.R;
import skytv_com.banking.enjoymovie.MyApplication;

import com.banking.xc.entity.AirlineInfoEntity;
import com.banking.xc.entity.AirportInfoEntity;
import com.banking.xc.entity.FlightRoute;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.xml.flight.FlightAriportInfoResponseXmlParseHandler;
import com.banking.xc.utils.xml.flight.FlightSearchXmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightAirPortUtil {
	private final static String TAG = "FlightAirPortUtil";
	public static ArrayList<AirportInfoEntity> airPortList;
	public Map<String,String> airPortMap;
	public List<String> airPortNameList;
	
	/*public static ArrayList<AirlineInfoEntity> getAirlineList(){
		return airlineList;
	}*/
	
	public void initializeFlightAirport(){
		//getFlightAirlineByHttp();
		getAirportByFile();
		
		//productCityMapByCode();
		//productCityNameListByCode();
	}
	public void getAirportByFile(){
		InputStream is = null;
		try {
			is = MyApplication.getInstance().getResources().getAssets().open("airport.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(Log.D){
			Log.d(TAG,"getAirportByFile()-->InputStream null"+is.toString());
		}
		final FlightAriportInfoResponseXmlParseHandler parseHandler = new FlightAriportInfoResponseXmlParseHandler(new XmlParseListener() {
			@Override
			public void onParseStart() {

			}

			@Override
			public void onParseError(String reason) {
				
			}

			@Override
			public void onParseSuccess(Object o) {
				airPortList = (ArrayList<AirportInfoEntity>)o;
				if(Log.D){
					Log.d(TAG, "airPortList.size()"+airPortList.size());
					Log.d(TAG, "airPortList.get(2)"+airPortList.get(2).getAirportName());
				}
			}

		}, is);
		parseHandler.parse();
	}
	public String getNameByThree(String three){
		for(AirportInfoEntity entity : airPortList){
			if(TextUtils.equals(entity.getAirport(),three)){
				return entity.getAirportName();
			}
		}
		return null;
	}
	public Map<String, String> getAirPortMap() {
		return airPortMap;
	}
	public List<String> getAirPortNameList() {
		return airPortNameList;
	}
	/**
	 * 相对于枚举实现，asset中导入数据库,这种方法最优化.
	 * 这个方法没必要，只需要根据城市汉字得到.可以记住热门的
	 */
	public void productCityMapByCode(){
		airPortMap = null;
		airPortMap = new HashMap<String, String>();
		airPortMap.put("南苑机场","NAY");
		airPortMap.put("首都国际机场","PEK");
		airPortMap.put("浦东国际机场","PVG");
		airPortMap.put("虹桥国际机场","SHA");
		airPortMap.put("滨海国际机场","TSN");
		airPortMap.put("江北国际机场","NAY");
		airPortMap.put("太平国际机场","HRB");
		airPortMap.put("周水子国际机场","DLC");
		airPortMap.put("流亭国际机场","TAO");
		airPortMap.put("咸阳国际机场","XIY");
		airPortMap.put("敦煌机场","DNH");
		airPortMap.put("禄口国际机场","NKJ");
		airPortMap.put("无锡苏南硕放国际机场","WUX");
		airPortMap.put("扬州泰州机场","YTY");
		airPortMap.put("萧山国际机场","HGH");
		airPortMap.put("普陀山机场","HSN");
		
		airPortMap.put("屯溪机场","TXN");
		airPortMap.put("庐山机场","JIU");
		airPortMap.put("高崎国际机场","XMN");
		airPortMap.put("武夷山机场","WUS");
		airPortMap.put("荷花机场","DYG");
		airPortMap.put("双流国际机场","CTU");
		
		airPortMap.put("宝安国际机场","SZX");
		airPortMap.put("三灶机场","ZUH");
		airPortMap.put("新白云国际机场","CAN");
		airPortMap.put("两江国际机场","KWL");
		airPortMap.put("巫家坝国际机场","KMG");
		airPortMap.put("西双版纳机场","JHG");
		airPortMap.put("大理机场","DLU");
		airPortMap.put("丽江机场","LJG");
		airPortMap.put("双流国际机场","CTU");
	}
	public void productCityNameListByCode(){
		airPortNameList = null;
		airPortNameList = new ArrayList<String>();
		airPortNameList.add("南苑机场");
		airPortNameList.add("首都国际机场");
		airPortNameList.add("浦东国际机场");
		airPortNameList.add("虹桥国际机场");
		airPortNameList.add("滨海国际机场");
		airPortNameList.add("江北国际机场");
		airPortNameList.add("滨海国际机场");
		airPortNameList.add("滨海国际机场");
		airPortNameList.add("滨海国际机场");
		airPortNameList.add("滨海国际机场");
		airPortNameList.add("滨海国际机场");
		
	}
	
}
