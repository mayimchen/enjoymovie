package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.Hotel;
import com.banking.xc.entity.HotelAward;
import com.banking.xc.entity.HotelRelativePosition;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelSearchXmlParseHandler extends XmlParseHandler{

	private List<Hotel> hotelList;// = new ArrayList<Hotel>();
	private Hotel hotel ;//= new Hotel();
	private HotelAward hotelAward; //= new HotelAward();
	private List<HotelAward> hotelAwardList;// = new ArrayList<HotelAward>();
	private HotelRelativePosition hotelRelativePosition;// = new HotelRelativePosition();
	private List<HotelRelativePosition> hotelRelativePositionList;// = new ArrayList<HotelRelativePosition>();
	private String preTag;
	
	public HotelSearchXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getObjectWhenEnd() {
		// TODO Auto-generated method stub
		return hotelList;
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
		super.endDocument();
	}

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
		if(TextUtils.equals(localName, "Properties"))
		{
			hotelList = new ArrayList<Hotel>(); 
		}
		if(TextUtils.equals(localName, "Property"))
		{
			hotel = new Hotel();
			hotelAwardList = new ArrayList<HotelAward>();
			hotelRelativePositionList = new ArrayList<HotelRelativePosition>();
			
			hotel.setHotelCode(attributes.getValue("HotelCode"));
			hotel.setHotelCityCode(attributes.getValue("HotelCityCode"));
			hotel.setHotelName(attributes.getValue("HotelName"));
			hotel.setAreaID(attributes.getValue("AreaID"));
			return;
		}
		if(TextUtils.equals(localName, "Position"))
		{
			hotel.setLatitude(attributes.getValue("Latitude"));
			hotel.setLongitude(attributes.getValue("Longitude"));
		}
		if(TextUtils.equals(localName, "Award"))
		{
			hotelAward = new HotelAward();
			hotelAward.setProvider(attributes.getValue("Provider"));
			hotelAward.setRating(attributes.getValue("Rating"));
		}
		if(TextUtils.equals(localName, "RelativePosition"))
		{
			hotelRelativePosition = new HotelRelativePosition();
			hotelRelativePosition.setDistance(attributes.getValue("Distance"));
			hotelRelativePosition.setUnitOfMeasureCode(attributes.getValue("UnitOfMeasureCode"));
			hotelRelativePosition.setName(attributes.getValue("Name"));
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "Properties"))
		{
			//do nothing
		}
		if(TextUtils.equals(localName, "Property"))
		{
			hotel.setHotelAwards(hotelAwardList);
			hotel.setHotelRelativePositions(hotelRelativePositionList);
			hotelList.add(hotel);
			hotel = null;
			hotelAwardList = null;
			hotelRelativePositionList = null;		
		}
		if(TextUtils.equals(localName, "Award")){
			hotelAwardList.add(hotelAward);
			hotelAward = null;		
		}
		if(TextUtils.equals(localName, "RelativePosition"))
		{
			hotelRelativePositionList.add(hotelRelativePosition);
			hotelRelativePosition = null;
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	
			final String data = new String(ch,start,length);
			if("Text".equals(preTag)){
				//直接这样处理吧，后面还有其他的Text
				if(TextUtils.isEmpty(hotel.getImageUrl())){
					hotel.setImageUrl(data);	
				}
			}else if ("AddressLine".equals(preTag)){
				hotel.setAddressLine(data);
			}else if ("CityName".equals(preTag)){
				hotel.setCityName(data);
			}else if ("PostalCode".equals(preTag)){
				hotel.setPostalCode(data);
			}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		hotelList = null;
	}
	
	
	/**
	 *  <HotelResponse>
    <OTA_HotelSearchRS TimeStamp="2012-09-26T14:38:27.1360755+08:00" Version="1.0" PrimaryLangID="zh" xmlns="http://www.opentravel.org/OTA/2003/05">
      <Properties>
        <Property HotelCode="18392" HotelCityCode="2" HotelName="上海银发大酒店" AreaID="112">
          <VendorMessages>
            <VendorMessage InfoType="23">
              <SubSection>
                <Paragraph>
                  <Text>http://Images4.c-ctrip.com/target/hotel/19000/18392/27EC273D3C2C4569A345350C5B7E1797_100_75.Jpg</Text>
                </Paragraph>
              </SubSection>
            </VendorMessage>
          </VendorMessages>
          <Position Latitude="31.22936" Longitude="121.45935"/>
          <Address>
            <AddressLine>北京西路1068号</AddressLine>
            <CityName>上海</CityName>
            <PostalCode>200041</PostalCode>
          </Address>
          <Award Provider="HotelStarRate" Rating="3"/>
          <Award Provider="CtripStarRate" Rating="3"/>
          <Award Provider="CtripRecommendRate" Rating="3.0"/>
          <Award Provider="CtripCommRate" Rating="3.9"/>
          <Award Provider="CommSurroundingRate" Rating="4.3"/>
          <Award Provider="CommFacilityRate" Rating="3.5"/>
          <Award Provider="CommCleanRate" Rating="4"/>
          <Award Provider="CommServiceRate" Rating="4"/>
          <RelativePosition Distance="10.26" UnitOfMeasureCode="2" Name="上海火车南站"/>
          <RelativePosition Distance="42.39" UnitOfMeasureCode="2" Name="上海浦东国际机场"/>
          <RelativePosition Distance="16.6" UnitOfMeasureCode="2" Name="虹桥火车站"/>
          <RelativePosition Distance="12.56" UnitOfMeasureCode="2" Name="上海虹桥机场（一号航站楼）"/>
          <RelativePosition Distance="16.15" UnitOfMeasureCode="2" Name="上海虹桥机场（二号航站楼）"/>
          <RelativePosition Distance="2" UnitOfMeasureCode="2" Name="静安寺"/>
          <RelativePosition Distance="2" UnitOfMeasureCode="2" Name="人民广场"/>
          <RelativePosition Distance="4" UnitOfMeasureCode="2" Name="上海火车站"/>
        </Property>
        <Property BrandCode="53" HotelCode="6698" HotelCityCode="2" HotelName="上海静安星程赣园宾馆" AreaID="112">
          <VendorMessages>
            <VendorMessage InfoType="23">
              <SubSection>
                <Paragraph>
                  <Text>http://Images4.c-ctrip.com/target/hotel/7000/6698/4A8F0913-1F49-4628-BB62-E1D92A0B362D_100_75.jpg</Text>
                </Paragraph>
              </SubSection>
            </VendorMessage>
          </VendorMessages>
          <Position Latitude="31.23336" Longitude="121.441"/>
          <Address>
            <AddressLine>余姚路417号</AddressLine>
            <CityName>上海</CityName>
            <PostalCode>200042</PostalCode>
          </Address>
          <Award Provider="HotelStarRate" Rating="3"/>
          <Award Provider="CtripStarRate" Rating="3"/>
          <Award Provider="CtripRecommendRate" Rating="3.0"/>
          <Award Provider="CtripCommRate" Rating="3.6"/>
          <Award Provider="CommSurroundingRate" Rating="3.5"/>
          <Award Provider="CommFacilityRate" Rating="3.4"/>
          <Award Provider="CommCleanRate" Rating="3.8"/>
          <Award Provider="CommServiceRate" Rating="3.7"/>
          <RelativePosition Distance="14.6" UnitOfMeasureCode="2" Name="上海火车南站"/>
          <RelativePosition Distance="43.53" UnitOfMeasureCode="2" Name="上海浦东国际机场"/>
          <RelativePosition Distance="17.76" UnitOfMeasureCode="2" Name="虹桥火车站"/>
          <RelativePosition Distance="13.72" UnitOfMeasureCode="2" Name="上海虹桥机场（一号航站楼）"/>
          <RelativePosition Distance="17.31" UnitOfMeasureCode="2" Name="上海虹桥机场（二号航站楼）"/>
          <RelativePosition Distance="3" UnitOfMeasureCode="2" Name="静安寺"/>
          <RelativePosition Distance="4.63" UnitOfMeasureCode="2" Name="人民广场"/>
          <RelativePosition Distance="3.56" UnitOfMeasureCode="2" Name="上海火车站"/>
        </Property>
      </Properties>
      <Success/>
    </OTA_HotelSearchRS>
  </HotelResponse>

	 */
	
}
