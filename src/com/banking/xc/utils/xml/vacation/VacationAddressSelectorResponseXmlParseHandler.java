package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.AddressSelector;
import com.banking.xc.utils.xml.frame.XmlParseHandler;

public class VacationAddressSelectorResponseXmlParseHandler extends XmlParseHandler {

	ArrayList<AddressSelector> addressSelectorList;
	AddressSelector addressSelector;
	String preTag;
	public VacationAddressSelectorResponseXmlParseHandler(com.banking.xc.utils.xml.frame.XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		addressSelectorList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return addressSelectorList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "SearchID")){
			addressSelector.setSearchID(data);
		}
		if(TextUtils.equals(preTag, "SearchType")){
			addressSelector.setSearchType(data);
		}
		if(TextUtils.equals(preTag, "SearchValue")){
			addressSelector.setSearchValue(data);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "AddressList")){
		}
		if(TextUtils.equals(localName, "AddressSelector")){
			addressSelectorList.add(addressSelector);
			addressSelector = null;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "AddressList")){
			addressSelectorList = new ArrayList<AddressSelector>();
		}
		if(TextUtils.equals(localName, "AddressSelector")){
			addressSelector = new AddressSelector();
		}
		preTag = localName;
	}
	
}
