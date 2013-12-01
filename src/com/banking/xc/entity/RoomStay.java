package com.banking.xc.entity;
/**
 * HotelAvail关联对象
 * @author zhangyinhang
 *
 */
public class RoomStay {
	
	private String availabilityStatus;//"AvailableForSale"
	private String roomType;
	private String roomTypeCode;
	private String roomDescription;
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomTypeCode() {
		return roomTypeCode;
	}
	public void setRoomTypeCode(String roomTypeCode) {
		this.roomTypeCode = roomTypeCode;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	
	
}
