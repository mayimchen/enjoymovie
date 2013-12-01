package com.banking.xc.entity;

import java.util.ArrayList;

public class CraftInfosResponse {
	ArrayList<CraftInfoEntity> craftInfoEntityList;
	private String recordCount;
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public ArrayList<CraftInfoEntity> getCraftInfoEntityList() {
		return craftInfoEntityList;
	}
	public void setCraftInfoEntityList(ArrayList<CraftInfoEntity> craftInfoEntityList) {
		this.craftInfoEntityList = craftInfoEntityList;
	}
	
}
