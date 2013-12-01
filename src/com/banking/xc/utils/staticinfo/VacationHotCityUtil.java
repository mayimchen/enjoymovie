package com.banking.xc.utils.staticinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.banking.xc.database.table.CityTable;

import android.text.TextUtils;

public class VacationHotCityUtil {
	private final String TAG = "VacationHotCityUtil";
	
	public  ArrayList<String> cityNames;
	public  Map<String,String> cityNameCodeMap;
	public void initializeVacationHotCity(){
		productCityMapByCode();
		productCityNameListByCode();
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
	public  String getCityCodeByCityName(String cityName){
		if(cityNameCodeMap==null){
			return "";
		}
		for(Entry<String,String> entry:cityNameCodeMap.entrySet()){
			if(TextUtils.equals(entry.getKey(),cityName)){
				return entry.getValue();
			}
		}
		//return "";
		return CityTable.getCityIdByName(cityName);
	}
	/**
	 * 选择器中第几个city
	 * @param cityName
	 * @return
	 */
	public int getCityTagByCityName(String cityName){
		int count = cityNames.size();
		for(int i=0;i<count;i++){
			if(TextUtils.equals(cityName, cityNames.get(i))){
				return i;
			}
		}
		return -1;
	}
	public String getCityNameByCityCode(String cityCode){
		if(cityNameCodeMap==null){
			return "";
		}
		for(Entry<String,String> entry:cityNameCodeMap.entrySet()){
			if(TextUtils.equals(entry.getValue(),cityCode)){
				return entry.getKey();
			}
		}
		return CityTable.getCityNameById(cityCode);
	}
	public String getCityCodeByCityTag(int tag){
		String cityName = cityNames.get(tag);
		String cityId = getCityCodeByCityName(cityName);
		return cityId;
	}
	/**
	 * 相对于枚举实现，asset中导入数据库,这种方法最优化.
	 * 这个方法没必要，只需要根据城市汉字得到.可以记住热门的
	 */
	public void productCityMapByCode(){
		if(cityNameCodeMap!=null&&cityNameCodeMap.size()>5){
			return;
		}
		cityNameCodeMap = null;
		cityNameCodeMap = new HashMap<String, String>();
		cityNameCodeMap.put("北京","1");
		cityNameCodeMap.put("上海","2");
		cityNameCodeMap.put("天津","3");
		cityNameCodeMap.put("重庆","4");
		cityNameCodeMap.put("哈尔滨","5");
		cityNameCodeMap.put("大连","6");
		cityNameCodeMap.put("青岛","7");
		cityNameCodeMap.put("西安","10");
		cityNameCodeMap.put("南京","12");
		cityNameCodeMap.put("无锡","13");
		cityNameCodeMap.put("苏州","14");
		cityNameCodeMap.put("杭州","17");
		cityNameCodeMap.put("厦门","25");
		cityNameCodeMap.put("成都","28");
		cityNameCodeMap.put("深圳","30");
		cityNameCodeMap.put("珠海","31");
		cityNameCodeMap.put("广州","32");
		cityNameCodeMap.put("昆明","34");
		cityNameCodeMap.put("贵阳","38");
		cityNameCodeMap.put("乌鲁木齐","39");
		cityNameCodeMap.put("拉萨","41");
		cityNameCodeMap.put("海口","42");
		cityNameCodeMap.put("三亚","43");
		cityNameCodeMap.put("香港","58");
		cityNameCodeMap.put("银川","99");
		cityNameCodeMap.put("兰州","100");
		cityNameCodeMap.put("呼和浩特","103");
		cityNameCodeMap.put("太原","105");
		cityNameCodeMap.put("喀什市","109");
		cityNameCodeMap.put("西宁","124");
		cityNameCodeMap.put("包头","141");
		cityNameCodeMap.put("海拉尔","142");
		cityNameCodeMap.put("长春","158");
		cityNameCodeMap.put("长沙","206");
		cityNameCodeMap.put("常州","213");
		cityNameCodeMap.put("东莞","223");
		cityNameCodeMap.put("佛山","251");
		cityNameCodeMap.put("福州","258");
		cityNameCodeMap.put("合肥","278");
		cityNameCodeMap.put("江门","316");
		cityNameCodeMap.put("绵阳","370");
		cityNameCodeMap.put("宁波","375");
		cityNameCodeMap.put("南昌","376");
		cityNameCodeMap.put("南宁","380");
		cityNameCodeMap.put("泉州","406");
		cityNameCodeMap.put("石家庄","428");
		cityNameCodeMap.put("汕头","447");
		cityNameCodeMap.put("沈阳","451");
		cityNameCodeMap.put("武汉","477");
		cityNameCodeMap.put("威海","479");
		cityNameCodeMap.put("徐州","512");
		cityNameCodeMap.put("烟台","533");
		cityNameCodeMap.put("义乌","536");
		cityNameCodeMap.put("郑州","559");
		cityNameCodeMap.put("台州","578");
		//以上是热门城市
		cityNameCodeMap.put("九寨沟","91");
		cityNameCodeMap.put("泰安","454");
		cityNameCodeMap.put("黄山","23");
		cityNameCodeMap.put("林芝","108");
		cityNameCodeMap.put("曲阜","143");
		cityNameCodeMap.put("泰山","145");
		cityNameCodeMap.put("济南","144");
	}
	public void productCityNameListByCode(){
		if(cityNames!=null&&cityNames.size()>5){
			return;
		}
		cityNames = null;
		cityNames = new ArrayList<String>();
		cityNames.add("北京");
		cityNames.add("上海");
		cityNames.add("天津");
		cityNames.add("重庆");
		cityNames.add("哈尔滨");
		cityNames.add("大连");
		cityNames.add("青岛");
		cityNames.add("西安");
		cityNames.add("南京");
		cityNames.add("无锡");
		cityNames.add("苏州");
		cityNames.add("杭州");
		cityNames.add("厦门");
		cityNames.add("成都");
		cityNames.add("深圳");
		cityNames.add("珠海");
		cityNames.add("广州");
		cityNames.add("昆明");
		cityNames.add("贵阳");
		cityNames.add("乌鲁木齐");
		cityNames.add("拉萨");
		cityNames.add("海口");
		cityNames.add("三亚");
		cityNames.add("香港");
		cityNames.add("银川");
		cityNames.add("兰州");
		cityNames.add("呼和浩特");
		cityNames.add("太原");
		cityNames.add("喀什市");
		cityNames.add("西宁");
		cityNames.add("包头");
		cityNames.add("海拉尔");
		cityNames.add("长春");
		cityNames.add("长沙");
		cityNames.add("常州");
		cityNames.add("东莞");
		cityNames.add("佛山");
		cityNames.add("福州");
		cityNames.add("合肥");
		cityNames.add("江门");
		cityNames.add("绵阳");
		cityNames.add("宁波");
		cityNames.add("南昌");
		cityNames.add("南宁");
		cityNames.add("泉州");
		cityNames.add("石家庄");
		cityNames.add("汕头");
		cityNames.add("沈阳");
		cityNames.add("武汉");
		cityNames.add("威海");
		cityNames.add("徐州");
		cityNames.add("烟台");
		cityNames.add("义乌");
		cityNames.add("郑州");
		cityNames.add("台州");
		//以上是热门城市
		cityNames.add("九寨沟");
		cityNames.add("泰安");
		cityNames.add("黄山");
		cityNames.add("林芝");
		cityNames.add("曲阜");
		cityNames.add("济南");
		cityNames.add("泰山");
	}
}
