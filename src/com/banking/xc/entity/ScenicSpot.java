package com.banking.xc.entity;

import java.util.ArrayList;

public class ScenicSpot {
	private String category;
	//private String EName;
	private String feature;
	private String intro;
	private String linkName;
	private String linkUrl;
	private String name;
	private String recommendOrder;
	private String scenicSpotId;
	private String star;
	private String synopsis;
	private String takeTime;
	private String zoneTopOrder;
	private ArrayList<ImageItem> scenicSpotImages; //它的intro忽略掉
	private ArrayList<ScenicSpotDetailEntity> scenicSpotDetailEntitys;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRecommendOrder() {
		return recommendOrder;
	}
	public void setRecommendOrder(String recommendOrder) {
		this.recommendOrder = recommendOrder;
	}
	public String getScenicSpotId() {
		return scenicSpotId;
	}
	public void setScenicSpotId(String scenicSpotId) {
		this.scenicSpotId = scenicSpotId;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public String getZoneTopOrder() {
		return zoneTopOrder;
	}
	public void setZoneTopOrder(String zoneTopOrder) {
		this.zoneTopOrder = zoneTopOrder;
	}
	public ArrayList<ImageItem> getScenicSpotImages() {
		return scenicSpotImages;
	}
	public void setScenicSpotImages(ArrayList<ImageItem> scenicSpotImages) {
		this.scenicSpotImages = scenicSpotImages;
	}
	public ArrayList<ScenicSpotDetailEntity> getScenicSpotDetailEntitys() {
		return scenicSpotDetailEntitys;
	}
	public void setScenicSpotDetailEntitys(ArrayList<ScenicSpotDetailEntity> scenicSpotDetailEntitys) {
		this.scenicSpotDetailEntitys = scenicSpotDetailEntitys;
	}
	
}
