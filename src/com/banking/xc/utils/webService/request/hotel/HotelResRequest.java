package com.banking.xc.utils.webService.request.hotel;

import java.util.List;

import com.banking.xc.entity.Customer;
import com.banking.xc.entity.UniqueID;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 酒店订单生成
 * @author zhangyinhang
 *
 */
public class HotelResRequest extends HotelRequest {
	/**
	 * 504,固定100000
	 * 28 AllianceID,
	 * 503 SID,
	 * 1用户unique ID,
	 * 501表示订单号
	 */
	private final  String requestType = RequestConstant.HOTEL_RES;
	private final String TAG = "HotelResRequest";
	
	private List<UniqueID> uniqueIDList; 
	private String numberOfUnits;
	private String ratePlanCode;
	private String hotelCode;
	private String arrivalTime;
	
	private Customer customer;
	private String lateArrivalTime;
	private boolean isPerRoom;
	private String guestCount;
	private String start;
	private String end;
	private String sprcialRequestTest;
	
	private String DepositPayment; //可为空
	private String totalAmountBeforeTax; //总价，不可空
	private String totalCurrencyCode;
	
	private String penaltyStart; //取消订单的罚金，可为空
	private String penaltyEnd;
	private String penaltyAmout;
	private String penaltyCurrencyCode;
	
	
	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getLateArrivalTime() {
		return lateArrivalTime;
	}

	public void setLateArrivalTime(String lateArrivalTime) {
		this.lateArrivalTime = lateArrivalTime;
	}

	public String getGuestCount() {
		return guestCount;
	}

	public void setGuestCount(String guestCount) {
		this.guestCount = guestCount;
	}

	public List<UniqueID> getUniqueIDList() {
		return uniqueIDList;
	}

	public void setUniqueIDList(List<UniqueID> uniqueIDs) {
		this.uniqueIDList = uniqueIDs;
	}

	public String getNumberOfUnits() {
		return numberOfUnits;
	}

	public void setNumberOfUnits(String numberOfUnits) {
		this.numberOfUnits = numberOfUnits;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public boolean isPerRoom() {
		return isPerRoom;
	}

	public void setPerRoom(boolean isPerRoom) {
		this.isPerRoom = isPerRoom;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getSprcialRequestTest() {
		return sprcialRequestTest;
	}

	public void setSprcialRequestTest(String sprcialRequestTest) {
		this.sprcialRequestTest = sprcialRequestTest;
	}

	public String getDepositPayment() {
		return DepositPayment;
	}

	public void setDepositPayment(String depositPayment) {
		DepositPayment = depositPayment;
	}

	public String getTotalAmountBeforeTax() {
		return totalAmountBeforeTax;
	}

	public void setTotalAmountBeforeTax(String totalAmountBeforeTax) {
		this.totalAmountBeforeTax = totalAmountBeforeTax;
	}

	public String getTotalCurrencyCode() {
		return totalCurrencyCode;
	}

	public void setTotalCurrencyCode(String totalCurrencyCode) {
		this.totalCurrencyCode = totalCurrencyCode;
	}

	public String getPenaltyStart() {
		return penaltyStart;
	}

	public void setPenaltyStart(String penaltyStart) {
		this.penaltyStart = penaltyStart;
	}

	public String getPenaltyEnd() {
		return penaltyEnd;
	}

	public void setPenaltyEnd(String penaltyEnd) {
		this.penaltyEnd = penaltyEnd;
	}

	public String getPenaltyAmout() {
		return penaltyAmout;
	}

	public void setPenaltyAmout(String penaltyAmout) {
		this.penaltyAmout = penaltyAmout;
	}

	public String getPenaltyCurrencyCode() {
		return penaltyCurrencyCode;
	}

	public void setPenaltyCurrencyCode(String penaltyCurrencyCode) {
		this.penaltyCurrencyCode = penaltyCurrencyCode;
	}

	public String getTAG() {
		return TAG;
	}

	@Override
	public String getHotelParams() {
		// TODO Auto-generated method stub
		
		final XmlNode hotelResNode = new XmlNode("ns:OTA_HotelResRQ"); 
		hotelResNode.putAttribute("Version", "1.0");
		hotelResNode.putAttribute("TimeStamp", "2013-03-12T18:26:00.000+08:00"); //还是用当前日期
		
		for(int i=0;i<getUniqueIDList().size();i++){
			final XmlNode uniqueIDNode = new XmlNode("ns:UniqueID");
			uniqueIDNode.putAttribute("Type",getUniqueIDList().get(i).getType());
			uniqueIDNode.putAttribute("ID", getUniqueIDList().get(i).getId());
			hotelResNode.addChildNode(uniqueIDNode);
		}
		
		final XmlNode hotelReservationsNode = new XmlNode("ns:HotelReservations"); 
		hotelResNode.addChildNode(hotelReservationsNode);
		
		final XmlNode hotelReservationNode = new XmlNode("ns:HotelReservation"); 
		hotelReservationsNode.addChildNode(hotelReservationNode);
		
		final XmlNode roomStaysNode = new XmlNode("ns:RoomStays"); 
		hotelReservationNode.addChildNode(roomStaysNode);
		final XmlNode roomStayNode = new XmlNode("ns:RoomStay"); 
		roomStaysNode.addChildNode(roomStayNode);
		
		final XmlNode roomTypesNode = new XmlNode("ns:RoomTypes"); 
		roomStayNode.addChildNode(roomTypesNode);
		final XmlNode roomTypeNode = new XmlNode("ns:RoomType"); 
		roomTypeNode.putAttribute("NumberOfUnits", getNumberOfUnits());
		roomTypesNode.addChildNode(roomTypeNode);
		
		final XmlNode roomPlansNode = new XmlNode("ns:RatePlans"); 
		roomStayNode.addChildNode(roomPlansNode);	
		final XmlNode roomPlanNode = new XmlNode("ns:RatePlan"); 
		roomPlanNode.putAttribute("RatePlanCode", getRatePlanCode());
		roomPlansNode.addChildNode(roomPlanNode);
		
		final XmlNode basicPropertyInfoNode = new XmlNode("ns:BasicPropertyInfo");
		basicPropertyInfoNode.putAttribute("HotelCode", getHotelCode());
		roomStayNode.addChildNode(basicPropertyInfoNode);	
		
		final XmlNode resGuestsNode = new XmlNode("ns:ResGuests");
		hotelReservationNode.addChildNode(resGuestsNode);
		final XmlNode resGuestNode = new XmlNode("ns:ResGuest");
		resGuestNode.putAttribute("ArrivalTime",getArrivalTime());
		resGuestsNode.addChildNode(resGuestNode);
		
		final XmlNode profilesNode = new XmlNode("ns:Profiles");
		resGuestNode.addChildNode(profilesNode);
		final XmlNode profileInfoNode = new XmlNode("ns:ProfileInfo");
		profilesNode.addChildNode(profileInfoNode);
		final XmlNode profileNode = new XmlNode("ns:Profile");
		profileInfoNode.addChildNode(profileNode);
		
		final XmlNode customerNode = new XmlNode("ns:Customer");
		profileNode.addChildNode(customerNode);
		final XmlNode personNameNode = new XmlNode("ns:PersonName");
		customerNode.addChildNode(personNameNode);
		final XmlNode surNameNode = new XmlNode("ns:Surname");
		surNameNode.setInnerValue(getCustomer().getCustomerSurname());
		personNameNode.addChildNode(surNameNode);
		
		final XmlNode contactPersonNode = new XmlNode("ns:ContactPerson");
		contactPersonNode.putAttribute("ContactType", getCustomer().getContactType());
		customerNode.addChildNode(contactPersonNode);
		final XmlNode personName2Node = new XmlNode("ns:PersonName");
		contactPersonNode.addChildNode(personName2Node);
		final XmlNode surName2Node = new XmlNode("ns:Surname");
		surName2Node.setInnerValue(getCustomer().getContactSurname());
		personName2Node.addChildNode(surNameNode);
		final XmlNode telephoneNode = new XmlNode("ns:Telephone");
		telephoneNode.putAttribute("PhoneNumber", getCustomer().getPhoneNumber());
		telephoneNode.putAttribute("PhoneTechType", getCustomer().getPhoneTechType());
		contactPersonNode.addChildNode(telephoneNode);
		
		final XmlNode extensionsNode = new XmlNode("ns:TPA_Extensions");
		resGuestNode.addChildNode(extensionsNode);
		final XmlNode lateArrivalTimeNode = new XmlNode("ns:LateArrivalTime");
		lateArrivalTimeNode.setInnerValue(getLateArrivalTime());
		System.out.println("XC time"+getLateArrivalTime());
		extensionsNode.addChildNode(lateArrivalTimeNode);
		
		final XmlNode resGlobalInfoNode = new XmlNode("ns:ResGlobalInfo");
		hotelReservationNode.addChildNode(resGlobalInfoNode);
		final XmlNode guestCountsNode = new XmlNode("ns:GuestCounts");
		guestCountsNode.putAttribute("IsPerRoom", String.valueOf(isPerRoom()));
		resGlobalInfoNode.addChildNode(guestCountsNode);
		final XmlNode guestCountNode = new XmlNode("ns:GuestCount");
		guestCountNode.putAttribute("Count", getGuestCount());
		guestCountsNode.addChildNode(guestCountNode);
		
		final XmlNode timeSpanNode = new XmlNode("ns:TimeSpan");
		timeSpanNode.putAttribute("Start", getStart());
		timeSpanNode.putAttribute("End", getEnd());
		resGlobalInfoNode.addChildNode(timeSpanNode);
		
		final XmlNode specialRequestsNode = new XmlNode("ns:SpecialRequests");
		resGlobalInfoNode.addChildNode(specialRequestsNode);
		final XmlNode specialRequestNode = new XmlNode("ns:SpecialRequest");
		specialRequestsNode.addChildNode(specialRequestNode);
		final XmlNode textNode = new XmlNode("ns:Text");
		specialRequestNode.addChildNode(textNode);
		
		
		//DepositPayments可选的
		
		final XmlNode totalNode = new XmlNode("ns:Total");
		totalNode.putAttribute("AmountBeforeTax", getTotalAmountBeforeTax());
		totalNode.putAttribute("CurrencyCode", getTotalCurrencyCode());
		resGlobalInfoNode.addChildNode(totalNode);
		
		//CancelPenalties可选的
		System.out.println("XC hotelResRequest:"+hotelResNode.toString());
		return hotelResNode.toString();
	}

	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <ns:OTA_HotelResRQ TimeStamp="2012-09-01T03:15:57" Version="1.0">
        <ns:UniqueID Type="504" ID="100000"/>
        <ns:UniqueID Type="28" ID="1"/>
        <ns:UniqueID Type="503" ID="50"/>
        <ns:UniqueID Type="1" ID="c563a9ed-a090-4ded-b5dc-ddf1d3709e29"/>
        <ns:HotelReservations>
          <ns:HotelReservation>
            <ns:RoomStays>
              <ns:RoomStay>
                <ns:RoomTypes>
                  <ns:RoomType NumberOfUnits="1"/>
                </ns:RoomTypes>
                <ns:RatePlans>
                  <ns:RatePlan RatePlanCode="136129"/>
                </ns:RatePlans>
                <ns:BasicPropertyInfo HotelCode="51885"/>
              </ns:RoomStay>
            </ns:RoomStays>
            <ns:ResGuests>
              <ns:ResGuest ArrivalTime="10:00:00+08:00">
                <ns:Profiles>
                  <ns:ProfileInfo>
                    <ns:Profile>
                      <ns:Customer>
                        <ns:PersonName>
                          <ns:Surname>123</ns:Surname>
                        </ns:PersonName>
                        <ns:ContactPerson ContactType="tel">
                          <ns:PersonName>
                            <ns:Surname>李海亮</ns:Surname>
                          </ns:PersonName>
                          <ns:Telephone PhoneNumber="15900560221" PhoneTechType="1"/>
                          <ns:Email>mr19830707@gmail.com</ns:Email>
                        </ns:ContactPerson>
                      </ns:Customer>
                    </ns:Profile>
                  </ns:ProfileInfo>
                </ns:Profiles>
                <ns:TPA_Extensions>
                  <ns:LateArrivalTime>2012-09-29T13:00:00+08:00</ns:LateArrivalTime>
                </ns:TPA_Extensions>
              </ns:ResGuest>
            </ns:ResGuests>
            <ns:ResGlobalInfo>
              <ns:GuestCounts IsPerRoom="false">
                <ns:GuestCount Count="1"/>
              </ns:GuestCounts>
              <ns:TimeSpan Start="2012-09-29T12:00:00+08:00" End="2012-09-30T12:00:00+08:00"/>
              <ns:SpecialRequests>
                <ns:SpecialRequest>
                  <ns:Text>要一个电视</ns:Text>
                </ns:SpecialRequest>
              </ns:SpecialRequests>
              <ns:DepositPayments>
                <ns:GuaranteePayment GuaranteeType="CC/DC/Voucher">
                  <ns:AcceptedPayments>
                    <ns:AcceptedPayment>
                      <ns:PaymentCard CardType="gBG1pcTHP+M=" CardNumber="jhXJzaRCoGreZXTmKx9fyFwyRJZqWimr" SeriesCode="cbyXWJ06Bx8=" EffectiveDate="pjUanDE3Ta8=" ExpireDate="pjUanDE3Ta8=">
                        <ns:CardHolderName>ASq6ZwnS3tKI8lqh+lL4YQ==</ns:CardHolderName>
                        <ns:CardHolderIDCard>AzoUFk527GHntp1pJryYhB+V4fuM75/1</ns:CardHolderIDCard>
                      </ns:PaymentCard>
                    </ns:AcceptedPayment>
                  </ns:AcceptedPayments>
                  <ns:AmountPercent Amount="1400"/>
                </ns:GuaranteePayment>
              </ns:DepositPayments>
              <ns:Total AmountBeforeTax="1400" CurrencyCode="CNY"/>
              <ns:CancelPenalties>
                <ns:CancelPenalty Start="2012-09-28T12:00:00" End="2012-09-30T15:00:00">
                  <ns:AmountPercent Amount="1400.00" CurrencyCode="CNY"/>
                </ns:CancelPenalty>
              </ns:CancelPenalties>
            </ns:ResGlobalInfo>
          </ns:HotelReservation>
        </ns:HotelReservations>
      </ns:OTA_HotelResRQ>
	 */
}
