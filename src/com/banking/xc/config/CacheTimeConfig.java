package com.banking.xc.config;

public class CacheTimeConfig {

	public static final long MINUTE = 1000 * 60;
	public static final long HOUR = MINUTE * 60;
	public static final long DAY = HOUR * 24;

	public static final long DEFAULT = -1;// 默认

	public static final long CRAZY_BUY = MINUTE * 5;// 疯狂抢购
	public static final long JD_NEWS = MINUTE * 5;// 京东快报
	public static final long RECOMMEND = MINUTE * 30;// 猜你喜欢
	public static final long CATEGORY = HOUR;// 分类列表
	public static final long PROVINCE = DAY * 3;// 库存地区
	public static final long IMAGE = DAY * 3;// 任何图片
	public static final long HOME_ACTIVTIES = MINUTE * 5;//活动栏
	public static final long HOME_BUTTON = HOUR * 1;//首页按钮
	public static final long SEARCH_TIP = MINUTE * 30; //搜索联想
	public static final long ALL_ORDER_BUTTON_CACHE = MINUTE * 30;
	public static final long MY_JD_ORDER_BUTTON_CACHE = MINUTE * 30;
	public static final long MY_JD_ORDER_BUTTON_IMAGE_CACHE = DAY*15;

}
