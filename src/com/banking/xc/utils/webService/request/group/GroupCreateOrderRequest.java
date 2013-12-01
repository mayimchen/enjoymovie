package com.banking.xc.utils.webService.request.group;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class GroupCreateOrderRequest extends GroupRequest{

	public final String requestType = RequestConstant.GROUP_CREATE_ORDER;
	
	private String productId;
	private String quantity;
	private String uid;
	private String mobile;
	private String phone;
	private String Email;
	@Override
	public String getGroupParams() {
		XmlNode groupCreateOrderRequestNode = new XmlNode("GroupCreateOrderRequest");
		
		XmlNode productIdNode = new XmlNode("ProductId");
		productIdNode.setInnerValue(getProductId());
		groupCreateOrderRequestNode.addChildNode(productIdNode);
		XmlNode QuantityNode = new XmlNode("Quantity");
		QuantityNode.setInnerValue(getQuantity());
		groupCreateOrderRequestNode.addChildNode(QuantityNode);
		XmlNode UidNode = new XmlNode("Uid");
		UidNode.setInnerValue(getUid());
		groupCreateOrderRequestNode.addChildNode(UidNode);
		XmlNode MobileNode = new XmlNode("Mobile");
		MobileNode.setInnerValue(getMobile());
		groupCreateOrderRequestNode.addChildNode(MobileNode);
		XmlNode PhoneNode = new XmlNode("Phone");
		PhoneNode.setInnerValue(getPhone());
		groupCreateOrderRequestNode.addChildNode(PhoneNode);
		XmlNode EmailNode = new XmlNode("Email");
		EmailNode.setInnerValue(getEmail());
		groupCreateOrderRequestNode.addChildNode(EmailNode);
		
		return groupCreateOrderRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * <GroupCreateOrderRequest>
		<ProductId>10400</ProductId>
		<Quantity>2</Quantity>
		<Uid>A2012042710035449</Uid>
		<Mobile>18601683247</Mobile>
		<Phone>65697281</Phone>
		<Email>wbxia@ctrip.com</Email>
	</GroupCreateOrderRequest>
	 */
}
