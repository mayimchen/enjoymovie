package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.RoomStay;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelAvailXmlParseHandler extends XmlParseHandler{

	List<RoomStay> roomStayList;
	RoomStay roomStay;
	
	public HotelAvailXmlParseHandler(XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
		
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		roomStayList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		// TODO Auto-generated method stub
		return roomStayList;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "RoomStays")){
			
		}
		if(TextUtils.equals(localName, "RoomStays")){
			roomStayList.add(roomStay);
			roomStay = null;
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "RoomStays")){
			roomStayList = new ArrayList<RoomStay>();
		}
		if(TextUtils.equals(localName, "RoomStays")){
			roomStay = new RoomStay();
			roomStay.setAvailabilityStatus(attributes.getValue("AvailabilityStatus"));
		}
	}
	
	/*
	 <HotelResponse>
    <OTA_HotelAvailRS TimeStamp="2012-09-27T16:32:35.3705695+08:00" Version="1.0" PrimaryLangID="zh" xmlns="http://www.opentravel.org/OTA/2003/05">
      <RoomStays>
        <RoomStay AvailabilityStatus="AvailableForSale">
          <RoomTypes>
            <RoomType RoomType="豪华2房2厅" RoomTypeCode="7367">
              <RoomDescription>
                <Text>主卧大床，次卧单人床^</Text>
              </RoomDescription>
            </RoomType>
          </RoomTypes>
          <RatePlans>
            <RatePlan RatePlanCode="136129" RatePlanName="豪华2房2厅" PrepaidIndicator="false">
              <MealsIncluded Breakfast="false"/>
            </RatePlan>
          </RatePlans>
          <RoomRates>
            <RoomRate RoomTypeCode="7367" RatePlanCode="136129">
              <Rates>
                <Rate EffectiveDate="2012-09-29" ExpireDate="2012-09-29" MaxGuestApplicable="3">
                  <Base AmountBeforeTax="3200.00" CurrencyCode="CNY"/>
                  <Fees>
                    <Fee Code="38" Amount="100.00" CurrencyCode="CNY" ChargeUnit="25">
                      <Description>
                        <Text>收费加床</Text>
                      </Description>
                    </Fee>
                    <Fee Code="1001" Amount="50.00" CurrencyCode="CNY" ChargeUnit="21">
                      <Description>
                        <Text>自助早餐</Text>
                      </Description>
                    </Fee>
                  </Fees>
                </Rate>
              </Rates>
              <Features>
                <Feature>
                  <Description>
                    <Text>携程客人入住每日每房可以直接抵扣50元房费。</Text>
                  </Description>
                </Feature>
              </Features>
            </RoomRate>
          </RoomRates>
          <DepositPayments>
            <GuaranteePayment GuaranteeCode="4" Start="2012-09-29 00:00:00" End="2012-09-29 00:00:00">
              <AmountPercent Amount="3200.00" CurrencyCode="CNY"/>
              <Description>
                <Text>Guarantee required. If no-show occurs and without cancellation before the latest cancellation/modification date, first day rate will be charged</Text>
              </Description>
            </GuaranteePayment>
          </DepositPayments>
          <CancelPenalties>
            <CancelPenalty Start="2012-9-28 12:00:00" End="2012-09-30 19:00:00">
              <AmountPercent Amount="3200.00" CurrencyCode="CNY"/>
            </CancelPenalty>
          </CancelPenalties>
          <Total AmountBeforeTax="3200.00" CurrencyCode="CNY"/>
        </RoomStay>
      </RoomStays>
      <Success/>
    </OTA_HotelAvailRS>
  </HotelResponse>

	 */
}
