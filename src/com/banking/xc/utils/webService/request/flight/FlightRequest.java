package com.banking.xc.utils.webService.request.flight;

import com.banking.xc.utils.webService.request.XCRequest;
import com.banking.xc.utils.webService.util.RequestConstant;

public abstract class FlightRequest extends XCRequest{

	private final String requestBigType = RequestConstant.FLIGHT_BIG_TYPE;
	
	public abstract String getFlightParams();
	@Override
	public String getPrams() {
		return getFlightParams();
	}

	@Override
	public String getRequesstBigType() {
		return requestBigType;
	}


}
