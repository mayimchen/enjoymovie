package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class VacationSegment implements Serializable{
	
	
	//public static final int GOTO_FLIGHT_SEGMENT_KIND = 8;
	//public static final int GOBACK_FLIGHT_SEGMENT_KIND = 9;
	public static final int FLIGHT_SEGMENT_KIND = 10;//泛指的flightSegmentKind
	public static final int STAY_SEGMENT_KIND = 11;
	
	public static final int NONE_STATUS = 21;
	public static final int SELECT_FLIGHT = 22;
	public static final int SELECT_HOTEL = 23;
	
	private String segment;
	private String startCity;
	private String destCity;
	private String startCityType;
	private String destCityType;
	private String startDistrictName;
	private String destDistrictName;
	private String departureDays;
	private String departureAdjust;
	private String checkOutAdjust;
	private String flightTag;//是否航班
	private String includingChioce;//是否包含单选项
	private String includingSysFlight;//是否包含系统机票
	private String includingSysHotel;//是否包含系统酒店
	private String siGruop;//是否成团
	private String maxStayDays;//最大住宿
	private String minLdgingDays;//停留晚数
	private String minStayDays;//最小住宿
	private String pkgAirfareEarliesTime;
	private String pkgAirfareLatestTime;
	
	private String departureDaysString;
	
	
	
	//private String 
	//状态判断
	private int segmentKind;
	private int segmentStatus = NONE_STATUS;//默认是none
	private VacationItem vacationItem;//可以根据VacatioItem中list两个元素和两个Flight_KIND区分是往还是返
	private GuestRoom guestRoom; 
	private FlightData flightData;
	private int roomCounts = 1;//暂时几天内不支持多个房间选择
	//maxStayDays很重要，staykind的旅馆
	private int flightPrice;//单程机票价格，如果去返程那么/2。应该是一个人
	private int roomPrice;//房间价格，应该是一间房子一天。
	//private int singleSegmentPrice;
	//相互持有引用，VacationSegment持有TourDailyInfo的引用
	
	ArrayList<TourDailyInfo> tourDailyInfoList;
	//这个没有作用
	//ArrayList<VacationDailySegment> tourDailySegmentList;
	//如果是flightkind，元素个数=1，stayKind应该=maxStayDays
	
	private String startCityName;
	private String destCityName;
	private String cityDescription;
	
	//再次改版后的效果
	private String theImageUrl;
	private String theDetail;
	private int stayDays = 0;
	//这里不考虑这么细，如果是flightKind，那么stayDays = 1;
	//先按照最大计算
	private String segmentStartDate;
	private String segmentEndDate;
	private String segmentDateDes;
	
	public void transformCicyIdToString(){
		
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getStartCity() {
		return startCity;
	}
	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	public String getStartCityType() {
		return startCityType;
	}
	public void setStartCityType(String startCityType) {
		this.startCityType = startCityType;
	}
	public String getDestCityType() {
		return destCityType;
	}
	public void setDestCityType(String destCityType) {
		this.destCityType = destCityType;
	}
	public String getStartDistrictName() {
		return startDistrictName;
	}
	public void setStartDistrictName(String startDistrictName) {
		this.startDistrictName = startDistrictName;
	}
	public String getDestDistrictName() {
		return destDistrictName;
	}
	public void setDestDistrictName(String destDistrictName) {
		this.destDistrictName = destDistrictName;
	}
	public String getDepartureDays() {
		return departureDays;
	}
	public void setDepartureDays(String departureDays) {
		this.departureDays = departureDays;
	}
	public String getDepartureAdjust() {
		return departureAdjust;
	}
	public void setDepartureAdjust(String departureAdjust) {
		this.departureAdjust = departureAdjust;
	}
	public String getCheckOutAdjust() {
		return checkOutAdjust;
	}
	public void setCheckOutAdjust(String checkOutAdjust) {
		this.checkOutAdjust = checkOutAdjust;
	}
	public String getFlightTag() {
		return flightTag;
	}
	public void setFlightTag(String flightTag) {
		this.flightTag = flightTag;
	}
	public String getIncludingChioce() {
		return includingChioce;
	}
	public void setIncludingChioce(String includingChioce) {
		this.includingChioce = includingChioce;
	}
	public String getIncludingSysFlight() {
		return includingSysFlight;
	}
	public void setIncludingSysFlight(String includingSysFlight) {
		this.includingSysFlight = includingSysFlight;
	}
	public String getInclidingSysHotel() {
		return includingSysHotel;
	}
	public void setIncludingSysHotel(String inclidingSysHotel) {
		this.includingSysHotel = inclidingSysHotel;
	}
	public String getSiGruop() {
		return siGruop;
	}
	public void setSiGruop(String siGruop) {
		this.siGruop = siGruop;
	}
	public String getMaxStayDays() {
		return maxStayDays;
	}
	public void setMaxStayDays(String maxStayDays) {
		this.maxStayDays = maxStayDays;
	}
	public String getMinLdgingDays() {
		return minLdgingDays;
	}
	public void setMinLdgingDays(String minLdgingDays) {
		this.minLdgingDays = minLdgingDays;
	}
	public String getMinStayDays() {
		return minStayDays;
	}
	public void setMinStayDays(String minStayDays) {
		this.minStayDays = minStayDays;
	}
	public String getPkgAirfareEarliesTime() {
		return pkgAirfareEarliesTime;
	}
	public void setPkgAirfareEarliesTime(String pkgAirfareEarliesTime) {
		this.pkgAirfareEarliesTime = pkgAirfareEarliesTime;
	}
	public String getPkgAirfareLatestTime() {
		return pkgAirfareLatestTime;
	}
	public void setPkgAirfareLatestTime(String pkgAirfareLatestTime) {
		this.pkgAirfareLatestTime = pkgAirfareLatestTime;
	}
	public String getStartCityName() {
		return startCityName;
	}
	public void setStartCityName(String startCityName) {
		this.startCityName = startCityName;
	}
	public String getDestCityName() {
		return destCityName;
	}
	public void setDestCityName(String destCityName) {
		this.destCityName = destCityName;
	}
	public String getDepartureDaysString() {
		return departureDaysString;
	}
	public void setDepartureDaysString(String departureDaysString) {
		this.departureDaysString = departureDaysString;
	}
	public String getIncludingSysHotel() {
		return includingSysHotel;
	}
	public int getSegmentKind() {
		return segmentKind;
	}
	public void setSegmentKind(int segmentKind) {
		this.segmentKind = segmentKind;
	}
	public int getSegmentStatus() {
		return segmentStatus;
	}
	public void setSegmentStatus(int segmentStatus) {
		this.segmentStatus = segmentStatus;
	}
	public VacationItem getVacationItem() {
		return vacationItem;
	}
	public void setVacationItem(VacationItem vacationItem) {
		this.vacationItem = vacationItem;
	}
	public GuestRoom getGuestRoom() {
		return guestRoom;
	}
	public void setGuestRoom(GuestRoom guestRoom) {
		this.guestRoom = guestRoom;
	}
	public int getRoomCounts() {
		return roomCounts;
	}
	public void setRoomCounts(int roomCounts) {
		this.roomCounts = roomCounts;
	}
	
	public boolean isStaySegment(){
		if(this.segmentKind==STAY_SEGMENT_KIND){
			return true;
		}
		return false;
	}
	/*public ArrayList<TourDailyInfo> getTourDailyInfoList() {
		return tourDailyInfoList;
	}
	public void setTourDailyInfoList(ArrayList<TourDailyInfo> tourDailyInfoList) {
		this.tourDailyInfoList = tourDailyInfoList;
	}*/
	public int getFlightPrice() {
		return flightPrice;
	}
	public void setFlightPrice(int flightPrice) {
		this.flightPrice = flightPrice;
	}
	public int getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	public int getSingleSegmentPrice() {
		if(getSegmentKind()==STAY_SEGMENT_KIND){
			return roomPrice;
		}else{
			return flightPrice;
		}
	}
	public String getCityDescription() {
		return cityDescription;
	}
	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}
	public String getTheImageUrl() {
		return theImageUrl;
	}
	public void setTheImageUrl(String theImageUrl) {
		this.theImageUrl = theImageUrl;
	}
	public String getTheDetail() {
		return theDetail;
	}
	public void setTheDetail(String theDetail) {
		this.theDetail = theDetail;
	}
	public ArrayList<TourDailyInfo> getTourDailyInfoList() {
		return tourDailyInfoList;
	}
	public void setTourDailyInfoList(ArrayList<TourDailyInfo> tourDailyInfoList) {
		this.tourDailyInfoList = tourDailyInfoList;
	}

	public void addTourDailyInfo(TourDailyInfo info){
		if(tourDailyInfoList==null){
			tourDailyInfoList = new ArrayList<TourDailyInfo>();
		}
		tourDailyInfoList.add(info);
	}
	public FlightData getFlightData() {
		return flightData;
	}
	public void setFlightData(FlightData flightData) {
		this.flightData = flightData;
	}
	public int getStayDays() {
		return stayDays;
	}
	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}
	public String getSegmentStartDate() {
		return segmentStartDate;
	}
	public void setSegmentStartDate(String segmentStartDate) {
		this.segmentStartDate = segmentStartDate;
	}
	public String getSegmentDateDes() {
		return segmentDateDes;
	}
	public void setSegmentDateDes(String segmentDateDes) {
		this.segmentDateDes = segmentDateDes;
	}
	public String getSegmentEndDate() {
		return segmentEndDate;
	}
	public void setSegmentEndDate(String segmentEndDate) {
		this.segmentEndDate = segmentEndDate;
	}
	
}
