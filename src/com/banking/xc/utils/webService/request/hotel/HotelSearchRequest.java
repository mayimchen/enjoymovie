package com.banking.xc.utils.webService.request.hotel;

import android.text.TextUtils;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;
/**
 * 
 * @author banking
 *
 */
public class HotelSearchRequest extends HotelRequest{

	private final  String requestType = RequestConstant.HOTEL_SEARCH;
	
	/**
	 * 以下三项都是：酒店信息查询条件，查询属性中至少有一条查询条件
	 */
	/**
	 * 城市ID
	 */
	private String HotelCityCode = "";
	/**
	 * 区域ID
	 */
	private String AreaID = "";
	
	/**
	 * 酒店名称
	 */
	private String HotelName = "";
	
	/**
	 * 酒店等级
	 */
	/**
	 * 评分者,HotelStarRate(酒店星级)
			CtripStarRate(携程星级)
			CtripRecommendRate(携程评分)

	 */
	private String Provider = "";
	/**
	 * 分数或级别,decimal
	 */
	private String Rating = "";
	/**
	 * 为真时，只返回可预订酒店；为假时，返回所有已激活酒店（针对不同渠道，可用性会不同）。
	 */
	private Boolean AvailableOnlyIndicator = true;
	
	@Override
	public String getRequestType() {
		// TODO Auto-generated method stub
		return requestType;
	}

	public String getHotelCityCode() {
		return HotelCityCode;
	}

	public void setHotelCityCode(String hotelCityCode) {
		HotelCityCode = hotelCityCode;
	}

	public String getAreaID() {
		return AreaID;
	}

	public void setAreaID(String areaID) {
		AreaID = areaID;
	}

	public String getHotelName() {
		return HotelName;
	}



	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}

	public String getProvider() {
		return Provider;
	}

	public void setProvider(String provider) {
		Provider = provider;
	}

	public String getRating() {
		return Rating;
	}

	public void setRating(String rating) {
		Rating = rating;
	}

	public Boolean getAvailableOnlyIndicator() {
		return AvailableOnlyIndicator;
	}

	public void setAvailableOnlyIndicator(Boolean availableOnlyIndicator) {
		AvailableOnlyIndicator = availableOnlyIndicator;
	}

	@Override
	public String getHotelParams() {
		// TODO Auto-generated method stub
		final XmlNode hotelSearchNode = new XmlNode("ns:OTA_HotelSearchRQ"); 
		hotelSearchNode.putAttribute("Version", "1.0");
		hotelSearchNode.putAttribute("PrimaryLangID", "zh");
		hotelSearchNode.putAttribute("xsi:schemaLocation", "http://www.opentravel.org/OTA/2003/05 OTA_HotelSearchRQ.xsd");
		hotelSearchNode.putAttribute("xmlns", "http://www.opentravel.org/OTA/2003/05");
		
		final XmlNode criteriaNode = new XmlNode("ns:Criteria");
		criteriaNode.putAttribute("AvailableOnlyIndicator",String.valueOf(getAvailableOnlyIndicator()));
		hotelSearchNode.addChildNode(criteriaNode);
		
		final XmlNode criterionNode = new XmlNode("ns:Criterion");
		criteriaNode.addChildNode(criterionNode);
		
		final XmlNode hotelRefNode = new XmlNode("ns:HotelRef");
		hotelRefNode.putAttribute("HotelCityCode", getHotelCityCode());
		if(!TextUtils.isEmpty(getAreaID())){
			hotelRefNode.putAttribute("AreaID", getAreaID());
		}
		if(!TextUtils.isEmpty(getHotelName())){
			hotelRefNode.putAttribute("HotelName", getHotelName());
		}
		criterionNode.addChildNode(hotelRefNode);
		
		final XmlNode awardNode = new XmlNode("ns:Award");
		awardNode.putAttribute("Provider", getProvider());
		awardNode.putAttribute("Rating", getRating());
		criterionNode.addChildNode(awardNode);
		
		//System.out.println("XC hotelSearchRequest"+hotelSearchNode.toString());
		return hotelSearchNode.toString();
	}
	/*
	<HotelRequest>
    <RequestBody xmlns:ns="http://www.opentravel.org/OTA/2003/05" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
      <ns:OTA_HotelSearchRQ Version="1.0" PrimaryLangID="zh" xsi:schemaLocation="http://www.opentravel.org/OTA/2003/05 OTA_HotelSearchRQ.xsd" xmlns="http://www.opentravel.org/OTA/2003/05">
        <ns:Criteria AvailableOnlyIndicator="true">
          <ns:Criterion>
            <ns:HotelRef HotelCityCode="2" AreaID="112"  HotelName="上海"/>
            <ns:Award Provider="HotelStarRate" Rating="3"/>
          </ns:Criterion>
        </ns:Criteria>
      </ns:OTA_HotelSearchRQ>
    </RequestBody>
  	</HotelRequest>
	 */

	@Override
	public Boolean checkParams() {
		// TODO Auto-generated method stub
		return null;
	}
}
