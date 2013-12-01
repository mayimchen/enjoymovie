package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.VacationBaseInfo;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class VacationPackageListResponseXmlParseHandler extends XmlParseHandler{

	ArrayList<VacationBaseInfo> vacationBaseInfoList;
	VacationBaseInfo vacationBaseInfo;
	String preTag;
	public VacationPackageListResponseXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		vacationBaseInfoList = null;
		
	}

	@Override
	public Object getObjectWhenEnd() {
		return vacationBaseInfoList;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "VacationList")){
			vacationBaseInfoList = new ArrayList<VacationBaseInfo>();
		}
		if(TextUtils.equals(localName, "VacationBaseInfo")){
			vacationBaseInfo = new VacationBaseInfo();
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "VacationList")){
		}
		if(TextUtils.equals(localName, "VacationBaseInfo")){
			vacationBaseInfoList.add(vacationBaseInfo);
			vacationBaseInfo = null;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String data = new String(ch,start,length);
		if(TextUtils.equals("Pkg", preTag)){
			vacationBaseInfo.setPkg(data);
			return;
		}
		if(TextUtils.equals("PkgName", preTag)){
			vacationBaseInfo.setPkgName(data);
			return;
		}
		if(TextUtils.equals("PkgTourGrade", preTag)){
			vacationBaseInfo.setPkgTourGrade(data);
			return;
		}
		if(TextUtils.equals("PriceDownTag", preTag)){
			vacationBaseInfo.setPriceDownTag(data);
			return;
		}
		if(TextUtils.equals("IsAffirmTour", preTag)){
			vacationBaseInfo.setIsAffirmTour(data);
			return;
		}
		if(TextUtils.equals("TableType", preTag)){
			vacationBaseInfo.setTableType(data);
			return;
		}
		if(TextUtils.equals("Attraction", preTag)){
			vacationBaseInfo.setAttraction(data);
			return;
		}
		if(TextUtils.equals("MinPrice", preTag)){
			vacationBaseInfo.setMinPrice(data);
			return;
		}
		if(TextUtils.equals("SetOffDays", preTag)){
			vacationBaseInfo.setSetOffDays(data);
			return;
		}
		if(TextUtils.equals("TypeDesc", preTag)){
			vacationBaseInfo.setTypeDesc(data);
			return;
		}
		
		if(TextUtils.equals("StartCity", preTag)){
			vacationBaseInfo.setStartCity(data);
			return;
		}if(TextUtils.equals("DestCity", preTag)){
			vacationBaseInfo.setDestCity(data);
			return;
		}
		
		if(TextUtils.equals("PkgURL", preTag)){
			vacationBaseInfo.setPkgURL(data);
			return;
		}
		if(TextUtils.equals("CharacteristicDesc", preTag)){
			vacationBaseInfo.setCharacteristicDesc(data);
			return;
		}
		/*if(TextUtils.equals("SeasonNotice", preTag)){
			vacationBaseInfo.setSeasonNotice(data);
			return;
		}*/
		if(TextUtils.equals("ListPrice", preTag)){
			vacationBaseInfo.setListPrice(data);
			return;
		}
		if(TextUtils.equals("MaxDays", preTag)){
			vacationBaseInfo.setMaxDays(data);
			return;
		}
		if(TextUtils.equals("MinDays", preTag)){
			vacationBaseInfo.setMinDays(data);
			return;
		}
		if(TextUtils.equals("MaxDays", preTag)){
			vacationBaseInfo.setMaxDays(data);
			return;
		}
		if(TextUtils.equals("MaxEMoney", preTag)){
			vacationBaseInfo.setMaxEMoney(data);
			return;
		}
		if(TextUtils.equals("MinPersons", preTag)){
			vacationBaseInfo.setMinPerson(data);
			return;
		}
		if(TextUtils.equals("Img", preTag)){
			vacationBaseInfo.setImg(data);
			return;
		}
		if(TextUtils.equals("Festival", preTag)){
			vacationBaseInfo.setFestival(data);
			return;
		}
		if(TextUtils.equals("Attrib1", preTag)){
			vacationBaseInfo.setAttrib1(data);
			return;
		}
		if(TextUtils.equals("District", preTag)){
			vacationBaseInfo.setDistrict(data);
			return;
		}
		if(TextUtils.equals("Star", preTag)){
			vacationBaseInfo.setStar(data);
			return;
		}
		
	
	}
	
	/**
	 * <VacationListResponse><VacationList><VacationBaseInfo>
	 * <Pkg>88031</Pkg>
	 * <PkgName>金牌导游·三亚蜈支洲岛5日豪华半自助游(赠专车送机、4人立减300)</PkgName>
	 * <PkgTourGrade>8</PkgTourGrade>
	 * <PriceDownTag /><IsAffirmTour>F</IsAffirmTour><TableType>GroupTravel</TableType>
	 * <Attraction>★ 后2晚精选豪华酒店：&lt;A href="http://hotels.ctrip.com/hotel/345082.html#ctm_ref=hd_0_0_0_0_lst_sr_1_df_ls_1_n_hi_0_0_0" target=_blank&gt;三亚文华东方酒店&lt;/A&gt;（评分4.7）、&lt;A href="http://hotels.ctrip.com/hotel/345522.html#ctm_ref=hd_sr_lst_hi_n_a_1_1_df" target=_blank&gt;天域度假酒店&lt;/A&gt;（评分4.6）、&lt;A href="http://hotels.ctrip.com/hotel/345071.html#ctm_ref=hd_0_0_0_0_lst_sr_1_df_ls_1_n_hi_0_0_0" target=_blank&gt;金茂三亚希尔顿大酒店&lt;/A&gt;（评分4.6)等，低至5折！&lt;A href="http://pages.ctrip.com/tour/ingroupline_pages.asp?folder=ingroup1211&amp;file=98
" target=_blank&gt;半自助产品说明书&lt;/A&gt;，助您快速选择最合适行程！</Attraction>
	<MinPrice>5510</MinPrice><SetOffDays>04/13、04/27</SetOffDays><TypeDesc>国内半自助</TypeDesc><StartCity>2</StartCity>
	<DestCity>43</DestCity><PkgURL>http://u.ctrip.com/union/CtripRedirect.aspx?TypeID=80&amp;AllianceID=5208&amp;SID=123887&amp;VacationID=p88031s2&amp;ouid=</PkgURL>
	<CharacteristicDesc>精选五星豪华酒店2晚，限量抢购，最低相当于现付价5折！</CharacteristicDesc><SeasonNotice />
	<PkgCharacteristic /><ListPrice>2708.0000</ListPrice><MaxDays>5</MaxDays><MinDays>5</MinDays>
	<MaxEMoney>0</MaxEMoney><MinPersons>0</MinPersons><Img>http://pkgpic.c-ctrip.com/images2/1/61/61_512_s28879.JPG</Img>
	<Festival>劳动节、</Festival><Attrib1>0</Attrib1><DistrictInfo><PackageDistrictInfo><District>61</District><Pkg>88031</Pkg>
	<Star>0</Star></PackageDistrictInfo></DistrictInfo></VacationBaseInfo>
	 */
	
}
