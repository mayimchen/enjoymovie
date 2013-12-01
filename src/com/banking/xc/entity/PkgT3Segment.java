package com.banking.xc.entity;
/**
 * 最后度假订单计算，完全按照这个价格来的。
 * 只和HotelPrice和FlightPrice有关
 * @author zhangyinhang
 *
 */
public class PkgT3Segment {
	  
	private String tempOrderId = "0";
	private String Segment = "1";
	private String StartCity = "2";
	private String DestCity = "3";
	private String Arrival = "2012-05-22";
	private String Departure = "2012-05-22";
	private String CheckinDate = "2012-05-22";
	private String CheckoutDate = "2012-05-24";
	private String Quantity= "1";
	private String Persons = "2";
	private String Hotel = "74033";
	private String Room = "554175";
	private String Choice = "0";
	private String Item = "0";
	private String Flight = "CA01142";
	private String HotelPrice = "0.0000";//1.0000
	private String FlightPrice = "0.0000";//10.0000
	private String ChoicePrice = "0.0000"; //100.0000//121的价格，ChoicePrice+2*flightPrice+hotelPrice
	private String HotelCost = "1000.0000";
	private String FlightCost = "0.1000";
	private String ChoiceCost = "0.0100";
	private String FligthSubOrderId = "0";
	private String IsGroup = "0";
	private String HPayTime = "0001-01-01T00:00:00";
	private String HCancelTime = "0001-01-01T00:00:00";
	private String FRemitTime = "0001-01-01T00:00:00";
	private String EDate = "0001-01-01T00:00:00";
	private String Distance= "0";
	private String FGetTime = "0001-01-01T00:00:00";
	private String AirPort = "中国国航";
	private String Zone = "0";
	public String getSegment() {
		return Segment;
	}
	public void setSegment(String segment) {
		Segment = segment;
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
	public String getArrival() {
		return Arrival;
	}
	public void setArrival(String arrival) {
		Arrival = arrival;
	}
	public String getDeparture() {
		return Departure;
	}
	public void setDeparture(String departure) {
		Departure = departure;
	}
	public String getCheckinDate() {
		return CheckinDate;
	}
	public void setCheckinDate(String checkinDate) {
		CheckinDate = checkinDate;
	}
	public String getCheckoutDate() {
		return CheckoutDate;
	}
	public void setCheckoutDate(String checkoutDate) {
		CheckoutDate = checkoutDate;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getPersons() {
		return Persons;
	}
	public void setPersons(String persons) {
		Persons = persons;
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
	public String getChoice() {
		return Choice;
	}
	public void setChoice(String choice) {
		Choice = choice;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public String getFlight() {
		return Flight;
	}
	public void setFlight(String flight) {
		Flight = flight;
	}
	public String getHotelPrice() {
		return HotelPrice;
	}
	public void setHotelPrice(String hotelPrice) {
		HotelPrice = hotelPrice;
	}
	public String getFlightPrice() {
		return FlightPrice;
	}
	public void setFlightPrice(String flightPrice) {
		FlightPrice = flightPrice;
	}
	public String getChoicePrice() {
		return ChoicePrice;
	}
	public void setChoicePrice(String choicePrice) {
		ChoicePrice = choicePrice;
	}
	public String getHotelCost() {
		return HotelCost;
	}
	public void setHotelCost(String hotelCost) {
		HotelCost = hotelCost;
	}
	public String getFlightCost() {
		return FlightCost;
	}
	public void setFlightCost(String flightCost) {
		FlightCost = flightCost;
	}
	public String getChoiceCost() {
		return ChoiceCost;
	}
	public void setChoiceCost(String choiceCost) {
		ChoiceCost = choiceCost;
	}
	public String getFligthSubOrderId() {
		return FligthSubOrderId;
	}
	public void setFligthSubOrderId(String fligthSubOrderId) {
		FligthSubOrderId = fligthSubOrderId;
	}
	public String getIsGroup() {
		return IsGroup;
	}
	public void setIsGroup(String isGroup) {
		IsGroup = isGroup;
	}
	public String getHPayTime() {
		return HPayTime;
	}
	public void setHPayTime(String hPayTime) {
		HPayTime = hPayTime;
	}
	public String getHCancelTime() {
		return HCancelTime;
	}
	public void setHCancelTime(String hCancelTime) {
		HCancelTime = hCancelTime;
	}
	public String getFRemitTime() {
		return FRemitTime;
	}
	public void setFRemitTime(String fRemitTime) {
		FRemitTime = fRemitTime;
	}
	public String getEDate() {
		return EDate;
	}
	public void setEDate(String eDate) {
		EDate = eDate;
	}
	public String getDistance() {
		return Distance;
	}
	public void setDistance(String distance) {
		Distance = distance;
	}
	public String getFGetTime() {
		return FGetTime;
	}
	public void setFGetTime(String fGetTime) {
		FGetTime = fGetTime;
	}
	public String getAirPort() {
		return AirPort;
	}
	public void setAirPort(String airPort) {
		AirPort = airPort;
	}
	public String getZone() {
		return Zone;
	}
	public void setZone(String zone) {
		Zone = zone;
	}
	public String getTempOrderId() {
		return tempOrderId;
	}
	public void setTempOrderId(String tempOrderId) {
		this.tempOrderId = tempOrderId;
	}
	
	/**
	 *  <PkgT3Segment>
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
