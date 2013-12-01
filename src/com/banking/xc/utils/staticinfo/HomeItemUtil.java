package com.banking.xc.utils.staticinfo;

import java.util.ArrayList;
import java.util.Random;



import skytv_com.banking.enjoymovie.R;

import com.banking.xc.entity.HomeCategory;
import com.banking.xc.entity.HomeItem;
import com.banking.xc.utils.Log;

public class HomeItemUtil {
	
	private ArrayList<HomeItem> allItems;
	private ArrayList<HomeItem> natureItems;//自然风光
	private ArrayList<HomeItem> placesOfInterestItems;//名胜古迹
	private ArrayList<HomeItem> cultureItems;//文化
	private ArrayList<HomeItem> selfServiceItems;//自助游
	private ArrayList<HomeItem> leisureItems;//悠闲游
	private ArrayList<HomeItem> luxuryItems;//豪华现代游
	private ArrayList<HomeItem> recommendItems;//推荐游
	private ArrayList<HomeCategory> homeCategorys;
	
	public void initial(){
		
		productHomeCategorys();
		
		productNatureItems();
		productPlacesOfInterestItems();
		productSelfServiceItems();
		productCultureItems();
		productLuxuryItems();
		productLeisureItems();
		productAllItems();
		productRecommendItems();
		
		
	}
	
	public void productHomeCategorys()
	{
		homeCategorys = new ArrayList<HomeCategory>();
		homeCategorys.add(new HomeCategory("全部精品"));
		homeCategorys.add(new HomeCategory("为我推荐"));
		homeCategorys.add(new HomeCategory("自然风光"));
		homeCategorys.add(new HomeCategory("名胜古迹"));
		homeCategorys.add(new HomeCategory("文化之旅"));
		homeCategorys.add(new HomeCategory("自助游玩"));
		homeCategorys.add(new HomeCategory("悠闲时光"));
		homeCategorys.add(new HomeCategory("奢侈享受"));
	}
	/**
	 * 为旅游推荐提供9个数据
	 */
	public ArrayList<HomeItem> get9Items(){
		ArrayList  itemList = new ArrayList<HomeItem>();
		itemList.addAll(natureItems);
		itemList.addAll(placesOfInterestItems);
		//itemList.addAll(luxuryItems);
		return itemList;
	}
	public void productNatureItems(){
		natureItems = new ArrayList<HomeItem>();
		
		final HomeItem homeItem2 = new HomeItem();
		homeItem2.setContent("在三亚如果你有伴儿，三亚将满足你对一场饱含山盟海誓恋爱的全部幻想；如果你是一个人，可以在潜水圣地蜈支洲岛零距离观赏珊瑚礁、可以去热带雨林探奇、也可以在“亚洲第一大道”椰梦长廊静候日落……在三亚，你就是一个被大自然宠坏的孩子");
		homeItem2.setTitle("春暖花开，去三亚呼吸新鲜空气");
		homeItem2.setDrawableResourceId(R.drawable.title_nature2);
		homeItem2.setPkgId("94345");
		final ArrayList<String> sArray2 = new ArrayList<String>();
		sArray2.add("新鲜空气");
		sArray2.add("山水");
		sArray2.add("美景");
		homeItem2.setTags(sArray2);
		natureItems.add(homeItem2);
		
		
		final HomeItem homeItem3 = new HomeItem();
		homeItem3.setContent("到九寨沟寻找最清澈的水源。");
		homeItem3.setTitle("花海九寨，春意盎然");
		homeItem3.setDrawableResourceId(R.drawable.title_nature3);
		homeItem3.setPkgId("94870");
		final ArrayList<String> sArray3 = new ArrayList<String>();
		sArray3.add("山清水秀");
		sArray3.add("大自然");
		homeItem3.setTags(sArray3);
		natureItems.add(homeItem3);
		
		
		final HomeItem homeItem4 = new HomeItem();
		homeItem4.setContent("房间见湖率达180度，五光十色美景，一览无余。 湖区内拥有各种名目淡水鱼87种，真正的湖鲜美食");
		homeItem4.setTitle("尽享大美千岛湖");
		homeItem4.setDrawableResourceId(R.drawable.title_nature4);
		homeItem4.setPkgId("1609300");
		final ArrayList<String> sArray4 = new ArrayList<String>();
		sArray4.add("亲朋游");
		sArray4.add("悠闲");
		homeItem4.setTags(sArray4);
		natureItems.add(homeItem4);
		
		
		final HomeItem homeItem5 = new HomeItem();
		homeItem5.setContent("香格里拉县原名中甸县，因与著名小说《消失的地平线》中描述的世外桃源相似而易名为香格里拉。在当地藏语中，香格里拉意为：心中的日月。");
		homeItem5.setTitle("心中的日月-香格里拉");
		homeItem5.setDrawableResourceId(R.drawable.title_nature5);
		homeItem5.setPkgId("78622");
		final ArrayList<String> sArray5 = new ArrayList<String>();
		sArray5.add("山水");
		sArray5.add("古镇");
		sArray5.add("精品");
		homeItem4.setTags(sArray5);
		natureItems.add(homeItem5);
		
		
		final HomeItem homeItem1 = new HomeItem();
		homeItem1.setContent("畅游杭州西湖，品读苏州园林，品味南京古城，风光旖旎之行。上有天堂，下有苏杭，西湖游船，闲散时光，人间天堂享悠然假期。“江南佳丽地，金陵帝王洲”，感受中国历史的厚重。");
		homeItem1.setTitle("游人间天堂，赏古都风光");
		homeItem1.setDrawableResourceId(R.drawable.title_nature1);
		homeItem1.setPkgId("94033");
		final ArrayList<String> sArray1 = new ArrayList<String>();
		sArray1.add("精品");
		sArray1.add("大自然");
		homeItem1.setTags(sArray1);
		natureItems.add(homeItem1);
	}
	public void productPlacesOfInterestItems(){
		placesOfInterestItems = new ArrayList<HomeItem>();
		final HomeItem homeItem1 = new HomeItem();
		homeItem1.setContent("兵马俑、华清池、法门寺、乾陵、汉阳陵、大雁塔广场……西安经典景点全囊括，全方位感受古都西安的深厚文化");
		homeItem1.setTitle("感受兵马俑的磅礴气势");
		homeItem1.setPkgId("80354");
		homeItem1.setDrawableResourceId(R.drawable.title_interest1);
		final ArrayList<String> sArray1 = new ArrayList<String>();
		sArray1.add("文化");
		sArray1.add("古典");
		homeItem1.setTags(sArray1);
		placesOfInterestItems.add(homeItem1);
		
		
		final HomeItem homeItem2 = new HomeItem();
		homeItem2.setContent("登十八盘，望山涧美景，赏封禅大典，看泰山日出，祈国泰民安");
		homeItem2.setTitle("泰山-五岳之尊");
		homeItem2.setDrawableResourceId(R.drawable.title_interest2);
		homeItem2.setPkgId("1618352");
		final ArrayList<String> sArray2 = new ArrayList<String>();
		sArray2.add("爬山");
		sArray2.add("名胜");
		homeItem2.setTags(sArray2);
		placesOfInterestItems.add(homeItem2);
		
		
		final HomeItem homeItem3 = new HomeItem();
		homeItem3.setContent("花最少的钱看最美的景、游最经典的黄山，性价比最高");
		homeItem3.setTitle("峰岩青黑，遥望苍黛-黄山");
		homeItem3.setPkgId("1616377");//1616377
		homeItem3.setDrawableResourceId(R.drawable.title_interest3);
		final ArrayList<String> sArray3 = new ArrayList<String>();
		sArray3.add("爬山");
		sArray3.add("山水");
		sArray3.add("名胜");
		homeItem3.setTags(sArray3);
		placesOfInterestItems.add(homeItem3);
		
		
		
		final HomeItem homeItem4 = new HomeItem();
		homeItem4.setContent("西宁火车入藏，感受青藏铁路最精华的一段，欣赏“天路”壮美神秘的风光。 林芝桃花美景冠绝西藏！不贪奢侈，不恋浮华，爱西藏，爱桃花！ 布达拉宫、大昭寺，纤尘不染闻梵唱。 沉睡了一整个冬季的纳木错等待你的到来。西藏小江南林芝，巴松措等绝美景色任你探访");
		homeItem4.setTitle("天湖纳木错，静候你的到来");
		homeItem4.setPkgId("1606467");
		homeItem4.setDrawableResourceId(R.drawable.title_interest4);
		final ArrayList<String> sArray4 = new ArrayList<String>();
		sArray4.add("名胜古迹");
		sArray4.add("神秘");
		sArray4.add("超凡脱俗");
		homeItem3.setTags(sArray4);
		placesOfInterestItems.add(homeItem4);
		
		
	}
	public void productCultureItems(){
		cultureItems = new ArrayList<HomeItem>();
		final HomeItem homeItem1 = new HomeItem();
		homeItem1.setContent("祈福平安，瞻仰目前世界上最高的释迦牟尼佛青铜立像拜大佛！体验佛教文化，享受心灵之旅！观大型音乐喷泉动态群雕—九龙灌浴，游梵宫，食素斋，祈福吉祥,体验佛教文化。");
		homeItem1.setTitle("去灵山，拜大佛，抱佛脚");
		homeItem1.setPkgId("1618865");
		homeItem1.setDrawableResourceId(R.drawable.title_culture1);
		final ArrayList<String> sArray1 = new ArrayList<String>();
		sArray1.add("佛教");
		sArray1.add("文化之旅");
		homeItem1.setTags(sArray1);
		cultureItems.add(homeItem1);
		
		final HomeItem homeItem2 = new HomeItem();
		homeItem2.setContent("有空闲，更应该去看看《丝路花雨》和《大梦敦煌》，尤其是前者，它取材于敦煌壁画，熔合了中国民族舞、敦煌舞、印度舞、黑巾舞、波斯马铃舞、波斯酒舞、土耳其舞、盘上舞、新疆舞等各种舞蹈形式于一身，被誉为中国古典舞的典范。其主角英娘的表演者已至五代，常演常新，堪称艺术的奇迹。");
		homeItem2.setTitle("去丝绸之路走走");
		homeItem2.setPkgId("1603463");
		homeItem2.setDrawableResourceId(R.drawable.title_culture2);
		final ArrayList<String> sArray2 = new ArrayList<String>();
		sArray2.add("广度");
		sArray2.add("文化之旅");
		homeItem2.setTags(sArray2);
		cultureItems.add(homeItem2);
	}
	public void productSelfServiceItems(){
		selfServiceItems = new ArrayList<HomeItem>();
		final HomeItem homeItem1 = new HomeItem();
		homeItem1.setContent("全城瞩目，环游世界的大黄鸭将于5月2号至6月9号游来香港维多利亚港！这个可爱的充气鸭子，在香港的维多利亚港展开为期一个月的深度旅行。");
		homeItem1.setTitle("去香港看大黄鸭!");
		homeItem1.setPkgId("50551");
		homeItem1.setDrawableResourceId(R.drawable.title_self1);
		final ArrayList<String> sArray1 = new ArrayList<String>();
		sArray1.add("半自助");
		sArray1.add("精品");
		homeItem1.setTags(sArray1);
		selfServiceItems.add(homeItem1);
		
		final HomeItem homeItem2 = new HomeItem();
		homeItem2.setContent("长江三峡与三峡工程交相辉映，巴楚文化和土家风情水乳交融。还有屈原祠、昭君故里、关陵等古代名人文化遗址；而三国古战场的历史遗风、三游洞的文墨千古、三峡水利枢纽的雄伟景观更是为宜昌神奇浪漫、多姿多彩的迷人画卷增添了浓重的色彩。");
		homeItem2.setTitle("长江三峡，自助游览");
		homeItem2.setPkgId("54725");
		homeItem2.setDrawableResourceId(R.drawable.title_self2);
		final ArrayList<String> sArray2 = new ArrayList<String>();
		sArray2.add("自助");
		sArray2.add("山水");
		sArray2.add("峡谷");
		homeItem2.setTags(sArray2);
		selfServiceItems.add(homeItem2);
	}
	public void productLeisureItems(){
		leisureItems = new ArrayList<HomeItem>();
		/*final HomeItem homeItem1 = new HomeItem();
		homeItem1.setContent("祈福平安，瞻仰目前世界上最高的释迦牟尼佛青铜立像拜大佛！体验佛教文化，享受心灵之旅！观大型音乐喷泉动态群雕—九龙灌浴，游梵宫，食素斋，祈福吉祥,体验佛教文化。");
		homeItem1.setTitle("去灵山，拜大佛，抱佛脚");
		homeItem1.setPkgId("1618865");
		homeItem1.setDrawableResourceId(R.drawable.title_culture1);
		final ArrayList<String> sArray1 = new ArrayList<String>();
		sArray1.add("佛教");
		sArray1.add("文化之旅");
		homeItem1.setTags(sArray1);
		leisureItems.add(homeItem1);
		
		final HomeItem homeItem2 = new HomeItem();
		homeItem2.setContent("有空闲，更应该去看看《丝路花雨》和《大梦敦煌》，尤其是前者，它取材于敦煌壁画，熔合了中国民族舞、敦煌舞、印度舞、黑巾舞、波斯马铃舞、波斯酒舞、土耳其舞、盘上舞、新疆舞等各种舞蹈形式于一身，被誉为中国古典舞的典范。其主角英娘的表演者已至五代，常演常新，堪称艺术的奇迹。");
		homeItem2.setTitle("去丝绸之路走走");
		homeItem2.setPkgId("1603463");
		homeItem2.setDrawableResourceId(R.drawable.title_culture2);
		final ArrayList<String> sArray2 = new ArrayList<String>();
		sArray2.add("广度");
		sArray2.add("文化之旅");
		homeItem2.setTags(sArray2);
		leisureItems.add(homeItem2);*/
	}
	public void productLuxuryItems(){
		luxuryItems = new ArrayList<HomeItem>();
		final HomeItem homeItem3 = new HomeItem();
		homeItem3.setContent("尽享悠闲假期，省一点，好一点，更要温馨一点");
		homeItem3.setTitle("相约朋友 带着孩子");
		homeItem3.setPkgId("93328");
		homeItem3.setDrawableResourceId(R.drawable.title_luxury1);
		final ArrayList<String> sArray3 = new ArrayList<String>();
		sArray3.add("亲朋游");
		sArray3.add("悠闲");
		homeItem3.setTags(sArray3);
		luxuryItems.add(homeItem3);
		
		
		luxuryItems = new ArrayList<HomeItem>();
		final HomeItem homeItem1 = new HomeItem();
		homeItem1.setContent("自由组合的航班，放慢脚步驻足停留，悠闲海滨假期在此启程！");
		homeItem1.setTitle("流连忘返-大连城");
		homeItem1.setPkgId("73610");
		homeItem1.setDrawableResourceId(R.drawable.title_luxury2);
		final ArrayList<String> sArray1 = new ArrayList<String>();
		sArray1.add("海滨假期");
		sArray1.add("豪华之旅");
		homeItem3.setTags(sArray1);
		luxuryItems.add(homeItem1);
		
		
	}
	public void productAllItems(){
		allItems = new ArrayList<HomeItem>();
		allItems.addAll(placesOfInterestItems);
		allItems.addAll(natureItems);
		allItems.addAll(cultureItems);
		allItems.addAll(selfServiceItems);
		allItems.addAll(leisureItems);
		allItems.addAll(luxuryItems);
		if(Log.D)
		{
			Log.d("", "");
			
		}
	}
	
	public void productRecommendItems(){
		//TODO
		recommendItems = new ArrayList<HomeItem>();
		
	}
	public ArrayList<HomeItem> getNatureItems() {
		return natureItems;
	}

	public ArrayList<HomeItem> getPlacesOfInterestItems() {
		return placesOfInterestItems;
	}

	public ArrayList<HomeItem> getCultureItems() {
		return cultureItems;
	}

	public ArrayList<HomeItem> getSelfServiceItems() {
		return selfServiceItems;
	}

	public ArrayList<HomeItem> getLeisureItems() {
		return leisureItems;
	}

	public ArrayList<HomeItem> getLuxuryItems() {
		return luxuryItems;
	}

	public ArrayList<HomeCategory> getHomeCategorys() {
		return homeCategorys;
	}

	public ArrayList<HomeItem> getRecommendItems() {
		recommendItems.clear();
		Random random = new Random();
		for(int i=0;i<5;i++){
			int s = random.nextInt(allItems.size());
			HomeItem item = allItems.get(s);
			if(!recommendItems.contains(item)){
				recommendItems.add(item);
			}
		}
		return recommendItems;
	}

	public ArrayList<HomeItem> getAllItems() {
		return allItems;
	}
	
/*
	public void setRecommendItems(ArrayList<HomeItem> recommendItems) {
		this.recommendItems = recommendItems;
	}*/
	
}
