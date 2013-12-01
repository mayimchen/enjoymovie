package com.banking.xc.utils.webService.request.hotel;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class HotelPingRequest extends HotelRequest
{
	private final  String requestType = RequestConstant.PING;
	private final  String default_EchoData = "测试文本";
	
	private String EchoData = "";
	
	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return requestType;
	}
	
	

	public String getEchoData() {
		if(EchoData==null){
			return default_EchoData;
		}
		return EchoData;
	}



	public void setEchoData(String echoData) {
		EchoData = echoData;
	}

	@Override
	public String getHotelParams() {
		// TODO Auto-generated method stub
		final XmlNode pingNode = new XmlNode("ns:OTA_PingRQ"); 
		final XmlNode echoDataNode = new XmlNode("ns:EchoData",getEchoData());
		pingNode.addChildNode(echoDataNode);
		//final String s = "<ns:OTA_PingRQ><ns:EchoData>"+getEchoData()+"</ns:EchoData></ns:OTA_PingRQ>";
		return pingNode.toString();
	}

	@Override
	public Boolean checkParams() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
