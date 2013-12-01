package com.banking.xc.entity;

import java.io.Serializable;

public class GroupOrder implements Serializable{
	private String createTime;
	private String price; //单价
	private String status;//现在默认支付方式是0.根据合作模式确定若为自行收款则为4 若为携程收款则为0 	请联系客户经理配置
	private String amount;
	private String orderId;
	private String payWay = "在线支付";
	public String getPayWay(){
		return payWay;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
