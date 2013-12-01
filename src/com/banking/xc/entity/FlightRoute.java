package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class FlightRoute {
	private String recordCount;
	private String orderBody;
	private String direction;
	private ArrayList<FlightData> flightDataList;
	public String getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	public String getOrderBody() {
		return orderBody;
	}
	public void setOrderBody(String orderBody) {
		this.orderBody = orderBody;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public ArrayList<FlightData> getFlightDataList() {
		return flightDataList;
	}
	public void setFlightDataList(ArrayList<FlightData> flightDataList) {
		this.flightDataList = flightDataList;
	}
	
}
