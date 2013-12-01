package com.banking.xc.utils.webService.request.group;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 不是自己收款和制定券，这个需要支付以后再查询
 * 根据订单id和用户id获取团购券
 * @author zhangyinhang
 *
 */
public class GroupQueryTicketsRequest extends GroupRequest {

	final String requestType = RequestConstant.GROUP_QUERY_TICKETS;
	private String orderId;
	private String UID;
	@Override
	public String getGroupParams() {
		XmlNode groupQueryTicketsRequestNode = new XmlNode("GroupQueryTicketsRequest");
		groupQueryTicketsRequestNode.addNodeByNameAndValue("OrderId", getOrderId());
		groupQueryTicketsRequestNode.addNodeByNameAndValue("UID", getUID());
		return groupQueryTicketsRequestNode.toString();
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

	public String getUID() {
		return UID;
	}

	public void setUID(String uID) {
		UID = uID;
	}
	
	/**
	 * <GroupQueryTicketsRequest>
		<OrderId>2688</OrderId>
		<UID>159c906a-aa28-4f54-b609-59d2c105fde2</UID>
	</GroupQueryTicketsRequest>

	 */
}
