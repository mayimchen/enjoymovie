package com.banking.xc.utils.xml.flight;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.AirportInfoEntity;
import com.banking.xc.entity.AirportInfosResponse;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightAriportInfoResponseXmlParseHandler  extends XmlParseHandler{

	
	private ArrayList<AirportInfoEntity> AirportInfoEntityList;
	private AirportInfoEntity airportInfoEntity;
	private String preTag;
	public FlightAriportInfoResponseXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		AirportInfoEntityList= null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return AirportInfoEntityList;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
		if(TextUtils.equals("AirportInfosList", localName)){
			AirportInfoEntityList = new ArrayList<AirportInfoEntity>();
		}
		if(TextUtils.equals("AirportInfoEntity", localName)){
			airportInfoEntity = new AirportInfoEntity();
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals("AirportInfoEntity", localName)){
			AirportInfoEntityList.add(airportInfoEntity);
			airportInfoEntity = null;
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		/*if(TextUtils.isEmpty(data)||TextUtils.equals(data, "\n")){
			return;
		}*/
		if(TextUtils.equals(preTag, "AirPort")){ //机场三字码
			if(airportInfoEntity.getAirport()==null){
				airportInfoEntity.setAirport(data); 
			}
			return;
		}
		if(TextUtils.equals(preTag, "AirPortName")){//机场中文名字
			if(airportInfoEntity.getAirportName()==null){
				airportInfoEntity.setAirportName(data);
			}
			if(Log.D){
				Log.d("","AirPortName"+data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "ShortName")){
			airportInfoEntity.setShortName(data);
			return;
		}
		if(TextUtils.equals(preTag, "CityId")){
			airportInfoEntity.setCityId(data);
			return;
		}
		//怎么会有空指针呢？空格问题
		if(TextUtils.equals(preTag, "CityName")){
			if(Log.D){
				Log.d("","CityName"+data);
			}
			if(airportInfoEntity!=null&&(airportInfoEntity.getCityName()==null)){
				airportInfoEntity.setCityName(data);
			}
			return;
		}
	}
	
}
