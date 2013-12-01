package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 和ratePlan相关联类
 * @author zhangyinhang
 *
 */
public class CancelPenalty implements Serializable{
	
	private String start;
	private String end;
	private String amount;//属于amountPercent;
	private String currencyCode;//属于amountPercent
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
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
	
	
}
