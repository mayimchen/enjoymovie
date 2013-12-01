package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 度假套餐，从VacationItem分离出来的类.ItemTypeName = package
 * @author zhangyinhang
 *
 */
public class VacationPackage implements Serializable{
	String ACityName;
	String DCiytName;
	String FlightMultiplePriceType;
	String itemAllPrice;
	String itemDesc;
	String itemName;
	public String getACityName() {
		return ACityName;
	}
	public void setACityName(String aCityName) {
		ACityName = aCityName;
	}
	public String getDCiytName() {
		return DCiytName;
	}
	public void setDCiytName(String dCiytName) {
		DCiytName = dCiytName;
	}
	public String getFlightMultiplePriceType() {
		return FlightMultiplePriceType;
	}
	public void setFlightMultiplePriceType(String flightMultiplePriceType) {
		FlightMultiplePriceType = flightMultiplePriceType;
	}
	public String getItemAllPrice() {
		return itemAllPrice;
	}
	public void setItemAllPrice(String itemAllPrice) {
		this.itemAllPrice = itemAllPrice;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
}
