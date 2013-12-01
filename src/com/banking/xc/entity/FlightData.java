package com.banking.xc.entity;

import java.io.Serializable;

public class FlightData implements Serializable{
	//departCityCode,arriv
	private String departCityCode;
	private String arriveCityCode;
	private String takeOffTime;
	private String departTime;
	private String arriveTime;
	private String flight;//航班号
	private String craftType;
	private String rate;
	private String price;
	private String standardPrice;
	private String childStandardPrice;
	private String babyStandardPrice;
	private String adultTax;
	private String childTax;
	private String babyTax;
	private String adultOilFee;
	private String childOilFee;
	private String babyOilFee;
	private String DepartAirportCode;//出发机场三字码
	private String ArriveAirportCode;//到达机场三字码,要根据机场列表查询
	private String DepartAirportName;//出发机场三字码
	private String ArriveAirportName;//到达机场三字码,要根据机场列表查询
	
	
	private String DepartAirportBuildingID;//出发机场航站楼ID
	private String ArriveAirportBuildingID;
	private String StopTimes;
	private String Nonrer;//更标示
	private String Nonend;//转签标示
	private String Nonref;//退票标示
	private String refNote;//更改说明 （中文 或 英文）
	private String rerNote;
	private String endNote;
	private String Remarks;//备注
	private String TicketType;//票种
	private String Quantity;//剩余数量
	private String PriceType;
	//NormalPrice ：普通,
	//SingleTripPrice：提前预售特价,
	//CZSpecialPrice：南航特价

	private String ProductType;
	//Normal
	//YOUNGMAN 
	//OLDMAN
	private String AirlineDibitCode;
	private String airLineName;
	
	
	private String ProductSource;
	//1大系统
	//2共享平台
	//3两者共有
	//4直连产品
	private String Recommend;//推荐等级
	
	private String CanUpGrade;//是否可升舱
	private String CanSeparateSale;//是否可单独销售
	private String  IsLowestPrice;//是否最低价
	private String IsLowestCZSpecialPrice;//是否南航最低价
	private String DeliverTicketType;//送票类型
	private String OutOfPostTime;//是否超出邮寄时间
	private String OutOfSendGetTime;//是否在送票取票时间之外
	private String OutOfAirlineCounterTime;
	private String CanPost;//是否可邮寄
	private String CanSendGet;
	private String CanAirlineCounter;
	private String BeforeFlyDate;//提前预定天数
	private String MealType;//餐食类型
	private String RouteIndex;//航程索引
	
	private String departAirportString;
	private String arriveAirportString;
	private String craftString;
	private String companyString;
	private String noDateArriveTime;
	private String noDateDepartTime;
	

	public String getDepartAirportString() {
		return departAirportString;
	}
	public void setDepartAirportString(String departAirportString) {
		this.departAirportString = departAirportString;
	}
	public String getArriveAirportString() {
		return arriveAirportString;
	}
	public void setArriveAirportString(String arriveAirportString) {
		this.arriveAirportString = arriveAirportString;
	}
	public String getCraftString() {
		return craftString;
	}
	public void setCraftString(String craftString) {
		this.craftString = craftString;
	}
	public String getCompanyString() {
		return companyString;
	}
	public void setCompanyString(String companyString) {
		this.companyString = companyString;
	}
	public String getNoDateArriveTime() {
		return noDateArriveTime;
	}
	public void setNoDateArriveTime(String noDateArriveTime) {
		this.noDateArriveTime = noDateArriveTime;
	}
	public String getNoDateDepartTime() {
		return noDateDepartTime;
	}
	public void setNoDateDepartTime(String noDateDepartTime) {
		this.noDateDepartTime = noDateDepartTime;
	}
	public String getAirlineDibitCode() {
		return AirlineDibitCode;
	}
	public void setAirlineDibitCode(String airlineDibitCode) {
		AirlineDibitCode = airlineDibitCode;
	}
	
	public String getAirLineName() {
		if(airLineName==null){
			return "某航空公司";
		}
		return airLineName;
	}
	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}
	public String getRouteIndex() {
		return RouteIndex;
	}
	public void setRouteIndex(String routeIndex) {
		RouteIndex = routeIndex;
	}
	public String getCanAirlineCounter() {
		return CanAirlineCounter;
	}
	public void setCanAirlineCounter(String canAirlineCounter) {
		CanAirlineCounter = canAirlineCounter;
	}
	public String getOutOfAirlineCounterTime() {
		return OutOfAirlineCounterTime;
	}
	public void setOutOfAirlineCounterTime(String outOfAirlineCounterTime) {
		OutOfAirlineCounterTime = outOfAirlineCounterTime;
	}
	public String getTakeOffTime() {
		return takeOffTime;
	}
	public void setTakeOffTime(String takeOffTime) {
		this.takeOffTime = takeOffTime;
	}
	public String getDepartCityCode() {
		return departCityCode;
	}
	public void setDepartCityCode(String departCityCode) {
		this.departCityCode = departCityCode;
	}
	public String getArriveCityCode() {
		return arriveCityCode;
	}
	public void setArriveCityCode(String arriveCityCode) {
		this.arriveCityCode = arriveCityCode;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStandardPrice() {
		return standardPrice;
	}
	public void setStandardPrice(String standardPrice) {
		this.standardPrice = standardPrice;
	}
	public String getChildStandardPrice() {
		return childStandardPrice;
	}
	public void setChildStandardPrice(String childStandardPrice) {
		this.childStandardPrice = childStandardPrice;
	}
	public String getBabyStandardPrice() {
		return babyStandardPrice;
	}
	public void setBabyStandardPrice(String babyStandardPrice) {
		this.babyStandardPrice = babyStandardPrice;
	}

	public String getAdultTax() {
		return adultTax;
	}
	public void setAdultTax(String adultTax) {
		this.adultTax = adultTax;
	}
	public String getChildTax() {
		return childTax;
	}
	public void setChildTax(String childTax) {
		this.childTax = childTax;
	}
	public String getBabyTax() {
		return babyTax;
	}
	public void setBabyTax(String babyTax) {
		this.babyTax = babyTax;
	}
	public String getAdultOilFee() {
		return adultOilFee;
	}
	public void setAdultOilFee(String adultOilFee) {
		this.adultOilFee = adultOilFee;
	}
	public String getChildOilFee() {
		return childOilFee;
	}
	public void setChildOilFee(String childOilFee) {
		this.childOilFee = childOilFee;
	}
	public String getBabyOilFee() {
		return babyOilFee;
	}
	public void setBabyOilFee(String babyOilFee) {
		this.babyOilFee = babyOilFee;
	}
	public String getDepartAirportCode() {
		return DepartAirportCode;
	}
	public void setDepartAirportCode(String departAirportCode) {
		DepartAirportCode = departAirportCode;
	}
	public String getArriveAirportCode() {
		return ArriveAirportCode;
	}
	public void setArriveAirportCode(String arriveAirportCode) {
		ArriveAirportCode = arriveAirportCode;
	}
	
	public String getDepartAirportName() {
		return DepartAirportName;
	}
	public void setDepartAirportName(String departAirportName) {
		DepartAirportName = departAirportName;
	}
	public String getArriveAirportName() {
		return ArriveAirportName;
	}
	public void setArriveAirportName(String arriveAirportName) {
		ArriveAirportName = arriveAirportName;
	}
	public String getDepartAirportBuildingID() {
		return DepartAirportBuildingID;
	}
	public void setDepartAirportBuildingID(String departAirportBuildingID) {
		DepartAirportBuildingID = departAirportBuildingID;
	}
	public String getArriveAirportBuildingID() {
		return ArriveAirportBuildingID;
	}
	public void setArriveAirportBuildingID(String arriveAirportBuildingID) {
		ArriveAirportBuildingID = arriveAirportBuildingID;
	}
	public String getStopTimes() {
		return StopTimes;
	}
	public void setStopTimes(String stopTimes) {
		StopTimes = stopTimes;
	}
	public String getNonrer() {
		return Nonrer;
	}
	public void setNonrer(String nonrer) {
		Nonrer = nonrer;
	}
	public String getNonend() {
		return Nonend;
	}
	public void setNonend(String nonend) {
		Nonend = nonend;
	}
	public String getNonref() {
		return Nonref;
	}
	public void setNonref(String nonref) {
		Nonref = nonref;
	}
	
	public String getRefNote() {
		return refNote;
	}
	public void setRefNote(String refNote) {
		this.refNote = refNote;
	}
	public String getRerNote() {
		return rerNote;
	}
	public void setRerNote(String rerNote) {
		this.rerNote = rerNote;
	}
	public String getEndNote() {
		return endNote;
	}
	public void setEndNote(String endNote) {
		this.endNote = endNote;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getTicketType() {
		return TicketType;
	}
	public void setTicketType(String ticketType) {
		TicketType = ticketType;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getPriceType() {
		return PriceType;
	}
	public void setPriceType(String priceType) {
		PriceType = priceType;
	}
	public String getProductType() {
		return ProductType;
	}
	public void setProductType(String productType) {
		ProductType = productType;
	}
	public String getProductSource() {
		return ProductSource;
	}
	public void setProductSource(String productSource) {
		ProductSource = productSource;
	}
	public String getRecommend() {
		return Recommend;
	}
	public void setRecommend(String recommend) {
		Recommend = recommend;
	}
	public String getCanUpGrade() {
		return CanUpGrade;
	}
	public void setCanUpGrade(String canUpGrade) {
		CanUpGrade = canUpGrade;
	}
	public String getCanSeparateSale() {
		return CanSeparateSale;
	}
	public void setCanSeparateSale(String canSeparateSale) {
		CanSeparateSale = canSeparateSale;
	}
	public String getIsLowestPrice() {
		return IsLowestPrice;
	}
	public void setIsLowestPrice(String isLowestPrice) {
		IsLowestPrice = isLowestPrice;
	}
	public String getIsLowestCZSpecialPrice() {
		return IsLowestCZSpecialPrice;
	}
	public void setIsLowestCZSpecialPrice(String isLowestCZSpecialPrice) {
		IsLowestCZSpecialPrice = isLowestCZSpecialPrice;
	}
	public String getDeliverTicketType() {
		return DeliverTicketType;
	}
	public void setDeliverTicketType(String deliverTicketType) {
		DeliverTicketType = deliverTicketType;
	}
	public String getOutOfPostTime() {
		return OutOfPostTime;
	}
	public void setOutOfPostTime(String outOfPostTime) {
		OutOfPostTime = outOfPostTime;
	}
	public String getOutOfSendGetTime() {
		return OutOfSendGetTime;
	}
	public void setOutOfSendGetTime(String outOfSendGetTime) {
		OutOfSendGetTime = outOfSendGetTime;
	}
	public String getCanPost() {
		return CanPost;
	}
	public void setCanPost(String canPost) {
		CanPost = canPost;
	}
	public String getCanSendGet() {
		return CanSendGet;
	}
	public void setCanSendGet(String canSendGet) {
		CanSendGet = canSendGet;
	}
	public String getBeforeFlyDate() {
		return BeforeFlyDate;
	}
	public void setBeforeFlyDate(String beforeFlyDate) {
		BeforeFlyDate = beforeFlyDate;
	}
	public String getMealType() {
		return MealType;
	}
	public void setMealType(String mealType) {
		MealType = mealType;
	}
	
}
