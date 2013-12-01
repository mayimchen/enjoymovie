package com.banking.xc.entity;

public class VacationCity {
	private String cityNumber;
	private String startCityName;
	private String isHotCity; //boolean类型
	public String getCityNumber() {
		return cityNumber;
	}
	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}
	public String getStartCityName() {
		return startCityName;
	}
	public void setStartCityName(String startCityName) {
		this.startCityName = startCityName;
	}
	public String getIsHotCity() {
		return isHotCity;
	}
	public void setIsHotCity(String isHotCity) {
		this.isHotCity = isHotCity;
	}
	
}
