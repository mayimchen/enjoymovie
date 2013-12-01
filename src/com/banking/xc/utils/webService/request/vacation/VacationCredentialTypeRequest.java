package com.banking.xc.utils.webService.request.vacation;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationCredentialTypeRequest extends VacationRequest {

	private final String requestType = RequestConstant.VACATION_CREDENTIAL_TYPE;
	
	private String pkgID;
	private String startCity; //数字
	
	
	public String getPkgID() {
		return pkgID;
	}

	public void setPkgID(String pkgID) {
		this.pkgID = pkgID;
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
		XmlNode vacationCredentialTypeRequestNode = new XmlNode("VacationCredentialTypeRequest");
		
		XmlNode pkgIDNode = new XmlNode("PkgID");
		pkgIDNode.setInnerValue(getPkgID());
		vacationCredentialTypeRequestNode.addChildNode(pkgIDNode);
		
		XmlNode startCityNode = new XmlNode("StartCity");
		startCityNode.setInnerValue(getStartCity());
		vacationCredentialTypeRequestNode.addChildNode(startCityNode);
		
		return vacationCredentialTypeRequestNode.toString();
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
