package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class TourDailyHotelInfo implements Serializable{
	private String hotelAddress;
	private String hotelId;
	private ArrayList<String> hotelImageUrls;
	private String hotelName;
	private String hotelStar;
	private String sinceTime;
	private String surroundings;
	private String userComment;
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public ArrayList<String> getHotelImageUrls() {
		return hotelImageUrls;
	}
	public void setHotelImageUrls(ArrayList<String> hotelImageUrls) {
		this.hotelImageUrls = hotelImageUrls;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelStar() {
		return hotelStar;
	}
	public void setHotelStar(String hotelStar) {
		this.hotelStar = hotelStar;
	}
	public String getSinceTime() {
		return sinceTime;
	}
	public void setSinceTime(String sinceTime) {
		this.sinceTime = sinceTime;
	}
	public String getSurroundings() {
		return surroundings;
	}
	public void setSurroundings(String surroundings) {
		this.surroundings = surroundings;
	}
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	
}
