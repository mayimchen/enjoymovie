package com.banking.xc.utils.webService.request;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.Md5Encrypt;
import com.banking.xc.utils.webService.request.flight.FlightRequest;
import com.banking.xc.utils.webService.request.flight.flightproduct.FlightProductRequest;
import com.banking.xc.utils.webService.util.WebServiceConstant;
public abstract class XCRequest {
	
	private static String whole_timeStamp;//时间戳	
	public final String TAG = "XCRequest";
	//public abstract void putParams();
	public abstract String getPrams();
	public abstract String getRequesstBigType();
	public abstract String getRequestType();
	/**
	 * 检查参数是否合法
	 * @return
	 */
	public abstract Boolean checkParams();
	public String getWholeUrl(){
		if(this instanceof FlightRequest){
			return WebServiceConstant.BASE_URL+"/"+getRequesstBigType()+"/"+"DomesticFlight/"+getRequestType()+".asmx";
		}else if(this instanceof FlightProductRequest){
			return WebServiceConstant.BASE_URL+"/"+getRequesstBigType()+"/"+"FlightProduct/"+getRequestType()+".asmx";
		}
		else{
			return WebServiceConstant.BASE_URL+"/"+getRequesstBigType()+"/"+getRequestType()+".asmx";
		}
	}
	public static String getTimeStamp(){
		whole_timeStamp = String.valueOf(System.currentTimeMillis()/1000);
		return whole_timeStamp;
	}
	public String getSignature(){
		String result = (MyMd5Encrypt(whole_timeStamp+WebServiceConstant.ALLIANCEID+MyMd5Encrypt(WebServiceConstant.API_KEY).toUpperCase()+WebServiceConstant.SID+getRequestType())).toUpperCase();
		return result;
	}
	public static String getStaticSignature(String requestType){
		String result = (MyMd5Encrypt(whole_timeStamp+WebServiceConstant.ALLIANCEID+MyMd5Encrypt(WebServiceConstant.API_KEY).toUpperCase()+WebServiceConstant.SID+requestType)).toUpperCase();
		return result;
	}
	public static String MyMd5Encrypt(String s){
		return Md5Encrypt.md5(s);
	}
	public String getRequestString(){
		getTimeStamp();
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\"?>"+
				"<Request><Header AllianceID=\""+WebServiceConstant.ALLIANCEID+"\" SID=\""+WebServiceConstant.SID+"\" TimeStamp=\""+whole_timeStamp+"\" RequestType=\""+getRequestType()+"\" Signature=\""+getSignature()+"\" />");
		sb.append(getPrams());
		sb.append("</Request>");	
		final String result = sb.toString();
		if(Log.D)
		{
			Log.d(TAG,result);
		}
		System.out.println("XC Request"+result);
		return result;
	}
}
