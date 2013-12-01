package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GuestRoom;
import com.banking.xc.entity.Hotel;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelVacationXmlParseHandler extends XmlParseHandler{

	ArrayList<Hotel> hotelList;
	Hotel hotel;
	ArrayList<GuestRoom> guestRoomList;
	GuestRoom guestRoom;
	String preTag;
	public HotelVacationXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		hotelList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return hotelList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "HotelImgURL")){
			hotel.setImageUrl(data);
		}
		if(TextUtils.equals(preTag, "HotelID")){
			hotel.setHotelCode(data);
		}
		if(TextUtils.equals(preTag, "HotelName")){
			hotel.setHotelName(data);
		}
		if(TextUtils.equals(preTag, "HotelDesc")){
			//hotel.set(data);
		}
		if(TextUtils.equals(preTag, "Star")){
			hotel.setHotelStar(data);
		}
		//HotelDesc没解析，roomlist解析如下
		if(TextUtils.equals(preTag, "RoomID")){
			guestRoom.setRoomId(data);
			return;
		}
		if(TextUtils.equals(preTag, "RoomName")){
			guestRoom.setRoomTypeName(data);
			return;
		}
		if(TextUtils.equals(preTag, "PersonNum")){
			guestRoom.setPersonNum(data);
			return;
		}
		if(TextUtils.equals(preTag, "Price")){
			guestRoom.setPrice(data);
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "VacationHotelList")){
			return;
		}
		if(TextUtils.equals(localName, "VacationHotel")){
			hotelList.add(hotel);
			hotel = null;
			return;
		}
		if(TextUtils.equals(localName, "RoomList")){
			hotel.setGuestRooms(guestRoomList);
			guestRoomList = null;
			return;
		}
		if(TextUtils.equals(localName, "RoomInfo")){
			guestRoomList.add(guestRoom);
			guestRoom = null;
			return;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
		if(TextUtils.equals(localName, "VacationHotelList")){
			hotelList = new ArrayList<Hotel>();
			return;
		}
		if(TextUtils.equals(localName, "VacationHotel")){
			hotel = new Hotel();
			return;
		}
		if(TextUtils.equals(localName, "RoomList")){
			guestRoomList = new ArrayList<GuestRoom>();
			return;
		}
		if(TextUtils.equals(localName, "RoomInfo")){
			guestRoom = new GuestRoom();
			return;
		}
	}
	
}
