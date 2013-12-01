package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.VacationCity;
import com.banking.xc.utils.xml.frame.XmlParseHandler;

public class VacationCityXmlParseHandler extends XmlParseHandler {
	
	private List<VacationCity> vacationCityList;
	private VacationCity vacationCity;
	private String preTag;
	public VacationCityXmlParseHandler(com.banking.xc.utils.xml.frame.XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		vacationCityList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return vacationCityList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "City")){
			vacationCity.setCityNumber(data);
		}
		if(TextUtils.equals(preTag, "StartCityName")){
			vacationCity.setStartCityName(data);
		}
		if(TextUtils.equals(preTag, "IsHotStartCity")){
			vacationCity.setIsHotCity(data);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "VacationCity")){
			vacationCityList.add(vacationCity);
			vacationCity = null;
		}
		if(TextUtils.equals(localName, "VacationCityList")){
		
		}
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "VacationCityList")){
			vacationCityList = new ArrayList<VacationCity>();
		}
		if(TextUtils.equals(localName, "VacationCity")){
			vacationCity = new VacationCity();
		}
		preTag = localName;
	}
	
	/**
	 *<VacationCityResponse>
    <VacationCityList>
      <VacationCity>
        <City>1</City>
        <StartCityName>北京</StartCityName>
        <IsHotStartCity>true</IsHotStartCity>
      </VacationCity>
      <VacationCity>
        <City>2</City>
        <StartCityName>上海</StartCityName>
        <IsHotStartCity>true</IsHotStartCity>
      </VacationCity>
      ......
    </VacationCityList>
  </VacationCityResponse>
 
	 */
}
