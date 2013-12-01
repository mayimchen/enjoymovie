package com.banking.xc.utils.xml.group;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GroupOrderEntity;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class GroupOrderListXmlParseHandler extends XmlParseHandler{

	ArrayList<GroupOrderEntity> groupOrderEntityList;
	GroupOrderEntity groupOrderEntity;
	private String preTag;
	private boolean groupOrderInvoiceTag = false;//发票,当遇到它时不处理
	public GroupOrderListXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		groupOrderEntityList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return groupOrderEntityList;
	}
	

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "OrderList")){
			groupOrderEntityList = new ArrayList<GroupOrderEntity>();
		}
		if(TextUtils.equals(localName, "GroupOrderEntity")){
			groupOrderEntity = new GroupOrderEntity();
		}
		if(TextUtils.equals(localName, "GroupOrderInvoice")){
			groupOrderInvoiceTag = true;
		}
		preTag = localName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if(groupOrderInvoiceTag){
			return;
		}
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag, "Email")){
			groupOrderEntity.setEmail(data);
			return;
		}
		if(TextUtils.equals(preTag, "Phone")){
			groupOrderEntity.setPhone(data);
			return;
		}
		if(TextUtils.equals(preTag, "Mobile")){
			groupOrderEntity.setMobile(data);
			return;
		}
		if(TextUtils.equals(preTag, "PayType")){
			groupOrderEntity.setPayType(data);
			return;
		}
		if(TextUtils.equals(preTag, "CreateTime")){
			groupOrderEntity.setCreateTime(data);
			return;
		}
		if(TextUtils.equals(preTag, "UpdateTime")){
			groupOrderEntity.setUpdateTime(data);
			return;
		}
		if(TextUtils.equals(preTag, "Isdisplay")){
			groupOrderEntity.setIsDisplay(data);
			return;
		}
		if(TextUtils.equals(preTag, "UID")){
			groupOrderEntity.setUid(data);
			return;
		}
		if(TextUtils.equals(preTag, "Status")){
			groupOrderEntity.setStatus(data);
			return;
		}
		if(TextUtils.equals(preTag, "Amount")){
			groupOrderEntity.setAmount(data);
			return;
		}
		if(TextUtils.equals(preTag, "Quantity")){
			groupOrderEntity.setQuantity(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProductId")){
			groupOrderEntity.setProductId(data);
			return;
		}
		if(TextUtils.equals(preTag, "OrderId")){
			groupOrderEntity.setOrderId(data);
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "OrderList")){
			//
		}
		if(TextUtils.equals(localName, "GroupOrderEntity")){
			if(Log.D){
				Log.d("ParseHandler","Dom end GroupOrderEntity");
			}
			groupOrderEntityList.add(groupOrderEntity);
			groupOrderEntity = null;
		}
		if(TextUtils.equals(localName, "GroupOrderInvoice")){
			groupOrderInvoiceTag = false;;
		}
	}

	@Override
	public void endDocument() throws SAXException {
		if(groupOrderEntity!=null){ //防止Dom意外终止情况
			if(Log.D){
				Log.d("ParseHandler","Dom error end");
			}
			groupOrderEntityList.add(groupOrderEntity);
			groupOrderEntity = null;
		}
		super.endDocument();
	}
	

	/**
	 * GroupOrderListResponse>
		<OrderList>
			<GroupOrderEntity>
				<Email>test@te.com</Email>
				<Phone>--</Phone>
				<Mobile>13652555555</Mobile>
				<PayType>1</PayType>
				<CreateTime>2012-02-27T14:12:36</CreateTime>
				<UpdateTime>2012-05-30T14:37:23</UpdateTime>
				<Isdisplay>1</Isdisplay>
				<UID>abcdefg</UID>
				<Status>4</Status>
				<Amount>401.0000</Amount>
				<Quantity>1</Quantity>
				<ProductId>10283</ProductId>
				<OrderID>1865</OrderID>
			</GroupOrderEntity>
			<GroupOrderEntity>
				<Email>12@te.com</Email>
				<Phone>--</Phone>
				<Mobile>13652587777</Mobile>
				<PayType>1</PayType>
				<CreateTime>2012-02-28T09:09:35</CreateTime>
				<UpdateTime>0001-01-01T00:00:00</UpdateTime>
				<Isdisplay>1</Isdisplay>
				<UID>abcdefg</UID>
				<Status>2</Status>
				<Amount>400.0000</Amount>
				<Quantity>1</Quantity>
				<ProductId>10355</ProductId>
				<OrderID>1869</OrderID>
				<GroupOrderInvoice><InvoiceNo /><InvoiceHead /><InvoiceDesc /><RevAddr /><RevPerName /><PostCode /><OrderId>0</OrderId></GroupOrderInvoice>
			</GroupOrderEntity>
		</OrderList>
	</GroupOrderListResponse>

	 */
	
}
