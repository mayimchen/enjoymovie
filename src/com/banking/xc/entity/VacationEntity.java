package com.banking.xc.entity;

import java.util.ArrayList;

public class VacationEntity {
	private VacationBaseInfo vacationBaseInfo;
	private ArrayList<ImageItem> imageItems;
	//private String[] threeDaysPrice; //只保存今天以内，3天信息
	private String commentCount,saleCount,Score;
	private String minPrice,minPriceMark ="",ShortDesc,PaymentType;
	private String itemMinPrice,itemMinPriceMark =""; //由于这里有特殊字符，而且多个价格计划返回的字符串一致，所以这样特殊处理
	private ArrayList<VacationMinPricePlan> vacationMinPricePlans;
	private ArrayList<VacationDescInfo> vacationDescInfoList; 
	private ArrayList<TourDailyInfo> tourDailyInfoList;//list对应“TourDailyDescInfo”。忽略掉了TourAddInfo，直接拆分成TourDaily和TourTimeAssign
	private TourTimeAssignInfo tourTimeAssignInfo; //对象对应“TourTimeAssignInfo”
	private ArrayList<VacationSegment> segments;
	public VacationBaseInfo getVacationBaseInfo() {
		return vacationBaseInfo;
	}
	public void setVacationBaseInfo(VacationBaseInfo vacationBaseInfo) {
		this.vacationBaseInfo = vacationBaseInfo;
	}
	public ArrayList<ImageItem> getImageItems() {
		return imageItems;
	}
	public void setImageItems(ArrayList<ImageItem> imageItems) {
		this.imageItems = imageItems;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	public String getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(String saleCount) {
		this.saleCount = saleCount;
	}
	public String getScore() {
		return Score;
	}
	public void setScore(String score) {
		Score = score;
	}
	public String getMinPriceMark() {
		return minPriceMark;
	}
	@Deprecated
	public void setMinPriceMark(String minPriceMark) {
		this.minPriceMark = minPriceMark;
	}
	public void addMinPriceMark(String minPriceMark) {
		this.minPriceMark += minPriceMark;
	}
	
	public ArrayList<VacationMinPricePlan> getVacationMinPricePlans() {
		return vacationMinPricePlans;
	}
	public void setVacationMinPricePlans(ArrayList<VacationMinPricePlan> vacationMinPricePlans) {
		this.vacationMinPricePlans = vacationMinPricePlans;
	}
	public String getShortDesc() {
		return ShortDesc;
	}
	public void setShortDesc(String shortDesc) {
		ShortDesc = shortDesc;
	}
	public void addShortDesc(String shortDesc) {
		ShortDesc += shortDesc;
	}
	public String getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(String paymentType) {
		PaymentType = paymentType;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getItemMinPrice() {
		return itemMinPrice;
	}
	public void setItemMinPrice(String itemMinPrice) {
		this.itemMinPrice = itemMinPrice;
	}
	public String getItemMinPriceMark() {
		return itemMinPriceMark;
	}
	@Deprecated
	public void setItemMinPriceMark(String itemMinPriceMark) {
		this.itemMinPriceMark = itemMinPriceMark;
	}
	public void addItemMinPriceMark(String itemMinPriceMark) {
		this.itemMinPriceMark += itemMinPriceMark;
	}
	public ArrayList<VacationDescInfo> getVacationDescInfoList() {
		return vacationDescInfoList;
	}
	public void setVacationDescInfoList(ArrayList<VacationDescInfo> vacationDescInfoList) {
		this.vacationDescInfoList = vacationDescInfoList;
	}
	public TourTimeAssignInfo getTourTimeAssignInfo() {
		return tourTimeAssignInfo;
	}
	public void setTourTimeAssignInfo(TourTimeAssignInfo tourTimeAssignInfo) {
		this.tourTimeAssignInfo = tourTimeAssignInfo;
	}
	public ArrayList<VacationSegment> getSegments() {
		return segments;
	}
	public void setSegments(ArrayList<VacationSegment> segments) {
		this.segments = segments;
	}
	public ArrayList<TourDailyInfo> getTourDailyInfoList() {
		return tourDailyInfoList;
	}
	public void setTourDailyInfoList(ArrayList<TourDailyInfo> tourDailyInfoList) {
		this.tourDailyInfoList = tourDailyInfoList;
	}
	
}
