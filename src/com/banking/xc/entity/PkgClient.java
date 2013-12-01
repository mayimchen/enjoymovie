package com.banking.xc.entity;

import android.text.TextUtils;

public class PkgClient {
	private String PassportType = "P";
	private String IssueDate = "2013-01-01T00:00:00";//1900-01-01T00:00:00,签发日期
	private String Homeplace = "";
	private String InfoID = "0";//乘客id
	private String Id = "0";
	private String TmpOrderId="0";
	private String ClientName = "待编辑用户";//ss
	private String IDCardType = "1";
	private String IDCardNo = "sth";
	private String BirthDate = "1980-08-08T00:00:00";//
	private String AgeType = "ADU";
	private String Nationality = "CN";
	private String CardCity = "SHA";
	private String ContactInfo = "sth";//
	private String Gender = "M";//
	private String VisaCountry = "";
	private String ClientName_E = "";
	private String ST = "0";
	private String Addcity = "";
	private String Hzno = "";
	private String Hzadd = "";
	private String Hzdate = "0001-01-01T00:00:00";
	private String ISLIST = "";
	private String IDCardTimelimit = "2015-05-05T00:00:00";
	
	private String genderString = "男";
	
	public String getPassportType() {
		return PassportType;
	}
	public void setPassportType(String passportType) {
		PassportType = passportType;
	}
	public String getIssueDate() {
		return IssueDate;
	}
	public void setIssueDate(String issueDate) {
		IssueDate = issueDate;
	}
	public String getHomeplace() {
		return Homeplace;
	}
	public void setHomeplace(String homeplace) {
		Homeplace = homeplace;
	}
	public String getInfoID() {
		return InfoID;
	}
	public void setInfoID(String infoID) {
		InfoID = infoID;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getTmpOrderId() {
		return TmpOrderId;
	}
	public void setTmpOrderId(String tmpOrderId) {
		TmpOrderId = tmpOrderId;
	}
	public String getClientName() {
		return ClientName;
	}
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	public String getIDCardType() {
		return IDCardType;
	}
	public void setIDCardType(String iDCardType) {
		IDCardType = iDCardType;
	}
	public String getIDCardNo() {
		return IDCardNo;
	}
	public void setIDCardNo(String iDCardNo) {
		IDCardNo = iDCardNo;
	}
	public String getBirthDate() {
		return BirthDate;
	}
	public void setBirthDate(String birthDate) {
		BirthDate = birthDate;
	}
	public String getAgeType() {
		return AgeType;
	}
	public void setAgeType(String ageType) {
		AgeType = ageType;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getCardCity() {
		return CardCity;
	}
	public void setCardCity(String cardCity) {
		CardCity = cardCity;
	}
	public String getContactInfo() {
		return ContactInfo;
	}
	public void setContactInfo(String contactInfo) {
		ContactInfo = contactInfo;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getVisaCountry() {
		return VisaCountry;
	}
	public void setVisaCountry(String visaCountry) {
		VisaCountry = visaCountry;
	}
	public String getClientName_E() {
		return ClientName_E;
	}
	public void setClientName_E(String clientName_E) {
		ClientName_E = clientName_E;
	}
	public String getST() {
		return ST;
	}
	public void setST(String sT) {
		ST = sT;
	}
	public String getAddcity() {
		return Addcity;
	}
	public void setAddcity(String addcity) {
		Addcity = addcity;
	}
	public String getHzno() {
		return Hzno;
	}
	public void setHzno(String hzno) {
		Hzno = hzno;
	}
	public String getHzadd() {
		return Hzadd;
	}
	public void setHzadd(String hzadd) {
		Hzadd = hzadd;
	}
	public String getHzdate() {
		return Hzdate;
	}
	public void setHzdate(String hzdate) {
		Hzdate = hzdate;
	}
	public String getISLIST() {
		return ISLIST;
	}
	public void setISLIST(String iSLIST) {
		ISLIST = iSLIST;
	}
	public String getIDCardTimelimit() {
		return IDCardTimelimit;
	}
	public void setIDCardTimelimit(String iDCardTimelimit) {
		IDCardTimelimit = iDCardTimelimit;
	}
	
	public String getGenderString(){
		if(TextUtils.equals(Gender, "F")){
			return "女";
		}else{
			return "男";
		}
	}
}
