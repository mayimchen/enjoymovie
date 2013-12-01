package com.banking.xc.utils.webService.request.hotel;

import java.util.ArrayList;



import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 所有订单查询
 * @author zhangyinhang
 *
 */
public class HotelOrderListRequest extends HotelRequest {

	private final String requestType = RequestConstant.HOTEL_ORDERLIST;
	private String userId;
	private ArrayList<String> orderIds;
	private String checkInName;
	private String topCount;//不限制的话输入0
	private String userIp;
	private String reservation;//预订方式(0全部方式；网络预订（Online）；电话预订(OFFLINE)
	private String orderRange;//订单范围(0所有订单；国内酒店订单；国际酒店订单；机酒订单；度假订单)
	private String orderStatus;//订单状态(0全部订单；未提交；处理中（包括确认中，已确认，已付款）；完成状态（包括已成交，已取消）；未出行)

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ArrayList<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(ArrayList<String> orderIds) {
		this.orderIds = orderIds;
	}

	public String getCheckInName() {
		return checkInName;
	}

	public void setCheckInName(String checkInName) {
		this.checkInName = checkInName;
	}

	public String getTopCount() {
		return topCount;
	}

	public void setTopCount(String topCount) {
		this.topCount = topCount;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public String getOrderRange() {
		return orderRange;
	}

	public void setOrderRange(String orderRange) {
		this.orderRange = orderRange;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String getHotelParams() {
		final XmlNode domesticHotelOrderListRequestNode = new XmlNode("DomesticHotelOrderListRequest");
		
		final XmlNode userIdNode = new XmlNode("UserID");
		userIdNode.setInnerValue(getUserId());
		domesticHotelOrderListRequestNode.addChildNode(userIdNode);
		
		final XmlNode orderListNode = new XmlNode("OrderList");
		domesticHotelOrderListRequestNode.addChildNode(orderListNode);
		
		final ArrayList<String> orderIds = getOrderIds();
		if(orderIds!=null){
			for(int i=0,len=orderIds.size();i<len;i++){
				final XmlNode domesticHotelOrderRequestNode = new XmlNode("DomesticHotelOrderRequest");
				final XmlNode orderIdNode = new XmlNode("OrderID");
				orderIdNode.setInnerValue(orderIds.get(i));
				domesticHotelOrderRequestNode.addChildNode(orderIdNode);
				orderListNode.addChildNode(domesticHotelOrderRequestNode);
			}
		}
		XmlNode checkInNameNode = new XmlNode("CheckInName");
		checkInNameNode.setInnerValue(getCheckInName());
		domesticHotelOrderListRequestNode.addChildNode(checkInNameNode);
		
		XmlNode topCountNode = new XmlNode("TopCount");
		topCountNode.setInnerValue(getTopCount());
		domesticHotelOrderListRequestNode.addChildNode(topCountNode);
		
		XmlNode userIPNode = new XmlNode("UserIP");
		userIPNode.setInnerValue(getUserIp());
		domesticHotelOrderListRequestNode.addChildNode(userIPNode);
		
		XmlNode reservationNode = new XmlNode("Reservation");
		reservationNode.setInnerValue(getReservation());
		domesticHotelOrderListRequestNode.addChildNode(reservationNode);
		
		XmlNode orderRangeNode = new XmlNode("OrderRange");
		orderRangeNode.setInnerValue(getOrderRange());
		domesticHotelOrderListRequestNode.addChildNode(orderRangeNode);
		
		XmlNode orderStatusNode = new XmlNode("OrderStatus");
		orderStatusNode.setInnerValue(getOrderStatus());
		domesticHotelOrderListRequestNode.addChildNode(orderStatusNode);
		
		if(Log.D){
			Log.d(TAG, "HotelOrderListRequest"+domesticHotelOrderListRequestNode.toString());
		}
		return domesticHotelOrderListRequestNode.toString();
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
	 *<DomesticHotelOrderListRequest>
    <UserID>xxxxxxx</UserID>
    <OrderList>
      <DomesticHotelOrderRequest>
        <OrderID>100069972</OrderID>
      </DomesticHotelOrderRequest>
      <DomesticHotelOrderRequest>
        <OrderID>100069974</OrderID>
      </DomesticHotelOrderRequest>
      <DomesticHotelOrderRequest>
        <OrderID>100069984</OrderID>
      </DomesticHotelOrderRequest>
    </OrderList>
<CheckInName>小明</CheckInName>
	<TopCount>0</ TopCount >
    <UserIP>127.0.0.1</UserIP>
    <Reservation>0</Reservation>
    <OrderRange>2</OrderRange>
    <OrderStatus>0</OrderStatus>
  </DomesticHotelOrderListRequest>

	 */
}
