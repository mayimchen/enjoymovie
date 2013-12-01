package com.banking.xc.utils.xml.frame;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class XmlParseHandler extends DefaultHandler{
	private XmlParseListener xmlParseListener;
	InputStream inputStream;
	public abstract void cancelParse();
	public abstract void destroy();
	public XmlParseHandler(XmlParseListener xmlParseListener,InputStream inputStream){
		this.xmlParseListener = xmlParseListener;
		this.inputStream = inputStream;
	}
	public void parse(){
		try {
			readXml();
		} catch (Exception e) {
			 
			e.printStackTrace();
			System.out.println("XC XmlParseHandler parae() exception");
			//if(Log)
			System.out.println("XC XmlParseHandler exception"+e);
			//解析时异常放到onParseErro处理
			//空列表异常自己根据个数处理，详见listview
			xmlParseListener.onParseError(e.toString());
		}
	}
	protected XmlParseListener getXmlParseListener(){
		return  xmlParseListener;
	}
	private void readXml()  {
		  
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			saxParser = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //创建解析器		
		try {
			saxParser.parse(inputStream, this);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public abstract Object getObjectWhenEnd();
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		xmlParseListener.onParseSuccess(this.getObjectWhenEnd());
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		destroy();
	}
	
}
