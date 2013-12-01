package com.banking.xc.entity;

/**
 * 旅游可选项里面的行程
 * @author zhangyinhang
 *
 */
public class CustomerSegment {
	private String segment;
	private String departure;
	private String CheckInDate;
	private String CheckOutDate;
	private String room;
	private String item;
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getCheckInDate() {
		return CheckInDate;
	}
	public void setCheckInDate(String checkInDate) {
		CheckInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return CheckOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		CheckOutDate = checkOutDate;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
}
