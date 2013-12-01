package com.banking.xc.utils.webService.request.vacation;

import java.util.ArrayList;

import com.banking.xc.entity.CustomerSegment;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 旅游的可选项.一个列表，很像酒店价格计划。某一个全是保险信息，不推荐；
 * @author zhangyinhang
 *
 */
public class VacationOrderOptionRequest extends VacationRequest{

	private final String requestType = RequestConstant.VACATION_ORDER_OPTION;
	private String pkgID;
	private String isSuperOrder = "false";//是否为强制订单
	private String isLocked = "false";//是否必选可选
	private String startCity;
	private String adults;
	private String children;
	private String rooms;
	private ArrayList<CustomerSegment> customerSegmentList; //单选项，两个时间，酒店房型id
	@Override
	public String getVacationParams() {
		XmlNode VacationOrderOptionRequestNode = new XmlNode("VacationOrderOptionRequest");
		VacationOrderOptionRequestNode.addNodeByNameAndValue("PkgID", getPkgID());
		VacationOrderOptionRequestNode.addNodeByNameAndValue("IsSuperOrder", getIsSuperOrder());
		VacationOrderOptionRequestNode.addNodeByNameAndValue("IsLocked", getIsLocked());
		VacationOrderOptionRequestNode.addNodeByNameAndValue("StartCity", getStartCity());
		VacationOrderOptionRequestNode.addNodeByNameAndValue("Adults", getAdults());
		VacationOrderOptionRequestNode.addNodeByNameAndValue("Children", getChildren());
		VacationOrderOptionRequestNode.addNodeByNameAndValue("Rooms", getRooms());
		
		XmlNode segmentsNode = new XmlNode("Segments");
		VacationOrderOptionRequestNode.addChildNode(segmentsNode);
		final ArrayList<CustomerSegment> customerSegmentList  = getCustomerSegmentList();
		for(int i=0;i<customerSegmentList.size();i++){
			final CustomerSegment segment = customerSegmentList.get(i);
			XmlNode CustomerSegmentNode = new XmlNode("CustomerSegment");
			CustomerSegmentNode.addNodeByNameAndValue("Segment", segment.getSegment());
			CustomerSegmentNode.addNodeByNameAndValue("Departure", segment.getDeparture());
			CustomerSegmentNode.addNodeByNameAndValue("CheckInDate", segment.getCheckInDate());
			CustomerSegmentNode.addNodeByNameAndValue("CheckOutDate", segment.getCheckOutDate());
			CustomerSegmentNode.addNodeByNameAndValue("Room", segment.getRoom());
			CustomerSegmentNode.addNodeByNameAndValue("Item", segment.getItem());
			segmentsNode.addChildNode(CustomerSegmentNode);
		}//
		return VacationOrderOptionRequestNode.toString();
		//return "<VacationOrderOptionRequest>    <PkgID>51424</PkgID>    <IsSuperOrder>false</IsSuperOrder>    <IsLocked>false</IsLocked>    <StartCity>2</StartCity>    <Adults>5</Adults>    <Children>0</Children>    <Rooms>0</Rooms>    <Segments>      <CustomerSegment>        <Segment>1</Segment>        <Departure>2012-05-15</Departure>        <CheckInDate>2012-05-15</CheckInDate>        <CheckOutDate>2012-05-15</CheckOutDate>        <Room>0</Room>        <Item>0</Item>      </CustomerSegment>      <CustomerSegment>        <Segment>2</Segment>        <Departure>2012-05-18</Departure>        <CheckInDate>2012-05-18</CheckInDate>        <CheckOutDate>2012-05-18</CheckOutDate>        <Room>0</Room>        <Item>0</Item>      </CustomerSegment>    </Segments>  </VacationOrderOptionRequest>";
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}
	/**
	 * <VacationOrderOptionRequest>
    <PkgID>51424</PkgID>
    <IsSuperOrder>false</IsSuperOrder>
    <IsLocked>false</IsLocked>
    <StartCity>2</StartCity>
    <Adults>5</Adults>
    <Children>0</Children>
    <Rooms>0</Rooms>
    <Segments>
      <CustomerSegment>
        <Segment>1</Segment>
        <Departure>2012-05-15</Departure>
        <CheckInDate>2012-05-15</CheckInDate>
        <CheckOutDate>2012-05-15</CheckOutDate>
        <Room>0</Room>
        <Item>0</Item>
      </CustomerSegment>
      <CustomerSegment>
        <Segment>2</Segment>
        <Departure>2012-05-18</Departure>
        <CheckInDate>2012-05-18</CheckInDate>
        <CheckOutDate>2012-05-18</CheckOutDate>
        <Room>0</Room>
        <Item>0</Item>
      </CustomerSegment>
    </Segments>
  </VacationOrderOptionRequest>

	 */

	public String getPkgID() {
		return pkgID;
	}

	public void setPkgID(String pkgID) {
		this.pkgID = pkgID;
	}

	public String getIsSuperOrder() {
		return isSuperOrder;
	}

	public void setIsSuperOrder(String isSuperOrder) {
		this.isSuperOrder = isSuperOrder;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getAdults() {
		return adults;
	}

	public void setAdults(String adults) {
		this.adults = adults;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public ArrayList<CustomerSegment> getCustomerSegmentList() {
		return customerSegmentList;
	}

	public void setCustomerSegmentList(ArrayList<CustomerSegment> customerSegmentList) {
		this.customerSegmentList = customerSegmentList;
	}
	
}
