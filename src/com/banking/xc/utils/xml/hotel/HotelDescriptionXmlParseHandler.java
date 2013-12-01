package com.banking.xc.utils.xml.hotel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GuestRoom;
import com.banking.xc.entity.HotelDescription;
import com.banking.xc.entity.ImageItem;
import com.banking.xc.entity.RefPoint;
import com.banking.xc.entity.HotelService;
import com.banking.xc.entity.TextItem;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class HotelDescriptionXmlParseHandler extends XmlParseHandler{

	HotelDescription hotelDescription;
	private List<RefPoint> refPoints;
	RefPoint refPoint;
	private List<ImageItem> imageItems;
	ImageItem imageItem;
	private List<TextItem> textItems;
	TextItem textItem;
	private List<HotelService> services;
	HotelService service;
	private List<GuestRoom> guestRooms;
	private GuestRoom guestRoom;
	//private String amenitiesString; //判断Amenities for one room
	private List<String> amenities;
	String preTag;
	public HotelDescriptionXmlParseHandler(XmlParseListener XmlParseListener, InputStream inputStream) {
		super(XmlParseListener, inputStream);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		hotelDescription = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		// TODO Auto-generated method stub
		return hotelDescription;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		if(TextUtils.equals(localName, "OTA_HotelDescriptiveInfoRS")){
			hotelDescription = new HotelDescription();
		}
		if(TextUtils.equals(localName, "HotelInfo")){
			hotelDescription.setBuildTime(attributes.getValue("WhenBuilt"));
		}
		
		if(TextUtils.equals(localName, "SegmentCategory")){
			hotelDescription.setSegmentCategoryCode(attributes.getValue("Code"));
		}
		if(TextUtils.equals(localName, "RefPoints")){
			refPoints = new ArrayList<RefPoint>();
		}
		if(TextUtils.equals(localName, "RefPoint")){
			refPoint = new RefPoint();
			refPoint.setDistance(attributes.getValue("Distance"));
			refPoint.setUnitOfMeasureCode(attributes.getValue("UnitOfMeasureCode"));
			refPoint.setName(attributes.getValue("Name"));
			refPoint.setRefPointCategoryCode(attributes.getValue("RefPointCategoryCode"));
			refPoint.setRefPointName(attributes.getValue("RefPointName"));
		}
		if(TextUtils.equals(localName, "ImageItems")){
			imageItems = new ArrayList<ImageItem>();
		}
		if(TextUtils.equals(localName, "ImageItem")){
			imageItem = new ImageItem();
			imageItem.setCategory(attributes.getValue("Category"));
		}
		if(TextUtils.equals(localName, "Description")){
			if(TextUtils.equals(preTag,"ImageFormat")||TextUtils.equals(preTag,"URL")){
				imageItem.setDescriptCaption(attributes.getValue("Caption"));
			}
		}
		if(TextUtils.equals(localName, "TextItems")){
			textItems = new ArrayList<TextItem>();
		}
		if(TextUtils.equals(localName, "TextItem")){
			textItem = new TextItem();
			textItem.setCategory(attributes.getValue("Category"));
		}
		if(TextUtils.equals(localName, "Services")){
			services = new ArrayList<HotelService>();
		}
		if(TextUtils.equals(localName, "Service")){
			service = new HotelService();
			service.setCode(attributes.getValue("Code"));
			service.setId(attributes.getValue("ID"));
		}
		if(TextUtils.equals(localName, "GuestRooms")){
			guestRooms = new ArrayList<GuestRoom>();
		}
		if(TextUtils.equals(localName, "GuestRoom")){
			guestRoom = new GuestRoom();
			guestRoom.setRoomTypeName(attributes.getValue("RoomTypeName"));
		}
		if(TextUtils.equals(localName, "TypeRoom")){
			//guestRoom = new GuestRoom();
			guestRoom.setSize(attributes.getValue("Size"));
			guestRoom.setStandardOccupancy(attributes.getValue("StandardOccupancy"));
			guestRoom.setRoomTypeCode(attributes.getValue("RoomTypeCode"));
			guestRoom.setFloor(attributes.getValue("Floor"));
			guestRoom.setBedTypeCode(attributes.getValue("BedTypeCode"));
			guestRoom.setQuantity(attributes.getValue("Quantity"));
		}
		if(TextUtils.equals(localName, "Amenities")){
			//amenitiesString = "Amenities";
			amenities = new ArrayList<String>();
		}
		preTag = localName;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "RefPoints")){
			hotelDescription.setRefPoints(refPoints);
			refPoints = null;
		}
		if(TextUtils.equals(localName, "RefPoint")){
			refPoints.add(refPoint);
			refPoint = null;
		}
		if(TextUtils.equals(localName, "ImageItems")){
			hotelDescription.setImageItems(imageItems);
			imageItems = null;
		}
		if(TextUtils.equals(localName, "ImageItem")){
			imageItems.add(imageItem);
			imageItem = null;
		}
		if(TextUtils.equals(localName, "TextItems")){
			hotelDescription.setTextItems(textItems);
			textItems = null;
		}
		if(TextUtils.equals(localName, "TextItem")){
			textItems.add(textItem);
			textItem = null;
		}
		if(TextUtils.equals(localName, "Services")){
			hotelDescription.setServices(services);
			services = null;
		}
		if(TextUtils.equals(localName, "Service")){
			services.add(service);
			service = null;
		}
		if(TextUtils.equals(localName, "GuestRooms")){
			hotelDescription.setGuestRooms(guestRooms);
			guestRooms = null;
		}
		if(TextUtils.equals(localName, "GuestRoom")){
			guestRooms.add(guestRoom);
			guestRoom = null;
		}
		if(TextUtils.equals(localName, "Amenities")){
			guestRoom.setAmenityDescriptiveTexts(amenities);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		final String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "DescriptiveText")){
			if(refPoint!=null)
			{
				refPoint.setDescriptiveText(data);
			}else if(service != null){
				service.setDescriptionTest(data);
			}else if(amenities != null){
				amenities.add(data);
			}
			
		}
		if(TextUtils.equals(preTag, "URL")&&imageItem!=null){
			imageItem.setImageUrl(data);
		}
		if(TextUtils.equals(preTag, "Description")&&textItem!=null){//这里 可以不判断
			textItem.setDescription(data);
		}
	}
	
	
/**
 * <Response>
  <Header ShouldRecordPerformanceTime="false" ReferenceID="414dfa1b-d66f-4c50-a341-0e0df231940e" ResultCode="Success" />
  <HotelResponse>
    <OTA_HotelDescriptiveInfoRS TimeStamp="2012-09-26T16:57:42.8992109+08:00" Version="1.0" PrimaryLangID="zh" xmlns="http://www.opentravel.org/OTA/2003/05">
      <HotelDescriptiveContents>
        <HotelDescriptiveContent BrandCode="0" HotelCode="18806" HotelCityCode="12" HotelName="南京湖滨金陵饭店" AreaID="180">
          <HotelInfo>
            <CategoryCodes>
              <SegmentCategory Code="4"/>
            </CategoryCodes>
            <Position Latitude="31.9489" Longitude="118.81598"/>
          </HotelInfo>
          <FacilityInfo>
            <GuestRooms/>
          </FacilityInfo>
          <AreaInfo>
            <RefPoints>
              <RefPoint Distance="27.63" UnitOfMeasureCode="2" Name="南京禄口国际机场" RefPointCategoryCode="3" RefPointName="机场">
                <DescriptiveText>乘坐机场巴士1号线，在雨花台站下车，步行至雨花南路东站，换乘129路，在卡子门西站下车，换乘安铜线，在殷巷站下车，步行约240米。乘坐出租车约63元。</DescriptiveText>
              </RefPoint>
              <RefPoint Distance="9.1" UnitOfMeasureCode="2" Name="南京火车南站" RefPointCategoryCode="2" RefPointName="火车站">
                <DescriptiveText>乘坐地铁1号线南延线(中国药科大学方向)，在胜太路站下车，步行至胜太路站，换乘安铜线，在殷巷站下车，步行约200米。乘坐出租车约25元。</DescriptiveText>
              </RefPoint>
              <RefPoint Distance="14.07" UnitOfMeasureCode="2" Name="新街口" RefPointCategoryCode="4" RefPointName="市中心">
                <DescriptiveText>乘坐地铁1号线南延线(中国药科大学方向)，在胜太路站下车，步行至胜太路站，换乘安铜线，在殷巷站下车，步行约240米。乘坐出租车约45元。</DescriptiveText>
              </RefPoint>
              <RefPoint Distance="18.65" UnitOfMeasureCode="2" Name="南京火车站" RefPointCategoryCode="2" RefPointName="火车站">
                <DescriptiveText>乘坐地铁1号线南延线(中国药科大学方向)，在胜太路站下车，步行至胜太路站，换乘安铜线，在殷巷站下车，步行约240米。乘坐出租车约58元。</DescriptiveText>
              </RefPoint>
            </RefPoints>
          </AreaInfo>
          <AffiliationInfo>
            <Awards>
              <Award Provider="HotelStarRate" Rating="5"/>
              <Award Provider="CtripStarRate" Rating="3"/>
              <Award Provider="CtripUserRate" Rating="4.5"/>
              <Award Provider="CtripCommRate" Rating="0"/>
              <Award Provider="CommSurroundingRate" Rating="0"/>
              <Award Provider="CommFacilityRate" Rating="0"/>
              <Award Provider="CommCleanRate" Rating="0"/>
              <Award Provider="CommServiceRate" Rating="0"/>
            </Awards>
          </AffiliationInfo>
          <MultimediaDescriptions>
            <MultimediaDescription>
              <ImageItems>
                <ImageItem Category="1">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/80E098FD-DE46-46F7-80B4-9FBC2F71490F_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="外观"/>
                </ImageItem>
                <ImageItem Category="6">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/AD3E3339-1818-4BD2-9DCE-0F073D7CCCD2_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="日式标准间"/>
                </ImageItem>
                <ImageItem Category="6">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/CF76D20B-7750-4297-A952-E599CC40CD8A_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="日式标准间"/>
                </ImageItem>
                <ImageItem Category="6">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/35ABEF01-D857-41BB-81B0-4DE76E616E89_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="标准间"/>
                </ImageItem>
                <ImageItem Category="6">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/FB29A9DC-CAF1-4614-9DBA-737A9DD549EA_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="豪华双床"/>
                </ImageItem>
                <ImageItem Category="1">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/C56BF773B253482987F97403684023B7_550_412.Jpg</URL>
                  </ImageFormat>
                  <Description Caption="花园"/>
                </ImageItem>
                <ImageItem Category="20">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/243B1A60232241EF9BC0384D5A8868DB_550_412.Jpg</URL>
                  </ImageFormat>
                  <Description Caption="服务"/>
                </ImageItem>
                <ImageItem Category="501">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/C418E3F1CBBA46718F70B0F333260435_550_412.Jpg</URL>
                  </ImageFormat>
                  <Description Caption="大堂"/>
                </ImageItem>
                <ImageItem Category="4">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/3B8985E5-9ACD-4396-B765-8091C85FECA3_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="香榭西餐厅"/>
                </ImageItem>
                <ImageItem Category="4">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/D206EB60-0B12-47C5-BA88-46D4B6040ECB_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="香榭西餐厅"/>
                </ImageItem>
                <ImageItem Category="1">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/1C7F371C-37EC-468F-A376-F5D4970A8BCB_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="长廊景色"/>
                </ImageItem>
                <ImageItem Category="2">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/20C58C62-D9BF-47D9-BB54-9D5EAFF37492_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="大堂灵璧石"/>
                </ImageItem>
                <ImageItem Category="2">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/F8752D74-3747-4916-A53E-81F23012F112_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="大厅"/>
                </ImageItem>
                <ImageItem Category="1">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/6FD70E62-48CB-4204-BFE4-52989BAE8BDB_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="湖滨金陵"/>
                </ImageItem>
                <ImageItem Category="1">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/1E526A22-AF8A-4C41-B736-2858AA8DD196_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="前庭广场"/>
                </ImageItem>
                <ImageItem Category="5">
                  <ImageFormat>
                    <URL>http://192.168.81.44:2227/upload/hotel/19000/18806/9846945B-7A31-4771-A531-E9F32758A12B_550_412.jpg</URL>
                  </ImageFormat>
                  <Description Caption="室内游泳池"/>
                </ImageItem>
              </ImageItems>
            </MultimediaDescription>
            <MultimediaDescription>
              <TextItems>
                <TextItem Category="5">
                  <Description>　　南京湖滨金陵饭店座落在风光秀丽的百家湖畔东北面的半岛上，三面环湖，与湖光山色相映成趣，融为一体，美仑美奂。分布于建筑物间的亲水休闲花园和庭园后部的垂钓休闲区更为饭店营造了一种人与自然和谐的气氛。地铁一号南延线于2010年中旬正式开通，胜太路站站距湖滨金陵饭店步行仅5分钟路程。</Description>
                </TextItem>
                <TextItem Category="11">
                  <Description>南京湖滨金陵饭店座落在风光秀丽的百家湖畔东北面的半岛上，三面环湖，与湖光山色相映成趣，融为一体，美仑美奂。分布于建筑物间的亲水休闲花园和庭园后部的垂钓休闲区更为饭店营造了一种人与自然和谐的气氛。</Description>
                </TextItem>
                <TextItem Category="501">
                  <URL>http://hotels.ctrip.com/hotel/18806.html</URL>
                </TextItem>
                <TextItem Category="8">
                  <Description>1.酒店环境和房间还算不错,早餐也很好&lt;br/>2.环境和服务都比较满意，客房用餐的响应速度较快，质量也不错</Description>
                </TextItem>
              </TextItems>
            </MultimediaDescription>
          </MultimediaDescriptions>
        </HotelDescriptiveContent>
      </HotelDescriptiveContents>
      <Success/>
    </OTA_HotelDescriptiveInfoRS>
  </HotelResponse>
</Response>

 */
}
