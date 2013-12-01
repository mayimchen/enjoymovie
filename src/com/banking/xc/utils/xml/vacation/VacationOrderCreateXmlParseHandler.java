package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class VacationOrderCreateXmlParseHandler extends XmlParseHandler{

	String tempOrderId;
	String preTag;
	public VacationOrderCreateXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
		
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		tempOrderId = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return tempOrderId;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "TmpOrderID")){
			tempOrderId = data;
		}
	}
	
	/**
	 * <?xml version="1.0"?><Response><Header ShouldRecordPerformanceTime="False" Timestamp="2013-04-07 11:04:24:23892" ReferenceID="150af9b9-ad63-4424-8c17-abaad31f101a" ResultCode="Success" />
	 * <VacationOrderCreateResponse><Result>true</Result>
	 * <TmpOrderID>152163953</TmpOrderID>
	 * </VacationOrderCreateResponse></Response>
	 */
}
