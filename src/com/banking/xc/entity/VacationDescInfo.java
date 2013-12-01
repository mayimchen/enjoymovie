package com.banking.xc.entity;

import java.util.ArrayList;

import com.banking.xc.utils.TextFilterUtil;

import android.text.TextUtils;

public class VacationDescInfo {
	private String desc;
	private ArrayList<VacationDescDetail> vacationDescDetailList;
	private String contentFromList;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<VacationDescDetail> getVacationDescDetailList() {
		return vacationDescDetailList;
	}
	public void setVacationDescDetailList(ArrayList<VacationDescDetail> vacationDescDetailList) {
		this.vacationDescDetailList = vacationDescDetailList;
	}
	public String getContentFromList() {
		return contentFromList;
	}
	public void setContentFromList(String contentFromList) {
		this.contentFromList = contentFromList;
	}
	public void transformListToContent(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<vacationDescDetailList.size();i++){
			final String rawString = vacationDescDetailList.get(i).getDetail();
			sb.append(TextFilterUtil.filterHtmlTag(rawString));
		}
		contentFromList = sb.toString();
	}
}
