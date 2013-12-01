package com.banking.xc.entity;

import java.io.Serializable;

public class VacationBaseInfo implements Serializable{
	private String Pkg;
	private String PkgName;
	private String PkgTourGrade;
	private String PriceDownTag;
	private String IsAffirmTour;
	private String TableType;
	private String Attraction;
	private String MinPrice;
	private String SetOffDays;
	private String TypeDesc;
	private String StartCity;
	private String DestCity;
	private String PkgURL;
	private String CharacteristicDesc;
	private String ListPrice;
	private String MaxDays;
	private String MinDays;
	private String MaxEMoney;
	private String MinPerson;
	private String Img;
	private String Festival;
	private String Attrib1;
	//PackageDistrictInfo节点
	private String District;
	private String Star;
	
	public String getPkg() {
		return Pkg;
	}
	public void setPkg(String pkg) {
		Pkg = pkg;
	}
	public String getPkgName() {
		return PkgName;
	}
	public void setPkgName(String pkgName) {
		PkgName = pkgName;
	}
	public String getPkgTourGrade() {
		return PkgTourGrade;
	}
	public void setPkgTourGrade(String pkgTourGrade) {
		PkgTourGrade = pkgTourGrade;
	}
	public String getPriceDownTag() {
		return PriceDownTag;
	}
	public void setPriceDownTag(String priceDownTag) {
		PriceDownTag = priceDownTag;
	}
	public String getIsAffirmTour() {
		return IsAffirmTour;
	}
	public void setIsAffirmTour(String isAffirmTour) {
		IsAffirmTour = isAffirmTour;
	}
	public String getTableType() {
		return TableType;
	}
	public void setTableType(String tableType) {
		TableType = tableType;
	}
	public String getAttraction() {
		return Attraction;
	}
	public void setAttraction(String attraction) {
		Attraction = attraction;
	}
	public void addAttraction(String attraction) {
		Attraction += attraction;
	}
	public String getMinPrice() {
		return MinPrice;
	}
	public void setMinPrice(String minPrice) {
		MinPrice = minPrice;
	}
	public String getSetOffDays() {
		return SetOffDays;
	}
	public void setSetOffDays(String setOffDays) {
		SetOffDays = setOffDays;
	}
	public String getTypeDesc() {
		return TypeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		TypeDesc = typeDesc;
	}
	public String getStartCity() {
		return StartCity;
	}
	public void setStartCity(String startCity) {
		StartCity = startCity;
	}
	public String getDestCity() {
		return DestCity;
	}
	public void setDestCity(String destCity) {
		DestCity = destCity;
	}
	public String getPkgURL() {
		return PkgURL;
	}
	public void setPkgURL(String pkgURL) {
		PkgURL = pkgURL;
	}
	public String getCharacteristicDesc() {
		return CharacteristicDesc;
	}
	public void setCharacteristicDesc(String characteristicDesc) {
		CharacteristicDesc = characteristicDesc;
	}
	public String getListPrice() {
		return ListPrice;
	}
	public void setListPrice(String listPrice) {
		ListPrice = listPrice;
	}
	public String getMaxDays() {
		return MaxDays;
	}
	public void setMaxDays(String maxDays) {
		MaxDays = maxDays;
	}
	public String getMinDays() {
		return MinDays;
	}
	public void setMinDays(String minDays) {
		MinDays = minDays;
	}
	public String getMaxEMoney() {
		return MaxEMoney;
	}
	public void setMaxEMoney(String maxEMoney) {
		MaxEMoney = maxEMoney;
	}
	public String getMinPerson() {
		return MinPerson;
	}
	public void setMinPerson(String minPerson) {
		MinPerson = minPerson;
	}
	public String getImg() {
		return Img;
	}
	public void setImg(String img) {
		Img = img;
	}
	public String getFestival() {
		return Festival;
	}
	public void setFestival(String festival) {
		Festival = festival;
	}
	public String getAttrib1() {
		return Attrib1;
	}
	public void setAttrib1(String attrib1) {
		Attrib1 = attrib1;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getStar() {
		return Star;
	}
	public void setStar(String star) {
		Star = star;
	}
	
}
