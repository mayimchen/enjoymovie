package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.BaseByGuestAmt;
import com.banking.xc.entity.CancelPenalty;
import com.banking.xc.entity.Fee;
import com.banking.xc.entity.GuaranteePolicy;
import com.banking.xc.entity.MealsIncluded;
import com.banking.xc.entity.RatePlan;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelRatePlanXmlParseHandler extends XmlParseHandler{

	
	List<RatePlan> ratePlanList;
	RatePlan ratePlan;
	BaseByGuestAmt baseByGuestAmt;
	Fee fee;
	GuaranteePolicy guaranteePolicy;
	CancelPenalty cancelPenalty;
	MealsIncluded mealsIncluded;
	ArrayList<String> invCodeList;
	String invCode;
	
	String preTag;
	public HotelRatePlanXmlParseHandler(XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		ratePlanList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return ratePlanList;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "RatePlans")){
			ratePlanList = new ArrayList<RatePlan>();
		}
		
		if(TextUtils.equals(localName, "RatePlan")){
			ratePlan = new RatePlan();
			ratePlan.setRatePlanCode(attributes.getValue("RatePlanCode"));
			ratePlan.setRatePlanCategory(attributes.getValue("RatePlanCategory"));
			ratePlan.setMarketCode(attributes.getValue("MarketCode"));
		}
		if(TextUtils.equals(localName, "Rate")){
			ratePlan.setStart(attributes.getValue("Start"));
			ratePlan.setEnd(attributes.getValue("End"));
			ratePlan.setStatus(attributes.getValue("Status"));
		}
		if(TextUtils.equals(localName, "BaseByGuestAmt")){
			baseByGuestAmt = new BaseByGuestAmt();
			baseByGuestAmt.setAmountBeforeTax(attributes.getValue("AmountBeforeTax"));
			baseByGuestAmt.setCurrencyCode(attributes.getValue("CurrencyCode"));
			baseByGuestAmt.setListPrice(attributes.getValue("ListPrice"));
			baseByGuestAmt.setNumberOfGuests(attributes.getValue("NumberOfGuests"));
		}
		if(TextUtils.equals(localName, "Fee")){
			fee = new Fee();
			fee.setAmount(attributes.getValue("Amount"));
			fee.setChargeUnit(attributes.getValue("ChargeUnit"));
			fee.setCode(attributes.getValue("Code"));
			fee.setCurrencyCode(attributes.getValue("CurrencyCode"));
		}
		
		//直接无视GuaranteePolicies节点
		if(TextUtils.equals(localName, "GuaranteePolicy")){
			guaranteePolicy = new GuaranteePolicy();
			guaranteePolicy.setGuaranteeCode(attributes.getValue("GuaranteePolicy"));
			guaranteePolicy.setHoldTime(attributes.getValue("HoldTime"));
		}
		//直接无视CancelPolicies节点
		if(TextUtils.equals(localName, "CancelPenalty")){
			cancelPenalty = new CancelPenalty();
			
			cancelPenalty.setStart(attributes.getValue("Start"));
			cancelPenalty.setEnd(attributes.getValue("End"));
		}
		if(TextUtils.equals(localName, "AmountPercent")&&cancelPenalty!=null){
			
			cancelPenalty.setAmount(attributes.getValue("Amount"));
			cancelPenalty.setCurrencyCode(attributes.getValue("CurrencyCode"));
		}
		
		if(TextUtils.equals(localName, "MealsIncluded")){
			
			mealsIncluded = new MealsIncluded();
			//mealsIncluded.getMeals().
			List<String> meals = new ArrayList<String>();
			if(attributes.getValue("Breakfast")!=null){
				meals.add("Breakfast");
			}
			mealsIncluded.setMeals(meals);
		}
		if(TextUtils.equals(localName, "SellableProducts")){
			invCodeList = new ArrayList<String>();
		}	
		if(TextUtils.equals(localName, "SellableProduct")){
			if(attributes.getValue("InvCode")!=null){
				invCode = attributes.getValue("InvCode");
			}
			 
		}
		if(TextUtils.equals(localName, "Description")){
			if(TextUtils.equals(preTag, "SellableProducts")||TextUtils.equals(preTag, "Offers")){
				ratePlan.setDescriptionName(attributes.getValue("Name"));
			}
			 
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "RatePlans")){
			
		}
		
		if(TextUtils.equals(localName, "RatePlan")){
			ratePlanList.add(ratePlan);
			ratePlan = null;
		}
		if(TextUtils.equals(localName, "Rate")){
		}
		if(TextUtils.equals(localName, "BaseByGuestAmt")){
			ratePlan.setBaseByGuestAmt(baseByGuestAmt);
			baseByGuestAmt = null;
		}
		if(TextUtils.equals(localName, "Fee")){
			ratePlan.setFee(fee);
			fee = null;
		}
		
		//直接无视GuaranteePolicies节点
		if(TextUtils.equals(localName, "GuaranteePolicy")){
			ratePlan.setGuaranteePolicy(guaranteePolicy);
			guaranteePolicy = null;
		}
		//直接无视CancelPolicies节点
		if(TextUtils.equals(localName, "GuaranteePolicy")){
			ratePlan.setCancelPenalty(cancelPenalty);
			cancelPenalty = null;
		}
		
		
		if(TextUtils.equals(localName, "MealsIncluded")){
			ratePlan.setMealsIncluded(mealsIncluded);
			mealsIncluded = null;
		}
		if(TextUtils.equals(localName, "SellableProducts")){
			ratePlan.setInvCodeList(invCodeList);
			invCodeList = null;
		}	
		//加入这个为了解析Description
		preTag = localName;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "Text")){
			if(fee!=null)
			{
				fee.setDescriptionText(data);
			}
		}
	}
	
	
/**
 <?xml version="1.0"?><Response>
<Header ShouldRecordPerformanceTime="False" Timestamp="2013-02-28 17:10:49:79088" ReferenceID="77b59c94-
c211-433c-8d0b-7d2863cd0a84" ResultCode="Success" />
	<HotelResponse>
		<OTA_HotelRatePlanRS TimeStamp="2013-02-28T17:10:49.7680751+08:00" Version="1.0" PrimaryLangID="zh" xmlns="http://www.opentravel.org/OTA/2003/05">
			<RatePlans HotelCode="120250">
				<RatePlan RatePlanCode="471843" RatePlanCategory="16" MarketCode="63">
					<BookingRules />
					<Rates>
					<Rate Start="2013-3-3 0:00:00" End="2013-3-3 0:00:00" Status="Open">
						<BaseByGuestAmts>
						<BaseByGuestAmt AmountBeforeTax="370.00" CurrencyCode="CNY" NumberOfGuests="2" ListPrice="370.00" />
						</BaseByGuestAmts>
						<Fees><Fee Code="1001" Amount="40.00" CurrencyCode="CNY" ChargeUnit="21">
							<Description><Text>自助餐</Text></Description></Fee>
						</Fees>
						<GuaranteePolicies>
						<GuaranteePolicy GuaranteeCode="3" HoldTime="18:00:00.0000000+08:00" /></GuaranteePolicies>
		
						<CancelPolicies>
							<CancelPenalty Start="2013-3-2 23:00:00" End="2013-3-4 0:00:00">
							<AmountPercent Amount="370.00" CurrencyCode="CNY" /></CancelPenalty></CancelPolicies>
						<MealsIncluded Breakfast="true" />
					</Rate>
					<Rate Start="2013-3-4 0:00:00" End="2013-3-4 0:00:00" Status="Open">
						<BaseByGuestAmts><BaseByGuestAmt AmountBeforeTax="370.00" CurrencyCode="CNY" NumberOfGuests="2" ListPrice="370.00" /></BaseByGuestAmts><Fees><Fee Code="1001" Amount="40.00" CurrencyCode="CNY" ChargeUnit="21">
						<Description><Text>自助餐</Text></Description></Fee>
						</Fees>
						<GuaranteePolicies><GuaranteePolicy GuaranteeCode="3" HoldTime="18:00:00.0000000+08:00" /></GuaranteePolicies><CancelPolicies>
						<CancelPenalty Start="2013-3-3 23:00:00" End="2013-3-5 0:00:00"><AmountPercent Amount="370.00" CurrencyCode="CNY" /></CancelPenalty></CancelPolicies>
						<MealsIncluded Breakfast="true" />
					</Rate>
					</Rates>
					<Offers>
						<Offer OfferCode="1002"><OfferRules>
						<OfferRule><DateRestriction Start="2012-11-16 0:00:00" End="2013-12-31 0:00:00" /></OfferRule></OfferRules><OfferDescription><Text>此期间入住酒店，每房首晚赠送洗衣币2枚（可用于酒店自助洗衣
							机）。</Text></OfferDescription></Offer>
					</Offers>
					<SellableProducts><SellableProduct InvCode="149761" /></SellableProducts>
					<Description Name="高级大床房" />
						
				</RatePlan>
			<RatePlans/>

 */
}
