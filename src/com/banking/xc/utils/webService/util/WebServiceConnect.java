package com.banking.xc.utils.webService.util;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.request.XCRequest;

public class WebServiceConnect {
	public  Object getEntityFromWebService(String responseString){

		return null;
	}
	public static String connect(XCRequest request) throws IOException, XmlPullParserException{
		final String wholeUrl = request.getWholeUrl();
		if(Log.D){
			Log.d("WebServiceConnect","getWholeUrl()"+wholeUrl);
		}
		return connect(request.getRequestString(),wholeUrl);
	}
	public static String connect(String requestString,String wholeUrl) throws IOException, XmlPullParserException{
		String result = "";
		//try {
			SoapObject rpc = new SoapObject(WebServiceConstant.XC_NAMESPACE, WebServiceConstant.XC_METHOD);			
			rpc.addProperty("requestXML", requestString);		
			//rpc.addProperty("requestXML", new UserRequest().getRequestString());		
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			//envelope.encodingStyle = "UTF-8";
			envelope.setOutputSoapObject(rpc);
			System.out.println("requestObject"+rpc);
			HttpTransportSE ht = new HttpTransportSE(wholeUrl);
			System.out.println("requestObject wholeUrl"+wholeUrl);
			//AndroidHttpTransport ht2 = new AndroidHttpTransport(URL);
			ht.debug = true;
			//ht.setXmlVersionTag("1.0");
			
			ht.call(WebServiceConstant.XC_SOAPACTION, envelope);
			result = envelope.getResponse().toString();
			System.out.println("XCdetail" + result);

			
		/*} catch (Exception e) {
			e.printStackTrace();
			System.out.println("XCdetail Exception" + e);
		}*/
		return result;
	}
}
