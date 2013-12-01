package com.banking.xc.entity;

import java.util.ArrayList;

public class CityInfosResponse {
	ArrayList<CityInfoEntity> cityInfoEntiyList;
	private String recordCount;

	public ArrayList<CityInfoEntity> getCityInfoEntiyList() {
		return cityInfoEntiyList;
	}

	public void setCityInfoEntiyList(ArrayList<CityInfoEntity> cityInfoEntiyList) {
		this.cityInfoEntiyList = cityInfoEntiyList;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

}