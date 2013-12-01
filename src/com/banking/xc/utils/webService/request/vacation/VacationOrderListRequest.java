package com.banking.xc.utils.webService.request.vacation;

import android.text.TextUtils;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationOrderListRequest extends VacationRequest{

	private final String requestType = RequestConstant.VACATION_ORDER_LIST;
	//文档上以下四个是必填的
	String PassengerName = ""; 
	String Uid;
	String TakeoffDate;
	String ReturnDate;
	String orderId;
	@Override
	public String getVacationParams() {
		XmlNode VacationOrderListRequestNode = new XmlNode("VacationOrderListRequest");
		if(!TextUtils.isEmpty(getPassengerName())){
			VacationOrderListRequestNode.addNodeByNameAndValue("PassengerName", getPassengerName());
		}
		if(!TextUtils.isEmpty(getOrderId())){
			VacationOrderListRequestNode.addNodeByNameAndValue("OrderID", getOrderId());
		}
		VacationOrderListRequestNode.addNodeByNameAndValue("Uid", getUid());
		if(!TextUtils.isEmpty(getTakeoffDate())){
			VacationOrderListRequestNode.addNodeByNameAndValue("TakeoffDate", getTakeoffDate());
		}
		if(!TextUtils.isEmpty(getReturnDate())){
			VacationOrderListRequestNode.addNodeByNameAndValue("ReturnDate", getReturnDate());
		}
		return VacationOrderListRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getPassengerName() {
		return PassengerName;
	}

	public void setPassengerName(String passengerName) {
		PassengerName = passengerName;
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}

	public String getTakeoffDate() {
		return TakeoffDate;
	}

	public void setTakeoffDate(String takeoffDate) {
		TakeoffDate = takeoffDate;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
