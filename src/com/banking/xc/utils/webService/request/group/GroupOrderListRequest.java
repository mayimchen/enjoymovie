package com.banking.xc.utils.webService.request.group;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class GroupOrderListRequest extends GroupRequest {

	private final String requestType = RequestConstant.GROUP_ORDER_LIST;
	private String orderId; // 查寻特定一个
	private String beginDate;
	private String endDate;
	private String orderStatus;
	private String Uid;

	@Override
	public String getGroupParams() {
		XmlNode GroupOrderListRequestNode = new XmlNode("GroupOrderListRequest");
		if (getOrderId() != null) {
			GroupOrderListRequestNode.addNodeByNameAndValue("OrderId", getOrderId());
		}
		GroupOrderListRequestNode.addNodeByNameAndValue("BeginDate", getBeginDate());
		GroupOrderListRequestNode.addNodeByNameAndValue("EndDate", getEndDate());
		//GroupOrderListRequestNode.addNodeByNameAndValue("UID", getUid());
		if (getOrderStatus() != null) {
			GroupOrderListRequestNode.addNodeByNameAndValue("OrderStatus", getOrderStatus());
		}
		if(Log.D){
			Log.d("GroupOrderListRequest","GroupOrderListRequestNode.toString()"+GroupOrderListRequestNode.toString());
		}
		return GroupOrderListRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}
	
	/**
	 * <GroupOrderListRequest> 
	 * <OrderId>1856</OrderId> 
	 * <BeginDate>2012-01-01</BeginDate> 
	 * <EndDate>2012-05-30</EndDate> 
	 * <OrderStatus>-1</OrderStatus> 
	   </GroupOrderListRequest>
	 * 
	 * <UID>abcdefg</UID> ?文档错误？
	 */
}
