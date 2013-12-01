package com.banking.xc.utils;

import android.text.TextUtils;

/**
 * 遇到<>标签信息，自己过滤到这些
 * @author zhangyinhang
 *
 */
public class TextFilterUtil {

	
	public static String filterHtmlTag(String rawString){
		
		if(TextUtils.isEmpty(rawString)){
			return "";
		}
		while(rawString.contains("<")){
			int tag1 = rawString.indexOf("<");
			if(rawString.contains(">")){
				int tag2 = rawString.indexOf(">");
				if(tag2>tag1){
					rawString = rawString.substring(0, tag1)+rawString.substring(tag2+1,rawString.length());
				}else{
					rawString = rawString.substring(0, tag2)+rawString.substring(tag2+1,tag1)+rawString.substring(tag1+1,rawString.length());
				}
			}else{
				rawString = rawString.substring(0, tag1);
			}
		}
		while(rawString.contains(">")){
			final int tag3 = rawString.indexOf(">");
			rawString = rawString.substring(0, tag3)+rawString.substring(tag3+1,rawString.length());
		}
		while(rawString.contains("null")){
			final int nullTag = rawString.indexOf("null");
			rawString = rawString.substring(0, nullTag)+rawString.substring(nullTag+4,rawString.length());
		}
		while(rawString.contains("【")){
			final int tag = rawString.indexOf("【");
			rawString = rawString.substring(0, tag)+rawString.substring(tag+1,rawString.length());
		}
		while(rawString.contains("】")){
			final int tag = rawString.indexOf("】");
			rawString = rawString.substring(0, tag)+rawString.substring(tag+1,rawString.length());
		}
		while(rawString.contains("&nbsp;")){
			final int tag = rawString.indexOf("&nbsp;");
			rawString = rawString.substring(0, tag)+rawString.substring(tag+6,rawString.length());
		}
		return rawString;


		
	}
}
