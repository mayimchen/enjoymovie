package com.banking.xc.utils.webService.request.flight;

import com.banking.xc.entity.FlightContactInfo;
import com.banking.xc.entity.FlightData;
import com.banking.xc.entity.FlightInfo;
import com.banking.xc.entity.FlightPayInfo;
import com.banking.xc.entity.Passenger;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class FlightSaveOrderRequest extends FlightRequest{

	private final String requestType = RequestConstant.FLIGHT_SAVE_ORDER;
	private String uid;
	private String orderType;
	private String amount;
	private String ProcessDesc;//省略
	
	//FlightInfo
	private FlightInfo flightInfo;
	private Passenger passenger;
	private FlightContactInfo contactInfo; //可为空
	//DeivceInfo忽略
	private FlightPayInfo payInfo;//可为空,主要包含信用卡信息。
	
	
	@Override
	public String getFlightParams() {
		XmlNode fltSaveOrderRequestNode = new XmlNode("FltSaveOrderRequest");
		fltSaveOrderRequestNode.addNodeByNameAndValue("UID",getUid());
		fltSaveOrderRequestNode.addNodeByNameAndValue("OrderType", getOrderType());//ADU
		fltSaveOrderRequestNode.addNodeByNameAndValue("Amount", getAmount());
		fltSaveOrderRequestNode.addNodeByNameAndValue("ProcessDesc", getProcessDesc());//订单处理描述
		
		XmlNode FlightInfoListNode = new XmlNode("FlightInfoList");
		fltSaveOrderRequestNode.addChildNode(FlightInfoListNode);
		
		XmlNode FlightInfoRequestNode = new XmlNode("FlightInfoRequest");
		FlightInfoListNode.addChildNode(FlightInfoRequestNode);
		
		FlightInfo flightInfo = getFlightInfo();
		FlightData flightData = flightInfo.getFlightData();
		FlightInfoRequestNode.addNodeByNameAndValue("DepartCityID", flightData.getDepartCityCode());
		FlightInfoRequestNode.addNodeByNameAndValue("ArriveCityID", flightData.getArriveCityCode());
		FlightInfoRequestNode.addNodeByNameAndValue("DPortCode", flightData.getDepartAirportCode());
		FlightInfoRequestNode.addNodeByNameAndValue("APortCode", flightData.getArriveAirportCode());
		FlightInfoRequestNode.addNodeByNameAndValue("AirlineCode", flightData.getAirlineDibitCode());
		FlightInfoRequestNode.addNodeByNameAndValue("Flight", flightData.getFlight());
		FlightInfoRequestNode.addNodeByNameAndValue("Class", "Y");
		FlightInfoRequestNode.addNodeByNameAndValue("SubClass", "H");
		FlightInfoRequestNode.addNodeByNameAndValue("TakeOffTime", flightData.getTakeOffTime());
		FlightInfoRequestNode.addNodeByNameAndValue("ArrivalTime", flightData.getArriveTime());
		FlightInfoRequestNode.addNodeByNameAndValue("Rate", flightData.getRate());
		FlightInfoRequestNode.addNodeByNameAndValue("Price", flightData.getPrice());
		FlightInfoRequestNode.addNodeByNameAndValue("Tax", flightData.getAdultTax());
		FlightInfoRequestNode.addNodeByNameAndValue("OilFee", flightData.getAdultOilFee());
		FlightInfoRequestNode.addNodeByNameAndValue("NonRer", flightData.getNonrer());
		FlightInfoRequestNode.addNodeByNameAndValue("NonRef", flightData.getNonref());
		FlightInfoRequestNode.addNodeByNameAndValue("NonEnd", flightData.getNonend());
		FlightInfoRequestNode.addNodeByNameAndValue("RerNote", flightData.getRerNote());
		FlightInfoRequestNode.addNodeByNameAndValue("RefNote", flightData.getRefNote());
		FlightInfoRequestNode.addNodeByNameAndValue("EndNote", flightData.getEndNote());
		FlightInfoRequestNode.addNodeByNameAndValue("Remark", flightData.getRemarks());
		FlightInfoRequestNode.addNodeByNameAndValue("EndNote", flightData.getEndNote());
		FlightInfoRequestNode.addNodeByNameAndValue("NeedAppl", "F");
		FlightInfoRequestNode.addNodeByNameAndValue("Recommend", "2");
		FlightInfoRequestNode.addNodeByNameAndValue("Canpost", flightData.getCanPost());
		FlightInfoRequestNode.addNodeByNameAndValue("CraftType", flightData.getCraftType());
		FlightInfoRequestNode.addNodeByNameAndValue("Quantity", flightData.getQuantity());
		FlightInfoRequestNode.addNodeByNameAndValue("Cost", "0");
		FlightInfoRequestNode.addNodeByNameAndValue("CostRate", "1.0");
		FlightInfoRequestNode.addNodeByNameAndValue("RefundFeeFormulaID", "0");
		FlightInfoRequestNode.addNodeByNameAndValue("UpGrade", "T");
		FlightInfoRequestNode.addNodeByNameAndValue("TicketType", flightData.getTicketType());
		FlightInfoRequestNode.addNodeByNameAndValue("AllowCPType", "");
		FlightInfoRequestNode.addNodeByNameAndValue("ProductType", flightData.getProductType());
		FlightInfoRequestNode.addNodeByNameAndValue("CanSeparateSale", "Y");
		FlightInfoRequestNode.addNodeByNameAndValue("RouteIndex", "1");
		FlightInfoRequestNode.addNodeByNameAndValue("InventoryType", "FIX");
		//两程航班必填FIX:定额 FAV :见舱两程时一程见舱一程定额不可预定
		FlightInfoRequestNode.addNodeByNameAndValue("ProductSource", flightData.getProductSource());
		
		
		
		
		
		//还有很多元素没加入
		
		
		XmlNode PassengerListNode = new XmlNode("PassengerList");
		fltSaveOrderRequestNode.addChildNode(PassengerListNode);
		XmlNode PassengerRequestNode = new XmlNode("PassengerRequest");
		PassengerListNode.addChildNode(PassengerRequestNode);
		Passenger passenger = getPassenger();
		PassengerRequestNode.addNodeByNameAndValue("PassengerName", passenger.getPassengerName());
		PassengerRequestNode.addNodeByNameAndValue("BirthDay", passenger.getBirthDay());
		PassengerRequestNode.addNodeByNameAndValue("PassportTypeID", passenger.getPassportTypeID());
		PassengerRequestNode.addNodeByNameAndValue("PassportNo", passenger.getPassportNo());
		PassengerRequestNode.addNodeByNameAndValue("ContactTelephone ", passenger.getContactTelephone());
		PassengerRequestNode.addNodeByNameAndValue("Gender", passenger.getGender());
		PassengerRequestNode.addNodeByNameAndValue("InsuranceNum", passenger.getInsuranceNum());
		PassengerRequestNode.addNodeByNameAndValue("NationalityCode", passenger.getNationalityCode());
		
		
		FlightPayInfo flightPayInfo = getPayInfo();
		if(flightPayInfo!=null){
			XmlNode CreditCardInfoNode = new XmlNode("CreditCardInfo");
			fltSaveOrderRequestNode.addChildNode(CreditCardInfoNode);
			CreditCardInfoNode.addNodeByNameAndValue("CreditCardType", flightPayInfo.getCreditCardType());
			CreditCardInfoNode.addNodeByNameAndValue("CardTypeName", flightPayInfo.getCardTypeName());
			CreditCardInfoNode.addNodeByNameAndValue("CreditCardNumber", flightPayInfo.getCreditCardNumber());
			CreditCardInfoNode.addNodeByNameAndValue("CardTypeName", flightPayInfo.getCardTypeName());
			CreditCardInfoNode.addNodeByNameAndValue("Validity", flightPayInfo.getValidity());
			CreditCardInfoNode.addNodeByNameAndValue("CardBin", flightPayInfo.getCardBin());
			CreditCardInfoNode.addNodeByNameAndValue("CardHolder", flightPayInfo.getCardHolder());
			CreditCardInfoNode.addNodeByNameAndValue("IdCardType", flightPayInfo.getIdCardType());
			CreditCardInfoNode.addNodeByNameAndValue("IdNumber", flightPayInfo.getIdNumber());
			CreditCardInfoNode.addNodeByNameAndValue("CVV2No", flightPayInfo.getCVV2No());
			CreditCardInfoNode.addNodeByNameAndValue("AgreementCode", flightPayInfo.getAgreementCode());
		}
		return fltSaveOrderRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getProcessDesc() {
		return ProcessDesc;
	}

	public void setProcessDesc(String processDesc) {
		ProcessDesc = processDesc;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public FlightInfo getFlightInfo() {
		return flightInfo;
	}

	public void setFlightInfo(FlightInfo flightInfo) {
		this.flightInfo = flightInfo;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public FlightContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(FlightContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public FlightPayInfo getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(FlightPayInfo payInfo) {
		this.payInfo = payInfo;
	}
	
	/**
	 * <FltSaveOrderRequest>
		<UID>159c906a-aa28-4f54-b609-59d2c105fde2</UID>
		<OrderType>ADU</OrderType>
		<Amount>1590</Amount>
		<ProcessDesc></ProcessDesc>
		<FlightInfoList>
			<FlightInfoRequest>
				<DepartCityID>1</DepartCityID>
				<ArriveCityID>30</ArriveCityID>
				<DPortCode>PEK</DPortCode>
				<APortCode>SZX</APortCode>
				<AirlineCode>CZ</AirlineCode>
				<Flight>CZ3160</Flight>
				<Class>Y</Class>
				<SubClass>H</SubClass>
				<TakeOffTime>2012-07-20T15:00:00</TakeOffTime>
				<ArrivalTime>2012-07-20T17:50:00</ArrivalTime>
				<Rate>1.0</Rate>
				<Price>1400</Price>
				<Tax>50</Tax>
				<OilFee>140.0000</OilFee>
				<NonRer>T</NonRer>
				<NonRef>T</NonRef>
				<NonEnd>T</NonEnd>
				<RerNote>不得更改。</RerNote>
				<RefNote>不得退票。</RefNote>
				<EndNote>不得签转。</EndNote>
				<Remark>yeye特价产品^</Remark>
				<NeedAppl>F</NeedAppl>
				<Recommend>2</Recommend>
				<Canpost>T</Canpost>
				<CraftType>333</CraftType>
				<Quantity>10</Quantity>
				<Cost>0</Cost>
				<CostRate>1.0</CostRate>
				<RefundFeeFormulaID>0</RefundFeeFormulaID>
				<UpGrade>T</UpGrade>
				<TicketType>1111</TicketType>
				<AllowCPType>1111</AllowCPType>
				<DeliverTicketType>1101</DeliverTicketType>
				<ProductType />
				<CanSeparateSale />
				<RouteIndex>0</RouteIndex>
                   <InventoryType>FAV</ InventoryType>
                   < ProductSource>1< ProductSource>
			</FlightInfoRequest>
		</FlightInfoList>
		<PassengerList>
			<PassengerRequest>
				<PassengerName>携程</PassengerName>
				<BirthDay>1984-01-01</BirthDay>
				<PassportTypeID>1</PassportTypeID>
				<PassportNo>341224198405174912</PassportNo>
				<ContactTelephone />
				<Gender>M</Gender>
				<InsuranceNum>0</InsuranceNum>
				<NationalityCode />
			</PassengerRequest>
		</PassengerList>
		<!--
		<ContactInfo><!--可为空-->
			<ContactName>携程</ContactName>
			<ConfirmOption>TEL</ConfirmOption>
			<MobilePhone>18015021548</MobilePhone>
			<ContactTel />
			<ForeignMobile />
			<MobileCountryFix />
			<ContactEMail>ee@ctrip.com</ContactEMail>
			<ContactFax />
		</ContactInfo>
		<DeliverInfo><!--可为空-->
			<DeliveryType>PJN</DeliveryType>
			<SendTicketCityID>0</SendTicketCityID>
			<OrderRemark></OrderRemark>
			<PJS>
				<Receiver />
				<Province />
				<City />
				<Canton />
				<Address />
				<PostCode />
			</PJS>
		</DeliverInfo>
		<PayInfo><!--可为空-->
			<CreditCardInfo><!--可为空-->
				<CardInfoID>0</CardInfoID>
				<CreditCardType>11</CreditCardType>
				<CreditCardNumber>hiNI6GWod48+siR777rXyLgBgE0dF6f4</CreditCardNumber>
				<Validity>gSb+/Uj5DEE=</Validity>
				<CardBin>uffYuvmpsOg=</CardBin>
				<CardHolder>5/bqCaNz3yw=</CardHolder>
				<IdCardType>gBG1pcTHP+M=</IdCardType>
				<IdNumber>EDXkCmkgwpjSs25jdmMPk6hi0ZpuQ1mV</IdNumber>
				<CVV2No>0DXVwD+y96Y=</CVV2No>
				<AgreementCode></AgreementCode>
				<Eid></Eid>
				<Remark></Remark>
				<IsClient>true</IsClient>
				<CCardPayFee>0</CCardPayFee>
				<CCardPayFeeRate>0</CCardPayFeeRate>
				<Exponent>0</Exponent>
				<ExchangeRate></ExchangeRate>
				<FAmount></FAmount>
			</CreditCardInfo>
		</PayInfo>-->
	</FltSaveOrderRequest>

	 */
}
