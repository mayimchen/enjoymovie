package com.banking.xc.utils.xml.group;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GroupProductImageEntity;
import com.banking.xc.entity.GroupProductInfoResponse;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class GroupProductInfoXmlParseHandler extends XmlParseHandler {

	GroupProductInfoResponse groupProductInfoResponse;
	ArrayList<GroupProductImageEntity> groupProductImageEntityList;
	GroupProductImageEntity groupProductImageEntity;
	ArrayList<String> hotelPictures;

	String preTag;

	public GroupProductInfoXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {

	}

	@Override
	public void destroy() {
		groupProductInfoResponse = null;

	}

	@Override
	public Object getObjectWhenEnd() {
		return groupProductInfoResponse;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (TextUtils.equals("GroupProductInfoResponse", localName)) {
			groupProductInfoResponse = new GroupProductInfoResponse();
		}
		if (TextUtils.equals("PictureInfoEntity", localName)) {
			groupProductImageEntityList = new ArrayList<GroupProductImageEntity>();
		}
		if (TextUtils.equals("GroupProductImageEntity", localName)) {
			groupProductImageEntity = new GroupProductImageEntity();
		}
		if (TextUtils.equals("HotelPicture", localName)) {
			hotelPictures = new ArrayList<String>();
		}
		preTag = localName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if (TextUtils.equals(preTag, "Description")) {
			if (groupProductImageEntity != null) {
				groupProductImageEntity.setDescription(data);
				return;
			}
		}
		if (TextUtils.equals(preTag, "Picture")) {
			groupProductImageEntity.setPicture(data);
			return;
		}
		if (TextUtils.equals(preTag, "Title")) {
			groupProductImageEntity.setTitle(data);
			return;
		}
		if (TextUtils.equals(preTag, "ID")) {
			if(groupProductImageEntity!=null){
				groupProductImageEntity.setId(data);
				return;
			}
		}
		if (TextUtils.equals(preTag, "string")) {
			hotelPictures.add(data);
			return;
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if (TextUtils.equals("GroupProductInfoResponse", localName)) {
			//
		}
		if (TextUtils.equals("PictureInfoEntity", localName)) {
			groupProductInfoResponse.setGroupProductImageEntityList(groupProductImageEntityList);
			groupProductImageEntityList = null;
		}
		if (TextUtils.equals("GroupProductImageEntity", localName)) {
			groupProductImageEntityList.add(groupProductImageEntity);
			groupProductImageEntity = null;
		}
		if (TextUtils.equals("HotelPicture", localName)) {
			groupProductInfoResponse.setHotelPictures(hotelPictures);
			hotelPictures = null;
		}
	}

}
