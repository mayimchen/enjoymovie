package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.VacationItem;
import com.banking.xc.entity.VacationItemSub;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class VacationItemResponseXmlParseHandler extends XmlParseHandler {

	private ArrayList<VacationItem> vacationItemList;
	VacationItem vacationItem;
	ArrayList<VacationItemSub> vacationItemSubList;
	VacationItemSub vacationItemSub;
	private String preTag;
	public VacationItemResponseXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		vacationItemList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return vacationItemList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String (ch,start,length);
		if(TextUtils.equals(preTag, "ACityName")){
			if(vacationItemSub!=null){
				vacationItemSub.setaCityName(data);
			}else{
			vacationItem.setACityName(data);
			return;
			}
		}
		if(TextUtils.equals(preTag, "ArrivalDate")){
			vacationItem.setArrivalDate(data);
			return;
		}
		if(TextUtils.equals(preTag, "AdvanceDays")){
			vacationItem.setAdvanceDays(data);
			return;
		}
		if(TextUtils.equals(preTag, "BargainPrice")){
			vacationItem.setBargainPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "CheckDate")){
			vacationItem.setCheckDate(data);
			return;
		}
		if(TextUtils.equals(preTag, "ChoiceDesc")){
			vacationItem.setChoiceDesc(data);
			return;
		}
		if(TextUtils.equals(preTag, "DCityName")){
			if(vacationItemSub!=null){
				vacationItemSub.setdCityName(data);
				return;
			}else{
			vacationItem.setdCityName(data);
			return;
			}
		}
		if(TextUtils.equals(preTag, "DepartureDate")){
			vacationItem.setDepartureDate(data);
			return;
		}
		if(TextUtils.equals(preTag, "DepartureDays")){
			vacationItem.setDepartureDays(data);
			return;
		}
		if(TextUtils.equals(preTag, "FlightMultiplePriceType")){
			vacationItem.setFlightMultiplePriceType(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsFlight")){
			vacationItem.setIsFlight(data);
			return;
		}
		if(TextUtils.equals(preTag, "ItemName")){
			if(Log.D){
				Log.d("", "ItemName"+data);
			}
			vacationItem.setItemName(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsInternational")){
			vacationItem.setIsInternational(data);
			return;
		}
		if(TextUtils.equals(preTag, "ItemAllPrice")){
			vacationItem.setItemAllPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "ItemPrice")){
			vacationItem.setItemPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "ItemDesc")){
				vacationItem.setItemDesc(data);
			return;
		}
		if(TextUtils.equals(preTag, "Airline")){
			if(vacationItemSub!=null){
				vacationItemSub.setAirline(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "AirlineName")){
			if(vacationItemSub!=null){
				vacationItemSub.setAirlineName(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "AirlineShortName")){
			if(Log.D){
				Log.d("", "AirlineShortName"+data);
			}
			if(vacationItemSub!=null){
				vacationItemSub.setAirlineShortName(data);
				return;
			}else{
				vacationItem.setAirLineShortName(data);
			}
		}
		if(TextUtils.equals(preTag, "BuildingName")){
			if(vacationItemSub!=null){
				vacationItemSub.setBuildingName(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "BuildingShortName")){
			if(vacationItemSub!=null){
				vacationItemSub.setBuildingShortName(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "ArrivalDateTime")){
			if(vacationItemSub!=null){
				vacationItemSub.setArrivalDateTime(data);
				return;
			}else{
				if(data.contains("T")){
					vacationItem.setArriveTime(data.split("T")[1]);
				}
				return;
			}
		}
		if(TextUtils.equals(preTag, "CraftType")){
			if(vacationItemSub!=null){
				vacationItemSub.setCraftType(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "DepartureDateTime")){
			if(vacationItemSub!=null){
				vacationItemSub.setDepartureDateTime(data);
				return;
			}else{
				if(data.contains("T")){
					vacationItem.setDepartureTime(data.split("T")[1]);
				}
				return;
			}
		}
		if(TextUtils.equals(preTag, "ElectricTicket")){
			if(vacationItemSub!=null){
				vacationItemSub.setElectricTicket(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "Flight")){
			if(Log.D){
				Log.d("", "the flight"+data);
			}
			if(vacationItemSub!=null){
				vacationItemSub.setFlight(data);
				return;
			}else{
				vacationItem.setFlight(data);
			}
		}
		if(TextUtils.equals(preTag, "FltAPort")){
			if(vacationItemSub!=null){
				vacationItemSub.setFltAPort(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "FltAPortName")){
			if(vacationItemSub!=null){
				vacationItemSub.setFltAPortName(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "FltDPort")){
			if(vacationItemSub!=null){
				vacationItemSub.setFltDPort(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "FltDPortName")){
			if(Log.D){
				Log.d("", "FltDPortName"+data);
			}
			if(vacationItemSub!=null){
				vacationItemSub.setFltDPortName(data);
				return;
			}else{
				vacationItem.setAirPort(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "Refnote")){
			vacationItem.setRefNote(data);
			return;
		}
		if(TextUtils.equals(preTag, "Rernote")){
			vacationItem.setRerNote(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsSavePrice")){
			if(vacationItemSub!=null){
				vacationItemSub.setIsSavePrice(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "ItemStorage")){
			if(vacationItemSub!=null){
				vacationItemSub.setItemStorage(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "ItemTypeName")){
			vacationItem.setItemTypeName(data);
		}
		if(TextUtils.equals(preTag, "PlaneType")){
			if(vacationItemSub!=null){
				vacationItemSub.setPlaneType(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "SubClassName")){
			if(vacationItemSub!=null){
				vacationItemSub.setSubclassName(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "Subclass")){
			if(vacationItemSub!=null){
				vacationItemSub.setSubclass(data);
				return;
			}
		}
		if(TextUtils.equals(preTag, "Travel")){
			if(vacationItemSub!=null){
				vacationItemSub.setTravel(data);
				return;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "ItemList")){
			return;
		}
		if(TextUtils.equals(localName, "ItemResource")){
			vacationItemList.add(vacationItem);
			vacationItem = null;
		}
		if(TextUtils.equals(localName, "ItemDetailList")){
			vacationItem.setVacationItemSubList(vacationItemSubList);
			vacationItemSubList = null;
		}
		if(TextUtils.equals(localName, "ItemResourceSub")){
			vacationItemSubList.add(vacationItemSub);
			vacationItemSub = null;
		}
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
		if(TextUtils.equals(localName, "ItemList")){
			vacationItemList = new ArrayList<VacationItem>();
		}
		if(TextUtils.equals(localName, "ItemResource")){
			vacationItem = new VacationItem();
		}
		if(TextUtils.equals(localName, "ItemDetailList")){
			vacationItemSubList = new ArrayList<VacationItemSub>();
		}
		if(TextUtils.equals(localName, "ItemResourceSub")){
			vacationItemSub = new VacationItemSub();
		}
	}
	
	
}
