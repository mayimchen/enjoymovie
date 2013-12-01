package com.banking.xc.utils.xml.group;

import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GroupOrder;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class GroupCreateOrderXmlParseHandler extends XmlParseHandler{
	
	GroupOrder groupOrder;
	String preTag;
	public GroupCreateOrderXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		groupOrder = null;
		
	}

	@Override
	public Object getObjectWhenEnd() {
		return groupOrder;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "GroupCreateOrderResponse")){
			groupOrder = new GroupOrder();
		}
		preTag = localName;
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag, "CreateTime")){
			groupOrder.setCreateTime(data);
		}
		if(TextUtils.equals(preTag, "Pirce")){ 
			groupOrder.setPrice(data);
		}
		if(TextUtils.equals(preTag, "Status")){ 
			groupOrder.setStatus(data);
		}
		if(TextUtils.equals(preTag, "Amount")){
			groupOrder.setAmount(data);
		}
		if(TextUtils.equals(preTag, "OrderID")){
			groupOrder.setOrderId(data);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	
	
	/**
	 * <GroupCreateOrderResponse>
		<CreateTime>2012-06-08T10:52:49.17</CreateTime>
		<Pirce>34.0000</Pirce> 
		<Status>0</Status>
		<Amount>68.0000</Amount>
		<OrderID>2867</OrderID>
	</GroupCreateOrderResponse>
	
	<!--<Price>-->错误

	 */
}
