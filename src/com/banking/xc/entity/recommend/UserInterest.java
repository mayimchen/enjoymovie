package com.banking.xc.entity.recommend;

import com.banking.xc.recommend.TagUtil;

/**
 * 用户兴趣类，0-6级别统计用户对于某个方面兴趣
 */
public class UserInterest {
	/*
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
	allTagList.add(new Tag(36,"看日出"));*/
	
	public static int natureInterest(){
		int i = 0;
		if(UserTagUtil.containsTag("大自然")|UserTagUtil.containsTag("艺术欣赏")|
				UserTagUtil.containsTag("虎河")|UserTagUtil.containsTag("山青水秀")|
				UserTagUtil.containsTag("天高云淡")){
			i++;
		}
		return i;
	}
	
	public static int cultureInterest(){
		int i = 0;
		if(UserTagUtil.containsTag("佛家道教")|UserTagUtil.containsTag("登山")|
				UserTagUtil.containsTag("罗曼蒂克")|UserTagUtil.containsTag("文艺情节")|
				UserTagUtil.containsTag("文化遗产")|UserTagUtil.containsTag("少数民族特色")){
			i++;
		}
		return i;
	}
	
	public static int placeOfInterestInterest(){
		int i = 0;
		if(UserTagUtil.containsTag("经典旅游")|UserTagUtil.containsTag("名胜古迹")|
				UserTagUtil.containsTag("观光游")|UserTagUtil.containsTag("文化遗产")
				){
			i++;
		}
		return i;
	}
	
	public static int selfServiceInterest(){
		int i = 0;
		if(UserTagUtil.containsTag("自由行")|UserTagUtil.containsTag("散心")|
				UserTagUtil.containsTag("悠闲")|UserTagUtil.containsTag("看日出")
				){
			i++;
		}
		return i;
	}
	
	/*public static int leisureInterest(){
		int i = 0;
		if(UserTagUtil.containsTag("自由行")|UserTagUtil.containsTag("散心")|
				UserTagUtil.containsTag("悠闲")|UserTagUtil.containsTag("看日出")
				){
			i++;
		}
		return i;
	}
	
	public static int leisureInterest(){
		int i = 0;
		if(UserTagUtil.containsTag("自由行")|UserTagUtil.containsTag("散心")|
				UserTagUtil.containsTag("悠闲")|UserTagUtil.containsTag("看日出")
				){
			i++;
		}
		return i;
	}*/
}
