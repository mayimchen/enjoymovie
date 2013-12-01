package com.banking.xc.utils.xml.flight;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.FlightData;
import com.banking.xc.entity.FlightRoute;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.staticinfo.FlightAirLineUtil;
import com.banking.xc.utils.staticinfo.FlightAirPortUtil;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightSearchXmlParseHandler extends XmlParseHandler{

	
	ArrayList<FlightRoute> flightRouteList;
	FlightRoute flightRoute;
	ArrayList<FlightData> flightDataList;
	FlightData flightData;
	String preTag;
	FlightAirPortUtil util;
	public FlightSearchXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void startDocument() throws SAXException {
		if(util == null){
			util = new FlightAirPortUtil();
			util.initializeFlightAirport();
		}
		super.startDocument();
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		flightRouteList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return flightRouteList;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag, "RecordCount")){
			flightRoute.setRecordCount(data);
			return;
		}
		if(TextUtils.equals(preTag, "DepartCityCode")){
			flightData.setDepartCityCode(data);
			return;
		}
		if(TextUtils.equals(preTag, "ArriveCityCode")){
			flightData.setArriveCityCode(data);
			return;
		}
		if(TextUtils.equals(preTag, "DPortCode")){//"DepartAirportCode"
			flightData.setDepartAirportCode(data);
			if(Log.D){
				Log.d("","DPortCode"+data);
			}
			flightData.setDepartAirportName(util.getNameByThree(data));
			//flightData.setDepartAirportString(departAirportString)
			return;
		}
		if(TextUtils.equals(preTag, "APortCode")){
			flightData.setArriveAirportCode(data);
			flightData.setArriveAirportName(util.getNameByThree(data));
			return;
		}
		if(TextUtils.equals(preTag, "TakeOffTime")){
			flightData.setTakeOffTime(data);
			final String[] datas = data.split("T");
			if(datas.length>1){
				flightData.setNoDateDepartTime(datas[1]);
			}
			return;
		}
		if(TextUtils.equals(preTag, "ArriveTime")){
			flightData.setArriveTime(data);
			final String[] datas = data.split("T");
			if(datas.length>1){
				flightData.setNoDateArriveTime(datas[1]);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Flight")){
			flightData.setFlight(data);
			return;
		}
		if(TextUtils.equals(preTag, "CraftType")){
			flightData.setCraftType(data);
			return;
		}
		if(TextUtils.equals(preTag, "AirlineCode")){//AirlineDibitCode
			flightData.setAirlineDibitCode(data);
			
			return;
		}
		
		if(TextUtils.equals(preTag, "Price")){
			flightData.setPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "Rate")){
			flightData.setRate(data);
			return;
		}
		if(TextUtils.equals(preTag, "StandardPrice")){
			flightData.setStandardPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "ChildStandardPrice")){
			flightData.setChildStandardPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "BabyStandardPrice")){
			flightData.setBabyStandardPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "MealType")){
			flightData.setMealType(data);
			return;
		}
		if(TextUtils.equals(preTag, "AdultTax")){
			flightData.setAdultTax(data);
			return;
		}
		if(TextUtils.equals(preTag, "ChildTax")){
			flightData.setChildTax(data);
			return;
		}
		if(TextUtils.equals(preTag, "BabyTax")){
			flightData.setBabyTax(data);
			return;
		}
		if(TextUtils.equals(preTag, "AdultOilFee")){
			flightData.setAdultOilFee(data);
			return;
		}
		if(TextUtils.equals(preTag, "ChildOilFee")){
			flightData.setChildOilFee(data);
			return;
		}
		if(TextUtils.equals(preTag, "BabyOilFee")){
			flightData.setBabyOilFee(data);
			return;
		}
		if(TextUtils.equals(preTag, "StopTimes")){
			flightData.setStopTimes(data);
			return;
		}
		if(TextUtils.equals(preTag, "Nonrer")){
			flightData.setNonrer(data);
			return;
		}
		if(TextUtils.equals(preTag, "Nonend")){
			flightData.setNonend(data);
			return;
		}
		if(TextUtils.equals(preTag, "Nonref")){
			flightData.setNonref(data);
			return;
		}
		if(TextUtils.equals(preTag, "Rernote")){
			flightData.setRefNote(data);
			return;
		}
		if(TextUtils.equals(preTag, "Remarks")){
			flightData.setRemarks(data);
			return;
		}
		if(TextUtils.equals(preTag, "Endnote")){
			flightData.setEndNote(data);
			return;
		}
		if(TextUtils.equals(preTag, "Refnote")){
			flightData.setRefNote(data);
			return;
		}
		if(TextUtils.equals(preTag, "BeforeFlyDate")){
			flightData.setBeforeFlyDate(data);
			return;
		}
		if(TextUtils.equals(preTag, "Quantity")){
			flightData.setQuantity(data);
			return;
		}
		if(TextUtils.equals(preTag, "PriceType")){
			flightData.setPriceType(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProductType")){
			flightData.setProductType(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProductSource")){
			flightData.setProductSource(data);
			return;
		}
		if(TextUtils.equals(preTag, "Recommend")){
			flightData.setRecommend(data);
			return;
		}
		/*if(TextUtils.equals(preTag, "OnlyOwnCity")){
			flightData.setOnlyOwnCity(data);
			return;
		}*/
		if(TextUtils.equals(preTag, "IsLowestPrice")){
			flightData.setIsLowestPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsLowestCZSpecialPrice")){
			flightData.setIsLowestCZSpecialPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "OutOfPostTime")){
			flightData.setOutOfPostTime(data);
			return;
		}
		if(TextUtils.equals(preTag, "OutOfSendGetTime")){
			flightData.setOutOfSendGetTime(data);
			return;
		}
		if(TextUtils.equals(preTag, "OutOfAirlineCounterTime")){
			flightData.setOutOfAirlineCounterTime(data);
			return;
		}
		if(TextUtils.equals(preTag, "CanPost")){
			flightData.setCanPost(data);
			return;
		}
		if(TextUtils.equals(preTag, "CanAirlineCounter")){
			flightData.setCanAirlineCounter(data);
			return;
		}
		if(TextUtils.equals(preTag, "CanSendGet")){
			flightData.setCanSendGet(data);
			return;
		}
		if(TextUtils.equals(preTag, "CanUpGrade")){
			flightData.setCanUpGrade(data);
			return;
		}
		if(TextUtils.equals(preTag, "RouteIndex")){
			flightData.setRouteIndex(data);
			return;
		}
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals("FlightRoutes", localName)){
			flightRouteList = new ArrayList<FlightRoute>();
		}
		if(TextUtils.equals("DomesticFlightRoute", localName)){
			flightRoute = new FlightRoute();
		}
		if(TextUtils.equals("FlightsList", localName)){
			flightDataList = new ArrayList<FlightData>();
		}
		if(TextUtils.equals("DomesticFlightData", localName)){
			flightData = new FlightData();
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals("DomesticFlightRoute", localName)){
			flightRouteList.add(flightRoute);
			flightRoute = null;
		}
		if(TextUtils.equals("FlightsList", localName)){
			flightRoute.setFlightDataList(flightDataList);
			flightDataList = null;
		}
		if(TextUtils.equals("DomesticFlightData", localName)){
			flightDataList.add(flightData);
			flightData = null;
		}
	}

	
	
}
