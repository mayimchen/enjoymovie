package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.datetime.DateTimeUtil;
import com.banking.xc.utils.staticinfo.VacationHotCityUtil;

import android.text.TextUtils;

/**
 * 旅游下单，计划核心类。
 * 酒店和机票的关联是VacationSegment，不支持比如三天在某城市选择不同酒店的房间。
 * @author zhangyinhang
 *
 */
public class VacationPlan implements Serializable{
	
	private final String TAG = "VacationPlan";
	private boolean isLocalTrip = false;
	//旅游基本信息
	//订单基本信息
	ArrayList<VacationSegment> vacationSegmentList;
	//VacationSegment gotoSegment;
	//VacationSegment gobackSegment;
	ArrayList<VacationSegment> staySegments = new ArrayList<VacationSegment>();
	ArrayList<VacationSegment> flightSegments = new ArrayList<VacationSegment>();
	
	
	//现在在使用的分割后的日程
	//ArrayList<VacationDailySegment> vacationDailySegments;
	
	//private int planPrice;
	//复杂旅程，感觉这个不靠谱
	/*VacationItem vacationFlightItem;
	int flightItemPosition;
	GuestRoom guestRoom;
	int guestRoomPosition;*/
	//旅游用户
	
	//private String startCityName;
	private String startCityId;//这个是不允许改变的
	//private String destCityName;
	private boolean isLockedCity = false;
	private boolean isSameCity = false;
	private String lockedCityName;
	
	//private int vacationPackagePrice; 
	private VacationPackage vacationPackage;
	
	/**
	 * 这里算法可能有问题，多个segment的时候
	 * 这里一次性处理了Segment中出发到达城市名字，startDestDescription问题。
	 * @param vacationSegmentList
	 */
	public void init(ArrayList<VacationSegment> vacationSegmentList,ArrayList<TourDailyInfo> tourDailyInfoList,String startCityId,VacationHotCityUtil util,int mustStayDays,String startDate){
		if(Log.D){
			Log.d(TAG, "Vacationplan init()-->startDate"+startDate);
		}
		this.vacationSegmentList = vacationSegmentList;
		//vacationDailySegments = new ArrayList<VacationDailySegment>();
		if(vacationSegmentList!=null&&vacationSegmentList.size()>0){
			for(int i=0;i<tourDailyInfoList.size();i++){
				if(Log.D){
					Log.d(TAG,"tourDailyInfoList.get"+i+tourDailyInfoList.get(i).getSegmentDesc()+" "+
							tourDailyInfoList.get(i).getPicPath()+tourDailyInfoList.get(i).getSort());
				}
			}
			if(tourDailyInfoList.size()==0){
				if(Log.D){
					Log.d(TAG,"tourDailyInfoList.size()==0");
				}
			}
			
			for(int i=0;i<vacationSegmentList.size();i++){
				final VacationSegment vacationSegment = vacationSegmentList.get(i);
				String sCity = vacationSegment.getStartCity();
				String dCity = vacationSegment.getDestCity();
				
				if(Log.D){
					Log.d(TAG, "Vacationplan init()-->出发"+sCity+"目标"+dCity);
				}
				/*if(TextUtils.equals(sCity, "0")){
					sCity = startCityId;
				}
				if(TextUtils.equals(dCity, "0")){
					dCity = startCityId;
				}*/
				
					if(i==0){
						if(TextUtils.equals(sCity, "0")){
							sCity = startCityId;
							isLockedCity = false;
							vacationSegment.setStartCity(sCity);
						}else{
							isLockedCity = true;
							lockedCityName = util.getCityNameByCityCode(sCity);
						}
						if(TextUtils.equals(startCityId, dCity)){
							isSameCity = true;
						}else{
							isSameCity = false;
						}
					}
				
					if(i==vacationSegmentList.size()-1){
						if(TextUtils.equals(dCity, "0")){
							dCity = startCityId;
						//isLockedCity = false;
							vacationSegment.setDestCity(dCity);
						}
					}
					
					
					if(i!=0&&i!=vacationSegmentList.size()-1){
						if(TextUtils.equals(sCity, "0")){
							sCity = vacationSegmentList.get(i-1).getDestCity();
							vacationSegment.setStartCity(sCity);
						}
						if(TextUtils.equals(dCity, "0")){
							dCity = vacationSegmentList.get(i+1).getStartCity();;
							vacationSegment.setDestCity(dCity);
						}
					}
				
				final String startCityName = util.getCityNameByCityCode(sCity);
				final String destCityName = util.getCityNameByCityCode(dCity);
				vacationSegment.setStartCityName(startCityName);
				vacationSegment.setDestCityName(destCityName);
				if(TextUtils.equals(sCity, dCity)){
					vacationSegment.setCityDescription(startCityName+"本地游");
				}else{
					vacationSegment.setCityDescription(startCityName+" 至  "+destCityName);
				}
				if(TextUtils.equals(sCity, dCity)){
						//TODO
					 	//其实也需要hotel的，还没加上 
						vacationSegment.setSegmentKind(VacationSegment.STAY_SEGMENT_KIND);
						
						
						for(int j=0;j<tourDailyInfoList.size();j++){
							final String desc = tourDailyInfoList.get(j).getSegmentDesc();
							if(!TextUtils.isEmpty(desc)&&desc.contains(startCityName)){
								if(desc.equals(startCityName)){ //三亚
									adapterSegmentDetail(vacationSegment,tourDailyInfoList.get(j));
								}
							}
							
						}
				}else{
						vacationSegment.setSegmentKind(VacationSegment.FLIGHT_SEGMENT_KIND);
						
						if(i==0){
							//一般
							for(int j=0;j<tourDailyInfoList.size();j++){
								final String desc = tourDailyInfoList.get(j).getSegmentDesc();
								if(!TextUtils.isEmpty(desc)&&desc.contains(destCityName)){
									if(desc.contains("抵达")){ //抵达三亚
										adapterSegmentDetail(vacationSegment,tourDailyInfoList.get(j));
										//处理这个特殊情况
										//return;
										break;
									}else{
										
									}
								}
								
							}
						}
						if(i==vacationSegmentList.size()-1){
							//一般
							for(int j=0;j<tourDailyInfoList.size();j++){
								final String desc = tourDailyInfoList.get(j).getSegmentDesc();
								if(!TextUtils.isEmpty(desc)&&desc.contains(startCityName)){ //从三亚返回
									if(desc.contains("返回")){
										adapterSegmentDetail(vacationSegment,tourDailyInfoList.get(j));
										//处理这个特殊情况
										//return;
										break;
									}else{
										
									}
								}
								
							}
						}
						for(int j=0;j<tourDailyInfoList.size();j++){
							final String desc = tourDailyInfoList.get(j).getSegmentDesc();
							if(!TextUtils.isEmpty(desc)&&desc.contains("-"+destCityName)){
								adapterSegmentDetail(vacationSegment,tourDailyInfoList.get(j));
									//处理这个特殊情况
								//return;
							}else{
								if(!TextUtils.isEmpty(desc)&&desc.contains(startCityName+"-")){
									adapterSegmentDetail(vacationSegment,tourDailyInfoList.get(j));
									//处理这个特殊情况
								//return;
								}
							}
							
						}
						
						/*final ArrayList<TourDailyInfo> tours = new ArrayList<TourDailyInfo>();
						if(tourDailyInfoList.size()>touri){
						tours.add(tourDailyInfoList.get(touri));
						vacationSegment.setTourDailyInfoList(tours);
								
						if(vacationSegment.getTheImageUrl() ==null){
							TourDailyInfo  tourDailyInfo = tourDailyInfoList.get(touri);
							vacationSegment.setTheImageUrl(tourDailyInfo.getPicPath());
							vacationSegment.setTheDetail(tourDailyInfo.getDailyType()+tourDailyInfo.getDescription()+tourDailyInfo.getSegmentDetail());
						}
								
						VacationDailySegment vsd = new VacationDailySegment();
						vsd.setTourDailyInfo(tourDailyInfoList.get(touri));
						vsd.setVacationSegment(vacationSegment);
						vacationDailySegments.add(vsd);*/
					}
				}
				initStayDays(mustStayDays,startDate);
				
			}
		}
	
	public void initStayDays(int mustStayDays,String startDate){
		int rawDaysCount = 0;
		for(int i=0;i<vacationSegmentList.size();i++){
			final VacationSegment vacationSegment = vacationSegmentList.get(i);
			if(vacationSegment.getSegmentKind()==VacationSegment.STAY_SEGMENT_KIND){
				staySegments.add(vacationSegment);
				int days = 0;
				try{
					days = Integer.parseInt(vacationSegment.getMaxStayDays());
				}catch(Exception e){
				}
				if(days<=0){
					days = 1;
				}
				vacationSegment.setStayDays(days);
				rawDaysCount += days;
			}else{
				flightSegments.add(vacationSegment);
				vacationSegment.setStayDays(1);
				rawDaysCount++;
			}
		}
		if(rawDaysCount>mustStayDays){
			//TODO
			int gap = rawDaysCount - mustStayDays;
			Random random = new Random();
			boolean doMinus = false;
			for(int i=0;i<gap;i++){
				for(int j=0;j<staySegments.size();j++){
					int days = staySegments.get(j).getStayDays();
					if(days>1){
						days --;
						staySegments.get(j).setStayDays(days);
						doMinus = true;
						continue;
					}
				}
				if(!doMinus){
					//TODO
					break;
				}
			}
		}else{
			if(rawDaysCount<mustStayDays){
				//这个是意外,但是自驾游确实可能
				vacationSegmentList.get(vacationSegmentList.size()-1).setStayDays(1+mustStayDays-rawDaysCount);
			}
		}
		String dateTemp = startDate;
		for(int i=0;i<vacationSegmentList.size();i++){
			final VacationSegment vs = vacationSegmentList.get(i);
			int stayDays = vs.getStayDays();
			int stayGap = stayDays-1;
			vs.setSegmentStartDate(dateTemp);
			dateTemp = DateTimeUtil.getNDaysAfterTheDay(stayGap,dateTemp);
			vs.setSegmentEndDate(dateTemp);
			dateTemp = DateTimeUtil.getNDaysAfterTheDay(1, dateTemp);
			if(vs.getSegmentKind()==VacationSegment.STAY_SEGMENT_KIND){
				vs.setSegmentDateDes(vs.getSegmentStartDate()+"至"+vs.getSegmentEndDate());
			}else{
				vs.setSegmentDateDes(vs.getSegmentEndDate());
			}
			
		}
		
	}
	
	public void adapterSegmentDetail(VacationSegment vacationSegment,TourDailyInfo tourDailyInfo){
		vacationSegment.addTourDailyInfo(tourDailyInfo);
		tourDailyInfo.setVacationSegment(vacationSegment);
		if(vacationSegment.getTheImageUrl()==null){
			vacationSegment.setTheImageUrl(tourDailyInfo.getPicPath());
		}
		if(vacationSegment.getTheDetail()==null){
			vacationSegment.setTheDetail(tourDailyInfo.getDailyType()+tourDailyInfo.getDescription()+tourDailyInfo.getSegmentDetail());
		}
	}
	/*public ArrayList<VacationDailySegment> getVacationDailySegments(){
		return vacationDailySegments;
	}*/
	public void adapterTourDailyInfo(ArrayList<VacationSegment> vacationSegmentList,ArrayList<TourDailyInfo> tourDailyInfoList){
		//Segmentdesc,位置，SegmentDetail,PicPath。4个重要的点
		
	}
	/**
	 * 是否该段行程是固定呆在某个城市
	 * @return
	 */
	public static boolean isStaySegment(VacationSegment segment){
		if(TextUtils.equals(segment.getStartCity(), segment.getDestCity())){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否所有行程段都有安排
	 * @return
	 */
	public boolean isWholePlan(){
		return false; 
	}
	/**
	 * 后期考虑去返程分开
	 * @param vacationItem
	 * @param position
	 */
	public void handleFlightForSegment(VacationItem vacationItem,int position){
		
		if(vacationItem == null||position>=vacationSegmentList.size()){
			return;
		}
		//VacationItem对象
		/*vacationFlightItem = vacationItem;
		flightItemPosition = position;*/
		//vacationSegmentList.get(position).setSegmentStatus(VacationSegment.SELECT_FLIGHT); 
		VacationSegment targetSegment = vacationSegmentList.get(position);
		cleanItemForSegment(targetSegment);
		
		targetSegment.setSegmentStatus(VacationSegment.SELECT_FLIGHT);
		targetSegment.setVacationItem(vacationItem);
		int allPriceInt = 0;
		
		if(!TextUtils.isEmpty(vacationItem.getItemPrice())){//getItemAllPrice()
			allPriceInt = (int)(Double.parseDouble(vacationItem.getItemPrice()));
		}
		if(Log.D){
			Log.d(TAG, "vacationItem.getItemAllPrice()"+vacationItem.getItemPrice());
			Log.d(TAG, "vacationItem.getItemAllPrice().parseDouble"+Double.parseDouble(vacationItem.getItemPrice()));
			Log.d(TAG, "vacationItem.getItemAllPrice() allpriceInt"+allPriceInt);
		}
		
		
		
		if(vacationItem.isRound()){
			targetSegment.setFlightPrice(allPriceInt/2);
			//想一下处理头尾的完整性.这样写：去程，返程
			//TODO
			VacationSegment otherSegment = getRoundTrip(targetSegment);
			if(otherSegment!=null){
				otherSegment.setSegmentStatus(VacationSegment.SELECT_FLIGHT);
				cleanItemForSegment(otherSegment);
				otherSegment.setVacationItem(vacationItem);
				otherSegment.setFlightPrice(allPriceInt/2);
			}
		}else{
			targetSegment.setFlightPrice(allPriceInt);
		}
		
		
	}
	public void cleanItemForSegment(VacationSegment vs){
		vs.setVacationItem(null);
		vs.setFlightData(null);
		vs.setSegmentStatus(VacationSegment.NONE_STATUS);
		vs.setFlightPrice(0);
	}
	
	public void clearAllFlight(){
		for(int i=0;i<flightSegments.size();i++){
			cleanItemForSegment(flightSegments.get(i));
		}
	}
	/**
	 * 获得了套餐之后，需要清空以前的机票元素
	 * @param data
	 * @param position
	 */
	public void handleSetPackage(VacationPackage vacationPackage){
		
		if(vacationPackage == null){
			return;
		}
		clearAllFlight();
		setVacationPackage(vacationPackage);
	}
	
	/**
	 * 删除套餐之后
	 * @param data
	 * @param position
	 */
	public void handleDelPackage(){
		setVacationPackage(null);
		
	}
	
	public void handleAbsoluteFlightForSegment(FlightData data,int position){
		
		if(data == null||position>=vacationSegmentList.size()){
			return;
		}
		VacationSegment targetSegment = vacationSegmentList.get(position);
		cleanItemForSegment(targetSegment);
		
		
		targetSegment.setSegmentStatus(VacationSegment.SELECT_FLIGHT);
		targetSegment.setFlightData(data);
		
		int allPriceInt = 0;
		
		if(!TextUtils.isEmpty(data.getPrice())){//getItemAllPrice()
			allPriceInt = (int)(Double.parseDouble(data.getPrice()));
		}
		if(Log.D){
			Log.d(TAG, "vacationItem.getItemAllPrice() allpriceInt"+allPriceInt);
		}
		targetSegment.setFlightPrice(allPriceInt);
		
	}
	public VacationSegment getRoundTrip(VacationSegment segment){
		String city1 = segment.getStartCity();
		String city2 = segment.getDestCity();
		for(int i=0;i<vacationSegmentList.size();i++){
			final VacationSegment vs = vacationSegmentList.get(i);
			if(Log.D){
				Log.d(TAG,"i"+i+"vs.getDestCity(),city1"+vs.getDestCity()+city1);
				Log.d(TAG,"i"+i+"vs.getStartCity(),city2"+vs.getStartCity()+city2);
			}
			if(vs.getSegmentKind()==VacationSegment.STAY_SEGMENT_KIND){
				continue;
			}
			//91 1 1 91  91错  1  91 91
			if(TextUtils.equals(vs.getDestCity(),city1)&&TextUtils.equals(vs.getStartCity(),city2)){
				return vs;
			}
		}
		return null;
	}
	public void handleGuestroomForSegment(GuestRoom guestroom,int position){
		if(Log.D){
			Log.d(TAG, "handleGuestroomForSegment()");
		}
		if(guestroom == null||position>=vacationSegmentList.size()){
			return;
		}
		
		VacationSegment targetSegment = vacationSegmentList.get(position);
		targetSegment.setSegmentStatus(VacationSegment.SELECT_HOTEL);
		targetSegment.setGuestRoom(guestroom);
		//TODO
		int priceInt = 0;
		final String price = guestroom.getPrice();
		if(!TextUtils.isEmpty(price)){
			priceInt = (int) Double.parseDouble(price);
		}
		targetSegment.setRoomPrice(priceInt);
		//guestRoom对象
		/*guestRoom = guestroom;
		guestRoomPosition = position;*/
		//vacationSegmentList.get(position).setSegmentStatus(VacationSegment.SELECT_HOTEL);
	}
	public void handleDelItem(int position){
		
		VacationSegment targetSegment = vacationSegmentList.get(position);
		targetSegment.setSegmentStatus(VacationSegment.NONE_STATUS);
		if(targetSegment.getSegmentKind()==VacationSegment.STAY_SEGMENT_KIND){
			targetSegment.setGuestRoom(null);
			targetSegment.setRoomPrice(0);
		}else{
			targetSegment.setFlightData(null);
			targetSegment.setVacationItem(null);
			targetSegment.setFlightPrice(0);
		}
		
		//guestRoom对象
		/*guestRoom = guestroom;
		guestRoomPosition = position;*/
		//vacationSegmentList.get(position).setSegmentStatus(VacationSegment.SELECT_HOTEL);
	}
	/*public VacationItem getFligtItem(){
		return vacationFlightItem;
	}
	public GuestRoom getGuestRoom(){
		return guestRoom;
	}*/
	public ArrayList<VacationSegment> getVacationSegmentList() {
		return vacationSegmentList;
	}

	public boolean isLocalTrip() {
		return isLocalTrip;
	}

	public void setLocalTrip(boolean isLocalTrip) {
		this.isLocalTrip = isLocalTrip;
	}

	public int getPlanPrice() {
		int allPrice = 0;
		for(int i =0;i<vacationSegmentList.size();i++){
			allPrice += vacationSegmentList.get(i).getSingleSegmentPrice();
		}
		int packagePrice = getVacationPackagePrice();
		allPrice += packagePrice;
		if(Log.D){
			Log.d("","");
		}
		return allPrice;
	}
	public String getStartCityId() {
		return startCityId;
	}
	public void setStartCityId(String startCityId) {
		this.startCityId = startCityId;
	}

	public boolean isLockedCity() {
		return isLockedCity;
	}

	public void setLockedCity(boolean isLockedCity) {
		this.isLockedCity = isLockedCity;
	}

	public boolean isSameCity() {
		return isSameCity;
	}

	public void setSameCity(boolean isSameCity) {
		this.isSameCity = isSameCity;
	}

	public String getLockedCityName() {
		return lockedCityName;
	}

	public void setLockedCityName(String lockedCityName) {
		this.lockedCityName = lockedCityName;
	}

	public int getVacationPackagePrice() {
		if(vacationPackage == null){
			return 0;
		}
		final String price = vacationPackage.getItemAllPrice();
		int priceInt = 0;
		if(!TextUtils.isEmpty(price)){
			try{
				priceInt = (int) Double.parseDouble(price);
			}catch(Exception e){
				return 0;
			}
		}
		return priceInt;
	}

	public VacationPackage getVacationPackage() {
		return vacationPackage;
	}

	public void setVacationPackage(VacationPackage vacationPackage) {
		this.vacationPackage = vacationPackage;
	}

	
	
	
}
