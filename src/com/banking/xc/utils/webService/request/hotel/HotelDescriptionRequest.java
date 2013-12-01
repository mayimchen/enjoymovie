package com.banking.xc.utils.webService.request.hotel;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class HotelDescriptionRequest extends HotelRequest{

	private Boolean sendHotelInfoData = true;//false;
	private Boolean  sendFacilityGuestRooms = true;//false;
	private Boolean sendAttractions = true;//false;
	private Boolean sendRecreations = true;//false;
	private Boolean sendContactData = true;//false;
	private Boolean sendMultimediaData = true;//false;
	private String hotelCode = "";
	private final  String requestType = RequestConstant.HOTEL_DESCRIPTION;
	
	public Boolean getSendHotelInfoData() {
		return sendHotelInfoData;
	}

	public void setSendHotelInfoData(Boolean sendHotelInfoData) {
		this.sendHotelInfoData = sendHotelInfoData;
	}

	public Boolean getSendFacilityGuestRooms() {
		return sendFacilityGuestRooms;
	}

	public void setSendFacilityGuestRooms(Boolean sendFacilityGuestRooms) {
		this.sendFacilityGuestRooms = sendFacilityGuestRooms;
	}

	public Boolean getSendAttractions() {
		return sendAttractions;
	}

	public void setSendAttractions(Boolean sendAttractions) {
		this.sendAttractions = sendAttractions;
	}

	public Boolean getSendRecreations() {
		return sendRecreations;
	}

	public void setSendRecreations(Boolean sendRecreations) {
		this.sendRecreations = sendRecreations;
	}

	public Boolean getSendContactData() {
		return sendContactData;
	}

	public void setSendContactData(Boolean sendContactData) {
		this.sendContactData = sendContactData;
	}

	public Boolean getSendMultimediaData() {
		return sendMultimediaData;
	}

	public void setSendMultimediaData(Boolean sendMultimediaData) {
		this.sendMultimediaData = sendMultimediaData;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	@Override
	public String getHotelParams() {
		// TODO Auto-generated method stub
		final XmlNode descriptionRQNode = new XmlNode("OTA_HotelDescriptiveInfoRQ");
		descriptionRQNode.putAttribute("Version", "1.0");
		descriptionRQNode.putAttribute("xsi:schemaLocation", "http://www.opentravel.org/OTA/2003/05 OTA_HotelDescriptiveInfoRQ.xsd");
		descriptionRQNode.putAttribute("xmlns", "http://www.opentravel.org/OTA/2003/05");
		descriptionRQNode.putAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		
		final XmlNode  descriptionInfosNode = new XmlNode("HotelDescriptiveInfos");
		descriptionRQNode.addChildNode(descriptionInfosNode);
		
		final XmlNode  descriptionInfoNode = new XmlNode("HotelDescriptiveInfo");		
		descriptionInfoNode.putAttribute("HotelCode", getHotelCode());
		descriptionInfosNode.addChildNode(descriptionInfoNode);
		
		final XmlNode hotelInfoNode = new XmlNode("HotelInfo");
		hotelInfoNode.putAttribute("SendData", String.valueOf(getSendHotelInfoData()));
		descriptionInfoNode.addChildNode(hotelInfoNode);
		
		
		final XmlNode facilityInfoNode = new XmlNode("FacilityInfo");
		facilityInfoNode.putAttribute("SendGuestRooms", String.valueOf(getSendFacilityGuestRooms()));
		descriptionInfoNode.addChildNode(facilityInfoNode);
		
		final XmlNode areaInfoNode = new XmlNode("AreaInfo");
		areaInfoNode.putAttribute("SendAttractions", String.valueOf(getSendAttractions()));
		areaInfoNode.putAttribute("SendRecreations", String.valueOf(getSendRecreations()));
		descriptionInfoNode.addChildNode(areaInfoNode);
		

		final XmlNode contactInfoNode = new XmlNode("ContactInfo");
		contactInfoNode.putAttribute("SendData", String.valueOf(getSendContactData()));
		descriptionInfoNode.addChildNode(contactInfoNode);
		
		final XmlNode multimediaObjects = new XmlNode("MultimediaObjects");
		multimediaObjects.putAttribute("SendData", String.valueOf(getSendMultimediaData()));
		descriptionInfoNode.addChildNode(multimediaObjects);
		
		return descriptionRQNode.toString();
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
	 * <Request>
  <Header AllianceID="1" SID="50" TimeStamp="1335341667" RequestType="OTA_HotelDescriptiveInfo" Signature="XXXXXXXXXXXXXXXXXXXXXXX" />
  <HotelRequest>
    <RequestBody xmlns:ns="http://www.opentravel.org/OTA/2003/05" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
      <OTA_HotelDescriptiveInfoRQ Version="1.0" xsi:schemaLocation="http://www.opentravel.org/OTA/2003/05 OTA_HotelDescriptiveInfoRQ.xsd" xmlns="http://www.opentravel.org/OTA/2003/05" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
        <HotelDescriptiveInfos>
          <HotelDescriptiveInfo HotelCode="18806">
            <HotelInfo SendData="true"/>
            <FacilityInfo SendGuestRooms="true"/>
            <AreaInfo SendAttractions="true" SendRecreations="true"/>
            <ContactInfo SendData="true"/>
            <MultimediaObjects SendData="true"/>
          </HotelDescriptiveInfo>
        </HotelDescriptiveInfos>
      </OTA_HotelDescriptiveInfoRQ>
    </RequestBody>
  </HotelRequest>
</Request>

	 */
	
}
