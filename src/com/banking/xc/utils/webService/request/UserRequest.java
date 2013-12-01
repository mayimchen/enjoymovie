package com.banking.xc.utils.webService.request;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.webService.util.WebServiceConstant;



public class UserRequest extends XCRequest {

	private final  String requestType = RequestConstant.USER_UNIQUE;
	private final String requestBigType = RequestConstant.USER_BIG_TYPE;
	private final String default_UidKey = "15510352329"; //手机号，密码：id
	private String UidKey;
	
	
	public void setUidKey(String UidKey){
		this.UidKey = UidKey;
	}
	public String getUidKey(){
		if(UidKey == null){
			return default_UidKey;
		}
		return UidKey;
	}
	@Override
	public String getPrams() {
		// TODO Auto-generated method stub
		final String result =
				"<UserRequest>"
				+"<AllianceID>"+WebServiceConstant.ALLIANCEID+"</AllianceID>"
				+"<SID>"+WebServiceConstant.SID+"</SID>"
				+"<UidKey>"+getUidKey()+"</UidKey>"
				+"</UserRequest>";
		return result;
	}

	@Override
	public String getRequesstBigType() {
		// TODO Auto-generated method stub
		return requestBigType;
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
