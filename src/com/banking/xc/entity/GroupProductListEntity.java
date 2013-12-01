package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 包含GroupProductHotelEntity,这是一家酒店的所有分店
 * 这个实际结果和文档有出入
 * @author zhangyinhang
 *
 */
public class GroupProductListEntity implements Serializable{
	private String oUrl;//网页地址
	private String locationID;
	private String productItemType;//如果是酒店的话;对应的是GroupProductHotelEntity
	private ArrayList<GroupProductHotelEntity> groupProductHotelEntityList;
	
	private String itemType;
	private String soldOut;//boolean型
	private String labelValue;
	private String hotelID;
	private String rate;
	private String startDate;
	private String endDate;
	private String name;
	private String description;
	private String expirationStartTime;
	private String pictures;
	private String saledItemCount;
	private String productID;
	private String productPrice;//门市价，高
	private String nowPrice;//现付价,
	private String price;//团购价,最低
	private String isGroup;//一定成团
	private String url; //这个是链接地址
	//ProductsMarket，特殊
	
	
	public String getoUrl() {
		return oUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}
	public String getLabelValue() {
		return labelValue;
	}
	public void setLabelValue(String labelValue) {
		this.labelValue = labelValue;
	}
	public String getHotelID() {
		return hotelID;
	}
	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpirationStartTime() {
		return expirationStartTime;
	}
	public void setExpirationStartTime(String expirationStartTime) {
		this.expirationStartTime = expirationStartTime;
	}
	public String getPictures() {
		return pictures;
	}
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	public String getSaledItemCount() {
		return saledItemCount;
	}
	public void setSaledItemCount(String saledItemCount) {
		this.saledItemCount = saledItemCount;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}
	public void setoUrl(String oUrl) {
		this.oUrl = oUrl;
	}
	public String getLocationID() {
		return locationID;
	}
	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}
	public String getProductItemType() {
		return productItemType;
	}
	public void setProductItemType(String productItemType) {
		this.productItemType = productItemType;
	}
	public ArrayList<GroupProductHotelEntity> getGroupProductHotelEntityList() {
		return groupProductHotelEntityList;
	}
	public void setGroupProductHotelEntityList(ArrayList<GroupProductHotelEntity> groupProductHotelEntityList) {
		this.groupProductHotelEntityList = groupProductHotelEntityList;
	}
	
	
	
}
