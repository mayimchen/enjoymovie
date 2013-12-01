package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 对应一个TourDailyInfo
 * 废弃类
 * @author zhangyinhang
 *
 */
@Deprecated
public class VacationDailySegment implements Serializable{
	
	//可能多个同时对应一个,//但VacationDailySegment一个只能最多对应一个VacationSegment
	private VacationSegment vacationSegment;
	private TourDailyInfo tourDailyInfo;
	
	private String segmentTitle;//这种模式没用了
	private String departDays;
	
	//所有的酒店和机票现在都是关联于其对应的VacationSegment的,包括价格
	
	public String getSegmentTitle() {
		return segmentTitle;
	}
	public void setSegmentTitle(String segmentTitle) {
		this.segmentTitle = segmentTitle;
	}
	public String getPicUrl() {
		if(tourDailyInfo==null){
			return "";
		}else{
			return tourDailyInfo.getPicPath();
		}
	}
	
	public String getDepartDays() {
		return departDays;
	}
	public void setDepartDays(String departDays) {
		this.departDays = departDays;
	}
	public String getDetail() {
		if(tourDailyInfo==null){
			return "";
		}else{
			final String result = tourDailyInfo.getDailyType()+tourDailyInfo.getDescription()+tourDailyInfo.getSegmentDetail();
			return result;
		}
	}
	public VacationSegment getVacationSegment() {
		return vacationSegment;
	}
	public void setVacationSegment(VacationSegment vacationSegment) {
		this.vacationSegment = vacationSegment;
	}
	public TourDailyInfo getTourDailyInfo() {
		return tourDailyInfo;
	}
	public void setTourDailyInfo(TourDailyInfo tourDailyInfo) {
		this.tourDailyInfo = tourDailyInfo;
	}
	
	
}
