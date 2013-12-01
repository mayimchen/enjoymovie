package com.banking.xc.utils.xml.vacation;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.ImageItem;
import com.banking.xc.entity.ScenicSpot;
import com.banking.xc.entity.ScenicSpotDetailEntity;
import com.banking.xc.entity.TourDailyDinnerInfo;
import com.banking.xc.entity.TourDailyHotelInfo;
import com.banking.xc.entity.TourDailyInfo;
import com.banking.xc.entity.TourDailyScenicSportInfo;
import com.banking.xc.entity.TourTimeAssignInfo;
import com.banking.xc.entity.VacationBaseInfo;
import com.banking.xc.entity.VacationDescDetail;
import com.banking.xc.entity.VacationDescInfo;
import com.banking.xc.entity.VacationEntity;
import com.banking.xc.entity.VacationMinPricePlan;
import com.banking.xc.entity.VacationSegment;
import com.banking.xc.utils.Log;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

/**
 * 内容非常大：baseInfo加一些解析；
 * 完全是架构上失败了...如果开始就选择注解解析多好~现在只能先一条道走到黑了，顺便熟悉下接口数据...
 * 下次，真别这样了，这次真是ＴＭＤ活该
 * @author zhangyinhang
 *
 */
public class VacationPackageInfoXmlParseHandler extends XmlParseHandler{

	private final String TAG = "VacationPackageInfoXmlParseHandler";
	
	VacationEntity vacationEntity;
	VacationBaseInfo vacationBaseInfo;//由于可能是从首页或者推荐根据id过来的元素，所以需要解析baseInfo
	ImageItem imageItem;
	ArrayList<ImageItem> imageItems;
	VacationMinPricePlan vacationMinPricePlan; //低价格计划
	ArrayList<VacationMinPricePlan> vacationMinPricePlanList;//低价格计划
	ArrayList<VacationDescInfo> vacationDescInfoList;
	VacationDescInfo vacationDescInfo; //度假静态描述
	ArrayList<VacationDescDetail> vacationDescDetailList;
	VacationDescDetail vacationDescDetail;
	
	ArrayList<TourDailyInfo> tourDailyInfoList; //团队行程描述,注意这里很可能有乱序情况
	//TODO
	ArrayList<String> containSort; 
	TourDailyInfo tourDailyInfo;
	ArrayList<TourDailyDinnerInfo> tourDailyDinnerInfoList;
	TourDailyDinnerInfo tourDailyDinnerInfo;
	
	ArrayList<TourDailyScenicSportInfo> tourDailyScenicSportInfoList;
	TourDailyScenicSportInfo tourDailyScenicSportInfo;
	ScenicSpot scenicSpot;
	ArrayList<ImageItem> scenicSpotImages;
	ImageItem scenicSpotImageItem;
	ArrayList<ScenicSpotDetailEntity> scenicSpotDetailEntitys;
	ScenicSpotDetailEntity scenicSpotDetailEntity;
	
	ArrayList<TourDailyHotelInfo> tourDailyHotelInfoList;
	TourDailyHotelInfo tourDailyHotelInfo;
	ArrayList<String> hotelImageUrls;
	
	TourTimeAssignInfo tourTimeAssignInfo;
	//少一个DailyDescInfo每日行程  
	ArrayList<VacationSegment> segments;
	VacationSegment vacationSegment;
	
	String preTag;
	boolean basePriceMarkGot = false;
	//boolean priceMarkGot = false;
	//boolean inPriceMarkTag = false;//正面现在的data都是priceMark的
	public VacationPackageInfoXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		vacationEntity = null;
		
	}

	@Override
	public Object getObjectWhenEnd() {
		return vacationEntity;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		preTag = localName;
		if(TextUtils.equals(localName, "VacationInfoResponse")){
			vacationEntity = new VacationEntity();
			return;
		}
		if(TextUtils.equals(localName, "BaseInfo")){
			vacationBaseInfo = new VacationBaseInfo();
		}
		if(TextUtils.equals(localName, "ImageList")){
			imageItems = new ArrayList<ImageItem>();
			return;
		}
		if(TextUtils.equals(localName, "MediaInfo")){
			imageItem = new ImageItem();
			return;
		}
		//minPrice标签重复啊，作为子标签和父标签<MinPrice><VacationMinPrice>...
		
		if(TextUtils.equals(localName, "VacationMinPrice")){
			if(vacationMinPricePlanList ==null){
				vacationMinPricePlanList = new ArrayList<VacationMinPricePlan>();
			}
			vacationMinPricePlan = new VacationMinPricePlan();
			return;
		}
		if(TextUtils.equals(localName, "MinPriceRemark")){
			//inPriceMarkTag = true;
			return;
		}
		if(TextUtils.equals(localName, "DescInfo")){
			vacationDescInfoList = new ArrayList<VacationDescInfo>();
			return;
		}
		if(TextUtils.equals(localName, "VacationDescInfo")){
			vacationDescInfo = new VacationDescInfo();
			return;
		}
		if(TextUtils.equals(localName, "Detail")){
			if(vacationDescDetailList==null){
				vacationDescDetailList = new ArrayList<VacationDescDetail>();
				return;
			}else{
				scenicSpotDetailEntitys = new ArrayList<ScenicSpotDetailEntity>();
				return;
			}
		}/*if(TextUtils.equals(localName, "Detail")){
			
		}*/
		if(TextUtils.equals(localName, "VacationDescDetail")){
			vacationDescDetail = new VacationDescDetail();
			return;
		}
		//new add
		if(TextUtils.equals(localName, "TourDailyDescInfo")){
			tourDailyInfoList = new ArrayList<TourDailyInfo>();
			containSort = new ArrayList<String>();
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyDesc")){
			tourDailyInfo = new TourDailyInfo();
			return;
		}
		if(TextUtils.equals(localName, "TourDailyDinnerDetailInfo")){
			tourDailyDinnerInfoList = new ArrayList<TourDailyDinnerInfo>();
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyDinnerDetailInfo")){
			tourDailyDinnerInfo = new TourDailyDinnerInfo();
			return;
		}
		if(TextUtils.equals(localName, "TourDailyHotelInfo")){
			tourDailyHotelInfoList = new ArrayList<TourDailyHotelInfo>();
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyHotelInfo")){
			tourDailyHotelInfo = new TourDailyHotelInfo();
			return;
		}
		if(TextUtils.equals(localName, "HotelImgUrls")){
			hotelImageUrls = new ArrayList<String>();
			return;
		}
		if(TextUtils.equals(localName, "TourDailyScenicSpotInfo")){
			tourDailyScenicSportInfoList = new ArrayList<TourDailyScenicSportInfo>();
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyHotelInfo")){
			tourDailyScenicSportInfo = new TourDailyScenicSportInfo();
			return;
		}
		if(TextUtils.equals(localName, "ScenicSpot")){
			scenicSpot = new ScenicSpot();
			return;
		}
		/*if(TextUtils.equals(localName, "Detail")){
			scenicSpotDetailEntitys = new ArrayList<ScenicSpotDetailEntity>();
			return;
		}*/
		if(TextUtils.equals(localName, "CMS_ScenicSpotDetailEntity")){
			scenicSpotDetailEntity = new ScenicSpotDetailEntity();
			return;
		}
		if(TextUtils.equals(localName, "Images")){
			scenicSpotImages = new ArrayList<ImageItem>();
			return;
		}
		if(TextUtils.equals(localName, "CMS_ScenicSpotImgEntity")){
			scenicSpotImageItem = new ImageItem();
			return;
		}
		if(TextUtils.equals(localName, "TourTimeAssignInfo")){
			tourTimeAssignInfo = new TourTimeAssignInfo();
			return;
		}
		
		if(TextUtils.equals(localName, "Segment")){
			if(segments==null){
				segments = new ArrayList<VacationSegment>();
				System.out.println("Segment begin");
				return;
			}
		}
		if(TextUtils.equals(localName, "VacationSegmentInfo")){
			vacationSegment = new VacationSegment();
			return;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "BaseInfo")){
			vacationEntity.setVacationBaseInfo(vacationBaseInfo);
			vacationBaseInfo = null;
		}
		if(TextUtils.equals(localName, "ImageList")){
			vacationEntity.setImageItems(imageItems);
			imageItems = null;
			return;
		}
		if(TextUtils.equals(localName, "MediaInfo")){
			imageItems.add(imageItem);
			imageItem = null;
			return;
		}
		if(TextUtils.equals(localName, "MinPrice")){
			if(vacationMinPricePlanList !=null&&vacationMinPricePlan==null)
			{
				vacationEntity.setVacationMinPricePlans(vacationMinPricePlanList);
				vacationMinPricePlanList = null;
			}
			return;
		}
		if(TextUtils.equals(localName, "VacationMinPrice")){
			vacationMinPricePlanList.add(vacationMinPricePlan);
			vacationMinPricePlan = null;
			return;
		}
		if(TextUtils.equals(localName, "MinPriceRemark")){
			return;
		}
		if(TextUtils.equals(localName, "DescInfo")){
			vacationEntity.setVacationDescInfoList(vacationDescInfoList);
			vacationDescInfoList = null;
			return;
		}
		if(TextUtils.equals(localName, "VacationDescInfo")){
			vacationDescInfoList.add(vacationDescInfo);
			vacationDescInfo = null;
			return;
		}
		if(TextUtils.equals(localName, "Detail")){
			if(scenicSpot!=null){
				scenicSpot.setScenicSpotDetailEntitys(scenicSpotDetailEntitys);
				scenicSpotDetailEntitys = null;
				return;
			}
			if(vacationDescDetail==null&&vacationDescInfo!=null){//这个判断是可行的，重复detail的TAG问题
				vacationDescInfo.setVacationDescDetailList(vacationDescDetailList);
				vacationDescDetailList = null;
				return;
			}
			
		}
		if(TextUtils.equals(localName, "VacationDescDetail")){
			vacationDescDetailList.add(vacationDescDetail);
			vacationDescDetail = null;
		}
		//new add
		if(TextUtils.equals(localName, "TourDailyDescInfo")){
			vacationEntity.setTourDailyInfoList(tourDailyInfoList);
			tourDailyInfoList = null;
			containSort = null;
		}
		if(TextUtils.equals(localName, "VacationTourDailyDesc")){
			//if(tourDailyInfoList.get)
			//if(tourDailyInfo)
			final String sort = tourDailyInfo.getSort();
			if(containSort.contains(sort)){
				
			}else{
				tourDailyInfoList.add(tourDailyInfo);
				containSort.add(sort);
				tourDailyInfo = null;
			}
			
		}
		if(TextUtils.equals(localName, "TourDailyDinnerDetailInfo")){
			tourDailyInfo.setTourDailyDinnerInfoList(tourDailyDinnerInfoList);
			tourDailyDinnerInfoList = null;
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyDinnerDetailInfo")){
			tourDailyDinnerInfoList.add(tourDailyDinnerInfo); 
			tourDailyDinnerInfo = null;
			return;
		}
		if(TextUtils.equals(localName, "TourDailyHotelInfo")){
			tourDailyInfo.setTourDailyHotelInfoList(tourDailyHotelInfoList); 
			tourDailyHotelInfoList = null;
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyHotelInfo")){
			tourDailyHotelInfoList.add(tourDailyHotelInfo);
			tourDailyHotelInfo = null;
			return;
		}
		if(TextUtils.equals(localName, "HotelImgUrls")){
			tourDailyHotelInfo.setHotelImageUrls(hotelImageUrls);
			hotelImageUrls = null;
			return;
		}
		if(TextUtils.equals(localName, "TourDailyScenicSpotInfo")){
			tourDailyInfo.setTourDailyScenicSportInfoList(tourDailyScenicSportInfoList);
			tourDailyScenicSportInfoList = null;
			return;
		}
		if(TextUtils.equals(localName, "VacationTourDailyHotelInfo")){
			tourDailyScenicSportInfoList.add(tourDailyScenicSportInfo); 
			tourDailyScenicSportInfo = null;
			return;
		}
		if(TextUtils.equals(localName, "ScenicSpot")){
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setScenicSpot(scenicSpot); 
				scenicSpot = null;
				return;
			}
		}
//		if(TextUtils.equals(localName, "Detail")){
//			scenicSpot.setScenicSpotDetailEntitys(scenicSpotDetailEntitys);
//			scenicSpotDetailEntitys = null;
//			return;
//		}
		if(TextUtils.equals(localName, "CMS_ScenicSpotDetailEntity")){
			scenicSpotDetailEntitys.add(scenicSpotDetailEntity);
			scenicSpotDetailEntity = null;
			return;
		}
		if(TextUtils.equals(localName, "Images")){
			scenicSpot.setScenicSpotImages(scenicSpotImages);
			scenicSpotImages = null;
			return;
		}
		if(TextUtils.equals(localName, "CMS_ScenicSpotImgEntity")){
			scenicSpotImages.add(scenicSpotImageItem);
			scenicSpotImageItem = null;
			return;
		}
		if(TextUtils.equals(localName, "TourTimeAssignInfo")){
			vacationEntity.setTourTimeAssignInfo(tourTimeAssignInfo);
			tourTimeAssignInfo = null;
			return;
		}
		if(TextUtils.equals(localName, "Segment")){
			if(vacationSegment==null){
				vacationEntity.setSegments(segments);
				segments = null;
				System.out.println("Segment end");
				return;
			}
		}
		if(TextUtils.equals(localName, "VacationSegmentInfo")){
				segments.add(vacationSegment);
				vacationSegment = null;
				return;
		}
	}
		

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		String data = new String(ch,start,length);
		if(TextUtils.equals(preTag, "MinPriceRemark")){
			if(Log.D){
				Log.d(TAG, "minPriceRemark"+data);
			}
			if(vacationMinPricePlan==null){
				vacationEntity.addMinPriceMark(data);//setMinPriceMark(data);
			}else{
				vacationMinPricePlan.addMinPriceRemark(data);
			}
		}
		
		//VacationBaseInfo开始
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
			if(Log.D){
				Log.d(TAG, "Attraction"+data);
			}
			vacationBaseInfo.addAttraction(data);
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
		
		if(TextUtils.equals("PkgURL", preTag)){
			vacationBaseInfo.setPkgURL(data);
			return;
		}
		if(TextUtils.equals("CharacteristicDesc", preTag)){
			if(Log.D){
				Log.d(TAG,"CharacteristicDesc"+data);
			}
			vacationBaseInfo.setCharacteristicDesc(data);
			return;
		}
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
		/*if(TextUtils.equals("Star", preTag)){
			vacationBaseInfo.setStar(data);
			return;
		}*/
		//VacationbaseInfo结束
		
		
		if(TextUtils.equals(preTag, "ShortDesc")){
			vacationEntity.addShortDesc(data);
			if(Log.D){
				Log.d(TAG,"ShortDesc"+data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "PaymentType")){
			vacationEntity.setPaymentType(data);
			return;
		}
		if(TextUtils.equals(preTag, "SegmentDetail")){
			if(Log.D){
				Log.d(TAG, "SegmentDetail"+data);
			}
			tourDailyInfo.appendSegmentDetail(data);
			return;
		}
		if(TextUtils.equals(preTag, "PicPath")){
			tourDailyInfo.setPicPath(data);
			return;
		}
		
		/**
		 * <EffectDate>2012-02-15T00:00:00</EffectDate><ExpireDate>2013-06-30T00:00:00</ExpireDate><AdvanceDays>5</AdvanceDays><AdvanceOrderDay>0</AdvanceOrderDay><AdvanceOrderTime>1200</AdvanceOrderTime><AdvancePayDays>0</AdvancePayDays><AdvanceWorks>0</AdvanceWorks><Attrib1>0</Attrib1><Bail>0</Bail><CharacteristicDesc>提前预订 享更多优惠</CharacteristicDesc><SeasonNotice /><PkgCharacteristic /><PriceAdjust>55.0000</PriceAdjust><ChdPriceAdjust>55.0000</ChdPriceAdjust><FirstReserveDate>0001-01-01T00:00:00</FirstReserveDate><LastReserveDate>0001-01-01T00:00:00</LastReserveDate><Holidayworking>T</Holidayworking><IsDiamordMember>F</IsDiamordMember><IsGroupBuy>F</IsGroupBuy><IsPopularity>F</IsPopularity><ListPrice>2146.0000</ListPrice><MaxDays>5</MaxDays><MinDays>5</MinDays><MinPersons>1</MinPersons><OptionTypeID />
		 */
		if(TextUtils.equals(preTag, "ImgURL")){
			imageItem.setImageUrl(data);
			return;
		}
		if(TextUtils.equals(preTag, "ImgDesc")){
			imageItem.setDescriptCaption(data);
			return;
		}
		
		
		//以下是PriceInfo
		if(TextUtils.equals(preTag, "TakeOffDate")){
			if(vacationMinPricePlan!=null){
				vacationMinPricePlan.setTakeOffDate(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "MinPrice")){
			if(vacationMinPricePlan!=null){
				vacationMinPricePlan.setMinPrice(data);
			}else{
				vacationEntity.setMinPrice(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "CalcDateTime")){
			if(vacationMinPricePlan!=null){
				vacationMinPricePlan.setCalcDateTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Desc")){
			if(vacationDescInfo!=null){
				vacationDescInfo.setDesc(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DeadlineDate")){
			if(vacationDescDetail!=null){
				vacationDescDetail.setDeadlineDate(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Detail")){
			if(Log.D){
				Log.d(TAG,"vacationDescDetail-->"+data);
			}
			if(vacationDescDetail!=null){
				//vacationDescDetail.setDetail(data);appendDetail
				vacationDescDetail.appendDetail(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Style")){
			if(vacationDescDetail!=null){
				vacationDescDetail.setStyle(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Segmentdesc")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setSegmentDesc(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Sort")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setSort(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "TakeOffTime")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setTakeOffTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "TakeTime")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setTakeTime(data);
			}
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setTakeTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DailyType")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setDailyType(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Description")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setDescription(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Destination")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setDestination(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Distance")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setDistance(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DriveTime")){
			if(tourDailyInfo!=null){
				tourDailyInfo.setDriveTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "BreakFastShift")){
			if(tourDailyDinnerInfo!=null){
				tourDailyDinnerInfo.setBreakFastShift(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "CareDinnerTimes")){
			if(tourDailyDinnerInfo!=null){
				tourDailyDinnerInfo.setCareDinnerTimes(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "LunchShift")){
			if(tourDailyDinnerInfo!=null){
				tourDailyDinnerInfo.setLunchShift(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "NoCareDinnerTimes")){
			if(tourDailyDinnerInfo!=null){
				tourDailyDinnerInfo.setNoCareDinnerTimes(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "NoConfirmDinnerTimes")){
			if(tourDailyDinnerInfo!=null){
				tourDailyDinnerInfo.setNoConfirmDinnerTimes(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "SupperShift")){
			if(tourDailyDinnerInfo!=null){
				tourDailyDinnerInfo.setSupperShift(data);
			}
			return;
		}
		
		if(TextUtils.equals(preTag, "HotelAddress")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setHotelAddress(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "HotelId")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setHotelId(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "HotelName")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setHotelName(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "string")){
			if(hotelImageUrls!=null){
				hotelImageUrls.add(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "HotelStar")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setHotelStar(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "SinceTime")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setSinceTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Surroundings")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setSurroundings(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "UserComment")){
			if(tourDailyHotelInfo!=null){
				tourDailyHotelInfo.setUserComment(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "CustomSynopsis")){
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setCustomSynopsis(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DisplayOrder")){
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setDisplayOrder(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "OrFlag")){
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setOrFlag(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "SuffixName")){
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setSuffixName(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "PreName")){
			if(tourDailyScenicSportInfo!=null){
				tourDailyScenicSportInfo.setPreName(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Category")){
			if(scenicSpot!=null){
				scenicSpot.setCategory(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Feature")){
			if(scenicSpot!=null){
				scenicSpot.setFeature(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Intro")){
			if(scenicSpot!=null){
				scenicSpot.setIntro(data);
			}
			if(scenicSpotDetailEntity!=null){
				scenicSpotDetailEntity.setIntro(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "LinkName")){
			if(scenicSpot!=null){
				scenicSpot.setLinkName(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "LinkUrl")){
			if(scenicSpot!=null){
				scenicSpot.setLinkUrl(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "RecommendOrder")){
			if(scenicSpot!=null){
				scenicSpot.setRecommendOrder(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Star")){
			if(scenicSpot!=null){
				scenicSpot.setStar(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Synopsis")){
			if(scenicSpot!=null){
				scenicSpot.setSynopsis(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Title")){
			if(scenicSpotDetailEntity!=null){
				scenicSpotDetailEntity.setTitle(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "FilePath")){
			if(scenicSpotImageItem!=null){
				scenicSpotImageItem.setImageUrl(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Name")){
			if(scenicSpotImageItem!=null){
				scenicSpotImageItem.setDescriptCaption(data); //setName
			}
			return;
		}
		if(TextUtils.equals(preTag, "AccommodationRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setAccommodationRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "AccommodationTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setAccommodationTimeLen(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "CareDinnerTimes")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setCareDinnerTimes(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DinnerRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setDinnerRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DriveRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setDriveRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "EatingRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setEatingRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "EatingTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setEatingTimeLength(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "FltRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setFltRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "FltTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setFltTimeLength(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "FreeRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setFreeRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "FreeTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setFreeTimeLength(data);
			}
			return;
		}
		
		if(TextUtils.equals(preTag, "FreeTimes")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setFreeTimes(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "NoCareDinnerTimes")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setNoCareDinnerTimes(data);
			}
			return;
		}
		
		if(TextUtils.equals(preTag, "OtherRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setOtherRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "OtherTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setOtherTimeLength(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "RestActivitiesRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setRestActivitiesRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "RestActivitiesTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setRestActivitiesTimeLength(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "ShopRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setShopRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "ShopTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setShopTimeLength(data);
			}
			return;
		}
		
		if(TextUtils.equals(preTag, "ShopTimes")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setShopTimes(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "TravelRemark")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setTravelRemark(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "TravelTimeLen")){
			if(tourTimeAssignInfo!=null){
				tourTimeAssignInfo.setTravelTimeLen(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "CheckoutAdjust")){
			if(vacationSegment!=null){
				vacationSegment.setCheckOutAdjust(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DepartureAdjust")){
			if(vacationSegment!=null){
				vacationSegment.setDepartureAdjust(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DepartureDays")){
			if(vacationSegment!=null){
				vacationSegment.setDepartureDays(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DestCity")){
			if(vacationSegment!=null){
				vacationSegment.setDestCity(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DestCityType")){
			if(vacationSegment!=null){
				vacationSegment.setDestCityType(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "DestDistrictName")){
			if(vacationSegment!=null){
				vacationSegment.setDestDistrictName(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "FlightTag")){
			if(vacationSegment!=null){
				vacationSegment.setFlightTag(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "IncludingSysHotel")){
			if(vacationSegment!=null){
				vacationSegment.setIncludingSysHotel(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "IncludingSysFlight")){
			if(vacationSegment!=null){
				vacationSegment.setIncludingSysFlight(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "IncludingSysHotel")){
			if(vacationSegment!=null){
				vacationSegment.setIncludingSysHotel(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "MaxStayDays")){
			if(vacationSegment!=null){
				vacationSegment.setMaxStayDays(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "MinLodgingDays")){
			if(vacationSegment!=null){
				vacationSegment.setMinLdgingDays(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "PkgAirfareEarliesTime")){
			if(vacationSegment!=null){
				vacationSegment.setPkgAirfareEarliesTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "PkgAirfareLatestTime")){
			if(vacationSegment!=null){
				vacationSegment.setPkgAirfareLatestTime(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "Segment")){
			if(vacationSegment!=null){
				vacationSegment.setSegment(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "IsGruop")){
			if(vacationSegment!=null){
				vacationSegment.setSiGruop(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "StartCity")){
			if(vacationSegment!=null){
				vacationSegment.setStartCity(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "StartCityType")){
			if(vacationSegment!=null){
				vacationSegment.setStartCityType(data);
			}
			return;
		}
		if(TextUtils.equals(preTag, "StartDistrictName")){
			if(vacationSegment!=null){
				vacationSegment.setStartDistrictName(data);
			}
			return;
		}
		
	}
	
	/**
	 * VacationInfoResponse:
	 * BaseInfo   Ok
		PriceInfo产品价格信息 OK
		DescInfo产品描述
		TimingSales产品限制售卖信息 *OK
		TourAddInfo团队产品附加信息
		DailyDescInfo每日行程  *
		Segment行程
	 */
}
