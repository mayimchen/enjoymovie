package com.banking.xc.utils.webService.request.hotel;

import java.util.ArrayList;
import java.util.List;

import com.banking.xc.entity.UniqueID;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class HotelOrderCancelRequest extends HotelRequest {

	public final String requestType = RequestConstant.HOTEL_ORDER_CANCEL;
	private List<UniqueID> uniqueIDList; 
	private ArrayList<String> reasons;
	
	public ArrayList<String> getReasons() {
		return reasons;
	}

	public void setReasons(ArrayList<String> reasons) {
		this.reasons = reasons;
	}

	public List<UniqueID> getUniqueIDList() {
		return uniqueIDList;
	}

	public void setUniqueIDList(List<UniqueID> uniqueIDList) {
		this.uniqueIDList = uniqueIDList;
	}
	
	@Override
	public String getHotelParams() {
		XmlNode cancelRQNode = new XmlNode("ns:OTA_CancelRQ"); //OTA_CancelRQ
		cancelRQNode.putAttribute("TimeStamp", "2012-04-20T00:00:00.000+08:00");
		cancelRQNode.putAttribute("Version", "1.0");
		
		
		for(int i=0;i<getUniqueIDList().size();i++){
			final XmlNode uniqueIDNode = new XmlNode("ns:UniqueID");
			uniqueIDNode.putAttribute("Type",getUniqueIDList().get(i).getType());
			uniqueIDNode.putAttribute("ID", getUniqueIDList().get(i).getId());
			cancelRQNode.addChildNode(uniqueIDNode);
		}
		XmlNode reasonsRQNode = new XmlNode("ns:Reasons");
		cancelRQNode.addChildNode(reasonsRQNode);
		for(int i=0;i<getReasons().size();i++){
			final XmlNode reasonNode = new XmlNode("ns:Reason");
			reasonNode.putAttribute("Type",getReasons().get(i));
			reasonsRQNode.addChildNode(reasonNode);
		}
		if(Log.D){
			Log.d(TAG,"HotelOrderCancelRequest"+cancelRQNode.toString());
		}
		return cancelRQNode.toString();
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
	/**
	 * <ns:OTA_CancelRQ TimeStamp="2012-04-20T00:00:00.000+08:00" Version="1.0">
				<ns:UniqueID ID="1" Type="28" />
				<ns:UniqueID ID="100063754" Type="501"/>
				<ns:UniqueID ID="50" Type="503"/>
				<!--UserUniqueID:-->
				<ns:UniqueID ID="159c906a-aa28-4f54-b609-59d2c105fde2" Type="1"/>
				<ns:Reasons>
					<ns:Reason Type="506"/>
				</ns:Reasons>
			</ns:OTA_CancelRQ>

	 */
}
