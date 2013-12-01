package com.banking.xc.utils.webService.request.vacation;

import android.text.TextUtils;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationOrderInfoRequest extends VacationRequest{

	public final String requestType = RequestConstant.VACATION_ORDER_INFO;
	
	public String uid;
	public String orderId;
	@Override
	public String getVacationParams() {
		XmlNode VacationOrderRequestNode = new XmlNode("VacationOrderInfoRequest");
		VacationOrderRequestNode.addNodeByNameAndValue("OrderID", getOrderId());
		VacationOrderRequestNode.addNodeByNameAndValue("Uid", getUid());
		return VacationOrderRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
