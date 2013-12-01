package com.banking.xc.entity.recommend;

public class VacationPeople {
	private int manCount = 0;
	private int womanCount = 0;
	private int childCount = 0;
	private int coupleCount = 0;
	//private int itemCount;//页面计算元素
	
	public int getPeopleCount() {
		return manCount+womanCount+2*coupleCount+childCount;
	}
	public int getItemCount() {
		return manCount+womanCount+coupleCount+childCount;
	}
	public void addAMan(){
		manCount++;
	}
	public void delAMan(){
		if(manCount>0){
			manCount--;
		}
	}
	public int getManCount() {
		return manCount;
	}
	public void setManCount(int manCount) {
		this.manCount = manCount;
	}
	public void addAWoman(){
		womanCount++;
	}
	public void delAWoman(){
		if(womanCount>0){
			womanCount--;
		}
	}
	public int getWomanCount() {
		return womanCount;
	}
	public void setWomanCount(int womanCount) {
		this.womanCount = womanCount;
	}
	public int getChildCount() {
		return childCount;
	}
	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}
	public void addACouple(){
		coupleCount++;
	}
	public void delACouple(){
		if(coupleCount>0){
			coupleCount--;
		}
	}
	public int getCoupleCount() {
		return coupleCount;
	}
	public void setCoupleCount(int coupleCount) {
		this.coupleCount = coupleCount;
	}
	
}
