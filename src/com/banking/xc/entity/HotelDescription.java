package com.banking.xc.entity;

import java.util.List;

/**
 * 酒店详述
 * @author zhangyinhang
 *
 */
public class HotelDescription {
	
	private Hotel hotel;
	private String buildTime;
	private String segmentCategoryCode; //默认先不用bean存储
	private List<RefPoint> refPoints;
	private List<ImageItem> imageItems;
	private List<TextItem> textItems;
	private List<HotelService> services;
	private List<GuestRoom> guestRooms;
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getSegmentCategoryCode() {
		return segmentCategoryCode;
	}

	public void setSegmentCategoryCode(String segmentCategoryCode) {
		this.segmentCategoryCode = segmentCategoryCode;
	}

	public List<RefPoint> getRefPoints() {
		return refPoints;
	}

	public void setRefPoints(List<RefPoint> refPoints) {
		this.refPoints = refPoints;
	}

	public List<ImageItem> getImageItems() {
		return imageItems;
	}

	public void setImageItems(List<ImageItem> imageItems) {
		this.imageItems = imageItems;
	}

	public List<TextItem> getTextItems() {
		return textItems;
	}

	public void setTextItems(List<TextItem> textItems) {
		this.textItems = textItems;
	}

	public HotelDescription(Hotel hotel){
		this.hotel = hotel;
	}
	public HotelDescription(){
		
	}

	public List<HotelService> getServices() {
		return services;
	}

	public void setServices(List<HotelService> services) {
		this.services = services;
	}

	public List<GuestRoom> getGuestRooms() {
		return guestRooms;
	}

	public void setGuestRooms(List<GuestRoom> guestRooms) {
		this.guestRooms = guestRooms;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	
}
