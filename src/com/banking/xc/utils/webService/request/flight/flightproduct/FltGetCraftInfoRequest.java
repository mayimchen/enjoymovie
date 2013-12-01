package com.banking.xc.utils.webService.request.flight.flightproduct;

import android.text.TextUtils;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 机型信息查询
 * @author zhangyinhang
 *
 */
public class FltGetCraftInfoRequest extends FlightProductRequest{
	
	
	private final String requestType = RequestConstant.FLIGHT_CRAFT_INFO;
	private String craftType;
	@Override
	public String getFlightProductParams() {
		XmlNode GetCraftInfosRequestNode = new XmlNode("GetCraftInfosRequest");
		if(!TextUtils.isEmpty(getCraftType())){
			GetCraftInfosRequestNode.addNodeByNameAndValue("CraftType", getCraftType());
		}
		return GetCraftInfosRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getCraftType() {
		return craftType;
	}

	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	
}
