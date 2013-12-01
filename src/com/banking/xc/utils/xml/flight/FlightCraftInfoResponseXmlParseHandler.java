package com.banking.xc.utils.xml.flight;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.CraftInfoEntity;
import com.banking.xc.entity.CraftInfosResponse;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightCraftInfoResponseXmlParseHandler extends XmlParseHandler{

	private CraftInfosResponse craftInfosResponse;
	private ArrayList<CraftInfoEntity> craftInfoEntityList;
	private CraftInfoEntity craftInfoEntity;
	private String preTag;
	public FlightCraftInfoResponseXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		 
	}

	@Override
	public void destroy() {
		craftInfosResponse = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return craftInfosResponse;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals("GetCraftInfosResponse", localName)){
			craftInfosResponse = new CraftInfosResponse();
		}
		if(TextUtils.equals("CraftInfosList", localName)){
			craftInfoEntityList = new ArrayList<CraftInfoEntity>();
		}
		if(TextUtils.equals("CraftInfoEntity", localName)){
			craftInfoEntity = new CraftInfoEntity();
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals("CraftInfosList", localName)){
			craftInfosResponse.setCraftInfoEntityList(craftInfoEntityList);
			craftInfoEntityList = null;
		}
		if(TextUtils.equals("CraftInfoEntity", localName)){
			craftInfoEntityList.add(craftInfoEntity);
			craftInfoEntity = null;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag, "RecordCount")){
			craftInfosResponse.setRecordCount(data);
			return;
		}
		if(TextUtils.equals(preTag, "CraftType")){
			craftInfoEntity.setCraftType(data);
			return;
		}
		if(TextUtils.equals(preTag, "CTName")){
			craftInfoEntity.setCTName(data);
			return;
		}
		if(TextUtils.equals(preTag, "WidthLevel")){
			craftInfoEntity.setWidthLevel(data);
			return;
		}
		if(TextUtils.equals(preTag, "MinSeats")){
			craftInfoEntity.setMinSeats(data);
			return;
		}
		if(TextUtils.equals(preTag, "MaxSeats")){
			craftInfoEntity.setMaxSeats(data);
			return;
		}
		if(TextUtils.equals(preTag, "Note")){
			craftInfoEntity.setNote(data);
			return;
		}
		if(TextUtils.equals(preTag, "Crafttype_ename")){
			craftInfoEntity.setCrafttypeEname(data);
			return;
		}
		if(TextUtils.equals(preTag, "CraftKind")){
			craftInfoEntity.setCraftKind(data);
			return;
		}
	}
	
}
