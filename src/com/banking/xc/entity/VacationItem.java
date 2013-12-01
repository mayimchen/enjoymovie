package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class VacationItem implements Serializable{
	private String ACityName;
	private String advanceDays;
	private String arrivalDate;//这个就是出发日期Departure
	private String checkDate;//也是出发日期
	private String BargainPrice;
	private String ChoiceDesc;
	private String dCityName;
	private String departureDate;
	private String departureDays;
	private String isFlight;//一般是true
	private String isInternational;
	private String FlightMultiplePriceType;
	private String isSavePrice;
	private String itemAllPrice;
	private String itemPrice;
	private String itemDesc;
	private ArrayList<VacationItemSub> vacationItemSubList;//一般两个元素，去程和返程
	private String itemName;
	
	private String airLineShortName;
	private String flight;
	/*private String arriveDateTime;
	private String departureDateTime;*/
	private String refNote;
	private String rerNote;
	private String arriveTime;
	private String departureTime;
	private String airPort;
	
	
	//一下是adapter需要用的
	private String gotoDAirport;
	private String gotoAAirport;
	private String gotoDTime;
	private String gotoATime;
	private String gotoSubClassName;
	private String gobackDAirport;
	private String gobackAAirport;
	private String gobackDTime;
	private String gobackATime; 
	private String gobackSubClassName;
	
	//新加入,并且改变time为去掉日期格式
	private String gotoDate;
	private String gobackDate;
	
	private String itemTypeName;
	
	private boolean isRound;//为了区分类型添加
	
	//为了单程机票添加
	//private String airportLineCompant;//中国国航CA1203
	private String desc;//refNote,Rernote
	//price,time2个,出发机场
	
	
	public String getACityName() {
		return ACityName;
	}
	public void setACityName(String aCityName) {
		ACityName = aCityName;
	}
	public String getAdvanceDays() {
		return advanceDays;
	}
	public void setAdvanceDays(String advanceDays) {
		this.advanceDays = advanceDays;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getdCityName() {
		return dCityName;
	}
	public void setdCityName(String dCityName) {
		this.dCityName = dCityName;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureDays() {
		return departureDays;
	}
	public void setDepartureDays(String departureDays) {
		this.departureDays = departureDays;
	}
	public String getIsFlight() {
		return isFlight;
	}
	public void setIsFlight(String isFlight) {
		this.isFlight = isFlight;
	}
	public String getIsInternational() {
		return isInternational;
	}
	public void setIsInternational(String isInternational) {
		this.isInternational = isInternational;
	}
	public String getIsSavePrice() {
		return isSavePrice;
	}
	public void setIsSavePrice(String isSavePrice) {
		this.isSavePrice = isSavePrice;
	}
	public String getItemAllPrice() {
		return itemAllPrice;
	}
	public void setItemAllPrice(String itemAllPrice) {
		this.itemAllPrice = itemAllPrice;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public ArrayList<VacationItemSub> getVacationItemSubList() {
		return vacationItemSubList;
	}
	public void setVacationItemSubList(ArrayList<VacationItemSub> vacationItemSubList) {
		this.vacationItemSubList = vacationItemSubList;
	}
	public String getBargainPrice() {
		return BargainPrice;
	}
	public void setBargainPrice(String bargainPrice) {
		BargainPrice = bargainPrice;
	}
	public String getChoiceDesc() {
		return ChoiceDesc;
	}
	public void setChoiceDesc(String choiceDesc) {
		ChoiceDesc = choiceDesc;
	}
	public String getFlightMultiplePriceType() {
		return FlightMultiplePriceType;
	}
	public void setFlightMultiplePriceType(String flightMultiplePriceType) {
		FlightMultiplePriceType = flightMultiplePriceType;
	}
	public String getGotoDAirport() {
		return gotoDAirport;
	}
	public void setGotoDAirport(String gotoDAirport) {
		this.gotoDAirport = gotoDAirport;
	}
	public String getGotoAAirport() {
		return gotoAAirport;
	}
	public void setGotoAAirport(String gotoAAirport) {
		this.gotoAAirport = gotoAAirport;
	}
	public String getGotoDTime() {
		return gotoDTime;
	}
	public void setGotoDTime(String gotoDTime) {
		this.gotoDTime = gotoDTime;
	}
	public String getGotoATime() {
		return gotoATime;
	}
	public void setGotoATime(String gotoATime) {
		this.gotoATime = gotoATime;
	}
	public String getGobackDAirport() {
		return gobackDAirport;
	}
	public void setGobackDAirport(String gobackDAirport) {
		this.gobackDAirport = gobackDAirport;
	}
	public String getGobackAAirport() {
		return gobackAAirport;
	}
	public void setGobackAAirport(String gobackAAirport) {
		this.gobackAAirport = gobackAAirport;
	}
	public String getGobackDTime() {
		return gobackDTime;
	}
	public void setGobackDTime(String gobackDTime) {
		this.gobackDTime = gobackDTime;
	}
	public String getGobackATime() {
		return gobackATime;
	}
	public void setGobackATime(String gobackATime) {
		this.gobackATime = gobackATime;
	}
	public String getGotoSubCalssName() {
		return gotoSubClassName;
	}
	public void setGotoSubClassName(String gotoSubClassName) {
		this.gotoSubClassName = gotoSubClassName;
	}
	public String getGobackSubClassName() {
		return gobackSubClassName;
	}
	public void setGobackSubClassName(String gobackSubClassName) {
		this.gobackSubClassName = gobackSubClassName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getGotoSubClassName() {
		return gotoSubClassName;
	}
	public String getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getGotoDate() {
		return gotoDate;
	}
	public void setGotoDate(String gotoDate) {
		this.gotoDate = gotoDate;
	}
	public String getGobackDate() {
		return gobackDate;
	}
	public void setGobackDate(String gobackDate) {
		this.gobackDate = gobackDate;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public boolean isRound() {
		return isRound;
	}
	public void setIsRound(boolean isRound) {
		this.isRound = isRound;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAirLineShortName() {
		return airLineShortName;
	}
	public void setAirLineShortName(String airLineShortName) {
		this.airLineShortName = airLineShortName;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getRefNote() {
		return refNote;
	}
	public void setRefNote(String refNote) {
		this.refNote = refNote;
	}
	public String getRerNote() {
		return rerNote;
	}
	public void setRerNote(String rerNote) {
		this.rerNote = rerNote;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getAirPort() {
		return airPort;
	}
	public void setAirPort(String airPort) {
		this.airPort = airPort;
	}
	
	
	
}
