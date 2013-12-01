package com.banking.xc.entity;

import java.io.Serializable;

public class HotelRelativePosition implements Serializable{
	private String distance;
	private String unitOfMeasureCode;
	private String name;
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getUnitOfMeasureCode() {
		return unitOfMeasureCode;
	}
	public void setUnitOfMeasureCode(String unitOfMeasureCode) {
		this.unitOfMeasureCode = unitOfMeasureCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
