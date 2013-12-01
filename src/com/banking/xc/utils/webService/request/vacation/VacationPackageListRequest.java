package com.banking.xc.utils.webService.request.vacation;

import android.text.TextUtils;

import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

/**
 * 必须先进行地址选择器搜索，实现方式见VacationList
 * @author zhangyinhang
 *
 */
public class VacationPackageListRequest extends VacationRequest{

	final String requestType = RequestConstant.VACATION_PACKAGE_LIST;
	String startCityId; //必要
	String addSelectorID;//目的城市？必要
	String searchType = "D";//必要U
	String searchValue;//必要
	String sortType = "DefaultSort";
	String SortDirection = "DESC";
	String pageCount;
	String pageNumber;//分页
	//目的地选择int[],地域int[]
	String DestCity;//3,12
	String SearchTabType;// = "ALL";//搜索大类标签
	
	//还有价格，地域筛选，目的地
	@Override
	public String getVacationParams() {
		XmlNode VacationListRequestNode = new XmlNode("VacationListRequest");
		VacationListRequestNode.addNodeByNameAndValue("StartCityID", getStartCityId());
		VacationListRequestNode.addNodeByNameAndValue("AddSelectorID", getAddSelectorID());
		VacationListRequestNode.addNodeByNameAndValue("SearchType", getSearchType());
		if(!TextUtils.isEmpty(getSearchTabType())){
			VacationListRequestNode.addNodeByNameAndValue("SearchTabType", getSearchTabType());
		}
		VacationListRequestNode.addNodeByNameAndValue("SearchValue", getSearchValue());
		VacationListRequestNode.addNodeByNameAndValue("SortType", getSortType());
		VacationListRequestNode.addNodeByNameAndValue("SortDirection", getSortDirection());
		VacationListRequestNode.addNodeByNameAndValue("PageCount", getPageCount());
		VacationListRequestNode.addNodeByNameAndValue("PageNumber", getPageNumber());
		if(!TextUtils.isEmpty(getDestCity())){
			VacationListRequestNode.addNodeByNameAndValue("DestCity", getDestCity());
		}
		return VacationListRequestNode.toString();
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

	public String getStartCityId() {
		return startCityId;
	}

	public void setStartCityId(String startCityId) {
		this.startCityId = startCityId;
	}

	public String getAddSelectorID() {
		return addSelectorID;
	}

	public void setAddSelectorID(String addSelectorID) {
		this.addSelectorID = addSelectorID;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchTabType() {
		return SearchTabType;
	}

	public void setSearchTabType(String searchTabType) {
		SearchTabType = searchTabType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortDirection() {
		return SortDirection;
	}

	public void setSortDirection(String sortDirection) {
		SortDirection = sortDirection;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getDestCity() {
		return DestCity;
	}

	public void setDestCity(String destCity) {
		DestCity = destCity;
	}
	
	
}
