package com.banking.xc.utils.webService.request.flight.flightproduct;

import com.banking.xc.utils.webService.request.XCRequest;
import com.banking.xc.utils.webService.util.RequestConstant;

public abstract class FlightProductRequest extends XCRequest{

	private final String requestBigType = RequestConstant.FLIGHT_BIG_TYPE;

	public abstract String getFlightProductParams();
	@Override
	public String getPrams() {
		return getFlightProductParams();
	}

	@Override
	public String getRequesstBigType() {
		return requestBigType;
	}

}
