package com.banking.xc.utils.webService.request.vacation;

import java.util.ArrayList;

import com.banking.xc.entity.CustomerSegment;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;
/**
 * 单选项：根据度假产品及出行人信息获取产品单选项列表
 * @author zhangyinhang
 *
 */
public class VacationOrderItemRequest extends VacationRequest{

	private String requestType = RequestConstant.VACATION_ORDER_ITEM;
	private String pkgID;
	private String isSuperOrder = "false";//是否为强制订单
	//private String isLocked = "false";//是否必选可选
	private String startCity;
	private String adults;
	private String children;
	private String rooms;
	private ArrayList<CustomerSegment> customerSegmentList; //单选项，两个时间，酒店房型id
	@Override
	public String getVacationParams() {
		XmlNode VacationOrderItemRequestNode = new XmlNode("VacationOrderItemRequest");
		VacationOrderItemRequestNode.addNodeByNameAndValue("PkgID", getPkgID());
		VacationOrderItemRequestNode.addNodeByNameAndValue("IsSuperOrder", getIsSuperOrder());
		VacationOrderItemRequestNode.addNodeByNameAndValue("StartCity", getStartCity());
		VacationOrderItemRequestNode.addNodeByNameAndValue("Adults", getAdults());
		VacationOrderItemRequestNode.addNodeByNameAndValue("Children", getChildren());
		VacationOrderItemRequestNode.addNodeByNameAndValue("Rooms", getRooms());

		XmlNode segmentsNode = new XmlNode("Segments");
		VacationOrderItemRequestNode.addChildNode(segmentsNode);
		final ArrayList<CustomerSegment> customerSegmentList  = getCustomerSegmentList();
		for(int i=0;i<customerSegmentList.size();i++){
			final CustomerSegment segment = customerSegmentList.get(i);
			XmlNode CustomerSegmentNode = new XmlNode("CustomerSegment");
			CustomerSegmentNode.addNodeByNameAndValue("Segment", segment.getSegment());
			CustomerSegmentNode.addNodeByNameAndValue("Departure", segment.getDeparture());
			CustomerSegmentNode.addNodeByNameAndValue("Room", segment.getRoom());
			CustomerSegmentNode.addNodeByNameAndValue("Item", segment.getItem());
			CustomerSegmentNode.addNodeByNameAndValue("CheckInDate", segment.getCheckInDate());
			CustomerSegmentNode.addNodeByNameAndValue("CheckOutDate", segment.getCheckOutDate());
			segmentsNode.addChildNode(CustomerSegmentNode);
		}
		//return "<VacationOrderItemRequest><PkgID>51114</PkgID><Adults>2</Adults><Children>0</Children><Rooms>0</Rooms><IsSuperOrder>false</IsSuperOrder><StartCity>2</StartCity><Segments><CustomerSegment><Segment>1</Segment><Departure>1</Departure><Room>0</Room><Item>0</Item></CustomerSegment></Segments></VacationOrderItemRequest>";//
		return VacationOrderItemRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * <VacationOrderItemRequest>
    <PkgID>51114</PkgID>
    <Adults>2</Adults>
    <Children>0</Children>
    <Rooms>0</Rooms>
    <IsSuperOrder>false</IsSuperOrder>
    <StartCity>2</StartCity>
    <Segments>
      <CustomerSegment>
        <Segment>1</Segment>
        <Departure>1</Departure>
        <Room>0</Room>
        <Item>0</Item>
      </CustomerSegment>
    </Segments>
  </VacationOrderItemRequest>
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

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
}
