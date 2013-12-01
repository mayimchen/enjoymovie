package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.DomesticHotelOrderDetailForList;
import com.banking.xc.utils.xml.frame.XmlParseHandler;

/**
 * 这个返回对象数据处理地十分友善
 * @author zhangyinhang
 *
 */
public class HotelOrderListXmlParseHandler extends XmlParseHandler{

	
	ArrayList<DomesticHotelOrderDetailForList> domesticHotelOrderDetailForListList;
	DomesticHotelOrderDetailForList domesticHotelOrderDetailForList;
	
	String preTag;
	public HotelOrderListXmlParseHandler(com.banking.xc.utils.xml.frame.XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		domesticHotelOrderDetailForListList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return domesticHotelOrderDetailForListList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch,start,length);
		if(TextUtils.equals(preTag,"IsMaskedOrder")){
			domesticHotelOrderDetailForList.setIsMaskedOrder(data);
		}
		if(TextUtils.equals(preTag,"BreakFastQuantity")){
			domesticHotelOrderDetailForList.setBreakFastQuantity(data);
		}
		if(TextUtils.equals(preTag,"TwinBed")){
			domesticHotelOrderDetailForList.setTwinBed(data);
		}
		if(TextUtils.equals(preTag,"KingSize")){
			domesticHotelOrderDetailForList.setKingSize(data);
		}
		if(TextUtils.equals(preTag,"ClientName")){
			domesticHotelOrderDetailForList.setClientName(data);
		}
		if(TextUtils.equals(preTag,"LocationName")){
			domesticHotelOrderDetailForList.setLocationName(data);
		}
		if(TextUtils.equals(preTag,"BalanceType")){
			domesticHotelOrderDetailForList.setBalanceType(data);
		}
		if(TextUtils.equals(preTag,"BreakfastCount")){
			domesticHotelOrderDetailForList.setBreakfastCount(data);
		}
		if(TextUtils.equals(preTag,"LastArrivalTime")){
			domesticHotelOrderDetailForList.setLastArrivalTime(data);
		}
		if(TextUtils.equals(preTag,"EarlyArrivalTime")){
			domesticHotelOrderDetailForList.setEarlyArrivalTime(data);
		}
		if(TextUtils.equals(preTag,"LastCancelTime")){
			domesticHotelOrderDetailForList.setLastCancelTime(data);
		}
		if(TextUtils.equals(preTag,"CityName")){
			domesticHotelOrderDetailForList.setCityName(data);
		}
		if(TextUtils.equals(preTag,"OrderStatus")){
			domesticHotelOrderDetailForList.setOrderStatus(data);
		}
		if(TextUtils.equals(preTag,"CheckOutDate")){
			domesticHotelOrderDetailForList.setCheckOutDate(data);
		}
		if(TextUtils.equals(preTag,"CheckInDate")){
			domesticHotelOrderDetailForList.setCheckInDate(data);
		}
		if(TextUtils.equals(preTag,"Price")){
			domesticHotelOrderDetailForList.setPrice(data);
		}
		if(TextUtils.equals(preTag,"HotelName")){
			domesticHotelOrderDetailForList.setHotelName(data);
		}
		if(TextUtils.equals(preTag,"OrderOverTime")){
			domesticHotelOrderDetailForList.setOrderOverTime(data);
		}
		if(TextUtils.equals(preTag,"OrderDate")){
			domesticHotelOrderDetailForList.setOrderDate(data);
		}
		if(TextUtils.equals(preTag,"OrderId")){
			domesticHotelOrderDetailForList.setOrderId(data);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		
		if(TextUtils.equals(localName, "DomesticHotelOrderDetailForList")){
			domesticHotelOrderDetailForListList.add(domesticHotelOrderDetailForList);
			domesticHotelOrderDetailForList = null;
		}
		
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "OrderDetailList")){
			domesticHotelOrderDetailForListList = new ArrayList<DomesticHotelOrderDetailForList>();
		}
		if(TextUtils.equals(localName, "DomesticHotelOrderDetailForList")){
			domesticHotelOrderDetailForList = new DomesticHotelOrderDetailForList();
		}
		preTag = localName;
	}
	/**
	 * 真实返回数据
	 * <?xml version="1.0"?>
	 * <Response>
	 * <Header ShouldRecordPerformanceTime="False" Timestamp="2013-03-17 14:24:36:45550" ReferenceID="2bd12c6c-ecb1-4f49-aa03-4b55777ce9d9" ResultCode="Success" />
	 * <DomesticHotelOrderListForList>
	 * 		<OrderDetailList>
	 * 			<DomesticHotelOrderDetailForList>
	 * <IsMaskedOrder>F</IsMaskedOrder>
	 * <BreakFastQuantity>2</BreakFastQuantity>
	 * <TwinBed>T</TwinBed>
	 * <KingSize>F</KingSize>
	 * <RoomNameEN>Standard Room</RoomNameEN>
	 * <HotelNameEN>Yinfa Hotel</HotelNameEN>
	 * <CityNameEN>Shanghai</CityNameEN>
	 * <ClientName>张四</ClientName>
	 * <LocationName>静安区</LocationName>
	 * <BalanceType>2</BalanceType>
	 * <Exchange>1.00000</Exchange>
	 * <Currency>RMB</Currency>
	 * <BreakfastCount>2</BreakfastCount>
	 * <Breakfast>有</Breakfast>
	 * <LastArrivalTime>2013-03-14T06:00:00</LastArrivalTime>
	 * <EarlyArrivalTime>2013-03-14T04:00:00</EarlyArrivalTime>
	 * <LastCancelTime>2013-03-15T00:00:00</LastCancelTime>
	 * <CityName>上海</CityName><CityID>2</CityID>
	 * <OrderStatus>已确认</OrderStatus>
	 * <CheckOutDate>2013-03-15T00:00:00</CheckOutDate>
	 * <CheckInDate>2013-03-14T06:00:00</CheckInDate>
	 * <Cost>435.0000</Cost>
	 * <PriceShowInfo>RMB 495.00</PriceShowInfo>
	 * <Price>495.0000</Price>
	 * <HotelName>上海银发大酒店</HotelName>
	 * <HotelID>18392</HotelID>
	 * <OrderOverTime>0001-01-01T00:00:00</OrderOverTime>
	 * <OrderDate>2013-03-12T18:06:03.05</OrderDate>
	 * <OrderId>154926579</OrderId>
	 * </DomesticHotelOrderDetailForList>
	 * </OrderDetailList><
	 * /DomesticHotelOrderListForList></Response>
	 */
}
