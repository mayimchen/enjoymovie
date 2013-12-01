package com.banking.xc.utils.datetime;

import android.text.TextUtils;

public class WeekUtil {
	public static String getDesStringByWeekNumber(String rawString){
		if(TextUtils.equals("123456", rawString)){
			return "周1至周6";
		}
		if(TextUtils.equals("12345", rawString)){
			return "周1至周5";
		}
		if(TextUtils.equals("1234567", rawString)){
			return "每天均可";
		}
		
		StringBuffer sb = new StringBuffer();
		//处理..3..6情况
		if(rawString.contains(".")){
			char[] charArray = rawString.toCharArray();
			for(int i=0;i<charArray.length;i++){
				if(charArray[i] == '.'){
					if(TextUtils.isEmpty(sb.toString())){
						continue;
					}else{
						if(sb.toString().endsWith("至")){
							continue;
						}else{
							sb.append("至");
						}
					}
				}
				if(isWeekNumber(String.valueOf(charArray[i]))){
					sb.append("周").append(charArray[i]);
				}
			}
		}
		return sb.toString();
	}
	public static boolean isWeekNumber(CharSequence c){
		if(TextUtils.equals("1", c)||TextUtils.equals("2", c)||TextUtils.equals("3", c)||TextUtils.equals("4", c)
				||TextUtils.equals("5", c)||TextUtils.equals("6", c)||TextUtils.equals("7", c)){
			return true;
		}else{
			return false;
		}
	}
}
