package com.banking.xc.entity;

import java.io.Serializable;
import java.util.List;

import com.banking.xc.utils.Log;


public class Hotel implements Serializable{
	
	private String hotelCode;
	private String hotelCityCode;
	private String hotelName;
	private String areaID;
	private String brandCode; // Not Important
	private String chainCode; // Not Important
	private String imageUrl ; 
	private String latitude;
	private String longitude;
	private String addressLine;
	private String cityName;
	private String postalCode;
	private String hotelStar;//酒店星级
	//private float hotelRating; //自己计算出来的评分
	private List<HotelAward> hotelAwards;
	private List<HotelRelativePosition> hotelRelativePositions;
	
	private List<GuestRoom> guestRooms;//主要是Vacation中酒店用到 
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getHotelCityCode() {
		return hotelCityCode;
	}
	public void setHotelCityCode(String hotelCityCode) {
		this.hotelCityCode = hotelCityCode;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}
	public String getBrandCode() {
		return brandCode;
	}
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	public String getChainCode() {
		return chainCode;
	}
	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public List<HotelAward> getHotelAwards() {
		return hotelAwards;
	}
	public void setHotelAwards(List<HotelAward> hotelAwards) {
		this.hotelAwards = hotelAwards;
	}
	public List<HotelRelativePosition> getHotelRelativePositions() {
		return hotelRelativePositions;
	}
	public void setHotelRelativePositions(List<HotelRelativePosition> hotelRelativePositions) {
		this.hotelRelativePositions = hotelRelativePositions;
	}
	public Float getHotelRating() {
		try{
			if(Log.D){
				for(int i=0;i<hotelAwards.size();i++){
					Log.d("", i+"getHotelRating()-->"+hotelAwards.get(i).getRating());
				}
			}
			return Float.valueOf(hotelAwards.get(0).getRating());
		}
		catch(Exception e){
			return (float) 0;
		}
		
	}
	public String getHotelStar() {
		return hotelStar;
	}
	public void setHotelStar(String hotelStar) {
		this.hotelStar = hotelStar;
	}
	public List<GuestRoom> getGuestRooms() {
		return guestRooms;
	}
	public void setGuestRooms(List<GuestRoom> guestRooms) {
		this.guestRooms = guestRooms;
	}
	
	
	
}

