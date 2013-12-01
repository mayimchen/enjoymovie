package com.banking.xc.entity;

import java.util.ArrayList;


public class HomeItem {
	
	public static final int TO_LIST = 1;
	public static final int TO_OBJECT = 2;
	private String title;
	private String content;
	private int drawableResourceId;
	private ArrayList<String> tags;
	/**
	 * 一定跳到度假详情，直接通pkgId即可
	 */
	private int kind;
	String pkgId;
	String keyWord;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getDrawableResourceId() {
		return drawableResourceId;
	}
	public void setDrawableResourceId(int drawableResourceId) {
		this.drawableResourceId = drawableResourceId;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public String getPkgId() {
		return pkgId;
	}
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
	
}
