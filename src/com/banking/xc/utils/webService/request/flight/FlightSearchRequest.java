package com.banking.xc.utils.webService.request.flight;

import android.text.TextUtils;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class FlightSearchRequest extends FlightRequest{

	public final String SINGLE = "S";
	public final String ROUNDTRIP = "D";
	public final String THROUGHTRIP = "M";
	private final String requestType = RequestConstant.FLIGHT_SEARCH;
	private String searchType;
	private String departCity;
	private String arriveCity;
	private String DepartDate;
	private String AirlineDibitCode;
	private String SendTicketCity;
	private String BookDate;
	private String orderBy;
	private String Direction;
	
	//可能是往返或者联程.以下都弃用了
	private String departCity2;
	private String arriveCity2;
	private String DepartDate2;
	private String AirlineDibitCode2;
	
	private String roundDate;
	
	public boolean isSingle(){
		if(TextUtils.equals(searchType, "S")){
			return true;
		}
		return false;
	}
	@Override
	public String getFlightParams() {
		XmlNode flightSearchRequestNode = new XmlNode("FlightSearchRequest");
		flightSearchRequestNode.addNodeByNameAndValue("SearchType", getSearchType());
		
		XmlNode routesNode = new XmlNode("Routes");
		flightSearchRequestNode.addChildNode(routesNode);
		
		XmlNode flightRouteNode1 = new XmlNode("FlightRoute");
		flightRouteNode1.addNodeByNameAndValue("DepartCity", getDepartCity());
		flightRouteNode1.addNodeByNameAndValue("ArriveCity", getArriveCity());
		flightRouteNode1.addNodeByNameAndValue("DepartDate", getDepartDate());
		flightRouteNode1.addNodeByNameAndValue("AirlineDibitCode", getAirlineDibitCode());
		routesNode.addChildNode(flightRouteNode1);
		
		if(!isSingle()){
			XmlNode flightRouteNode2 = new XmlNode("FlightRoute");
			flightRouteNode2.addNodeByNameAndValue("DepartCity", getArriveCity());//getDepartCity2()
			flightRouteNode2.addNodeByNameAndValue("ArriveCity", getDepartCity());//getArriveCity2()
			flightRouteNode2.addNodeByNameAndValue("DepartDate", getRoundDate());//getDepartDate2()
			flightRouteNode2.addNodeByNameAndValue("AirlineDibitCode", getAirlineDibitCode());
			routesNode.addChildNode(flightRouteNode2);
		}
		flightSearchRequestNode.addNodeByNameAndValue("SendTicketCity", getSendTicketCity());
		flightSearchRequestNode.addNodeByNameAndValue("BookDate", getBookDate());
		flightSearchRequestNode.addNodeByNameAndValue("OrderBy", getOrderBy());
		flightSearchRequestNode.addNodeByNameAndValue("Direction", getDirection());
		return flightSearchRequestNode.toString();
	}
	public void addFlightNode(XmlNode parent,int tag){
		
	}
	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getDepartCity() {
		return departCity;
	}

	public void setDepartCity(String departCity) {
		this.departCity = departCity;
	}

	public String getArriveCity() {
		return arriveCity;
	}

	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}

	public String getDepartDate() {
		return DepartDate;
	}

	public void setDepartDate(String departDate) {
		DepartDate = departDate;
	}

	public String getAirlineDibitCode() {
		return AirlineDibitCode;
	}

	public void setAirlineDibitCode(String airlineDibitCode) {
		AirlineDibitCode = airlineDibitCode;
	}

	public String getSendTicketCity() {
		return SendTicketCity;
	}

	public void setSendTicketCity(String sendTicketCity) {
		SendTicketCity = sendTicketCity;
	}

	public String getBookDate() {
		return BookDate;
	}

	public void setBookDate(String bookDate) {
		BookDate = bookDate;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}
	public String getDepartCity2() {
		return departCity2;
	}
	public void setDepartCity2(String departCity2) {
		this.departCity2 = departCity2;
	}
	public String getArriveCity2() {
		return arriveCity2;
	}
	public void setArriveCity2(String arriveCity2) {
		this.arriveCity2 = arriveCity2;
	}
	public String getDepartDate2() {
		return DepartDate2;
	}
	public void setDepartDate2(String departDate2) {
		DepartDate2 = departDate2;
	}
	public String getAirlineDibitCode2() {
		return AirlineDibitCode;
	}
	public void setAirlineDibitCode2(String AirlineDibitCode2) {
		AirlineDibitCode2 = AirlineDibitCode2;
	}
	public String getRoundDate() {
		return roundDate;
	}
	public void setRoundDate(String roundDate) {
		this.roundDate = roundDate;
	}
	
	/**
	 * <FlightSearchRequest>
		<SearchType>S</SearchType>
		<Routes>
			<FlightRoute>
				<DepartCity>SHA</DepartCity>
				<ArriveCity>BJS</ArriveCity>
				<DepartDate>2012-07-23T00:00:00</DepartDate>
				<AirlineDibitCode>CA</AirlineDibitCode>
			</FlightRoute>
		</Routes>
		<SendTicketCity>SHA</SendTicketCity>
		<BookDate>2012-06-13T00:00:00</BookDate>
		<OrderBy>DepartTime</OrderBy>
		<Direction>ASC</Direction>
	</FlightSearchRequest>
	 */
	/**
	 * <FlightSearchRequest>
		<SearchType>M</SearchType>
		<Routes>
			<FlightRoute>
				<DepartCity>BJS</DepartCity>
				<ArriveCity>SZX</ArriveCity>
				<DepartDate>2012-07-23T00:00:00</DepartDate>
				<AirlineDibitCode>CA</AirlineDibitCode>
			</FlightRoute>
			<FlightRoute>
				<DepartCity>SZX</DepartCity>
				<ArriveCity>BJS</ArriveCity>
				<DepartDate>2012-07-25T00:00:00</DepartDate>
				<AirlineDibitCode>CA</AirlineDibitCode>
			</FlightRoute>
		</Routes>
		<SendTicketCity>SHA</SendTicketCity>
		<BookDate>2012-06-13T00:00:00</BookDate>
		<OrderBy>DepartTime</OrderBy>
		<Direction>ASC</Direction>
	</FlightSearchRequest>

	 */
}
