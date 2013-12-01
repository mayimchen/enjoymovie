package com.banking.xc.entity;

import java.util.ArrayList;

/**
 * 团购详细信息图片关联类
 * 数据很冗余，列表接口已经返回了相关信息
 * @author zhangyinhang
 *
 */
public class GroupProductInfoResponse {
	ArrayList<GroupProductImageEntity> groupProductImageEntityList;
	ArrayList<String> hotelPictures;//这个是hotelPictures
	GroupProductListEntity groupProductListEntity;//可以代替大多数的string元素
	String note;//其它信息，抢购就放抢购链接
	String hotelDescription;//
	String headDescription;
	String status;
	String quantity; //已售份数在groupProductListEntity中
	public ArrayList<GroupProductImageEntity> getGroupProductImageEntityList() {
		return groupProductImageEntityList;
	}
	public void setGroupProductImageEntityList(ArrayList<GroupProductImageEntity> groupProductImageEntityList) {
		this.groupProductImageEntityList = groupProductImageEntityList;
	}
	public ArrayList<String> getHotelPictures() {
		return hotelPictures;
	}
	public void setHotelPictures(ArrayList<String> hotelPictures) {
		this.hotelPictures = hotelPictures;
	}
	public GroupProductListEntity getGroupProductListEntity() {
		return groupProductListEntity;
	}
	public void setGroupProductListEntity(GroupProductListEntity groupProductListEntity) {
		this.groupProductListEntity = groupProductListEntity;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getHotelDescription() {
		return hotelDescription;
	}
	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}
	public String getHeadDescription() {
		return headDescription;
	}
	public void setHeadDescription(String headDescription) {
		this.headDescription = headDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
}
