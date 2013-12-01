package com.banking.xc.entity;

import java.util.ArrayList;

public class AirportInfosResponse {
	private String RecordCount;
	private ArrayList<AirportInfoEntity> AirportInfoEntityList;
	public String getRecordCount() {
		return RecordCount;
	}
	public void setRecordCount(String recordCount) {
		RecordCount = recordCount;
	}
	public ArrayList<AirportInfoEntity> getAirportInfoEntityList() {
		return AirportInfoEntityList;
	}
	public void setAirportInfoEntityList(ArrayList<AirportInfoEntity> airportInfoEntityList) {
		AirportInfoEntityList = airportInfoEntityList;
	}
	
}
