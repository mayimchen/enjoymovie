package com.banking.xc.utils.xml.group;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GroupProductHotelEntity;
import com.banking.xc.entity.GroupProductListEntity;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;
/**
 * 注解实现才是王道...
 * @author zhangyinhang
 *
 */
public class GroupProductListXmlParseHandler extends XmlParseHandler{

	
	private ArrayList<GroupProductListEntity> groupProductListEntityList;
	private GroupProductListEntity groupProductListEntity;
	private ArrayList<GroupProductHotelEntity> groupProductHotelEntityList;
	private GroupProductHotelEntity groupProductHotelEntity;
	private String preTag;
	private boolean isProductMarket = false;//该标签属于GroupProductListEntity，但是会覆盖掉前面值
	//有个count，没有再封装和返回
	public GroupProductListXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
		
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		groupProductListEntityList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return groupProductListEntityList;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "GroupDataList")){
			groupProductListEntityList = new ArrayList<GroupProductListEntity>();
		}
		if(TextUtils.equals(localName, "GroupProductListEntity")){
			groupProductListEntity = new GroupProductListEntity();
		}
		if(TextUtils.equals(localName, "HotelList")){
			groupProductHotelEntityList = new ArrayList<GroupProductHotelEntity>();
		}
		if(TextUtils.equals(localName, "GroupProductHotelEntity")){
			groupProductHotelEntity = new GroupProductHotelEntity();
		}
		if(TextUtils.equals(localName, "ProductsMarket")){
			isProductMarket = true;
		}
		preTag = localName;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if(isProductMarket){
			return;
		}
		final String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "OUrl")){
			groupProductListEntity.setoUrl(data);
			return;
		}
		if(TextUtils.equals(preTag, "LocationId")){
			if(groupProductHotelEntity==null){
				groupProductListEntity.setLocationID(data);
			}else{
				groupProductHotelEntity.setLocationId(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "ProductItemType")){
			groupProductListEntity.setProductItemType(data);
			return;
		}
		if(TextUtils.equals(preTag, "SoldOut")){
			groupProductListEntity.setSoldOut(data);
			return;
		}
		if(TextUtils.equals(preTag, "LabelValue")){
			groupProductListEntity.setLabelValue(data);
			return;
		}
		if(TextUtils.equals(preTag, "HotelID")){
			groupProductListEntity.setHotelID(data);
			return;
		}
		if(TextUtils.equals(preTag, "Rate")){
			groupProductListEntity.setRate(data);
			return;
		}
		if(TextUtils.equals(preTag, "StartDate")){
			groupProductListEntity.setStartDate(data);
			return;
		}
		if(TextUtils.equals(preTag, "EndDate")){
			groupProductListEntity.setEndDate(data);
			return;
		}
		if(TextUtils.equals(preTag, "Price")){
			groupProductListEntity.setPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProductId")){
			groupProductListEntity.setProductID(data);
			return;
		}
		if(TextUtils.equals(preTag, "NowPrice")){
			groupProductListEntity.setNowPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProductPrice")){
			groupProductListEntity.setProductPrice(data);
			return;
		}
		if(TextUtils.equals(preTag, "Pictures")){
			groupProductListEntity.setPictures(data);
			return;
		}
		if(TextUtils.equals(preTag, "SaledItemCount")){
			groupProductListEntity.setSaledItemCount(data);
			return;
		}
		if(TextUtils.equals(preTag, "Url")){
			groupProductListEntity.setUrl(data);
			return;
		}
		
		//加入两个非空判断
		if(TextUtils.equals(preTag, "Description")){
			if(groupProductHotelEntity!=null){
				groupProductHotelEntity.setDescription(data);
			}else{
				groupProductListEntity.setDescription(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Name")){
			if(groupProductHotelEntity!=null){
				groupProductHotelEntity.setName(data);
			}else{
				groupProductListEntity.setName(data);
			}
			return;
		}
		
		//专属于hotelEntity
		if(TextUtils.equals(preTag, "ItemName")){
			groupProductHotelEntity.setItemName(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsContain")){
			groupProductHotelEntity.setIsContain(data);
			return;
		}
		if(TextUtils.equals(preTag, "Zone")){
			groupProductHotelEntity.setZone(data);
			return;
		}
		if(TextUtils.equals(preTag, "CtripId")){
			groupProductHotelEntity.setCtripID(data);
			return;
		}
		if(TextUtils.equals(preTag, "GLON")){
			groupProductHotelEntity.setGlon(data);
			return;
		}
		if(TextUtils.equals(preTag, "GLAT")){
			groupProductHotelEntity.setGlat(data);
			return;
		}
		if(TextUtils.equals(preTag, "LON")){
			groupProductHotelEntity.setLon(data);
			return;
		}
		if(TextUtils.equals(preTag, "LAT")){
			groupProductHotelEntity.setLat(data);
			return;
		}
		if(TextUtils.equals(preTag, "Contact")){
			groupProductHotelEntity.setContact(data);
			return;
		}
		if(TextUtils.equals(preTag, "CityId")){
			groupProductHotelEntity.setCityID(data);
			return;
		}
		if(TextUtils.equals(preTag, "City")){
			groupProductHotelEntity.setCity(data);
			return;
		}
		if(TextUtils.equals(preTag, "HotelGroupId")){
			groupProductHotelEntity.setHotelGroupID(data);
			return;
		}
		if(TextUtils.equals(preTag, "Tel")){
			groupProductHotelEntity.setTel(data);
			return;
		}
		if(TextUtils.equals(preTag, "Address")){
			groupProductHotelEntity.setAddress(data);
			return;
		}
		if(TextUtils.equals(preTag, "IsStarRate")){
			groupProductHotelEntity.setIsStarRate(data);
			return;
		}
		if(TextUtils.equals(preTag, "StarRate")){
			groupProductHotelEntity.setStarRate(data);
			return;
		}
		
		if(TextUtils.equals(preTag, "Id")){
			groupProductHotelEntity.setId(data);
			return;
		}
		if(TextUtils.equals(preTag, "ProvinceName")){
			groupProductHotelEntity.setProvinceName(data);
			return;
		}
		if(TextUtils.equals(preTag, "LocationName")){
			groupProductHotelEntity.setLocationName(data);
		}
		/*if(TextUtils.equals(preTag, "LocationId")){
			groupProductHotelEntity.setLocationId(data);
		}*/
		if(TextUtils.equals(preTag, "ZoneId")){
			groupProductHotelEntity.setZoneId(data);
		}
		if(TextUtils.equals(preTag, "VendorID")){
			groupProductHotelEntity.setVendorID(data);
		}
		if(TextUtils.equals(preTag, "CommentValue")){
			groupProductHotelEntity.setCommentValue(data);
		}
		if(TextUtils.equals(preTag, "CtripStar")){
			groupProductHotelEntity.setCtripStar(data);
		}
		
		
		
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "GroupDataList")){
			//
		}
		if(TextUtils.equals(localName, "GroupProductListEntity")){
			groupProductListEntityList.add(groupProductListEntity);
			groupProductListEntity = null;
		}
		if(TextUtils.equals(localName, "HotelList")){
			groupProductListEntity.setGroupProductHotelEntityList(groupProductHotelEntityList);
			groupProductHotelEntityList = null;
		
		}
		if(TextUtils.equals(localName, "GroupProductHotelEntity")){
			groupProductHotelEntityList.add(groupProductHotelEntity);
			groupProductHotelEntity = null;
		}
		if(TextUtils.equals(localName, "ProductsMarket")){
			isProductMarket = false;
		}
	}

	
	
	/**
	 * <?xml version="1.0"?><Response><Header ShouldRecordPerformanceTime="False" Timestamp="2013-03-19 10:06:05:46979" ReferenceID="4c444b23-95f3-4912-92e7-0957c64df1a0" ResultCode="Success" /><GroupProductListResponse><GroupDataList><GroupProductListEntity><OUrl>http://tuan.ctrip.com/group/47689.html</OUrl><LocationId>0</LocationId><ProductItemType>酒店</ProductItemType><HotelList>

<GroupProductHotelEntity>
<ItemName>酒店</ItemName>
<IsContain>false</IsContain>
<Zone>上地、中关村地区</Zone><CtripId>123367</CtripId><GLON>116.331833</GLON><GLAT>39.992393</GLAT><LON>116.3236</LON><LAT>39.98931</LAT>
<Contact>田冲</Contact><CityId>1</CityId><City>北京</City><HotelGroupId>0</HotelGroupId>
<Tel>010-82629195</Tel><Address>成府路华清嘉园22号</Address><ItemType>1</ItemType><IsStarRate>0</IsStarRate><StarRate>2</StarRate><Description /><Name>和家宾馆连锁（北京北四环店）</Name><Id>24200</Id><ProvinceName>北京</ProvinceName><LocationName>海淀区</LocationName><LocationId>96</LocationId><ZoneId>651</ZoneId><VendorID>0</VendorID><CommentValue>4.0999999046325684</CommentValue><CtripStar>1.5</CtripStar>
</GroupProductHotelEntity>
...
	 */
}
