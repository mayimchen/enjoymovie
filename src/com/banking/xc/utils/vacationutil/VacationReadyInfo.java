package com.banking.xc.utils.vacationutil;

import java.util.ArrayList;

/**
 * 开始前
 * @author zhangyinhang
 *
 */
public class VacationReadyInfo {
	
	private String vacationKind;
	private String peopleCount;
	private String childCount;
	ArrayList<String> vacationInterested;
	private String targetCity;
	public String getVacationKind() {
		return vacationKind;
	}
	public void setVacationKind(String vacationKind) {
		this.vacationKind = vacationKind;
	}
	public String getPeopleCount() {
		return peopleCount;
	}
	public void setPeopleCount(String peopleCount) {
		this.peopleCount = peopleCount;
	}
	public String getChildCount() {
		return childCount;
	}
	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}
	public ArrayList<String> getVacationInterested() {
		return vacationInterested;
	}
	public void setVacationInterested(ArrayList<String> vacationInterested) {
		this.vacationInterested = vacationInterested;
	}
	public String getTargetCity() {
		return targetCity;
	}
	public void setTargetCity(String targetCity) {
		this.targetCity = targetCity;
	}
	
}
