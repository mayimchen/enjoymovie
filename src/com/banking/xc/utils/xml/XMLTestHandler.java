package com.banking.xc.utils.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.banking.xc.entity.Person;
/*
 <?xml version="1.0" encoding="utf-8"?>
<channel>
<item id="0" url="http://www.baidu.com">百度</item>
<item id="1" url="http://www.qq.com">腾讯</item>
<item id="2" url="http://www.sina.com.cn">新浪</item>
<item id="3" url="http://www.taobao.com">淘宝</item>
</channel>
 */
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;


public class XMLTestHandler extends XmlParseHandler {
	
	public XMLTestHandler(XmlParseListener xmlParseListener,InputStream inputStream) {
		super(xmlParseListener, null);
		//创建之后立即就会执行readXml
		/*
		try {
			readXml(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			xmlParseListener.onParseError();
		}*/
	}
	private List<Person> persons;
	private Person person;
	private String preTag;
	
	
	public List<Person> getPersons() {
		return persons;
	}
	
	/*
	 * 接收文档的开始的通知。
	 */
	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
	}
	
	/*
	 * 接收字符数据的通知。
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(person!=null){
			String data = new String(ch,start,length);
			
			if("name".equals(preTag)){	
				person.setName(data);			
			}else if ("age".equals(preTag)){
				person.setAge(new Short(data));
			}
		}
		
	}
	/*
	 * 接收元素开始的通知。	 
	 */
	@Override
	public void startElement(String uri, String localName, String qName, 
			Attributes attributes) throws SAXException {
		if("person".equals(localName)){
		    person = new Person();
			person.setId(new Integer(attributes.getValue("", "id")));
		}
		preTag = localName;
	}
	/*
	 * 接收文档的结尾的通知。
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		if("person".equals(localName)&&person!=null){
			persons.add(person);
			person = null;
		} 
		preTag = null;
	}
	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getObjectWhenEnd() {
		// TODO Auto-generated method stub
		return this.getPersons();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}