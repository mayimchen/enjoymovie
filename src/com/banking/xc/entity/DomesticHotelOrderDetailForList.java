package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 订单列表返回对象
 * @author zhangyinhang
 *
 */
public class DomesticHotelOrderDetailForList implements Serializable{
	private String isMaskedOrder;
	private String BreakFastQuantity;
	private String TwinBed;
	private String KingSize;
	private String RoomNameEN;
	private String HotelNameEN;
	private String CityNameEN;
	private String ClientName;
	private String LocationName;
	private String BalanceType; //预付订单（网上支付/游票支付/担保订单）、非预付订单
	private String Exchange;
	private String Currency;//币种
	private String Breakfast;
	private String LastArrivalTime;
	private String EarlyArrivalTime;
	private String CityName;
	private String LastCancelTime;
	private String CheckOutDate;
	private String CheckInDate;
	private String HotelName;
	private String OrderOverTime;
	private String OrderDate;
	private String BreakfastCount;
	/* 
	 CityID
Cost
PriceShowInfo
 HotelID
*/
	private String OrderStatus;
	private String Price;//订单总卖价
	private String OrderId;
	
	
	public String getBreakfastCount() {
		return BreakfastCount;
	}
	public void setBreakfastCount(String breakfastCount) {
		BreakfastCount = breakfastCount;
	}
	public String getLastArrivalTime() {
		return LastArrivalTime;
	}
	public void setLastArrivalTime(String lastArrivalTime) {
		LastArrivalTime = lastArrivalTime;
	}
	public String getEarlyArrivalTime() {
		return EarlyArrivalTime;
	}
	public void setEarlyArrivalTime(String earlyArrivalTime) {
		EarlyArrivalTime = earlyArrivalTime;
	}
	public String getCityName() {
		return CityName;
	}
	public void setCityName(String cityName) {
		CityName = cityName;
	}
	public String getLastCancelTime() {
		return LastCancelTime;
	}
	public void setLastCancelTime(String lastCancelTime) {
		LastCancelTime = lastCancelTime;
	}
	public String getCheckOutDate() {
		return CheckOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		CheckOutDate = checkOutDate;
	}
	public String getCheckInDate() {
		return CheckInDate;
	}
	public void setCheckInDate(String checkInDate) {
		CheckInDate = checkInDate;
	}
	public String getHotelName() {
		return HotelName;
	}
	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}
	public String getOrderOverTime() {
		return OrderOverTime;
	}
	public void setOrderOverTime(String orderOverTime) {
		OrderOverTime = orderOverTime;
	}
	public String getOrderDate() {
		return OrderDate==null?"":OrderDate;
	}
	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}
	public String getIsMaskedOrder() {
		return isMaskedOrder;
	}
	public void setIsMaskedOrder(String isMaskedOrder) {
		this.isMaskedOrder = isMaskedOrder;
	}
	public String getBreakFastQuantity() {
		return BreakFastQuantity;
	}
	public void setBreakFastQuantity(String breakFastQuantity) {
		BreakFastQuantity = breakFastQuantity;
	}
	public String getTwinBed() {
		return TwinBed;
	}
	public void setTwinBed(String twinBed) {
		TwinBed = twinBed;
	}
	public String getKingSize() {
		return KingSize;
	}
	public void setKingSize(String kingSize) {
		KingSize = kingSize;
	}
	public String getRoomNameEN() {
		return RoomNameEN;
	}
	public void setRoomNameEN(String roomNameEN) {
		RoomNameEN = roomNameEN;
	}
	public String getHotelNameEN() {
		return HotelNameEN;
	}
	public void setHotelNameEN(String hotelNameEN) {
		HotelNameEN = hotelNameEN;
	}
	public String getCityNameEN() {
		return CityNameEN;
	}
	public void setCityNameEN(String cityNameEN) {
		CityNameEN = cityNameEN;
	}
	public String getClientName() {
		return ClientName;
	}
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	public String getLocationName() {
		return LocationName;
	}
	public void setLocationName(String locationName) {
		LocationName = locationName;
	}
	public String getBalanceType() {
		return BalanceType;
	}
	public void setBalanceType(String balanceType) {
		BalanceType = balanceType;
	}
	public String getExchange() {
		return Exchange;
	}
	public void setExchange(String exchange) {
		Exchange = exchange;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getBreakfast() {
		return Breakfast;
	}
	public void setBreakfast(String breakfast) {
		Breakfast = breakfast;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
	}
	
	
	
}
