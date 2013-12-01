package com.banking.xc.utils.webService.util;

public class RequestConstant
{
	
	public static final String HOTEL_BIG_TYPE = "Hotel";
	public static final String USER_BIG_TYPE = "User";
	public static final String VACATION_BIG_TYPE = "Vacations";
	public static final String GROUP_BIG_TYPE = "Tuan";
	public static final String FLIGHT_BIG_TYPE = "Flight";
	
	public static final String USER_UNIQUE = "OTA_UserUniqueID";
	public static final String PING = "OTA_Ping";
	public static final String HOTEL_SEARCH = "OTA_HotelSearch";
	public static final String HOTEL_DESCRIPTION = "OTA_HotelDescriptiveInfo";
	public static final String HOTEL_RATEPLAN = "OTA_HotelRatePlan";
	public static final String HOTEL_AVAIL = "OTA_HotelAvail";
	public static final String HOTEL_RES = "OTA_HotelRes";
	public static final String HOTEL_ORDERLIST = "D_HotelOrderList";
	public static final String HOTEL_ORDER_READ = "OTA_Read";
	public static final String HOTEL_ORDER_CANCEL = "OTA_Cancel";
	
	public static final String VACATION_ADDRESS_SELECOTR = "OTA_VacationAddressSelector";
	public static final String VACATION_CITY = "OTA_VacationCity";
	public static final String VACATION_CREDENTIAL_TYPE = "OTA_VacationCredentialType";
	public static final String VACATION_PACKAGE_LIST = "OTA_VacationPackageList";
	public static final String VACATION_PACKAGE_INFO = "OTA_VacationPackageInfo";
	public static final String VACATION_ORDER_OPTION = "OTA_VacationOrderOption";
	public static final String VACATION_ORDER_ITEM = "OTA_VacationOrderItem";
	public static final String VACATION_ORDER_CREATE = "OTA_VacationOrderCreate";
	public static final String VACATION_ORDER_AMOUNT = "OTA_VacationOrderAmount";
	public static final String VACATION_ORDER_LIST = "OTA_VacationOrderList";
	public static final String VACATION_HOTELS = "OTA_VacationHotels";
	public static final String VACATION_ORDER_INFO = "OTA_VacationOrderInfo";
	

	public static final String GROUP_PRODUCT_LIST = "GroupProductList";
	public static final String GROUP_PRODUCT_INFO = "GroupProductInfo";
	public static final String GROUP_CREATE_ORDER = "GroupCreateOrder";
	public static final String GROUP_ORDER_LIST = "GroupOrderList";
	public static final String GROUP_QUERY_TICKETS = "GroupQueryTickets";
	
	//这里Url都有一个 /DomesticFlight/路径，注意处理
	//暂时只处理国内的
	public static final String FLIGHT_SEARCH = "OTA_FlightSearch";
	public static final String FLIGHT_SAVE_ORDER = "OTA_FltSaveOrder";//"OTA_ FltSaveOrder";
	
	//Flight静态查询
	public static final String FLIGHT_CRAFT_INFO = "OTA_FltGetCraftInfos";
	public static final String FLIGHT_CITY_INFO = "OTA_FltGetCityInfos";
	public static final String FLIGHT_AIRLINE_INFO = "OTA_FltGetAirlineInfos";
	public static final String FLIGHT_AIRPORT_INFO = "OTA_FltGetAirportInfos";
	
	
	
}
