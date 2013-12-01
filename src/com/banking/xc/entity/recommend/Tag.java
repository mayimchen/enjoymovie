package com.banking.xc.entity.recommend;

import android.text.TextUtils;

/**
 * 用户相关的Tag
 * @author zhangyinhang
 *
 */
public class Tag {
	private String tagString;
	private int tagId; //tagId=-1表示不确定
	private Boolean selected;//是否被用户使用了
	public Tag(int tagId,String tagString){
		this.tagId = tagId;
		this.tagString = tagString;
		this.selected = false;
	}
	public Tag(String tagString){
		this.tagId = -1;
		this.tagString = tagString;
		this.selected = false;
	}
	public String getTagString() {
		return tagString;
	}
	public int getTagId() {
		return tagId;
	}
	
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	/**
	 * 重写Tag的equals方法，当tagString或者tagId相同时，认为tag相同
	 */
	@Override
	public boolean equals(Object o) {
		Tag tag;
		try{
			tag = (Tag)o;
		}catch(Exception e){
			return false;
		}
		if(tag!=null){
			//if(TextUtils.equals(tag.getTagString(), this.tagString)||tag.getTagId()==this.tagId){
			if(TextUtils.equals(tag.getTagString(), this.tagString)){//不能id作为标准
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	
}	
