package com.banking.xc.entity;

import java.io.Serializable;

public class TourDailyDinnerInfo implements Serializable {
	private String breakFastShift;
	private String careDinnerTimes;
	private String lunchShift;
	private String noCareDinnerTimes;
	private String noConfirmDinnerTimes;
	private String supperShift;
	//PTD
	public String getBreakFastShift() {
		return breakFastShift;
	}
	public void setBreakFastShift(String breakFastShift) {
		this.breakFastShift = breakFastShift;
	}
	public String getCareDinnerTimes() {
		return careDinnerTimes;
	}
	public void setCareDinnerTimes(String careDinnerTimes) {
		this.careDinnerTimes = careDinnerTimes;
	}
	public String getLunchShift() {
		return lunchShift;
	}
	public void setLunchShift(String lunchShift) {
		this.lunchShift = lunchShift;
	}
	public String getNoCareDinnerTimes() {
		return noCareDinnerTimes;
	}
	public void setNoCareDinnerTimes(String noCareDinnerTimes) {
		this.noCareDinnerTimes = noCareDinnerTimes;
	}
	public String getNoConfirmDinnerTimes() {
		return noConfirmDinnerTimes;
	}
	public void setNoConfirmDinnerTimes(String noConfirmDinnerTimes) {
		this.noConfirmDinnerTimes = noConfirmDinnerTimes;
	}
	public String getSupperShift() {
		return supperShift;
	}
	public void setSupperShift(String supperShift) {
		this.supperShift = supperShift;
	}
	
}	
