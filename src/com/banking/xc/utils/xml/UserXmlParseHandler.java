package com.banking.xc.utils.xml;

import java.io.InputStream;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.banking.xc.entity.User;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class UserXmlParseHandler extends XmlParseHandler{
	
	private User user;
	private String preTag;
	
	public UserXmlParseHandler(XmlParseListener xmlParseListener, InputStream inStream) {
		super(xmlParseListener, inStream);
		// TODO Auto-generated constructor stub
	}		
	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getObjectWhenEnd() {
		// TODO Auto-generated method stub
		return user;
	}


	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		
	}


	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		//销毁元素
	}


	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub
		super.endPrefixMapping(prefix);
	}


	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(localName.equals("UserResponse"))
		{
			user = new User();
		}
		preTag = localName;
	}


	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		preTag = null;
		
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		if(user!=null)
		{
			String data = new String(ch,start,length);
			if("UniqueUID".equals(preTag)){	
				user.setUniqueID(data)	;		
			}else if ("OperationType".equals(preTag)){
				user.setOperationType(data);
			}else if ("RetCode".equals(preTag)){
				user.setRetCode(data);
			}
		}
		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		user = null;
	}
	
	
}
