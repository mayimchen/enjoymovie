package skytv_com.banking.enjoymovie;

import java.io.UnsupportedEncodingException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import skytv_com.banking.enjoymovie.R;

import com.banking.xc.utils.Md5Encrypt;
import com.banking.xc.utils.webService.request.UserRequest;



public class XCWebService {
	public static void main(String args[]){
		new XCWebService().getXCUserData();
	}
	private static final String API_KEY="BA5B38BA-B296-4382-9B22-3747D4972A13";//
	private static final String XC_NAMESPACE = "http://ctrip.com/";
	private static final String XC_SOAPACTION = "http://ctrip.com/Request";
	private static final String XC_METHOD ="Request";
	private static final String SID = "128999";//3192,123887
	private static final String AllianceID =  "5343";//联盟代码5,5208
	private static final String RequestType1 = "OTA_HotelSearch";
	private static final String RequestType2 = "OTA_Ping";//OTA_PING
	private static String whole_timeStamp;//时间戳
	
	
	
	private static String URL3 = "http://"+"OpenAPI.ctrip.com"+"/"+"Hotel"+"/"+"OTA_Ping"+".asmx";//OpenAPITestopenapitest
	public String getTimeStamp(){
		whole_timeStamp = String.valueOf(System.currentTimeMillis()/1000);
		return whole_timeStamp;
	}
	public String getSignature(String functionString){
		String result = (MyMd5Encrypt(whole_timeStamp+AllianceID+MyMd5Encrypt(API_KEY).toUpperCase()+SID+functionString)).toUpperCase();
		return result;
	}
	public String MyMd5Encrypt(String s){
		return Md5Encrypt.md5(s);
	}
	public String getPingString(){
		getTimeStamp();
		String s="<?xml version=\"1.0\"?>"+
		"<Request><Header AllianceID=\""+AllianceID+"\" SID=\""+SID+"\" TimeStamp=\""+whole_timeStamp+"\" RequestType=\"OTA_Ping\" Signature=\""+getSignature(RequestType2)+"\" />" 
		+"<HotelRequest>"
		+"<RequestBody xmlns:ns=\"http://www.opentravel.org/OTA/2003/05\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"
		+"<ns:OTA_PingRQ>"
				+"<ns:EchoData>测试文本</ns:EchoData>"
			+"</ns:OTA_PingRQ>"
		+"</RequestBody>"
	    +"</HotelRequest>"
        +"</Request>";
		return s;
	}
	public void getXCPingData(){
		try {
			SoapObject rpc = new SoapObject(XC_NAMESPACE, XC_METHOD);			
			rpc.addProperty("requestXML", getPingString());		
			//rpc.addProperty("requestXML", new UserRequest().getRequestString());		
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			//envelope.encodingStyle = "UTF-8";
			envelope.setOutputSoapObject(rpc);
			System.out.println("requestObject"+rpc);
			HttpTransportSE ht = new HttpTransportSE(URL3);
			//AndroidHttpTransport ht2 = new AndroidHttpTransport(URL);
			ht.debug = true;
			//ht.setXmlVersionTag("1.0");
			
			ht.call(XC_SOAPACTION, envelope);
			System.out.println("XCdetail" + envelope.getResponse().toString());

			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getUserString(){
		getTimeStamp();
		String s="<?xml version=\"1.0\"?>"+
		"<Request><Header AllianceID=\""+AllianceID+"\" SID=\""+SID+"\" TimeStamp=\""+whole_timeStamp+"\" RequestType=\"OTA_User\" Signature=\""+getSignature("OTA_User")+"\" />" 
		+"<UserRequest>"
		+"<AllianceID>5343</AllianceID>"
		+"<SID>128999</SID>"
		+"<UidKey>admin</UidKey>"
		+"</UserRequest>"
        +"</Request>";
		return s;
	}
	public void getXCUserData(){
		try {
			SoapObject rpc = new SoapObject(XC_NAMESPACE, XC_METHOD);			
			//rpc.addProperty("requestXML", getUserString());		
			rpc.addProperty("requestXML", new UserRequest().getRequestString());		
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			System.out.println("requestObject2"+rpc);
			HttpTransportSE ht = new HttpTransportSE("http://"+"openapi.ctrip.com"+"/"+"User"+"/"+"OTA_UserUniqueID"+".asmx");
			//AndroidHttpTransport ht2 = new AndroidHttpTransport(URL);
			ht.debug = true;
			//ht.setXmlVersionTag("1.0");
			
			ht.call(XC_SOAPACTION, envelope);
			System.out.println("XCdetail2" + envelope.getResponse().toString());

			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
