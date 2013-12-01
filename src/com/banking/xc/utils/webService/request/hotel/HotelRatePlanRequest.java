package com.banking.xc.utils.webService.request.hotel;

import java.util.ArrayList;
import java.util.List;

import com.banking.xc.entity.RatePlan;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 价格查询，需要在可预订检查和预定酒店时使用。
 * @author zhangyinhang
 *
 */
public class HotelRatePlanRequest extends HotelRequest{

	private final  String requestType = RequestConstant.HOTEL_RATEPLAN;
	
	
	private ArrayList<RatePlan> ratePlanList;
	
	
	public ArrayList<RatePlan> getRatePlanList() {
		return ratePlanList;
	}

	public void setRatePlanList(ArrayList<RatePlan> ratePlanList) {
		this.ratePlanList = ratePlanList;
	}

	@Override
	public String getHotelParams() {
		// TODO Auto-generated method stub
		final XmlNode hotelRatePlanNode = new XmlNode("ns:OTA_HotelRatePlanRQ"); 
		hotelRatePlanNode.putAttribute("Version", "1.0");
		hotelRatePlanNode.putAttribute("TimeStamp", "2013-02-27T18:26:00.000+08:00"); //还是用当前日期
		
		final XmlNode ratePlansNode = new XmlNode("ns:RatePlans"); 
		hotelRatePlanNode.addChildNode(ratePlansNode);
		
		ArrayList<RatePlan> ratePlans = getRatePlanList();
		for(int i=0;i<ratePlans.size();i++){
			ratePlansNode.addChildNode(getRatePlanNode(ratePlans.get(i)));
		}
		
		return hotelRatePlanNode.toString();
	}
	
	public XmlNode getRatePlanNode(RatePlan ratePlan){
		final XmlNode ratePlanNode = new XmlNode("ns:RatePlan");
		
		final XmlNode dateRangeNode = new XmlNode("ns:DateRange");
		dateRangeNode.putAttribute("Start", ratePlan.getStart());
		dateRangeNode.putAttribute("End", ratePlan.getEnd());
		ratePlanNode.addChildNode(dateRangeNode);
		
		final XmlNode ratePlanCandidatesNode = new XmlNode("ns:RatePlanCandidates");
		ratePlanNode.addChildNode(ratePlanCandidatesNode);
		
		final XmlNode ratePlanCandidateNode = new XmlNode("ns:RatePlanCandidate");
		if(ratePlan.getRatePlanCode()!=null){
			ratePlanCandidateNode.putAttribute("RatePlanCode",ratePlan.getRatePlanCode());
		}
		ratePlanCandidateNode.putAttribute("AvailRatesOnlyInd", String.valueOf(ratePlan.isAvailRatesOnlyInd()));
		ratePlanCandidatesNode.addChildNode(ratePlanCandidateNode);
		
		final XmlNode hotelRefsNode = new XmlNode("ns:HotelRefs");
		ratePlanCandidateNode.addChildNode(hotelRefsNode);
		
		final XmlNode hotelRefNode = new XmlNode("ns:HotelRef");
		hotelRefNode.putAttribute("HotelCode", ratePlan.getHotelCode());
		hotelRefsNode.addChildNode(hotelRefNode);
		
		if(ratePlan.getRestrictedDisplayIndicator()!=null){
			final XmlNode tpaExtensions = new XmlNode("ns:TPA_Extensions");
			tpaExtensions.putAttribute("RestrictedDisplayIndicator", ratePlan.getRestrictedDisplayIndicator());
			ratePlanNode.addChildNode(tpaExtensions);
		}
		
		return ratePlanNode;
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
	 * <ns:OTA_HotelRatePlanRQ TimeStamp="2012-04-20T00:00:00.000+08:00" Version="1.0">
        <ns:RatePlans>
          <ns:RatePlan>
            <ns:DateRange Start="2012-09-28" End="2012-09-30"/>
            <ns:RatePlanCandidates>
              <ns:RatePlanCandidate RatePlanCode="8671" AvailRatesOnlyInd="false">
                <ns:HotelRefs>
                  <ns:HotelRef HotelCode="625"/>
                </ns:HotelRefs>
              </ns:RatePlanCandidate>
            </ns:RatePlanCandidates>
          </ns:RatePlan>
          <ns:RatePlan>
            <ns:DateRange Start="2012-09-28" End="2012-09-30"/>
            <ns:RatePlanCandidates>
              <ns:RatePlanCandidate AvailRatesOnlyInd="false">
                <ns:HotelRefs>
                  <ns:HotelRef HotelCode="635"/>
                </ns:HotelRefs>
              </ns:RatePlanCandidate>
            </ns:RatePlanCandidates>
			<ns:TPA_Extensions RestrictedDisplayIndicator="false"/>
          </ns:RatePlan>
        </ns:RatePlans>
      </ns:OTA_HotelRatePlanRQ>

	 */
}
