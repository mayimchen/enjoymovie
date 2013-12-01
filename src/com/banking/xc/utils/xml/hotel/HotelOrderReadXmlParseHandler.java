package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.ContactNumber;
import com.banking.xc.entity.Customer;
import com.banking.xc.entity.HotelReservation;
import com.banking.xc.entity.UniqueID;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelOrderReadXmlParseHandler extends XmlParseHandler{

	HotelReservation hotelReservation;
	ArrayList<UniqueID> uniqueIDList;
	ArrayList<ContactNumber> contactNumbers;
	ContactNumber contactNumber;
	Customer customer;
	
	// perRoom TODO
	String preTag;
	boolean isContact = false;
	public HotelOrderReadXmlParseHandler(XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		hotelReservation = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return hotelReservation;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "AddressLine")){
			hotelReservation.setAddressLine(data);
		}
		if(TextUtils.equals(preTag, "CityName")){
			hotelReservation.setCityName(data);
		}
		if(TextUtils.equals(preTag, "PostalCode")){
			hotelReservation.setPostalCode(data);
		}
		
		if(TextUtils.equals(preTag, "Surname")){
			if(!isContact){
				customer.setCustomerSurname(data);
			}else{
				customer.setContactSurname(data);
			}
		}
		
		if(TextUtils.equals(preTag, "Email")){
			customer.setEmail(data);
		}
		if(TextUtils.equals(preTag, "LateArrivalTime")){
			hotelReservation.setLateArrivalTime(data);
		}
		if(TextUtils.equals(preTag, "Text")){
			hotelReservation.setSpecialText(data);
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "HotelReservation")){
			hotelReservation = new HotelReservation();
			hotelReservation.setCreateDateTime(attributes.getValue("CreateDateTime"));
			hotelReservation.setCreatorID(attributes.getValue("CreatorID"));
			hotelReservation.setLastModifyDateTime(attributes.getValue("LastModifyDateTime"));
			hotelReservation.setLastModifierID(attributes.getValue("LastModifierID"));
			hotelReservation.setResStatus(attributes.getValue("ResStatus"));
		}
		/*暂不设置
		if(TextUtils.equals(localName, "UniqueID")){
			
		}*/
		if(TextUtils.equals(localName, "ContactNumbers")){
			contactNumbers = new ArrayList<ContactNumber>();
		}
		if(TextUtils.equals(localName, "ContactNumber")){
			contactNumber = new ContactNumber();
			contactNumber.setPhoneNumber(attributes.getValue("PhoneNumber"));
			contactNumber.setPhoneTechType(attributes.getValue("PhoneTechType"));
		}
		if(TextUtils.equals(localName, "ResGuest")){
			hotelReservation.setArrivalTime(attributes.getValue("ArrivalTime"));
		}
		if(TextUtils.equals(localName, "Customer")){
			customer = new Customer();
		}
		if(TextUtils.equals(localName, "ContactPerson")){
			customer.setContactType(attributes.getValue("ContactType"));
		}
		if(TextUtils.equals(localName, "Telephone")){
			customer.setPhoneTechType(attributes.getValue("PhoneTechType"));
			customer.setPhoneNumber(attributes.getValue("PhoneNumber"));
		}
		if(TextUtils.equals(localName, "GuestCounts")){
			hotelReservation.setIsPerRoom(attributes.getValue("IsPerRoom"));
		}
		if(TextUtils.equals(localName, "GuestCount")){
			hotelReservation.setGuestCount(attributes.getValue("Count"));
		}
		if(TextUtils.equals(localName, "Total")){
			hotelReservation.setAmountBeforeTax(attributes.getValue("AmountBeforeTax"));
		}
		
		preTag = localName;
	}
	
	/**
	 * <OTA_ResRetrieveRS TimeStamp="2012-09-28T15:17:25.3738203+08:00" Version="1.0" PrimaryLangID="zh" xmlns="http://www.opentravel.org/OTA/2003/05">
      <ReservationsList>
        <HotelReservation CreateDateTime="2012-09-28T14:44:56" CreatorID="HotelWebService" LastModifyDateTime="0001-01-01T00:00:00" LastModifierID="" ResStatus="W">
          <UniqueID Type="501" ID="100628170"/>
          <UniqueID Type="1" ID="wwwwww"/>
          <UniqueID Type="28" ID="1"/>
          <UniqueID Type="503" ID="50"/>
          <RoomStays>
            <RoomStay>
              <RoomTypes>
                <RoomType NumberOfUnits="1" RoomTypeCode="7367"/>
              </RoomTypes>
              <RatePlans>
                <RatePlan RatePlan Code="136129"/>
              </RatePlans>
              <BasicPropertyInfo HotelCode="51885">
                <Address>
                  <AddressLine>长宁路833号</AddressLine>
                  <CityName>上海</CityName>
                  <PostalCode>200050</PostalCode>
                </Address>
                <ContactNumbers>
                  <ContactNumber PhoneTechType="Data" PhoneNumber="021-62132222"/>
                  <ContactNumber PhoneTechType="Fax" PhoneNumber="021-62510000"/>
                </ContactNumbers>
              </BasicPropertyInfo>
            </RoomStay>
          </RoomStays>
          <BillingInstructionCode BillingCode="FG"/>
          <ResGuests>
            <ResGuest ArrivalTime="10:00:00.0000000+08:00">
              <Profiles>
                <ProfileInfo>
                  <Profile>
                    <Customer>
                      <PersonName>
                        <Surname>123</Surname>
                      </PersonName>
                      <ContactPerson ContactType="TEL">
                        <PersonName>
                          <Surname>李海亮</Surname>
                        </PersonName>
                        <Telephone PhoneTechType="Data" PhoneNumber="15900560221"/>
                        <Email>mr19830707@gmail.com</Email>
                      </ContactPerson>
                    </Customer>
                  </Profile>
                </ProfileInfo>
              </Profiles>
              <TPA_Extensions>
                <LateArrivalTime>2012-09-29 13:00:00</LateArrivalTime>
              </TPA_Extensions>
            </ResGuest>
          </ResGuests>
          <ResGlobalInfo>
            <GuestCounts IsPerRoom="false">
              <GuestCount Count="1"/>
            </GuestCounts>
            <TimeSpan Start="2012-09-29 13:00:00" End="2012-09-30 0:00:00"/>
            <SpecialRequests>
              <SpecialRequest>
                <Text>要一个电视</Text>
              </SpecialRequest>
            </SpecialRequests>
            <Guarantee/>
            <DepositPayments>
              <GuaranteePayment GuaranteeCode="4" GuaranteeType="CC/DC/Voucher">
                <AmountPercent Amount="1400.00" CurrencyCode="CNY"/>
              </GuaranteePayment>
            </DepositPayments>
            <CancelPenalties>
              <CancelPenalty Start="2012-09-28 12:00:00" End="2012-09-30 0:00:00">
                <AmountPercent Amount="1400.00" CurrencyCode="CNY"/>
              </CancelPenalty>
            </CancelPenalties>
            <Total AmountBeforeTax="1400.00" CurrencyCode="CNY"/>
            <HotelReservationIDs>
              <HotelReservationID ResID_Type="501" ResID_Value="100628170"/>
            </HotelReservationIDs>
          </ResGlobalInfo>
          <TPA_Extensions>
            <DayNightAudit/>
          </TPA_Extensions>
        </HotelReservation>
      </ReservationsList>
      <Success/>
    </OTA_ResRetrieveRS>

	 */
}
