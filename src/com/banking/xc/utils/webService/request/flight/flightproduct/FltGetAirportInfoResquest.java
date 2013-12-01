package com.banking.xc.utils.webService.request.flight.flightproduct;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class FltGetAirportInfoResquest extends FlightProductRequest{
	
	
	private final String requestType = RequestConstant.FLIGHT_AIRPORT_INFO;
	private String AirportCode;

	@Override
	public String getFlightProductParams() {
		XmlNode GetAirportInfosRequestNode = new XmlNode("GetAirportInfosRequest");
		GetAirportInfosRequestNode.addNodeByNameAndValue("AirportCode", getAirportCode());
		return GetAirportInfosRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getAirportCode() {
		return AirportCode;
	}

	public void setAirportCode(String airportCode) {
		AirportCode = airportCode;
	}
}