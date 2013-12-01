package com.banking.xc.entity;

public class CraftInfoEntity {
	private String craftType; //机型代码
	private String CTName; //机型中文名称
	private String WidthLevel;//宽度等级
	private String MinSeats;//最少座位数
	private String MaxSeats;//最多座位数
	private String Note;//备注
	private String CrafttypeEname;//机型英文名称
	private String CraftKind;//飞机大小分类
	
	public String getCraftType() {
		return craftType;
	}
	public void setCraftType(String craftType) {
		this.craftType = craftType;
	}
	public String getCTName() {
		return CTName;
	}
	public void setCTName(String cTName) {
		CTName = cTName;
	}
	public String getWidthLevel() {
		return WidthLevel;
	}
	public void setWidthLevel(String widthLevel) {
		WidthLevel = widthLevel;
	}
	public String getMinSeats() {
		return MinSeats;
	}
	public void setMinSeats(String minSeats) {
		MinSeats = minSeats;
	}
	public String getMaxSeats() {
		return MaxSeats;
	}
	public void setMaxSeats(String maxSeats) {
		MaxSeats = maxSeats;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public String getCrafttypeEname() {
		return CrafttypeEname;
	}
	public void setCrafttypeEname(String crafttypeEname) {
		CrafttypeEname = crafttypeEname;
	}
	public String getCraftKind() {
		return CraftKind;
	}
	public void setCraftKind(String craftKind) {
		CraftKind = craftKind;
	}
	
	
	
}
