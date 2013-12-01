package com.banking.xc.utils.webService.request.group;

import com.banking.xc.utils.webService.request.XCRequest;
import com.banking.xc.utils.webService.util.RequestConstant;

public abstract class GroupRequest extends XCRequest{
	private final String requestBigType = RequestConstant.GROUP_BIG_TYPE;
	
	public abstract String getGroupParams();
	@Override
	public String getPrams() {
		
		return getGroupParams();
	}

	@Override
	public String getRequesstBigType() {
		return requestBigType;
	}


}
