package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.OrderCancelResponseObject;
import com.banking.xc.utils.xml.frame.XmlParseHandler;

public class HotelCancelOrderXmlParseHandler extends XmlParseHandler{

	OrderCancelResponseObject orderCancelResponseObject;
	String preTag;
	public HotelCancelOrderXmlParseHandler(com.banking.xc.utils.xml.frame.XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		orderCancelResponseObject = null;
		
	}

	@Override
	public Object getObjectWhenEnd() {
		
		return orderCancelResponseObject;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "Header")){
			final String tag = attributes.getValue("ResultCode");
			if(TextUtils.equals(tag, "Fail"))//Success
			{
				final String msg = attributes.getValue("ResultMsg");
				getXmlParseListener().onParseError(msg);
			}
		}
		if(TextUtils.equals(localName, "OTA_CancelRS")){
			orderCancelResponseObject = new OrderCancelResponseObject();
		}
	}
	
}
