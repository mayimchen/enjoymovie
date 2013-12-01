package com.banking.xc.utils.xml.flight;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.AirlineInfoEntity;
import com.banking.xc.entity.AirlineInfosResponse;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightAirLineInfoResponseXmlParseHandler extends XmlParseHandler{

	private AirlineInfosResponse AirlineInfosResponse;
	private ArrayList<AirlineInfoEntity> AirlineInfoEntityList;
	private AirlineInfoEntity AirlineInfoEntity;
	private String preTag;
	public FlightAirLineInfoResponseXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		AirlineInfosResponse= null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return AirlineInfosResponse;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals("GetAirlineInfosResponse", localName)){
			AirlineInfosResponse = new AirlineInfosResponse();
		}
		if(TextUtils.equals("AirlineInfosList", localName)){
			AirlineInfoEntityList = new ArrayList<AirlineInfoEntity>();
		}
		if(TextUtils.equals("AirlineInfoEntity", localName)){
			AirlineInfoEntity = new AirlineInfoEntity();
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals("AirlineInfosList", localName)){
			AirlineInfosResponse.setAirlineInfoEntityList(AirlineInfoEntityList);
			AirlineInfoEntityList = null;
		}
		if(TextUtils.equals("AirlineInfoEntity", localName)){
			AirlineInfoEntityList.add(AirlineInfoEntity);
			AirlineInfoEntity = null;
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag, "RecordCount")){
			AirlineInfosResponse.setRecordCount(data);
			return;
		}
		if(TextUtils.equals(preTag, "AirLine")){
			AirlineInfoEntity.setAirLine(data);
			return;
		}
		if(TextUtils.equals(preTag, "AirLineCode")){
			AirlineInfoEntity.setAirLineCode(data);
			return;
		}
		if(TextUtils.equals(preTag, "AirLineName")){
			AirlineInfoEntity.setAirLineName(data);
			return;
		}
		if(TextUtils.equals(preTag, "ShortName")){
			AirlineInfoEntity.setShortName(data);
			return;
		}
		if(TextUtils.equals(preTag, "GroupId")){
			AirlineInfoEntity.setGroupId(data);
			return;
		}
		if(TextUtils.equals(preTag, "GroupName")){
			AirlineInfoEntity.setGroupName(data);
			return;
		}
		if(TextUtils.equals(preTag, "StrictType")){
			AirlineInfoEntity.setStrictType(data);
			return;
		}
		if(TextUtils.equals(preTag, "AddonPriceProtected")){
			AirlineInfoEntity.setAddonPriceProtected(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsSupportAirPlus ")){
			AirlineInfoEntity.setIsSupportAirPlus (data);
			return;
		}
		if(TextUtils.equals(preTag, "OnlineCheckinUrl ")){
			AirlineInfoEntity.setOnlineCheckinUrl (data);
			return;
		}
		
	}
	
}