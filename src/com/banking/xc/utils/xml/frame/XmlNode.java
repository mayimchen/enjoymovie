package com.banking.xc.utils.xml.frame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 
 * @author banking
 *
 */
public class XmlNode {
	
	private String name;
	private List<XmlNode> xmlNodeList = new ArrayList<XmlNode>();
	private Map<String,String> attributesMap = new HashMap<String,String>();
	/**
	 * inner和xmlNodeList只可以有一项
	 */
	private String innerValue;
	
	public XmlNode(String name){
		this.name = name;
	}
	public XmlNode(String name,String innerValue){
		this.name = name;
		this.innerValue = innerValue;
	}
	
	@Deprecated
	public void setInnerValue(String innerValue){
		this.innerValue = innerValue;
	}
	public void addChildNode(XmlNode node){
		xmlNodeList.add(node);
	}
	public void putAttribute(String key,String value){
		attributesMap.put(key, value);
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("<"+name);
		for(Entry<String, String> i:attributesMap.entrySet()){
			sb.append(" "+i.getKey()+"=\""+i.getValue()+"\"");
		}
		sb.append(">");
		if(innerValue!=null){
			sb.append(innerValue);
		}else{
			for(XmlNode node:xmlNodeList)
			{
				sb.append(node.toString());
			}
		}
		
		sb.append("</"+name+">");
		return sb.toString();
	}
	public void addNodeByNameAndValue(String name,String innerValue){
		final XmlNode node = new XmlNode(name);
		node.setInnerValue(innerValue);
		this.addChildNode(node);
	}
	/*
	public class XmlNodeAttribute{
		
	}
	*/
}
