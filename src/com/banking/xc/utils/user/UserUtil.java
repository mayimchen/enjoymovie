package com.banking.xc.utils.user;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Random;

import skytv_com.banking.enjoymovie.MyApplication;
import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.banking.xc.database.table.UserTable;
import com.banking.xc.entity.User;
import com.banking.xc.entity.VacationEntity;
import com.banking.xc.utils.HttpGroup;
import com.banking.xc.utils.HttpGroup.HttpError;
import com.banking.xc.utils.HttpGroup.HttpResponse;
import com.banking.xc.utils.HttpGroup.HttpSetting;
import com.banking.xc.utils.HttpGroup.HttpSettingParams;
import com.banking.xc.utils.HttpGroup.OnCommonListener;
import com.banking.xc.utils.HttpGroupUtils;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.request.UserRequest;
import com.banking.xc.utils.webService.request.vacation.VacationOrderAmountRequest;
import com.banking.xc.utils.webService.request.vacation.VacationOrderCreateRequest;
import com.banking.xc.utils.xml.UserXmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;
import com.banking.xc.utils.xml.vacation.VacationOrderAmountXmlParseHandler;
import com.banking.xc.utils.xml.vacation.VacationOrderCreateXmlParseHandler;
import com.banking.xc.utils.xml.vacation.VacationPackageInfoXmlParseHandler;

public class UserUtil {
	
	private static String TAG = "UserUtil";
	/**
	 * 1表示UserUniqueID 28表示AllianceID 501表示订单号 503表示SID
	 */
	private static User mUser;

	/**
	 * 通过这个randonName来唯一指定
	 * @return
	 */
	public static String getRandomName(){
		StringBuffer sb = new StringBuffer("用户");
		final String[] sArray = new String[]{"a","b","c","d","e","f","g",
				"h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w",
				"x","y","z","0","1","2","3","4","5","6","7","8","9"};
		Random random = new Random();
		int size = sArray.length;
		for(int i=0;i<6;i++){
			final int x = random.nextInt(size);
			sb.append(sArray[x]);
		}
		if(Log.D){
			Log.d(TAG, "New random User"+sb.toString());
		}
		return sb.toString();
	}
	
	public static void initializeUser(){
		User user = UserTable.getUser();
		if(user == null){
			getUserByUniqueID(getRandomName());
		}else{
			if(Log.D){
				Log.d(TAG, "get User"+user.getRandomName()+user.getUniqueID());
			}
			if(!TextUtils.isEmpty(user.getUniqueID())){
				mUser = user;
			}else{
				//还需要
				getUserByUniqueID(getRandomName());
			}
			
		}
	}
	
	public static void getUserByUniqueID(final String randomUserName) {
		
		HttpGroupUtils.getHttpGroupaAsynPool();
		
		UserRequest userRequest = new UserRequest();
		userRequest.setUidKey(randomUserName);
		HttpSetting httpSetting = new HttpGroup.HttpSetting(userRequest);
		httpSetting.setListener(new OnCommonListener() {

			@Override
			public void onEnd(HttpResponse httpResponse) {
				String result = httpResponse.getString();
				InputStream inputStream = new ByteArrayInputStream(result.getBytes());
				final UserXmlParseHandler parseHandler = new UserXmlParseHandler(new XmlParseListener() {

					@Override
					public void onParseStart() {

					}

					@Override
					public void onParseError(String reason) {

					}

					@Override
					public void onParseSuccess(Object o) {
						User user = (User) o;
						mUser = user;
						System.out.println("mUser.getUniqueID()"+mUser.getUniqueID());
						
						UserTable.insertUser(randomUserName,mUser.getUniqueID());
						//VacationDetailActivity.getVacationItem();
						//VacationDetailActivity.getVacationOption();
						//createVacationOrder();
						//getVacationOrderAmout();
					}
				}, inputStream);
				parseHandler.parse();

			}

			@Override
			public void onError(HttpError error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onReady(HttpSettingParams httpSettingParams) {
				// TODO Auto-generated method stub

			}

		});
		HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
	}
	
	/*public static void createVacationOrder(){
		VacationOrderCreateRequest request = new VacationOrderCreateRequest();
		request.setPkg("93912");//vacationBaseInfo.getPkg()
		request.setUid(UserUtil.getUser().getUniqueID());
		//request.setTemporaryOrderDate("2011-04-05");//DateTimeUtil.getTodayDate());
		HttpSetting httpSetting = new HttpGroup.HttpSetting(request);
		httpSetting.setEffect(HttpSetting.EFFECT_DEFAULT);
		httpSetting.setListener(new OnCommonListener() {

			@Override
			public void onReady(HttpSettingParams httpSettingParams) {
				
			}

			@Override
			public void onError(HttpError error) {
			}

			@Override
			public void onEnd(HttpResponse httpResponse) {
				String result = httpResponse.getString();
				System.out.println("createVacationOrder()");
				if(Log.D){
					Log.dAll("","createVacationOrder()",result);
				}
				InputStream inputStream = new ByteArrayInputStream(result.getBytes());
				final VacationOrderCreateXmlParseHandler parseHandler = new VacationOrderCreateXmlParseHandler(new XmlParseListener() {
					@Override
					public void onParseStart() {

					}

					@Override
					public void onParseError(String reason) {

					}

					@Override
					public void onParseSuccess(Object o) {
						String tempOrder = (String)o;
						System.out.println("XC tempOrder"+tempOrder);
						getVacationOrderAmout(tempOrder);
					}
				}, inputStream);
				parseHandler.parse();
			}
		});
		HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
	}*/
	public static User getRememberedUser() {
		//TODO数据库中读取
		return null;
	}

	public static User getUser(){
		if(mUser==null){
			mUser = getRememberedUser();
		}
		
		return mUser;
	}
	public static String getIp(){
		WifiManager wm = (WifiManager) MyApplication.getInstance()
				.getSystemService(Context.WIFI_SERVICE);
		DhcpInfo di = wm.getDhcpInfo();
		if (di != null) {
			return String.valueOf(di.ipAddress);
		}
		return "";
	}
	public static String getNetAddressInfo() {
		try {
			StringBuffer sb = new StringBuffer();

			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						String addr = inetAddress.getHostAddress();

						if (!TextUtils.isEmpty(addr)) {
							if (sb.length() == 0) {
								sb.append(addr);
							} else {
								sb.append(", ").append(addr); //$NON-NLS-1$
							}
						}
					}
				}
			}

			String netAddress = sb.toString();

			if (!TextUtils.isEmpty(netAddress)) {
				return netAddress;
			}
		} catch (SocketException e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
		}

		return null;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isUserAvaliable() {
		if (mUser != null) {
			return true;
		}
		if ((mUser = getRememberedUser()) != null) {
			return true;
		}
		return false;
	}

}
