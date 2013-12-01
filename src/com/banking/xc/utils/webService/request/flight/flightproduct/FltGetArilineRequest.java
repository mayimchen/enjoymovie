package com.banking.xc.utils.webService.request.flight.flightproduct;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class FltGetArilineRequest extends FlightProductRequest{
	
	
	private final String requestType = RequestConstant.FLIGHT_AIRLINE_INFO;
	private String AirLine;

	@Override
	public String getFlightProductParams() {
		XmlNode GetAirlineInfosRequestNode = new XmlNode("GetAirlineInfosRequest");
		GetAirlineInfosRequestNode.addNodeByNameAndValue("AirportCode", getAirLine());
		return GetAirlineInfosRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getAirLine() {
		return AirLine;
	}

	public void setAirLine(String airLine) {
		AirLine = airLine;
	}

	
}