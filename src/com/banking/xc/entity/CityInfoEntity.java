package com.banking.xc.entity;

public class CityInfoEntity {
	private String cityCode;
	private String cityId;
	private String cityName;
	private String airPort;
	
	
	
	private String cityNameEn;
	private String provinceId;
	private String countryId;
	private String countryCNName;
	private String IsDCity;//出发
	private String IsACity;//到达
	private String IsTCity;//送票
	//可以直接根据城市id来判断这个...但是要一次网络请求
	private String IsDomesticCity;//是否国内城市
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityNameEn() {
		return cityNameEn;
	}
	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryCNName() {
		return countryCNName;
	}
	public void setCountryCNName(String countryCNName) {
		this.countryCNName = countryCNName;
	}
	public String getIsDCity() {
		return IsDCity;
	}
	public void setIsDCity(String isDCity) {
		IsDCity = isDCity;
	}
	public String getIsACity() {
		return IsACity;
	}
	public void setIsACity(String isACity) {
		IsACity = isACity;
	}
	public String getIsTCity() {
		return IsTCity;
	}
	public void setIsTCity(String isTCity) {
		IsTCity = isTCity;
	}
	public String getIsDomesticCity() {
		return IsDomesticCity;
	}
	public void setIsDomesticCity(String isDomesticCity) {
		IsDomesticCity = isDomesticCity;
	}
	public String getAirPort() {
		return airPort;
	}
	public void setAirPort(String airPort) {
		this.airPort = airPort;
	}
	
}
