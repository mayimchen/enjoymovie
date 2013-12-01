package com.banking.xc.utils.webService.request.group;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 团购详情，仅需要productID
 * @author zhangyinhang
 *
 */
public class GroupProductInfoRequest extends GroupRequest{
	
	public final String requestType = RequestConstant.GROUP_PRODUCT_INFO;
	private String productID;
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}

	@Override
	public String getGroupParams() {
		XmlNode groupProductInfoRequestNode = new XmlNode("GroupProductInfoRequest");
		XmlNode productIDNode = new XmlNode("ProductID");
		productIDNode.setInnerValue(getProductID());
		groupProductInfoRequestNode.addChildNode(productIDNode);
		return groupProductInfoRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}
	
	
}
