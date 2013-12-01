package com.banking.xc.utils.xml.flight;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.CityInfoEntity;
import com.banking.xc.entity.CityInfosResponse;
import com.banking.xc.entity.CraftInfoEntity;
import com.banking.xc.entity.CraftInfosResponse;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class FlightCityInfoResponseXmlParseHandler extends XmlParseHandler{

	private ArrayList<CityInfoEntity> cityInfoEntityList;
	private CityInfoEntity cityInfoEntity;
	private String preTag;
	public FlightCityInfoResponseXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		cityInfoEntityList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return cityInfoEntityList;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals("CityDetails", localName)){
			cityInfoEntityList = new ArrayList<CityInfoEntity>();
		}
		if(TextUtils.equals("CityDetail", localName)){
			cityInfoEntity = new CityInfoEntity();
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals("CityDetail", localName)){
			cityInfoEntityList.add(cityInfoEntity);
			cityInfoEntity = null;
		}
		
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag, "CityCode")){
			cityInfoEntity.setCityCode(data);
			return;
		}
		if(TextUtils.equals(preTag, "City")){
			cityInfoEntity.setCityId(data);
			return;
		}
		if(TextUtils.equals(preTag, "CityName")){
			cityInfoEntity.setCityName(data);
			return;
		}
		if(TextUtils.equals(preTag, "Airport")){
			cityInfoEntity.setAirPort(data);
			return;
		}
		/*if(TextUtils.equals(preTag, "CityName_En")){
			cityInfoEntity.setCityNameEn(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProvinceId")){
			cityInfoEntity.setProvinceId(data);
			return;
		}
		if(TextUtils.equals(preTag, "CountryId")){
			cityInfoEntity.setCountryId(data);
			return;
		}
		if(TextUtils.equals(preTag, "CountryCNName")){
			cityInfoEntity.setCountryCNName(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsDCity")){
			cityInfoEntity.setIsDCity(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsACity")){
			cityInfoEntity.setIsACity(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsDCity")){
			cityInfoEntity.setIsTCity(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsDomesticCity")){
			cityInfoEntity.setIsDomesticCity(data);
			return;
		}*/
	}
	/**
	 * 
-<CityDetails> -<CityDetail> 
<CityCode>BJS</CityCode> 
<City>1</City> 
<CityName>北京</CityName> 
<CityEName>Beijing</CityEName> 
<Country>1</Country> 
<Province>1</Province> 
<Airport>NAY,PEK</Airport> 
</CityDetail>
	 */
}
