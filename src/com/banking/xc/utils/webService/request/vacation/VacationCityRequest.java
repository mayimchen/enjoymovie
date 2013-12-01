package com.banking.xc.utils.webService.request.vacation;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationCityRequest extends VacationRequest{

	private final String requestType = RequestConstant.VACATION_CITY;
	private String cityName;
	private String TAG = "VacationCityRequest";
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Override
	public String getVacationParams() {
		XmlNode vacationCityRequestNode = new XmlNode("VacationCityRequest");
		
		XmlNode cityNameNode = new XmlNode("CityName");
		cityNameNode.setInnerValue(getCityName());
		vacationCityRequestNode.addChildNode(cityNameNode);
		
		if(Log.D){
			Log.d(TAG,vacationCityRequestNode.toString());
		}
		return vacationCityRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

}
