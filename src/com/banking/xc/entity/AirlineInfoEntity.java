package com.banking.xc.entity;

public class AirlineInfoEntity {
	private String AirLine;//航空公司二字码
	private String AirLineCode;//航空公司代码
	private String AirLineName;//AirLineEnName
	private String ShortName;//航空公司简称s
	private String GroupId;
	private String GroupName;
	private String StrictType;//航班退改签政策适用的严格类型
	private String AddonPriceProtected;
	private String IsSupportAirPlus;//国内段是否启用价格保护，即是否要求联程的总价不低于国内段航线经济舱全票价之和
	private String OnlineCheckinUrl ;//Online值机URL
	public String getAirLine() {
		return AirLine;
	}
	public void setAirLine(String airLine) {
		AirLine = airLine;
	}
	public String getAirLineCode() {
		return AirLineCode;
	}
	public void setAirLineCode(String airLineCode) {
		AirLineCode = airLineCode;
	}
	public String getAirLineName() {
		return AirLineName;
	}
	public void setAirLineName(String airLineName) {
		AirLineName = airLineName;
	}
	public String getShortName() {
		return ShortName;
	}
	public void setShortName(String shortName) {
		ShortName = shortName;
	}
	public String getGroupId() {
		return GroupId;
	}
	public void setGroupId(String groupId) {
		GroupId = groupId;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void OnlineCheckinUrl (String groupName) {
		GroupName = groupName;
	}
	public String getStrictType() {
		return StrictType;
	}
	public void setStrictType(String strictType) {
		StrictType = strictType;
	}
	public String getAddonPriceProtected() {
		return AddonPriceProtected;
	}
	public void setAddonPriceProtected(String addonPriceProtected) {
		AddonPriceProtected = addonPriceProtected;
	}
	public String getIsSupportAirPlus() {
		return IsSupportAirPlus;
	}
	public void setIsSupportAirPlus(String isSupportAirPlus) {
		IsSupportAirPlus = isSupportAirPlus;
	}
	public String getOnlineCheckinUrl() {
		return OnlineCheckinUrl;
	}
	public void setOnlineCheckinUrl(String onlineCheckinUrl) {
		OnlineCheckinUrl = onlineCheckinUrl;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	
}
