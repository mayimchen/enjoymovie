package com.banking.xc.utils.webService.request.vacation;

import com.banking.xc.utils.webService.request.XCRequest;
import com.banking.xc.utils.webService.util.RequestConstant;

public abstract class VacationRequest extends XCRequest{

    private final String requestBigType = RequestConstant.VACATION_BIG_TYPE;
	
	public abstract String getVacationParams();
	@Override
	/**
	 * 不具有一般性
	 * 例子：<Request>
  		<Header  AllianceID="1" SID="50" TimeStamp="1336458115"  RequestType="OTA_VacationAddressSelector" Signature="XXXXXXXXXXXXXXXXXXXXXXX" />
  			<VacationAddressSelectorRequest>
    		<SearchValue>三亚</SearchValue>
    		<StartCity>2</StartCity>
  		</VacationAddressSelectorRequest>
			</Request>

	 */
	public String getPrams() {
		
		return getVacationParams();
	}

	@Override
	public String getRequesstBigType() {
		// TODO Auto-generated method stub
		return requestBigType;
	}

	
	
}
