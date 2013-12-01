package com.banking.xc.utils.webService.request.hotel;

import java.util.List;

import com.banking.xc.entity.UniqueID;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 查看某订单详情
 * @author zhangyinhang
 *
 */
public class HotelOrderReadRequest extends HotelRequest{
	/**
	 * 504,固定100000
	 * 28 AllianceID,
	 * 503 SID,
	 * 1用户unique ID,
	 * 501表示订单号
	 */
	public static final String requestType = RequestConstant.HOTEL_ORDER_READ;
	private List<UniqueID> uniqueIDList; 
	private String TAG = "HotelOrderReadRequest";
	
	public List<UniqueID> getUniqueIDList() {
		return uniqueIDList;
	}

	public void setUniqueIDList(List<UniqueID> uniqueIDList) {
		this.uniqueIDList = uniqueIDList;
	}

	@Override
	public String getHotelParams() {
		XmlNode readRQNode = new XmlNode("ns:OTA_ReadRQ");
		readRQNode.putAttribute("Version", "1.0");
		
		for(int i=0;i<getUniqueIDList().size();i++){
			final XmlNode uniqueIDNode = new XmlNode("ns:UniqueID");
			uniqueIDNode.putAttribute("Type",getUniqueIDList().get(i).getType());
			uniqueIDNode.putAttribute("ID", getUniqueIDList().get(i).getId());
			readRQNode.addChildNode(uniqueIDNode);
		}
		if(Log.D){
			Log.d(TAG,"HotelOrderReadRequest"+readRQNode.toString());
		}
		return readRQNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}
	/**
	 * <ns:OTA_ReadRQ Version="1.0">
				<ns:UniqueID Type="28" ID="1"/>
				<ns:UniqueID Type="503" ID="50"/>
				<ns:UniqueID Type="1" ID="159c906a-aa28-4f54-b609-59d2c105fde2"/>
				<ns:UniqueID Type="501" ID=" 100628170"/>
			</ns:OTA_ReadRQ>

	 */
}
