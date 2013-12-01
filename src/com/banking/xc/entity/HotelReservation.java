package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class HotelReservation implements Serializable{
	private String createDateTime;
	private String resStatus;
	private String ratePlanCode;
	private String hotelCode;
	private String guaranteeCode; //N,Y
	private String cancelPenalty;//没有担保，就没有惩罚金
	private String amountAfterTax;//
	private String currencyCodeTotalCode;
	private String resIDType;
	private String resIDValue;
	
	//详情查询Response
	String creatorID;
	String lastModifyDateTime;
	String lastModifierID;
	//String resStatus;
	ArrayList<UniqueID> uinqueIDList;
	String addressLine;
	String cityName;
	String postalCode;
	ArrayList<ContactNumber> contactNumberList;
	Customer customer;
	String arrivalTime;
	String lateArrivalTime;
	String guestCount;
	String isPerRoom; // boolean
	String specialText;
	String amountBeforeTax;
	//保证金，罚金，暂时不处理
	
	public String getCreateDateTime() {
		return createDateTime;
	}
	public String getAmountBeforeTax() {
		return amountBeforeTax;
	}
	public void setAmountBeforeTax(String amountBeforeTax) {
		this.amountBeforeTax = amountBeforeTax;
	}
	public String getCreatorID() {
		return creatorID;
	}
	public void setCreatorID(String creatorID) {
		this.creatorID = creatorID;
	}
	public String getLastModifyDateTime() {
		return lastModifyDateTime;
	}
	public void setLastModifyDateTime(String lastModifyDateTime) {
		this.lastModifyDateTime = lastModifyDateTime;
	}
	public String getLastModifierID() {
		return lastModifierID;
	}
	public void setLastModifierID(String lastModifierID) {
		this.lastModifierID = lastModifierID;
	}
	public ArrayList<UniqueID> getUinqueIDList() {
		return uinqueIDList;
	}
	public void setUinqueIDList(ArrayList<UniqueID> uinqueIDList) {
		this.uinqueIDList = uinqueIDList;
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
	public ArrayList<ContactNumber> getContactNumberList() {
		return contactNumberList;
	}
	public void setContactNumberList(ArrayList<ContactNumber> contactNumberList) {
		this.contactNumberList = contactNumberList;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getLateArrivalTime() {
		return lateArrivalTime;
	}
	public void setLateArrivalTime(String lateArrivalTime) {
		this.lateArrivalTime = lateArrivalTime;
	}
	public String getGuestCount() {
		return guestCount;
	}
	public void setGuestCount(String guestCount) {
		this.guestCount = guestCount;
	}
	public String getIsPerRoom() {
		return isPerRoom;
	}
	public void setIsPerRoom(String isPerRoom) {
		this.isPerRoom = isPerRoom;
	}
	public String getSpecialText() {
		return specialText;
	}
	public void setSpecialText(String specialText) {
		this.specialText = specialText;
	}
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getResStatus() {
		return resStatus;
	}
	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getGuaranteeCode() {
		return guaranteeCode;
	}
	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
	}
	public String getCancelPenalty() {
		return cancelPenalty;
	}
	public void setCancelPenalty(String cancelPenalty) {
		this.cancelPenalty = cancelPenalty;
	}
	public String getAmountAfterTax() {
		return amountAfterTax;
	}
	public void setAmountAfterTax(String amountAfterTax) {
		this.amountAfterTax = amountAfterTax;
	}
	public String getCurrencyCodeTotalCode() {
		return currencyCodeTotalCode;
	}
	public void setCurrencyCodeTotalCode(String currencyCodeTotalCode) {
		this.currencyCodeTotalCode = currencyCodeTotalCode;
	}
	public String getResIDType() {
		return resIDType;
	}
	public void setResIDType(String resIDType) {
		this.resIDType = resIDType;
	}
	public String getResIDValue() {
		return resIDValue;
	}
	public void setResIDValue(String resIDValue) {
		this.resIDValue = resIDValue;
	}
	
	
}
