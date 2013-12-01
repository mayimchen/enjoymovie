package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 某个Vacation所有低价计划
 * @author zhangyinhang
 *
 */
public class VacationMinPricePlan implements Serializable{
	private String TakeOffDate;
	private String MinPrice;
	private String MinPriceRemark;
	private String CalcDateTime;//计算时间,这个方案计算最近时间
	
	//private String  departTime;//暂时不做
	private String  simpleRemark;
	private String feeContains;
	
	public String getTakeOffDate() {
		return TakeOffDate;
	}
	public void setTakeOffDate(String takeOffDate) {
		TakeOffDate = takeOffDate;
	}
	public String getMinPrice() {
		return MinPrice;
	}
	public void setMinPrice(String minPrice) {
		MinPrice = minPrice;
	}
	public String getMinPriceRemark() {
		return MinPriceRemark;
	}
	public void setMinPriceRemark(String minPriceRemark) {
		MinPriceRemark = minPriceRemark;
	}
	public void addMinPriceRemark(String minPriceRemark) {
		MinPriceRemark += minPriceRemark;
	}
	public String getCalcDateTime() {
		return CalcDateTime;
	}
	public void setCalcDateTime(String calcDateTime) {
		CalcDateTime = calcDateTime;
	}
	public String getSimpleRemark() {
		return simpleRemark;
	}
	public void setSimpleRemark(String simpleRemark) {
		this.simpleRemark = simpleRemark;
	}
	public String getFeeContains() {
		return feeContains;
	}
	public void setFeeContains(String feeContains) {
		this.feeContains = feeContains;
	}
	
	
}
