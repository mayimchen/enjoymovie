package com.banking.xc.utils.webService.request.vacation;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationPackageInfoRequest extends VacationRequest{

	public final String requestType = RequestConstant.VACATION_PACKAGE_INFO;
	private String startCity;
	private String pkgID;
	@Override
	public String getVacationParams() {
		
		XmlNode VacationInfoRequestNode = new XmlNode("VacationInfoRequest");
		VacationInfoRequestNode.addNodeByNameAndValue("PkgID", getPkgID());
		VacationInfoRequestNode.addNodeByNameAndValue("StartCity", getStartCity());
		return VacationInfoRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getPkgID() {
		return pkgID;
	}

	public void setPkgID(String pkgID) {
		this.pkgID = pkgID;
	}
	
	/**
	 * <VacationInfoRequest>
    <PkgID>1234</PkgID>
    <StartCity>2</StartCity>
  </VacationInfoRequest>

	 */
}
