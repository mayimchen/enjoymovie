package com.banking.xc.entity;

public class VacationDescDetail {
	private String deadlineDate;
	private String detail;
	private String style;
	public String getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(String deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void appendDetail(String detail) {
		this.detail += detail;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
