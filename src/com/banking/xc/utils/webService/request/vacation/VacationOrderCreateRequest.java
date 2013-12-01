package com.banking.xc.utils.webService.request.vacation;

import java.util.ArrayList;

import com.banking.xc.entity.PkgClient;
import com.banking.xc.entity.PkgT3Segment;
import com.banking.xc.entity.PkgT3SegmentDemo;
import com.banking.xc.entity.VacationSegment;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 度假临时订单生产，关键。看这个request需要什么就好
 * 已经确定适合segment相关的，不写segment这个订单没意义
 * 其实基本就是segment里面hotel和flight价格相加
 * @author zhangyinhang
 *
 */
public class VacationOrderCreateRequest extends VacationRequest{

	private final String requestType = RequestConstant.VACATION_ORDER_CREATE;
	//PkgOrder
	private String TMoney = "0.0000";
	private String IsTemporaryOrder = "true";
	private String TemporaryOrderDate = "2012-05-09";//用现在日期会报SQL错误
	private String ReceiptGuid = "00000000-0000-0000-0000-000000000000";//收银标示
	private String SubPaySystem = "0";//支付子系统ID;
	private String IsFlightelOrder = "false";
	private String OnlyPassport = "false";
	private String Mileage = "0";
	private String OrderID = "0";
	private String GroupID = "0";
	private String IsLeadOrder = "false";
	private String TmpOrderID = "0";
	private String PkgName = "sth";
	private String Factor = "0";
	private String Pkg;
	private String Uid;
	private String orderDate = "2012-05-09";
	private String CtripCardNo;
	private String Eid = "0";
	private String OrderStatus = "U";
	private String ProcessStatus = "0";
	private String Currency = "RMB";
	private String RoomDiscount = "1.000";
	private String PrepayType = "CCARD";
	private String amount ="21";
	private String cost ="0.0000";
	private String takeoffdate = "2012-05-09";
	private String returndate = "2012-05-09";
	private String arrival = "0001-01-01T00:00:00";
	private String Departure = "0001-01-01T00:00:00";
    private String IncludingHotel= "T";
    private String IncludingFlight = "T";
    private String StartCity = "2";
    private String DestCity = "43";
    private String Hotel = "0";
    private String Room = "0";
    private String RoomQty = "1";
    private String FlightOption = "0";
    private String NumAdult = "2";
    private String NumChild = "0";
    private String NumBaby = "0";
    private String NumberChildNoBed = "0";
    private String ConfirmWay = "1003";
    private String ConfirmType= "TEL";
    private String ContactName = "asdf";
    private String ContactTel = "asd-asdf*asdf";
    private String ContactFax = "asdf-dfasdf*asdf";
    private String ContactMobile = "asdf";
    private String ContactEmail = "asdf"; 
    private String ContactAddress;
    private String GetTicketType = "EMS";
    private String Attrib1 = "0";
    private String Attrib2 = "0";
    private String Standard = "T4";
    private String Remarks;
    private String  ServerFrom = "api.dev.sh.ctriptravel.c";
    private String IsOnline = "true";
    private String UrgencyLevel = "2";
    private String ProcessDeadLine = "2012-05-21";
    private String PostStatus = "0";
    private String FinishDate= "0001-01-01T00:00:00";
    private String SendTicketETime = "2011-11-17T09:00:00";
    private String SendTicketLTime = "2011-11-17T18:00:00";

	//private ArrayList<PkgT3Segment> segmentList;
	
    
    private ArrayList<PkgClient> pkgClients;
	private ArrayList<VacationSegment> vacationSegmentList;

	/**
	 * 全都不可为空
	 * PkgOrder订单信息
PkgClientList     乘客信息列表  
PkgEMS EMS信息
PkgT3FlightList系统机票信息
PkgT3FlightPriceList系统机票价格信息
PkgT3HotelPriceList酒店价格信息
PkgT3ItemDetailList携程推荐机票
PkgT3ItemPriceList携程推荐机票价格
PkgT3SegmentList行程段信息
	 */
	@Override
	public String getVacationParams() {
		// TODO Auto-generated method stub
		XmlNode VacationOrderCreateRequestNode = new XmlNode("VacationOrderCreateRequest");
		
		XmlNode PkgOrderNode = new XmlNode("PkgOrder");
		VacationOrderCreateRequestNode.addChildNode(PkgOrderNode);
		PkgOrderNode.addNodeByNameAndValue("TMoney",getTMoney());
		PkgOrderNode.addNodeByNameAndValue("IsTemporaryOrder",getIsTemporaryOrder()); //这个肯定加上，值肯定是True
		PkgOrderNode.addNodeByNameAndValue("TemporaryOrderDate",getTemporaryOrderDate());
		PkgOrderNode.addNodeByNameAndValue("ReceiptGuid",getReceiptGuid());
		PkgOrderNode.addNodeByNameAndValue("SubPaySystem",getSubPaySystem());
		PkgOrderNode.addNodeByNameAndValue("IsFlightelOrder",getIsFlightelOrder());
		PkgOrderNode.addNodeByNameAndValue("OnlyPassport",getOnlyPassport());
		PkgOrderNode.addNodeByNameAndValue("Mileage",getMileage());
		PkgOrderNode.addNodeByNameAndValue("Factor",getFactor());
		PkgOrderNode.addNodeByNameAndValue("OrderID",getOrderID());
		PkgOrderNode.addNodeByNameAndValue("GroupID",getGroupID());
		PkgOrderNode.addNodeByNameAndValue("IsLeadOrder",getIsLeadOrder());
		PkgOrderNode.addNodeByNameAndValue("TmpOrderID",getTmpOrderID());
		PkgOrderNode.addNodeByNameAndValue("Pkg",getPkg());
		PkgOrderNode.addNodeByNameAndValue("PkgName",getPkgName());
		PkgOrderNode.addNodeByNameAndValue("OrderDate",getOrderDate());
		PkgOrderNode.addNodeByNameAndValue("Uid",getUid());
		PkgOrderNode.addNodeByNameAndValue("CtripCardNo",getCtripCardNo ());
		PkgOrderNode.addNodeByNameAndValue("Eid",getEid());
		PkgOrderNode.addNodeByNameAndValue("OrderStatus",getOrderStatus());
		PkgOrderNode.addNodeByNameAndValue("ProcessStatus",getProcessStatus());
		PkgOrderNode.addNodeByNameAndValue("Currency",getCurrency());
		PkgOrderNode.addNodeByNameAndValue("RoomDiscount",getRoomDiscount());
		PkgOrderNode.addNodeByNameAndValue("Amount",getAmount());
		PkgOrderNode.addNodeByNameAndValue("Cost",getCost());
		PkgOrderNode.addNodeByNameAndValue("PrepayType",getPrepayType());
		PkgOrderNode.addNodeByNameAndValue("TakeoffDate",getTakeoffdate());
		PkgOrderNode.addNodeByNameAndValue("ReturnDate",getReturndate());
		PkgOrderNode.addNodeByNameAndValue("Arrival",getArrival());
		PkgOrderNode.addNodeByNameAndValue("Departure",getDeparture());
		PkgOrderNode.addNodeByNameAndValue("IncludingHotel",getIncludingHotel());
		PkgOrderNode.addNodeByNameAndValue("IncludingFlight",getIncludingFlight());
		PkgOrderNode.addNodeByNameAndValue("StartCity",getStartCity());
		PkgOrderNode.addNodeByNameAndValue("DestCity",getDestCity());
		PkgOrderNode.addNodeByNameAndValue("Hotel",getHotel());
		PkgOrderNode.addNodeByNameAndValue("Room",getRoom());
		PkgOrderNode.addNodeByNameAndValue("RoomQty",getRoomQty());
		PkgOrderNode.addNodeByNameAndValue("FlightOption",getFlightOption());
		PkgOrderNode.addNodeByNameAndValue("NumAdult",getNumAdult());
		PkgOrderNode.addNodeByNameAndValue("NumChild",getNumChild());
		PkgOrderNode.addNodeByNameAndValue("NumBaby",getNumBaby());
		PkgOrderNode.addNodeByNameAndValue("NumberChildNoBed",getNumberChildNoBed());
		PkgOrderNode.addNodeByNameAndValue("ConfirmWay",getConfirmWay());
		PkgOrderNode.addNodeByNameAndValue("ConfirmType",getConfirmType());
		PkgOrderNode.addNodeByNameAndValue("ContactName",getContactName());
		PkgOrderNode.addNodeByNameAndValue("ContactTel",getContactTel());
		PkgOrderNode.addNodeByNameAndValue("ContactFax",getContactFax());
		PkgOrderNode.addNodeByNameAndValue("ContactMobile",getContactMobile());
		PkgOrderNode.addNodeByNameAndValue("ContactEmail",getContactEmail());
		PkgOrderNode.addNodeByNameAndValue("ContactAddress",getContactAddress());
		    
		PkgOrderNode.addNodeByNameAndValue("GetTicketType",getGetTicketType());
		PkgOrderNode.addNodeByNameAndValue("Attrib1",getAttrib1());
		PkgOrderNode.addNodeByNameAndValue("Attrib2",getAttrib2());
		PkgOrderNode.addNodeByNameAndValue("Standard",getStandard());
		PkgOrderNode.addNodeByNameAndValue("Remarks",getRemarks());
		PkgOrderNode.addNodeByNameAndValue("ServerFrom",getServerFrom());
		PkgOrderNode.addNodeByNameAndValue("IsOnline",getIsOnline());
		PkgOrderNode.addNodeByNameAndValue("UrgencyLevel",getUrgencyLevel());
		PkgOrderNode.addNodeByNameAndValue("ProcessDeadLine",getProcessDeadLine());
		PkgOrderNode.addNodeByNameAndValue("PostStatus",getPostStatus());
		PkgOrderNode.addNodeByNameAndValue("FinishDate",getFinishDate());
		PkgOrderNode.addNodeByNameAndValue("SendTicketETime",getSendTicketETime());
		PkgOrderNode.addNodeByNameAndValue("SendTicketLTime",getSendTicketLTime());
		
		
		
		XmlNode PkgT3SegmentListNode = new XmlNode("PkgT3SegmentList");
		
		/*XmlNode segmentNode = addSegment(new PkgT3SegmentDemo());
		PkgT3SegmentListNode.addChildNode(segmentNode);*/
		if(getVacationSegmentList() !=null){
			for(int i=0;i<getVacationSegmentList().size();i++){
				//if(i==0){
					XmlNode segmentNode = convertToSegment(getVacationSegmentList().get(i));
					PkgT3SegmentListNode.addChildNode(segmentNode);
				//}
			}
		}
		
		
		//System.out.println("XC PkgT3SegmentListNode.toString():"+PkgT3SegmentListNode.toString());
		VacationOrderCreateRequestNode.addChildNode(PkgT3SegmentListNode);
		XmlNode PkgT3FlightListNode = new XmlNode("PkgT3FlightList");
		VacationOrderCreateRequestNode.addChildNode(PkgT3FlightListNode);
		XmlNode PkgT3FlightPriceListNode = new XmlNode("PkgT3FlightPriceList");
		VacationOrderCreateRequestNode.addChildNode(PkgT3FlightPriceListNode);
		XmlNode PkgT3HotelPriceListNode = new XmlNode("PkgT3HotelPriceList");
		VacationOrderCreateRequestNode.addChildNode(PkgT3HotelPriceListNode);
		XmlNode PkgT3ItemPriceListNode = new XmlNode("PkgT3ItemPriceList");
		VacationOrderCreateRequestNode.addChildNode(PkgT3ItemPriceListNode);
		XmlNode PkgT3ItemDetailListNode = new XmlNode("PkgT3ItemDetailList");
		VacationOrderCreateRequestNode.addChildNode(PkgT3ItemDetailListNode);
		
		XmlNode PkgClientListNode = new XmlNode("PkgClientList");
		VacationOrderCreateRequestNode.addChildNode(PkgClientListNode);
		
		XmlNode PkgClientNode = new XmlNode("PkgClient");
		/**
		 * <PkgClient>
        <IsHotelLive />
        <PassportType>P</PassportType>
        <IssueDate>1900-01-01T00:00:00</IssueDate>
        <Homeplace />
        <InfoID>0</InfoID>
        <Id>0</Id>
        <TmpOrderId>0</TmpOrderId>
        <ClientName>ASF</ClientName>
        <IDCardType>1</IDCardType>
        <IDCardNo>adf</IDCardNo>
        <BirthDate>1980-08-08T00:00:00</BirthDate>
        <AgeType>ADU</AgeType>
        <Nationality>CN</Nationality>
        <CardCity>SHA</CardCity>
        <ContactInfo>adf</ContactInfo>
        <Gender>F</Gender>
        <VisaCountry />
        <ClientName_E />
        <ST>0</ST>
        <Addcity />
        <Hzno />
        <Hzadd />
        <Hzdate>0001-01-01T00:00:00</Hzdate>
        <ISLIST />
        <IDCardTimelimit>2015-05-05T00:00:00</IDCardTimelimit>
      </PkgClient>
		 */
		/*PkgClientListNode.addChildNode(PkgClientNode);
		PkgClientNode.addNodeByNameAndValue("PassportType",getPassportType());
		PkgClientNode.addNodeByNameAndValue("IssueDate",getIssueDate());
		PkgClientNode.addNodeByNameAndValue("Id",getId());
		PkgClientNode.addNodeByNameAndValue("TmpOrderId",getTmpOrderId());
		PkgClientNode.addNodeByNameAndValue("ClientName",getClientName());
		PkgClientNode.addNodeByNameAndValue("IDCardType",getIDCardType());
		PkgClientNode.addNodeByNameAndValue("IDCardNo",getIDCardNo());
		PkgClientNode.addNodeByNameAndValue("BirthDate",getBirthDate());
		PkgClientNode.addNodeByNameAndValue("AgeType",getAgeType());
		PkgClientNode.addNodeByNameAndValue("Nationality",getNationality());
		PkgClientNode.addNodeByNameAndValue("CardCity",getCardCity());
		PkgClientNode.addNodeByNameAndValue("ContactInfo",getContactInfo());
		PkgClientNode.addNodeByNameAndValue("Gender",getGender());
		PkgClientNode.addNodeByNameAndValue("ST",getST());
		PkgClientNode.addNodeByNameAndValue("Hzdate",getHzdate());
		PkgClientNode.addNodeByNameAndValue("IDCardTimelimit",getIDCardTimelimit());*/
		
		//81还是82
		XmlNode PkgEMSNode = new XmlNode("PkgEMS");
		VacationOrderCreateRequestNode.addChildNode(PkgEMSNode);
		
		


		
		
		//String s =""
		return VacationOrderCreateRequestNode.toString();
	}
	
	/**
	 * 
	 * @param pkgSegment
	 * @return
	 */
	public XmlNode convertToSegment(VacationSegment vacationSegment){
		PkgT3Segment pkgSegment = new PkgT3Segment();
		XmlNode pkgSegmentNode = new XmlNode("PkgT3Segment");
		pkgSegmentNode.addNodeByNameAndValue("TmpOrderId",pkgSegment.getTempOrderId());
		pkgSegmentNode.addNodeByNameAndValue("Segment",pkgSegment.getSegment());
		pkgSegmentNode.addNodeByNameAndValue("StartCity",pkgSegment.getStartCity());
		pkgSegmentNode.addNodeByNameAndValue("DestCity",pkgSegment.getDestCity());
		pkgSegmentNode.addNodeByNameAndValue("Arrival",pkgSegment.getArrival());
		pkgSegmentNode.addNodeByNameAndValue("Departure",pkgSegment.getDeparture());
		pkgSegmentNode.addNodeByNameAndValue("CheckinDate",pkgSegment.getCheckinDate());
		pkgSegmentNode.addNodeByNameAndValue("CheckoutDate",pkgSegment.getCheckoutDate());
		pkgSegmentNode.addNodeByNameAndValue("Quantity",pkgSegment.getQuantity());
		pkgSegmentNode.addNodeByNameAndValue("Persons",pkgSegment.getPersons());
		pkgSegmentNode.addNodeByNameAndValue("Hotel",pkgSegment.getHotel());
		pkgSegmentNode.addNodeByNameAndValue("Room",pkgSegment.getRoom());
		pkgSegmentNode.addNodeByNameAndValue("Choice",pkgSegment.getChoice());
		pkgSegmentNode.addNodeByNameAndValue("Item",pkgSegment.getItem());
		pkgSegmentNode.addNodeByNameAndValue("Flight",pkgSegment.getFlight());
		if(vacationSegment.getSegmentKind()==VacationSegment.STAY_SEGMENT_KIND){
			pkgSegmentNode.addNodeByNameAndValue("HotelPrice",vacationSegment.getRoomPrice()+".0000");
			pkgSegmentNode.addNodeByNameAndValue("FlightPrice",pkgSegment.getFlightPrice());
		}else{
			pkgSegmentNode.addNodeByNameAndValue("HotelPrice",pkgSegment.getHotelPrice());
			pkgSegmentNode.addNodeByNameAndValue("FlightPrice",vacationSegment.getFlightPrice()+".0000");
		}
		/*if(vacationSegment.getSegmentKind()!=VacationSegment.STAY_SEGMENT_KIND){
			
		}else{
			
		}*/
		pkgSegmentNode.addNodeByNameAndValue("ChoicePrice",pkgSegment.getChoicePrice());
		pkgSegmentNode.addNodeByNameAndValue("HotelCost",pkgSegment.getHotelCost());
		pkgSegmentNode.addNodeByNameAndValue("FlightCost",pkgSegment.getFlightCost());
		pkgSegmentNode.addNodeByNameAndValue("ChoiceCost",pkgSegment.getChoiceCost());
		pkgSegmentNode.addNodeByNameAndValue("FligthSubOrderId",pkgSegment.getFligthSubOrderId());
		pkgSegmentNode.addNodeByNameAndValue("IsGroup",pkgSegment.getIsGroup());
		pkgSegmentNode.addNodeByNameAndValue("HPayTime",pkgSegment.getHPayTime());
		pkgSegmentNode.addNodeByNameAndValue("HCancelTime",pkgSegment.getHCancelTime());
		pkgSegmentNode.addNodeByNameAndValue("FRemitTime",pkgSegment.getFRemitTime());
		pkgSegmentNode.addNodeByNameAndValue("EDate",pkgSegment.getEDate());
		pkgSegmentNode.addNodeByNameAndValue("Distance",pkgSegment.getDistance());
		pkgSegmentNode.addNodeByNameAndValue("FGetTime",pkgSegment.getFGetTime());
		pkgSegmentNode.addNodeByNameAndValue("AirPort",pkgSegment.getAirPort());
		pkgSegmentNode.addNodeByNameAndValue("Zone",pkgSegment.getZone());
		
		if(Log.D){
			Log.d(TAG,"pkgSegmentNode"+pkgSegmentNode.toString());
		}
		return pkgSegmentNode;
		/**
		 *  <PkgT3Segment> //3540
        <TmpOrderId>0</TmpOrderId>
        <Segment>1</Segment>
        <StartCity>2</StartCity>
        <DestCity>43</DestCity>
        <Arrival>2012-05-22</Arrival>
        <Departure>2012-05-22</Departure>
        <CheckinDate>2012-05-22</CheckinDate>
        <CheckoutDate>2012-05-24</CheckoutDate>
        <Quantity>1</Quantity>
        <Persons>2</Persons>
        <Hotel>74033</Hotel>
        <Room>554175</Room>
        <Choice>0</Choice>
        <Item>0</Item>
        <Flight>CA01142</Flight>
        <HotelPrice>3300.0000</HotelPrice>
        <FlightPrice>240.0000</FlightPrice>
        <ChoicePrice>240.0000</ChoicePrice>
        <HotelCost>3300.0000</HotelCost>
        <FlightCost>240.0000</FlightCost>
        <ChoiceCost>240.0000</ChoiceCost>
        <FligthSubOrderId>0</FligthSubOrderId>
        <IsGroup>0</IsGroup>
        <HPayTime>0001-01-01T00:00:00</HPayTime>
        <HCancelTime>0001-01-01T00:00:00</HCancelTime>
        <FRemitTime>0001-01-01T00:00:00</FRemitTime>
        <EDate>0001-01-01T00:00:00</EDate>
        <Distance>0</Distance>
        <FGetTime>0001-01-01T00:00:00</FGetTime>
        <AirPort>中国国航</AirPort>
        <Zone>0</Zone>
      </PkgT3Segment>
		 */
	}
	
	
	
	/**
	 * 核心方法，加入行程段demo
	 */
	public XmlNode addSegment(PkgT3Segment pkgSegment){
		XmlNode pkgSegmentNode = new XmlNode("PkgT3Segment");
		pkgSegmentNode.addNodeByNameAndValue("TmpOrderId",pkgSegment.getTempOrderId());
		pkgSegmentNode.addNodeByNameAndValue("Segment",pkgSegment.getSegment());
		pkgSegmentNode.addNodeByNameAndValue("StartCity",pkgSegment.getStartCity());
		pkgSegmentNode.addNodeByNameAndValue("DestCity",pkgSegment.getDestCity());
		pkgSegmentNode.addNodeByNameAndValue("Arrival",pkgSegment.getArrival());
		pkgSegmentNode.addNodeByNameAndValue("Departure",pkgSegment.getDeparture());
		pkgSegmentNode.addNodeByNameAndValue("CheckinDate",pkgSegment.getCheckinDate());
		pkgSegmentNode.addNodeByNameAndValue("CheckoutDate",pkgSegment.getCheckoutDate());
		pkgSegmentNode.addNodeByNameAndValue("Quantity",pkgSegment.getQuantity());
		pkgSegmentNode.addNodeByNameAndValue("Persons",pkgSegment.getPersons());
		pkgSegmentNode.addNodeByNameAndValue("Hotel",pkgSegment.getHotel());
		pkgSegmentNode.addNodeByNameAndValue("Room",pkgSegment.getRoom());
		pkgSegmentNode.addNodeByNameAndValue("Choice",pkgSegment.getChoice());
		pkgSegmentNode.addNodeByNameAndValue("Item",pkgSegment.getItem());
		pkgSegmentNode.addNodeByNameAndValue("Flight",pkgSegment.getFlight());
		pkgSegmentNode.addNodeByNameAndValue("HotelPrice",pkgSegment.getHotelPrice());
		pkgSegmentNode.addNodeByNameAndValue("FlightPrice",pkgSegment.getFlightPrice());
		pkgSegmentNode.addNodeByNameAndValue("ChoicePrice",pkgSegment.getChoicePrice());
		pkgSegmentNode.addNodeByNameAndValue("HotelCost",pkgSegment.getHotelCost());
		pkgSegmentNode.addNodeByNameAndValue("FlightCost",pkgSegment.getFlightCost());
		pkgSegmentNode.addNodeByNameAndValue("ChoiceCost",pkgSegment.getChoiceCost());
		pkgSegmentNode.addNodeByNameAndValue("FligthSubOrderId",pkgSegment.getFligthSubOrderId());
		pkgSegmentNode.addNodeByNameAndValue("IsGroup",pkgSegment.getIsGroup());
		pkgSegmentNode.addNodeByNameAndValue("HPayTime",pkgSegment.getHPayTime());
		pkgSegmentNode.addNodeByNameAndValue("HCancelTime",pkgSegment.getHCancelTime());
		pkgSegmentNode.addNodeByNameAndValue("FRemitTime",pkgSegment.getFRemitTime());
		pkgSegmentNode.addNodeByNameAndValue("EDate",pkgSegment.getEDate());
		pkgSegmentNode.addNodeByNameAndValue("Distance",pkgSegment.getDistance());
		pkgSegmentNode.addNodeByNameAndValue("FGetTime",pkgSegment.getFGetTime());
		pkgSegmentNode.addNodeByNameAndValue("AirPort",pkgSegment.getAirPort());
		pkgSegmentNode.addNodeByNameAndValue("Zone",pkgSegment.getZone());
		
		return pkgSegmentNode;
		/**
		 *  <PkgT3Segment> //3540
        <TmpOrderId>0</TmpOrderId>
        <Segment>1</Segment>
        <StartCity>2</StartCity>
        <DestCity>43</DestCity>
        <Arrival>2012-05-22</Arrival>
        <Departure>2012-05-22</Departure>
        <CheckinDate>2012-05-22</CheckinDate>
        <CheckoutDate>2012-05-24</CheckoutDate>
        <Quantity>1</Quantity>
        <Persons>2</Persons>
        <Hotel>74033</Hotel>
        <Room>554175</Room>
        <Choice>0</Choice>
        <Item>0</Item>
        <Flight>CA01142</Flight>
        <HotelPrice>3300.0000</HotelPrice>
        <FlightPrice>240.0000</FlightPrice>
        <ChoicePrice>240.0000</ChoicePrice>
        <HotelCost>3300.0000</HotelCost>
        <FlightCost>240.0000</FlightCost>
        <ChoiceCost>240.0000</ChoiceCost>
        <FligthSubOrderId>0</FligthSubOrderId>
        <IsGroup>0</IsGroup>
        <HPayTime>0001-01-01T00:00:00</HPayTime>
        <HCancelTime>0001-01-01T00:00:00</HCancelTime>
        <FRemitTime>0001-01-01T00:00:00</FRemitTime>
        <EDate>0001-01-01T00:00:00</EDate>
        <Distance>0</Distance>
        <FGetTime>0001-01-01T00:00:00</FGetTime>
        <AirPort>中国国航</AirPort>
        <Zone>0</Zone>
      </PkgT3Segment>
		 */
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

	public String getIsTemporaryOrder() {
		return IsTemporaryOrder;
	}

	public void setIsTemporaryOrder(String isTemporaryOrder) {
		IsTemporaryOrder = isTemporaryOrder;
	}

	public String getTemporaryOrderDate() {
		return TemporaryOrderDate;
	}

	public void setTemporaryOrderDate(String temporaryOrderDate) {
		TemporaryOrderDate = temporaryOrderDate;
	}

	public String getReceiptGuid() {
		return ReceiptGuid;
	}

	public void setReceiptGuid(String receiptGuid) {
		ReceiptGuid = receiptGuid;
	}

	public String getSubPaySystem() {
		return SubPaySystem;
	}

	public void setSubPaySystem(String subPaySystem) {
		SubPaySystem = subPaySystem;
	}

	public String getFactor() {
		return Factor;
	}

	public void setFactor(String factor) {
		Factor = factor;
	}

	public String getPkg() {
		return Pkg;
	}

	public void setPkg(String pkg) {
		Pkg = pkg;
	}

	public String getUid() {
		return Uid;
	}

	public void setUid(String uid) {
		Uid = uid;
	}
	
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getTakeoffdate() {
		return takeoffdate;
	}
	public void setTakeoffdate(String takeoffdate) {
		this.takeoffdate = takeoffdate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public String getDeparture() {
		return Departure;
	}
	public void setDeparture(String departure) {
		Departure = departure;
	}
	public String getIncludingHotel() {
		return IncludingHotel;
	}
	public void setIncludingHotel(String includingHotel) {
		IncludingHotel = includingHotel;
	}
	public String getIncludingFlight() {
		return IncludingFlight;
	}
	public void setIncludingFlight(String includingFlight) {
		IncludingFlight = includingFlight;
	}
	public String getStartCity() {
		return StartCity;
	}
	public void setStartCity(String startCity) {
		StartCity = startCity;
	}
	public String getDestCity() {
		return DestCity;
	}
	public void setDestCity(String destCity) {
		DestCity = destCity;
	}
	public String getHotel() {
		return Hotel;
	}
	public void setHotel(String hotel) {
		Hotel = hotel;
	}
	public String getRoom() {
		return Room;
	}
	public void setRoom(String room) {
		Room = room;
	}
	public String getRoomQty() {
		return RoomQty;
	}
	public void setRoomQty(String roomQty) {
		RoomQty = roomQty;
	}
	public String getFlightOption() {
		return FlightOption;
	}
	public void setFlightOption(String flightOption) {
		FlightOption = flightOption;
	}
	public String getNumAdult() {
		return NumAdult;
	}
	public void setNumAdult(String numAdult) {
		NumAdult = numAdult;
	}
	public String getNumChild() {
		return NumChild;
	}
	public void setNumChild(String numChild) {
		NumChild = numChild;
	}
	public String getNumBaby() {
		return NumBaby;
	}
	public void setNumBaby(String numBaby) {
		NumBaby = numBaby;
	}
	public String getNumberChildNoBed() {
		return NumberChildNoBed;
	}
	public void setNumberChildNoBed(String numberChildNoBed) {
		NumberChildNoBed = numberChildNoBed;
	}
	public String getGetTicketType() {
		return GetTicketType;
	}
	public void setGetTicketType(String getTicketType) {
		GetTicketType = getTicketType;
	}
	public String getAttrib1() {
		return Attrib1;
	}
	public void setAttrib1(String attrib1) {
		Attrib1 = attrib1;
	}
	public String getAttrib2() {
		return Attrib2;
	}
	public void setAttrib2(String attrib2) {
		Attrib2 = attrib2;
	}
	public String getStandard() {
		return Standard;
	}
	public void setStandard(String standard) {
		Standard = standard;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getServerFrom() {
		return ServerFrom;
	}
	public void setServerFrom(String serverFrom) {
		ServerFrom = serverFrom;
	}
	public String getIsOnline() {
		return IsOnline;
	}
	public void setIsOnline(String isOnline) {
		IsOnline = isOnline;
	}
	public String getUrgencyLevel() {
		return UrgencyLevel;
	}
	public void setUrgencyLevel(String urgencyLevel) {
		UrgencyLevel = urgencyLevel;
	}
	public String getProcessDeadLine() {
		return ProcessDeadLine;
	}
	public void setProcessDeadLine(String processDeadLine) {
		ProcessDeadLine = processDeadLine;
	}
	public String getPostStatus() {
		return PostStatus;
	}
	public void setPostStatus(String postStatus) {
		PostStatus = postStatus;
	}
	public String getFinishDate() {
		return FinishDate;
	}
	public void setFinishDate(String finishDate) {
		FinishDate = finishDate;
	}
	public String getSendTicketETime() {
		return SendTicketETime;
	}
	public void setSendTicketETime(String sendTicketETime) {
		SendTicketETime = sendTicketETime;
	}
	public String getSendTicketLTime() {
		return SendTicketLTime;
	}
	public void setSendTicketLTime(String sendTicketLTime) {
		SendTicketLTime = sendTicketLTime;
	}
	public String getTMoney() {
		return TMoney;
	}
	public void setTMoney(String tMoney) {
		TMoney = tMoney;
	}
	public String getIsFlightelOrder() {
		return IsFlightelOrder;
	}
	public void setIsFlightelOrder(String isFlightelOrder) {
		IsFlightelOrder = isFlightelOrder;
	}
	public String getOnlyPassport() {
		return OnlyPassport;
	}
	public void setOnlyPassport(String onlyPassport) {
		OnlyPassport = onlyPassport;
	}
	public String getMileage() {
		return Mileage;
	}
	public void setMileage(String mileage) {
		Mileage = mileage;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getGroupID() {
		return GroupID;
	}
	public void setGroupID(String groupID) {
		GroupID = groupID;
	}
	public String getIsLeadOrder() {
		return IsLeadOrder;
	}
	public void setIsLeadOrder(String isLeadOrder) {
		IsLeadOrder = isLeadOrder;
	}
	public String getTmpOrderID() {
		return TmpOrderID;
	}
	public void setTmpOrderID(String tmpOrderID) {
		TmpOrderID = tmpOrderID;
	}
	public String getPkgName() {
		return PkgName;
	}
	public void setPkgName(String pkgName) {
		PkgName = pkgName;
	}
	public String getCtripCardNo() {
		return CtripCardNo;
	}
	public void setCtripCardNo(String ctripCardNo) {
		CtripCardNo = ctripCardNo;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	public String getProcessStatus() {
		return ProcessStatus;
	}
	public void setProcessStatus(String processStatus) {
		ProcessStatus = processStatus;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getRoomDiscount() {
		return RoomDiscount;
	}
	public void setRoomDiscount(String roomDiscount) {
		RoomDiscount = roomDiscount;
	}
	public String getPrepayType() {
		return PrepayType;
	}
	public void setPrepayType(String prepayType) {
		PrepayType = prepayType;
	}
	public String getEid() {
		return Eid;
	}
	public void setEid(String eid) {
		Eid = eid;
	}
	public String getConfirmWay() {
		return ConfirmWay;
	}
	public void setConfirmWay(String confirmWay) {
		ConfirmWay = confirmWay;
	}
	public String getConfirmType() {
		return ConfirmType;
	}
	public void setConfirmType(String confirmType) {
		ConfirmType = confirmType;
	}
	public String getContactName() {
		return ContactName;
	}
	public void setContactName(String contactName) {
		ContactName = contactName;
	}
	public String getContactTel() {
		return ContactTel;
	}
	public void setContactTel(String contactTel) {
		ContactTel = contactTel;
	}
	public String getContactFax() {
		return ContactFax;
	}
	public void setContactFax(String contactFax) {
		ContactFax = contactFax;
	}
	public String getContactMobile() {
		return ContactMobile;
	}
	public void setContactMobile(String contactMobile) {
		ContactMobile = contactMobile;
	}
	public String getContactEmail() {
		return ContactEmail;
	}
	public void setContactEmail(String contactEmail) {
		ContactEmail = contactEmail;
	}
	public String getContactAddress() {
		return ContactAddress;
	}
	public void setContactAddress(String contactAddress) {
		ContactAddress = contactAddress;
	}

	public ArrayList<VacationSegment> getVacationSegmentList() {
		return vacationSegmentList;
	}

	public void setVacationSegmentList(ArrayList<VacationSegment> vacationSegmentList) {
		this.vacationSegmentList = vacationSegmentList;
	}

	public ArrayList<PkgClient> getPkgClients() {
		return pkgClients;
	}

	public void setPkgClients(ArrayList<PkgClient> pkgClients) {
		this.pkgClients = pkgClients;
	}
	
}
