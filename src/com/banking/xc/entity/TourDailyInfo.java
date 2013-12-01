package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 团队日程
 * @author zhangyinhang
 *
 */
public class TourDailyInfo implements Serializable{
	private String segmentDesc;
	private String takeOffTime;
	private String takeTime;
	private String dailyType;
	private String description;
	private String destination;
	private String distance;
	private String driveTime;
	private String sort;//标志唯一性id
	private ArrayList<TourDailyDinnerInfo> tourDailyDinnerInfoList;
	private ArrayList<TourDailyHotelInfo> tourDailyHotelInfoList;
	private ArrayList<TourDailyScenicSportInfo> tourDailyScenicSportInfoList;//景点
	
	private String segmentDetail;
	private String picPath;
	
	private VacationSegment vacationSegment;
	public String getSegmentDesc() {
		return segmentDesc;
	}
	public void setSegmentDesc(String segmentDesc) {
		this.segmentDesc = segmentDesc;
	}
	public String getTakeOffTime() {
		return takeOffTime;
	}
	public void setTakeOffTime(String takeOffTime) {
		this.takeOffTime = takeOffTime;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public String getDailyType() {
		return dailyType;
	}
	public void setDailyType(String dailyType) {
		this.dailyType = dailyType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDriveTime() {
		return driveTime;
	}
	public void setDriveTime(String driveTime) {
		this.driveTime = driveTime;
	}
	public ArrayList<TourDailyDinnerInfo> getTourDailyDinnerInfoList() {
		return tourDailyDinnerInfoList;
	}
	public void setTourDailyDinnerInfoList(ArrayList<TourDailyDinnerInfo> tourDailyDinnerInfoList) {
		this.tourDailyDinnerInfoList = tourDailyDinnerInfoList;
	}
	public ArrayList<TourDailyHotelInfo> getTourDailyHotelInfoList() {
		return tourDailyHotelInfoList;
	}
	public void setTourDailyHotelInfoList(ArrayList<TourDailyHotelInfo> tourDailyHotelInfoList) {
		this.tourDailyHotelInfoList = tourDailyHotelInfoList;
	}
	public ArrayList<TourDailyScenicSportInfo> getTourDailyScenicSportInfoList() {
		return tourDailyScenicSportInfoList;
	}
	public void setTourDailyScenicSportInfoList(ArrayList<TourDailyScenicSportInfo> tourDailyScenicSportInfoList) {
		this.tourDailyScenicSportInfoList = tourDailyScenicSportInfoList;
	}
	public String getSegmentDetail() {
		return segmentDetail;
	}
	public void setSegmentDetail(String segmentDetail) {
		this.segmentDetail = segmentDetail;
	}
	public void appendSegmentDetail(String segmentDetail) {
		if(this.segmentDetail==null){
			this.segmentDetail = segmentDetail;
		}else{
			this.segmentDetail += segmentDetail;
		}
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public VacationSegment getVacationSegment() {
		return vacationSegment;
	}
	public void setVacationSegment(VacationSegment vacationSegment) {
		this.vacationSegment = vacationSegment;
	}
	
}
