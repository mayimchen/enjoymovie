package com.banking.xc.entity;

import java.io.Serializable;

public class TourDailyScenicSportInfo implements Serializable{
	private String customSynopsis;
	private String displayOrder;
	private String orFlag;
	private String preName;
	private String suffixName;
	private String takeTime;
	private ScenicSpot scenicSpot;
	public String getCustomSynopsis() {
		return customSynopsis;
	}
	public void setCustomSynopsis(String customSynopsis) {
		this.customSynopsis = customSynopsis;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getOrFlag() {
		return orFlag;
	}
	public void setOrFlag(String orFlag) {
		this.orFlag = orFlag;
	}
	public String getPreName() {
		return preName;
	}
	public void setPreName(String preName) {
		this.preName = preName;
	}
	public String getSuffixName() {
		return suffixName;
	}
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public ScenicSpot getScenicSpot() {
		return scenicSpot;
	}
	public void setScenicSpot(ScenicSpot scenicSpot) {
		this.scenicSpot = scenicSpot;
	}
	
}
