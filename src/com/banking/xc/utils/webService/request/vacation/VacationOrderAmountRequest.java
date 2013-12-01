package com.banking.xc.utils.webService.request.vacation;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 这个计算的金额是跟行程有关的吧
 * @author zhangyinhang
 *
 */
public class VacationOrderAmountRequest extends VacationRequest {

	private final String requestType = RequestConstant.VACATION_ORDER_AMOUNT;
	private String tempOrderId;
	@Override
	public String getVacationParams() {
		XmlNode VacationOrderAmountRequestNode = new XmlNode("VacationOrderAmountRequest");
		VacationOrderAmountRequestNode.addNodeByNameAndValue("TempOrderID", getTempOrderId());
		return VacationOrderAmountRequestNode.toString();
	}

	public String getTempOrderId() {
		return tempOrderId;
	}

	public void setTempOrderId(String tempOrderId) {
		this.tempOrderId = tempOrderId;
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
