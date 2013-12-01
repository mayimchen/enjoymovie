package com.banking.xc.utils.webService.request.hotel;

import com.banking.xc.utils.webService.request.XCRequest;
import com.banking.xc.utils.webService.util.RequestConstant;

/**
 * HotelRequest抽象类，子实现类需要实现getHotelParams()和getRequestType()
 * @author banking
 *
 */
public abstract class HotelRequest extends XCRequest{
	
	private final String requestBigType = RequestConstant.HOTEL_BIG_TYPE;
	
	public abstract String getHotelParams();
	
	@Override
	public String getPrams() {
		StringBuffer sb = new StringBuffer();
		sb.append("<HotelRequest>"
		+"<RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
		);
		sb.append(getHotelParams());
		sb.append("</RequestBody></HotelRequest>");
		return sb.toString();
	}
		
	@Override
	public String getRequesstBigType() {
		// TODO Auto-generated method stub
		return requestBigType;
	}

	
	
}
