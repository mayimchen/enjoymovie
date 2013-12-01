package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 和RatePlan相关类
 * @author zhangyinhang
 *
 */
public class Fee implements Serializable{
	private String code;
	private String amount;
	private String currencyCode;
	private String chargeUnit;
	private String descriptionText;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getChargeUnit() {
		return chargeUnit;
	}
	public void setChargeUnit(String chargeUnit) {
		this.chargeUnit = chargeUnit;
	}
	public String getDescriptionText() {
		return descriptionText;
	}
	public void setDescriptionText(String descriptionText) {
		this.descriptionText = descriptionText;
	}
	
	
}
