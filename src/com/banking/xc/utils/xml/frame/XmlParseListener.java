package com.banking.xc.utils.xml.frame;

public interface XmlParseListener {
	public void onParseStart();
	public void onParseError(String reason);
	public void onParseSuccess(Object o);//如果这里接受以后仍然类型转化错误，当做onError
	
}
