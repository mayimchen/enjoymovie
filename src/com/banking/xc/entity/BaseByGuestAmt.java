package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 和RatePlan相关联类
 * @author zhangyinhang
 *
 */
public class BaseByGuestAmt implements Serializable{
	private String amountBeforeTax;
	private String currencyCode;
	private String numberOfGuests;
	private String listPrice;
	public String getAmountBeforeTax() {
		return amountBeforeTax;
	}
	public void setAmountBeforeTax(String amountBeforeTax) {
		this.amountBeforeTax = amountBeforeTax;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getNumberOfGuests() {
		return numberOfGuests;
	}
	public void setNumberOfGuests(String numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	public String getListPrice() {
		return listPrice;
	}
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}
	
	
}
