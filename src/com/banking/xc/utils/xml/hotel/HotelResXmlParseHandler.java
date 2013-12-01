package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.HotelReservation;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelResXmlParseHandler extends XmlParseHandler {

	private ArrayList<HotelReservation>  hotelReservations;
	private HotelReservation hotelReservation;
	
	
	public HotelResXmlParseHandler(XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
		
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		hotelReservations = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return hotelReservations;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "HotelReservations")){
			
		}
		if(TextUtils.equals(localName, "HotelReservation")){
			hotelReservations.add(hotelReservation);
			hotelReservation = null;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "HotelReservations")){
			hotelReservations = new ArrayList<HotelReservation>();
		}
		if(TextUtils.equals(localName, "HotelReservation")){
			hotelReservation = new HotelReservation();
			hotelReservation.setCreateDateTime(attributes.getValue("CreateDateTime"));
			hotelReservation.setResStatus(attributes.getValue("ResStatus"));
		}
		if(TextUtils.equals(localName, "RatePlan")){
			hotelReservation.setRatePlanCode(attributes.getValue("RatePlanCode"));
		}
		if(TextUtils.equals(localName, "BasicPropertyInfo")){
			hotelReservation.setHotelCode(attributes.getValue("HotelCode"));
		}
		if(TextUtils.equals(localName, "Guaranteeyment")){
			hotelReservation.setGuaranteeCode(attributes.getValue("GuaranteeCode"));
		}
		if(TextUtils.equals(localName, "Total")){
			hotelReservation.setAmountAfterTax(attributes.getValue("AmountAfterTax"));
			hotelReservation.setCurrencyCodeTotalCode(attributes.getValue("CurrencyCode"));
		}
		if(TextUtils.equals(localName, "HotelReservationID")){
			hotelReservation.setResIDType(attributes.getValue("ResID_Type"));
			hotelReservation.setResIDValue(attributes.getValue("ResID_Value"));
		}
		
	}

	
	
	/**
	 * <?xml version="1.0"?><Response><Header ShouldRecordPerformanceTime="False" Timestamp="2013-03-12 

18:06:03:24740" ReferenceID="cca81235-9d84-4e09-bc7d-90075447fa29" ResultCode="Success" 

/><HotelResponse><OTA_HotelResRS TimeStamp="2013-03-12T18:06:03.1849712+08:00" Version="1.0" 

PrimaryLangID="zh" xmlns="http://www.opentravel.org/OTA/2003/05"><HotelReservations><HotelReservation 

CreateDateTime="2013-03-12T18:06:03.1849712+08:00" ResStatus="S"><RoomStays><RoomStay><RatePlans><RatePlan 

RatePlanCode="10519" /></RatePlans><BasicPropertyInfo HotelCode="18392" 

/></RoomStay></RoomStays><ResGlobalInfo><DepositPayments><GuaranteePayment GuaranteeCode="N"><AmountPercent 

Amount="0.00" CurrencyCode="CNY" /></GuaranteePayment></DepositPayments><CancelPenalties><CancelPenalty 

Start="2013-3-15 0:00:00" End="2013-03-15T00:00:00.000+08:00"><AmountPercent Amount="0.00" 

CurrencyCode="CNY" /></CancelPenalty></CancelPenalties><Total AmountAfterTax="495.00" CurrencyCode="CNY" 

/><HotelReservationIDs><HotelReservationID ResID_Type="501" ResID_Value="154926579" 

/></HotelReservationIDs></ResGlobalInfo></HotelReservation></HotelReservations><Success 

/></OTA_HotelResRS></HotelResponse></Response>
	 */
}
