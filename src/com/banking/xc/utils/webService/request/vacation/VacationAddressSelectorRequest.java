package com.banking.xc.utils.webService.request.vacation;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationAddressSelectorRequest extends VacationRequest {
	
	////OTA_ VacationCredentialType 
	private final String requestType = RequestConstant.VACATION_ADDRESS_SELECOTR;
	
	private String searchValue;
	private String startCity; //数字
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	@Override
	public String getVacationParams() {
		// TODO Auto-generated method stub
		XmlNode vacationAddressSelectorRequestNode = new XmlNode("VacationAddressSelectorRequest");
		
		XmlNode searchValueNode = new XmlNode("SearchValue");
		searchValueNode.setInnerValue(getSearchValue());
		vacationAddressSelectorRequestNode.addChildNode(searchValueNode);
		
		XmlNode startCityNode = new XmlNode("StartCity");
		startCityNode.setInnerValue(getStartCity());
		vacationAddressSelectorRequestNode.addChildNode(startCityNode);
		
		return vacationAddressSelectorRequestNode.toString();
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
	
}


