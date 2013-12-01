package com.banking.xc.entity.recommend;

import java.util.ArrayList;

import android.text.TextUtils;

import com.banking.xc.utils.CommonUtil;

/**
 * 唯一单例对应VacationFilterActivity
 * @author zhangyinhang
 *
 */
public class VacationFilter {
	
	
	//VacationFilter相关
	public final static String VACATION_KIND = "vacationKind"; 
	public final static String VACATION_INTERE = "vacationInst";
	public final static String VACATION_MAN = "man";
	public final static String VACATION_WOMAN = "woman";
	public final static String VACATION_COUPLE = "couple";
	//少child
	public final static String[] VACATION_KINDTAGS = {"全部", "单人散心游", "情侣浪漫游", "家庭温馨游", "同学消遣游", "同事团建游"};
	public final static String[] VACATION_INTERESTS = { "自然风光", "山青水秀", "拥抱大海", "大漠草原", "文化遗产", "名胜古迹", "小桥流水", "民俗风情" };
	//时间，出发目的地先不存
	
	
/*	public final static String NATURE_TAG = "自然风光";
	public final static String TAG1 = "大漠风采";
	public final static String TAg2 = "拥抱大海";
	*/
	
	private static VacationPeople vacationPeople;
	private static int kindTag = -1;
	private static String interestString;
	//private static ArrayList<Integer> interestTags;
	
	
	
	private int destCityTag = -1;
	private String destCityId = "1";
	private String startDate;
	private String searchValue;
	private String departCityId;
	
	/**
	 * 全都存储在Prefrence里面
	 * @return
	 */
	public static VacationPeople getVacationPeople() {
		if(vacationPeople == null){
			vacationPeople = new VacationPeople();
			vacationPeople.setManCount(CommonUtil.getVacationMan());
			vacationPeople.setWomanCount(CommonUtil.getVacationWoman());
			vacationPeople.setCoupleCount(CommonUtil.getVacationCouple());
		}
		return vacationPeople;
	}
	
	/**
	 * 当选择完成时调用
	 * @param vacationPeople
	 */
	public static void saveVacationPeople(VacationPeople vacationPeopleTemp) {
		vacationPeople = vacationPeopleTemp;
		CommonUtil.setVacationMan(vacationPeople.getManCount());
		CommonUtil.setVacationWoman(vacationPeople.getWomanCount());
		CommonUtil.setVacationCouple(vacationPeople.getCoupleCount());
	}
	
	public static int getKindTag() {
		if(kindTag == -1){
			kindTag = CommonUtil.getVacationKindTag();
		}
		return kindTag;
	}
	public static void saveKindTag(int kindTagTemp) {
		kindTag = kindTagTemp;
		CommonUtil.setVacationKindTag(kindTag);
	}
	
	
	
	public static ArrayList<Integer> getInterestTags() {
		if(interestString==null){
			interestString = CommonUtil.getVacationInterest();
		}
		ArrayList<Integer> interestTags = new ArrayList<Integer>();
		if(TextUtils.isEmpty(interestString)){
			return interestTags;
		}
		String[] sArray = interestString.split("-");
		for(int i=0;i<sArray.length;i++){
			interestTags.add(Integer.parseInt(sArray[i]));
		}
		return interestTags;
	}
	public static void saveInterestTags(ArrayList<Integer> interestTags) {
		String intereSt = "";
		for(int i=0;i<interestTags.size();i++){
			if(i!=0){
				intereSt += "-";
			}
			intereSt += interestTags.get(i);
		}
		interestString = intereSt;
		CommonUtil.setVacationInterest(interestString);
	}
	
	
	
	/*public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getDestCityTag() {
		return destCityTag;
	}
	public void setDestCityTag(int destCityTag) {
		this.destCityTag = destCityTag;
	}
	public String getDestCityId() {
		return destCityId;
	}
	public void setDestCityId(String destCityId) {
		this.destCityId = destCityId;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getDepartCityId() {
		return departCityId;
	}
	public void setDepartCityId(String departCityId) {
		this.departCityId = departCityId;
	}*/
	
	
}
