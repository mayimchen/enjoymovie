package com.banking.xc.recommend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.text.TextUtils;

import com.banking.xc.entity.recommend.UserTagUtil;
import com.banking.xc.entity.recommend.Tag;
import com.banking.xc.utils.Log;

/**
 * 用户标签工具类
 * @author zhangyinhang
 *
 */
public class TagUtil {
	private final static String TAG = "TagUtil";
	static ArrayList<Tag> allTagList;
	//ArrayList<Tag> avaliableTagList;
	public static ArrayList<Tag> getAllTagList(){
		if(allTagList == null){
			productAllTag();
		}
		return allTagList;
	}
	
	@Deprecated
	public static ArrayList<Tag> getAllAvaliableTagList(){
		ArrayList<Tag> tagList = getAllTagList();
		for(int i=0;i<UserTagUtil.getNowUserTagList().size();i++){
			tagList.remove(UserTagUtil.getNowUserTagList().get(i));
		}
		return tagList;
	}
	
	/**
	 * 获取所有tag,但是标记selected属性
	 * @return
	 */
	public static ArrayList<Tag> newGetAllAvaliableTagList(){
		ArrayList<Tag> tagList = getAllTagList();
		for(Tag theTag : tagList){//一定记住情况上次的
			theTag.setSelected(false);
		}
		for(int i=0;i<UserTagUtil.getNowUserTagList().size();i++){
			//tagList.remove(CommonUser.getNowUserTagList().get(i));
			final Tag tag = UserTagUtil.getNowUserTagList().get(i);
			/*if(Log.D){
				Log.d(TAG,"newGetAllAvaliableTagList()"+tag.getTagString());
			}*/
			for(Tag tag2 : tagList){
				if(TextUtils.equals(tag2.getTagString(), tag.getTagString())){
					/*if(Log.D){
						Log.d(TAG,"newGetAllAvaliableTagList() equals"+tag2.getTagString()+" "+tag.getTagString());
					}*/
					tag2.setSelected(true);
				}else{
					//tag2.setSelected(false);
				}
			}
		}
		return tagList;
	}
	
	public static ArrayList<Tag> get13RandomTag(){
		ArrayList<Tag> avaliableTagList = newGetAllAvaliableTagList();//getAllAvaliableTagList();
		ArrayList<Tag> target13Tags = new ArrayList<Tag>();
		int i =0 ;
		final int allInt = avaliableTagList.size();
		final Random random = new Random();
		for(;i<13;){
			final int randomInt = random.nextInt(allInt-i);
			if(Log.D){
				Log.d(TAG,"randomInt"+randomInt);
			}
			final Tag selectedTag = avaliableTagList.get(randomInt);
			//if(!(target13Tags.contains(selectedTag))){
			if(!selectedTag.getSelected()){
				target13Tags.add(selectedTag);
				i++;
			}
			
		}
		//TO-DO
		return target13Tags;
	}
	public static void productAllTag(){
		allTagList = new ArrayList<Tag>();
		//旅游爱好
		allTagList.add(new Tag(1,"大自然"));
		allTagList.add(new Tag(2,"登山"));
		allTagList.add(new Tag(3,"湖河"));
		allTagList.add(new Tag(4,"沙漠戈壁"));
		allTagList.add(new Tag(5,"自由行"));
		allTagList.add(new Tag(6,"团队游"));	
		allTagList.add(new Tag(7,"少数民族特色"));
		allTagList.add(new Tag(8,"大草原"));
		allTagList.add(new Tag(9,"文化遗产"));
		allTagList.add(new Tag(10,"散心"));
		allTagList.add(new Tag(11,"名胜古迹"));
		allTagList.add(new Tag(12,"山清水秀"));
		allTagList.add(new Tag(13,"骑马"));
		allTagList.add(new Tag(14,"天高云淡"));
		allTagList.add(new Tag(15,"大海"));
		allTagList.add(new Tag(16,"海滩"));
		allTagList.add(new Tag(17,"悠闲"));
		allTagList.add(new Tag(18,"佛家道教"));
		allTagList.add(new Tag(19,"艺术欣赏"));
		allTagList.add(new Tag(20,"亲子游"));
		
		//生活习惯21-40
		allTagList.add(new Tag(21,"中产阶级"));
		allTagList.add(new Tag(22,"多金"));
		allTagList.add(new Tag(23,"小资情调"));
		allTagList.add(new Tag(24,"罗曼蒂克"));
		allTagList.add(new Tag(25,"经济出行"));
		allTagList.add(new Tag(26,"特色小吃"));
		allTagList.add(new Tag(27,"原始丛林"));
		allTagList.add(new Tag(28,"极限运动"));
		allTagList.add(new Tag(29,"特色游"));
		allTagList.add(new Tag(30,"观光游"));
		allTagList.add(new Tag(31,"文艺情节"));
		allTagList.add(new Tag(32,"放心游"));
		allTagList.add(new Tag(33,"动物气息"));
		allTagList.add(new Tag(34,"经典旅游"));
		allTagList.add(new Tag(35,"古街小巷"));
		allTagList.add(new Tag(36,"看日出"));
		
	}
}
