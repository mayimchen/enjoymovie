package com.banking.xc.entity;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * 依托于Request的价格计划对象
 */
public class RatePlan implements Serializable {
	private String start; //*
	private String end;   //*
	private String hotelCode; //*
	private String ratePlanCode; //request中不应该包含
	private boolean availRatesOnlyInd = false; //*
	private String restrictedDisplayIndicator; //伪String的boolean，是否查询预付计划
	
    /*
     * 以下是Response中新加入必须持有的字段
     * 
     */
	private String ratePlanCategory;
	private String  marketCode;
	private String Status;
	private BaseByGuestAmt baseByGuestAmt; //列表，用对象代替
	private Fee fee; //列表，用对象代替
	private GuaranteePolicy guaranteePolicy;
	private CancelPenalty cancelPenalty;
	private MealsIncluded mealsIncluded;
	private String descriptionName;//这个是价格计划的单独描述信息，很关键一点，这个应该是和roomName对应的!
	private ArrayList<String> invCodeList;//房型代码：用以明确这个价格施加于哪个房型。14076
	
	
	
	public ArrayList<String> getInvCodeList() {
		return invCodeList;
	}
	public void setInvCodeList(ArrayList<String> invCodeList) {
		this.invCodeList = invCodeList;
	}
	public String getDescriptionName() {
		return descriptionName;
	}
	public void setDescriptionName(String descriptionName) {
		this.descriptionName = descriptionName;
	}
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
	public String getHotelCode() {
		return hotelCode;
	}
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	public String getRatePlanCode() {
		return ratePlanCode;
	}
	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}
	public boolean isAvailRatesOnlyInd() {
		return availRatesOnlyInd;
	}
	public void setAvailRatesOnlyInd(boolean availRatesOnlyInd) {
		this.availRatesOnlyInd = availRatesOnlyInd;
	}
	public String getRestrictedDisplayIndicator() {
		return restrictedDisplayIndicator;
	}
	public void setRestrictedDisplayIndicator(String restrictedDisplayIndicator) {
		this.restrictedDisplayIndicator = restrictedDisplayIndicator;
	}
	public String getRatePlanCategory() {
		return ratePlanCategory;
	}
	public void setRatePlanCategory(String ratePlanCategory) {
		this.ratePlanCategory = ratePlanCategory;
	}
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public BaseByGuestAmt getBaseByGuestAmt() {
		return baseByGuestAmt;
	}
	public void setBaseByGuestAmt(BaseByGuestAmt baseByGuestAmt) {
		this.baseByGuestAmt = baseByGuestAmt;
	}
	public Fee getFee() {
		return fee;
	}
	public void setFee(Fee fee) {
		this.fee = fee;
	}
	public GuaranteePolicy getGuaranteePolicy() {
		return guaranteePolicy;
	}
	public void setGuaranteePolicy(GuaranteePolicy guaranteePolicy) {
		this.guaranteePolicy = guaranteePolicy;
	}
	public CancelPenalty getCancelPenalty() {
		return cancelPenalty;
	}
	public void setCancelPenalty(CancelPenalty cancelPenalty) {
		this.cancelPenalty = cancelPenalty;
	}
	public MealsIncluded getMealsIncluded() {
		return mealsIncluded;
	}
	public void setMealsIncluded(MealsIncluded mealsIncluded) {
		this.mealsIncluded = mealsIncluded;
	}
	
	
	
}
