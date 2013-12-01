package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 两个航班
 * @author zhangyinhang
 *
 */
public class VacationItemSub implements Serializable{
	private String aCityName;
	private String Airline;
	private String AirlineName;
	private String AirlineShortName;
	private String arrivalDate;//出发日期，2013-04-14T00:00:00
	private String arrivalDateTime;//实际可用到达时间,2013-04-14T18:55:00
	private String bargainPrice;
	private String canPost;
	private String craftType;//机型
	private String dCityName;
	private String departureDateTime;//实际可用出发时间,2013-04-14T15:00:00
	private String electricTicket;
	private String flight;
	private String flightMultiplePriceType;
	private String fltAPort;
	private String fltAPortName;
	private String fltDPort;
	private String fltDPortName;
	private String itemTypeName; //flight
	private String itemStorage; //enough
	private String subclass;
	private String subclassName;
	private String travel;
	private String buildingName;
	private String buildingShortName;
	private String planeType;
	private String isSavePrice;
	public String getaCityName() {
		return aCityName;
	}
	public void setaCityName(String aCityName) {
		this.aCityName = aCityName;
	}
	public String getAirline() {
		return Airline;
	}
	public void setAirline(String airline) {
		Airline = airline;
	}
	public String getAirlineName() {
		return AirlineName;
	}
	public void setAirlineName(String airlineName) {
		AirlineName = airlineName;
	}
	public String getAirlineShortName() {
		return AirlineShortName;
	}
	public void setAirlineShortName(String airlineShortName) {
		AirlineShortName = airlineShortName;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getArrivalDateTime() {
		return arrivalDateTime;
	}
	public void setArrivalDateTime(String arrivalDateTime) {
		this.arrivalDateTime = arrivalDateTime;
	}
	public String getBargainPrice() {
		return bargainPrice;
	}
	public void setBargainPrice(String bargainPrice) {
		this.bargainPrice = bargainPrice;
	}
	public String getCanPost() {
		return canPost;
	}
	public void setCanPost(String canPost) {
		this.canPost = canPost;
	}
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getdCityName() {
		return dCityName;
	}
	public void setdCityName(String dCityName) {
		this.dCityName = dCityName;
	}
	public String getDepartureDateTime() {
		return departureDateTime;
	}
	public void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}
	public String getElectricTicket() {
		return electricTicket;
	}
	public void setElectricTicket(String electricTicket) {
		this.electricTicket = electricTicket;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getFlightMultiplePriceType() {
		return flightMultiplePriceType;
	}
	public void setFlightMultiplePriceType(String flightMultiplePriceType) {
		this.flightMultiplePriceType = flightMultiplePriceType;
	}
	public String getFltAPort() {
		return fltAPort;
	}
	public void setFltAPort(String fltAPort) {
		this.fltAPort = fltAPort;
	}
	public String getFltAPortName() {
		return fltAPortName;
	}
	public void setFltAPortName(String fltAPortName) {
		this.fltAPortName = fltAPortName;
	}
	public String getFltDPort() {
		return fltDPort;
	}
	public void setFltDPort(String fltDPort) {
		this.fltDPort = fltDPort;
	}
	public String getFltDPortName() {
		return fltDPortName;
	}
	public void setFltDPortName(String fltDPortName) {
		this.fltDPortName = fltDPortName;
	}
	public String getItemTypeName() {
		return itemTypeName;
	}
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	public String getItemStorage() {
		return itemStorage;
	}
	public void setItemStorage(String itemStorage) {
		this.itemStorage = itemStorage;
	}
	public String getSubclass() {
		return subclass;
	}
	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}
	public String getSubclassName() {
		return subclassName;
	}
	public void setSubclassName(String subclassName) {
		this.subclassName = subclassName;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBuildingShortName() {
		return buildingShortName;
	}
	public void setBuildingShortName(String buildingShortName) {
		this.buildingShortName = buildingShortName;
	}
	public String getIsSavePrice() {
		return isSavePrice;
	}
	public void setIsSavePrice(String isSavePrice) {
		this.isSavePrice = isSavePrice;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	
}
