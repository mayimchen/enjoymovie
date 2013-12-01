package com.banking.xc.entity;

import java.io.Serializable;
import java.util.List;

public class GuestRoom implements Serializable{
	private String roomTypeName;
	private String standardOccupancy;
	private String size;
	private String roomTypeCode;
	private String floor;
	private String bedTypeCode;
	private String quantity; //数量
	private List<String> amenityDescriptiveTexts; //这里抛弃了Amenity类
	private RatePlan ownedRatePlan;//某酒店某房间拥有的
	private String imageUrl; //从酒店所有图片中分析获取的图片url
	private String price;//从价格计划中获取到的price
	private String roomId;//其实roomTypeCode应该和roomId同一个，六位数数字
	private String personNum;
	
	public RatePlan getOwnedRatePlan() {
		return ownedRatePlan;
	}
	public void setOwnedRatePlan(RatePlan ownedRatePlan) {
		this.ownedRatePlan = ownedRatePlan;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public String getStandardOccupancy() {
		return standardOccupancy;
	}
	public void setStandardOccupancy(String standardOccupancy) {
		this.standardOccupancy = standardOccupancy;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getBedTypeCode() {
		return bedTypeCode;
	}
	public void setBedTypeCode(String bedTypeCode) {
		this.bedTypeCode = bedTypeCode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public List<String> getAmenityDescriptiveTexts() {
		return amenityDescriptiveTexts;
	}
	public void setAmenityDescriptiveTexts(List<String> amenityDescriptiveTexts) {
		this.amenityDescriptiveTexts= amenityDescriptiveTexts;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getPersonNum() {
		return personNum;
	}
	public void setPersonNum(String personNum) {
		this.personNum = personNum;
	}
	
	
}
