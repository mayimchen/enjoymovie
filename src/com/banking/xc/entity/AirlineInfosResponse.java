package com.banking.xc.entity;

import java.util.ArrayList;

public class AirlineInfosResponse {
	private String RecordCount;
	private ArrayList<AirlineInfoEntity> AirlineInfoEntityList;
	public String getRecordCount() {
		return RecordCount;
	}
	public void setRecordCount(String recordCount) {
		RecordCount = recordCount;
	}
	public ArrayList<AirlineInfoEntity> getAirlineInfoEntityList() {
		return AirlineInfoEntityList;
	}
	public void setAirlineInfoEntityList(ArrayList<AirlineInfoEntity> airlineInfoEntityList) {
		AirlineInfoEntityList = airlineInfoEntityList;
	}
	
}
