package com.banking.xc.utils.webService.request.vacation;

import org.apache.http.protocol.RequestContent;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class VacationHotelsRequest extends VacationRequest{

	private final String requestType = RequestConstant.VACATION_HOTELS;
	private String pkg; //度假产品id
	private String segment; //行程段数
	private String pkgRoom;
	private String PKGAutoMatch;
	private String FGToPP;//是否现付转预付
	private String PriceType;
	private String IsCanReserve;
	private String SearchType;
	private String HotelCount;//显示酒店条数
	private String city;
	private String checkInDate;//入住日期
	private String checkOutDate;//离店日期
	private String sortName;
	private String sortDirection;
	
	
	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getPkgRoom() {
		return pkgRoom;
	}

	public void setPkgRoom(String pkgRoom) {
		this.pkgRoom = pkgRoom;
	}

	public String getPKGAutoMatch() {
		return PKGAutoMatch;
	}

	public void setPKGAutoMatch(String pKGAutoMatch) {
		PKGAutoMatch = pKGAutoMatch;
	}

	public String getFGToPP() {
		return FGToPP;
	}

	public void setFGToPP(String fGToPP) {
		FGToPP = fGToPP;
	}

	public String getPriceType() {
		return PriceType;
	}

	public void setPriceType(String priceType) {
		PriceType = priceType;
	}

	public String getIsCanReserve() {
		return IsCanReserve;
	}

	public void setIsCanReserve(String isCanReserve) {
		IsCanReserve = isCanReserve;
	}

	public String getSearchType() {
		return SearchType;
	}

	public void setSearchType(String searchType) {
		SearchType = searchType;
	}

	public String getHotelCount() {
		return HotelCount;
	}

	public void setHotelCount(String hotelCount) {
		HotelCount = hotelCount;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	@Override
	public String getVacationParams() {
		// TODO Auto-generated method stub
		XmlNode vacationHotelsRequestNode = new XmlNode("VacationHotelsRequest");

		XmlNode pkgNode = new XmlNode("Pkg");
		pkgNode.setInnerValue(getPkg());
		vacationHotelsRequestNode.addChildNode(pkgNode);
		
		XmlNode segmentNode = new XmlNode("Segment");
		segmentNode.setInnerValue(getSegment());
		vacationHotelsRequestNode.addChildNode(segmentNode);
		
		if(getPkgRoom()!=null){
		XmlNode pkgRoomNode = new XmlNode("PkgRoom");
		pkgRoomNode.setInnerValue(getPkgRoom());
		vacationHotelsRequestNode.addChildNode(pkgRoomNode);
		}
		
		if(getPKGAutoMatch()!=null){
		XmlNode pKGAutoMatchNode = new XmlNode("PKGAutoMatch");
		pKGAutoMatchNode.setInnerValue(getPKGAutoMatch());
		vacationHotelsRequestNode.addChildNode(pKGAutoMatchNode);
		}
		if(getFGToPP()!=null){
		XmlNode fGToPPNode = new XmlNode("FGToPP");
		fGToPPNode.setInnerValue(getFGToPP());
		vacationHotelsRequestNode.addChildNode(fGToPPNode);
		}
		if(getPriceType()!=null){
		XmlNode priceTypeNode = new XmlNode("PriceType");
		priceTypeNode.setInnerValue(getPriceType());
		vacationHotelsRequestNode.addChildNode(priceTypeNode);
		}
		if(getIsCanReserve()!=null){
		XmlNode isCanReserveNode = new XmlNode("IsCanReserve");
		isCanReserveNode.setInnerValue(getIsCanReserve());
		vacationHotelsRequestNode.addChildNode(isCanReserveNode);
		}
		vacationHotelsRequestNode.addNodeByNameAndValue("Hotels", "");
		//<Hotels />忽略
		if(getSearchType()!=null){
		XmlNode searchTypeNode = new XmlNode("SearchType");
		searchTypeNode.setInnerValue(getSearchType());
		vacationHotelsRequestNode.addChildNode(searchTypeNode);	
		}
		if(getHotelCount()!=null){
		XmlNode hotelCountNode = new XmlNode("HotelCount");
		hotelCountNode.setInnerValue(getHotelCount());
		vacationHotelsRequestNode.addChildNode(hotelCountNode);
		}
		if(getCity()!=null){
		XmlNode cityNode = new XmlNode("City");
		cityNode.setInnerValue(getCity());
		vacationHotelsRequestNode.addChildNode(cityNode);
		}
		if(getCheckInDate()!=null){
		XmlNode checkInDateNode = new XmlNode("CheckInDate");
		checkInDateNode.setInnerValue(getCheckInDate());
		vacationHotelsRequestNode.addChildNode(checkInDateNode);
		}
		if(getCheckOutDate()!=null){
			XmlNode checkoutDateNode = new XmlNode("CheckOutDate");
			checkoutDateNode.setInnerValue(getCheckOutDate());
			vacationHotelsRequestNode.addChildNode(checkoutDateNode);
		}
		if(getSortName()!=null){
		XmlNode sortNameNode = new XmlNode("SortName");
		sortNameNode.setInnerValue(getSortName());
		vacationHotelsRequestNode.addChildNode(sortNameNode);
		}
		if(getSortDirection()!=null){
		XmlNode sortDirectionNode = new XmlNode("SortDirection");
		sortDirectionNode.setInnerValue(getSortDirection());
		vacationHotelsRequestNode.addChildNode(sortDirectionNode);
		}
		return vacationHotelsRequestNode.toString();
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
	 * <VacationHotelsRequest>
    <Pkg>51114</Pkg>
    <Segment>1</Segment0.1.
    <PkgRoom>true</PkgRoom>
    <PKGAutoMatch>true</PKGAutoMatch>
    <FGToPP>true</FGToPP>
    <PriceType>PP</PriceType>
    <IsCanReserve>true</IsCanReserve>
    <Hotels />
    <SearchType>PkgResSearch</SearchType>
    <HotelCount>10</HotelCount>
    <City>2</City>
    <CheckInDate>2012-05-20</CheckInDate>
    <CheckOutDate>2012-05-22</CheckOutDate>
    <SortName>Price</SortName>
    <SortDirection>Asc</SortDirection>
  </VacationHotelsRequest>

	 */
}
