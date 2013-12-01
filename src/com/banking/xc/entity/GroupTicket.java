package com.banking.xc.entity;

public class GroupTicket {
	private String expirationDate; //过期时间
	/**
	 * 券状态
	 * 16(申请退款);
	32(退款成功);
	64(退款失败);
	128(不可退款);
	256(已结束,全部券被使用，或活动过期)
	512(申请取消付款);
	1024(取消成功);
	2048(取消失败);
	4096(订单发生错误)
	 */
	private String ticketStatus;
	private String ticketPWD;
	private String ticketNo;
	private String UID;
	private String orderID;
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getTicketPWD() {
		return ticketPWD;
	}
	public void setTicketPWD(String ticketPWD) {
		this.ticketPWD = ticketPWD;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	
}
