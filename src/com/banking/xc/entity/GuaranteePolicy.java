package com.banking.xc.entity;

import java.io.Serializable;

/**
 * 和ratePlan相关联类
 * @author zhangyinhang
 *
 */
public class GuaranteePolicy implements Serializable {
	private String guaranteeCode;
	private String holdTime;
	
	public String getGuaranteeCode() {
		return guaranteeCode;
	}
	public void setGuaranteeCode(String guaranteeCode) {
		this.guaranteeCode = guaranteeCode;
	}
	public String getHoldTime() {
		return holdTime;
	}
	public void setHoldTime(String holdTime) {
		this.holdTime = holdTime;
	}
	
	
}
