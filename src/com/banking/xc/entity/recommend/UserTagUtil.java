package com.banking.xc.entity.recommend;

import java.util.ArrayList;

import com.banking.xc.database.table.UserTagsTable;
import com.banking.xc.entity.HotelReservation;
import com.banking.xc.entity.User;
import com.banking.xc.utils.Log;

/**
 * @author zhangyinhang
 *
 */
public class UserTagUtil {
	private static ArrayList<Tag> ownTagList; //tag暂时是所有用户都具有的同一个，本地数据库保存
	private final static String TAG = "TagUtil";
	//但是下一次进入会从数据库中取，所以可以先不做这个5次统一
	private static ArrayList<HotelReservation> mHotelReservationList;
	
	private static boolean initialTagList(){
		
		ownTagList = null;
		ownTagList = new ArrayList<Tag>();
		ArrayList<String> tagStrings = UserTagsTable.getTags();
		if(Log.D){
			Log.d(TAG,"initialTagList()-->tagStrings.size"+tagStrings.size());
		}
		for(int i=0;i<tagStrings.size();i++){
			if(Log.D){
				Log.d(TAG,"initialTagList()-->tagString"+tagStrings.get(i));
			}
			ownTagList.add(new Tag(tagStrings.get(i)));
		}
		return false;
	}
	public static ArrayList<Tag> getNowUserTagList(){
		if(ownTagList!=null){
			return ownTagList;
		}else{
			initialTagList();
			return ownTagList;
		}
	}
	/*
	 * 尚无这个tag添加成功
	 * 否则添加失败
	 */
	public static boolean addTag(Tag tag){
		if(Log.D){
			Log.d(TAG,"addTag()-->");
			Log.d(TAG,"addTag()-->getNowUserTagList().size()"+getNowUserTagList().size());
		}
		if(getNowUserTagList().contains(tag)){
			return false;
		}
		getNowUserTagList().add(tag);
		if(Log.D){
			Log.d(TAG,"addTag()-->getNowUserTagList().add(tag)");
		}
		UserTagsTable.insertTag(tag.getTagString());
		if(Log.D){
			Log.d(TAG,"addTag()-->UserTagsTable.insertTag()");
			Log.d(TAG,"UserTagsTable.getTags()"+UserTagsTable.getTags().size());
		}
		return true;
	}
	public static boolean containsTag(Tag tag){
		//final String name = tag.getTagString();
		ArrayList<Tag> tags = getNowUserTagList();
		for(Tag theTag: tags){
			if(theTag.equals(tag)){
				return true;
			}
		}
		
		return false;
	}
	public static boolean containsTag(String name){
		//final String name = tag.getTagString();
		ArrayList<Tag> tags = getNowUserTagList();
		for(Tag theTag: tags){
			if(theTag.getTagString().equals(name)){
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 删除tag
	 * @param tag
	 * @return
	 */
	public static boolean deleteTag(Tag tag){
		UserTagsTable.delTag(tag.getTagString());
		return getNowUserTagList().remove(tag);
	}
	
	public static void initHotelOrders(){
		mHotelReservationList = null;
		mHotelReservationList = new ArrayList<HotelReservation>();
		//find from db
	}
	public static void finishAHotelOrder(HotelReservation hotelReservation){
		
		if(mHotelReservationList == null){
			initHotelOrders();
		}
		mHotelReservationList.add(hotelReservation);
		//save Todatabase
	}
	public static ArrayList<HotelReservation> getUserHotelOrders(){
		return mHotelReservationList;
	}
	public static void delHotelOrder(HotelReservation hotelReservation){
		
	}
	/*
	private boolean loveMeat;
	private int smoke;//1,
	private void analyse(){
		
	}*/
	
}
