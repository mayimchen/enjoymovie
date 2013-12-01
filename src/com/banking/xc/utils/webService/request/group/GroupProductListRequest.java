package com.banking.xc.utils.webService.request.group;

import android.text.TextUtils;

import com.banking.xc.utils.Log;
import com.banking.xc.utils.webService.util.RequestConstant;
import com.banking.xc.utils.xml.frame.XmlNode;

public class GroupProductListRequest extends GroupRequest {

	private final String TAG = "GroupProductListRequest";
	private final String requestType = RequestConstant.GROUP_PRODUCT_LIST;
	private String beginDate;
	private String endDate;
	private String keyWords;
	private String lowPrice;
	private String upperPrice;
	private String city;
	private String quantity;
	private String topCount;//显示条数，max=100
	private String sortType;
	/*0 携程推荐;
	1 折扣从高到低;
	2  折扣从低到高;
	3 价格从高到低;
	4 价格从低到高;
	5 销量从高到低;
	6 销量从低到高;
	7 星级从高到低;
	8 星级从低到高;
	9 产品即将开团;
	10 产品即将到期
	 */
	//ProductType,rank
	
	@Override
	public String getGroupParams() {
		XmlNode groupProductListRequestNode = new XmlNode("GroupProductListRequest");
		if(!TextUtils.isEmpty(getBeginDate())){
			XmlNode beginDate = new XmlNode("BeginDate");
			beginDate.setInnerValue(getBeginDate());
			groupProductListRequestNode.addChildNode(beginDate);
		}
		if(!TextUtils.isEmpty(getEndDate())){
			XmlNode endDateNode = new XmlNode("EndDate");
			endDateNode.setInnerValue(getEndDate());
			groupProductListRequestNode.addChildNode(endDateNode);
		}
		if(!TextUtils.isEmpty(getKeyWords())){
			XmlNode keyWordsNode = new XmlNode("KeyWords");
			keyWordsNode.setInnerValue(getKeyWords());
			groupProductListRequestNode.addChildNode(keyWordsNode);
		}
		if(!TextUtils.isEmpty(getLowPrice())){
			XmlNode lowpriceNode = new XmlNode("Lowprice");
			lowpriceNode.setInnerValue(getLowPrice());
			groupProductListRequestNode.addChildNode(lowpriceNode);
		}
		if(!TextUtils.isEmpty(getUpperPrice())){
			XmlNode UpperpriceNode = new XmlNode("Upperprice");
			UpperpriceNode.setInnerValue(getUpperPrice());
			groupProductListRequestNode.addChildNode(UpperpriceNode);
		}
		if(!TextUtils.isEmpty(getCity())){
			XmlNode CityNode = new XmlNode("City");
			CityNode.setInnerValue(getCity());
			groupProductListRequestNode.addChildNode(CityNode);
		}
		
		XmlNode topcountNode = new XmlNode("Topcount");
		topcountNode.setInnerValue(getTopCount());
		groupProductListRequestNode.addChildNode(topcountNode);
		/*
		XmlNode SortTypeNode = new XmlNode("SortType");
		SortTypeNode.setInnerValue(getSortType());
		groupProductListRequestNode.addChildNode(SortTypeNode);
		*/
		if(Log.D){
			Log.d(TAG,"groupProductListRequestNode.toString()"+groupProductListRequestNode.toString());
		}
		return groupProductListRequestNode.toString();
	}

	@Override
	public String getRequestType() {
		return requestType;
	}

	@Override
	public Boolean checkParams() {
		return null;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(String upperPrice) {
		this.upperPrice = upperPrice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTopCount() {
		return topCount;
	}

	public void setTopCount(String topCount) {
		this.topCount = topCount;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
}
