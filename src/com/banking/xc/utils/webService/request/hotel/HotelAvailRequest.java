package com.banking.xc.utils.webService.request.hotel;



import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class HotelAvailRequest extends HotelRequest{
	
	private final  String requestType = RequestConstant.HOTEL_AVAIL;
	private final String TAG = "HotelAvailRequest";
	
	private String hotelCode;
	private String startTime;
	private String endTime;
	private String ratePlanCode; //价格计划代码
	private String quantity;
	private Boolean isPerRoom; //是否每个客人一间房
	private String lateArrivalTime;
	private String count;//客人数量
	
	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRatePlanCode() {
		return ratePlanCode;
	}

	public void setRatePlanCode(String ratePlanCode) {
		this.ratePlanCode = ratePlanCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Boolean getIsPerRoom() {
		return isPerRoom;
	}

	public void setIsPerRoom(Boolean isPerRoom) {
		this.isPerRoom = isPerRoom;
	}

	public String getLateArrivalTime() {
		return lateArrivalTime;
	}

	public void setLateArrivalTime(String lateArrivalTime) {
		this.lateArrivalTime = lateArrivalTime;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String getHotelParams() {
		// TODO Auto-generated method stub
		final XmlNode hotelAvailNode = new XmlNode("ns:OTA_HotelAvailRQ"); 
		hotelAvailNode.putAttribute("Version", "1.0");
		hotelAvailNode.putAttribute("TimeStamp", "2013-02-27T18:26:00.000+08:00"); //还是用当前日期
		
		final XmlNode availRequestSegmentsNode = new XmlNode("ns:AvailRequestSegments");
		hotelAvailNode.addChildNode(availRequestSegmentsNode);
		
		final XmlNode availRequestSegmentNode = new XmlNode("ns:AvailRequestSegment");
		availRequestSegmentsNode.addChildNode(availRequestSegmentNode);
		
		final XmlNode hotelSearchCriteriaNode = new XmlNode("ns:HotelSearchCriteria");
		availRequestSegmentNode.addChildNode(hotelSearchCriteriaNode);
		
		final XmlNode criterionNode = new XmlNode("ns:Criterion");
		hotelSearchCriteriaNode.addChildNode(criterionNode);
		
		final XmlNode hotelRefNode = new XmlNode("ns:HotelRef");
		hotelRefNode.putAttribute("HotelCode", getHotelCode());
		criterionNode.addChildNode(hotelRefNode);
		
		final XmlNode stayDateRangeNode = new XmlNode("ns:StayDateRange");
		stayDateRangeNode.putAttribute("Start", getStartTime());
		stayDateRangeNode.putAttribute("End", getEndTime());
		criterionNode.addChildNode(stayDateRangeNode);
		
		final XmlNode ratePlanCandidatesNode = new XmlNode("ns:RatePlanCandidates");
		criterionNode.addChildNode(ratePlanCandidatesNode);
		
		final XmlNode RatePlanCandidateNode = new XmlNode("ns:RatePlanCandidate");
		RatePlanCandidateNode.putAttribute("RatePlanCode", getRatePlanCode());
		ratePlanCandidatesNode.addChildNode(RatePlanCandidateNode);
		
		final XmlNode roomStayCandidatesNode = new XmlNode("ns:RoomStayCandidates");
		criterionNode.addChildNode(roomStayCandidatesNode);
		
		final XmlNode roomStayCandidateNode = new XmlNode("ns:RoomStayCandidate");
		roomStayCandidateNode.putAttribute("Quantity", getQuantity());
		roomStayCandidatesNode.addChildNode(roomStayCandidateNode);
		
		final XmlNode guestCountsNode = new XmlNode("ns:GuestCounts");
		guestCountsNode.putAttribute("IsPerRoom", String.valueOf(getIsPerRoom()));
		roomStayCandidateNode.addChildNode(guestCountsNode);
		
		final XmlNode guestCountNode = new XmlNode("ns:GuestCount");
		guestCountNode.putAttribute("Count", getCount());
		guestCountsNode.addChildNode(guestCountNode);
		
		final XmlNode tPAExtensionsNode = new XmlNode("ns:TPA_Extensions");
		criterionNode.addChildNode(tPAExtensionsNode);
		
		final XmlNode lateArrivalTimeNode = new XmlNode("ns:LateArrivalTime");
		lateArrivalTimeNode.setInnerValue(getLateArrivalTime());
		tPAExtensionsNode.addChildNode(lateArrivalTimeNode);
		
		if(Log.D){
			Log.d(TAG, hotelAvailNode.toString());
		}
		return hotelAvailNode.toString();
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
	/*
	 * <HotelRequest>
    <RequestBody xmlns:ns="http://www.opentravel.org/OTA/2003/05" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
      <ns:OTA_HotelAvailRQ Version="1.0" TimeStamp="2012-04-20T00:00:00.000+08:00">
        <ns:AvailRequestSegments>
          <ns:AvailRequestSegment>
            <ns:HotelSearchCriteria>
              <ns:Criterion>
                <ns:HotelRef HotelCode="51885"/>
                <ns:StayDateRange Start="2012-09-29T00:00:00.000+08:00" End="2012-09-30T19:00:00.000+08:00"/>
                <ns:RatePlanCandidates>
                  <ns:RatePlanCandidate RatePlanCode="136129"/>
                </ns:RatePlanCandidates>
                <ns:RoomStayCandidates>
                  <ns:RoomStayCandidate Quantity="1">
                    <ns:GuestCounts IsPerRoom="false">
                      <ns:GuestCount Count="1"/>
                    </ns:GuestCounts>
                  </ns:RoomStayCandidate>
                </ns:RoomStayCandidates>
                <ns:TPA_Extensions>
                  <ns:LateArrivalTime>2012-09-29T00:00:00.000+08:00</ns:LateArrivalTime>
                </ns:TPA_Extensions>
              </ns:Criterion>
            </ns:HotelSearchCriteria>
          </ns:AvailRequestSegment>
        </ns:AvailRequestSegments>
      </ns:OTA_HotelAvailRQ>
    </RequestBody>
  </HotelRequest>

	 */
}
