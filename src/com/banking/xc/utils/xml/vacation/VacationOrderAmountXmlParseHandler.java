package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class VacationOrderAmountXmlParseHandler extends XmlParseHandler {

	private String amount;
	private String preTag;
	public VacationOrderAmountXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		amount = null;
		
	}

	@Override
	public Object getObjectWhenEnd() {
		return amount;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		final String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "Amount")){
			amount = data;
		}
	}
	/**
	 * <VacationOrderAmountResponse>
    <Amount>300</Amount>
  </VacationOrderAmountResponse>

	 */
}
