package com.banking.xc.utils.webService.request.flight.flightproduct;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class FltGetCityInfoRequest extends FlightProductRequest {

	private final String requestType = RequestConstant.FLIGHT_CITY_INFO;
	private String CityCode;
	private String CityId;
	private String CityName;

	@Override
	public String getFlightProductParams() {
		XmlNode GetCityInfosRequestNode = new XmlNode("GetCityInfosRequest");
		if (getCityCode() != null) {
			GetCityInfosRequestNode.addNodeByNameAndValue("CityCode", getCityCode());
		}
		if (getCityId() != null) {
			GetCityInfosRequestNode.addNodeByNameAndValue("CityId", getCityId());
		}
		if (getCityName() != null) {
			GetCityInfosRequestNode.addNodeByNameAndValue("CityName", getCityName());
		}
		return GetCityInfosRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}

	public String getCityId() {
		return CityId;
	}

	public void setCityId(String cityId) {
		CityId = cityId;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

}
