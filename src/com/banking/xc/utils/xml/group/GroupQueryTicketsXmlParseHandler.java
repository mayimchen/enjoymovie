package com.banking.xc.utils.xml.group;

import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import android.text.TextUtils;

import com.banking.xc.entity.GroupTicket;
import com.banking.xc.utils.xml.frame.XmlParseHandler;
import com.banking.xc.utils.xml.frame.XmlParseListener;

public class GroupQueryTicketsXmlParseHandler extends XmlParseHandler{

	ArrayList<GroupTicket> groupTicketList;
	GroupTicket groupTicket;
	String preTag;
	public GroupQueryTicketsXmlParseHandler(XmlParseListener xmlParseListener, InputStream inputStream) {
		super(xmlParseListener, inputStream);
	}

	@Override
	public void cancelParse() {
		
	}

	@Override
	public void destroy() {
		groupTicketList = null;
	}

	@Override
	public Object getObjectWhenEnd() {
		return groupTicketList;
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if(TextUtils.equals(localName, "TicketList")){
			groupTicketList = new ArrayList<GroupTicket> ();
		}
		if(TextUtils.equals(localName, "GroupQueryTicketsEntity")){
			groupTicket = new GroupTicket();
		}
		preTag = localName;
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		final String data = new String(ch, start, length);
		if(TextUtils.equals(preTag,"ExpirationDate")){
			groupTicket.setExpirationDate(data);
			return;
		}
		if(TextUtils.equals(preTag,"TicketStatus")){
			groupTicket.setTicketStatus(data);
			return;
		}
		if(TextUtils.equals(preTag,"TicketPWD")){
			groupTicket.setTicketPWD(data);
			return;
		}
		if(TextUtils.equals(preTag,"TicketNo")){
			groupTicket.setTicketNo(data);
			return;
		}
		if(TextUtils.equals(preTag,"UID")){
			groupTicket.setUID(data);
			return;
		}
		if(TextUtils.equals(preTag,"OrderID")){
			groupTicket.setOrderID(data);
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		if(TextUtils.equals(localName, "TicketList")){
			//
		}
		if(TextUtils.equals(localName, "GroupQueryTicketsEntity")){
			groupTicketList.add(groupTicket);
			groupTicket = null;
		}
	}

	
	
	/**
	 * <GroupQueryTicketsResponse>
		<TicketList>
			<GroupQueryTicketsEntity>
				<ExpirationDate>2012-08-01T00:00:00</ExpirationDate>
				<TicketStatus>4</TicketStatus>
				<TicketPWD>0166142132</TicketPWD>
				<TicketNo>0002742001</TicketNo>
				<UID>159c906a-aa28-4f54-b609-59d2c105fde2</UID>
				<OrderID>2742</OrderID>
			</GroupQueryTicketsEntity>
			<GroupQueryTicketsEntity>
				<ExpirationDate>2012-08-01T00:00:00</ExpirationDate>
				<TicketStatus>4</TicketStatus>
				<TicketPWD>1637758846</TicketPWD>
				<TicketNo>0002742002</TicketNo>
				<UID>159c906a-aa28-4f54-b609-59d2c105fde2</UID>
				<OrderID>2742</OrderID>
			</GroupQueryTicketsEntity>
		</TicketList>
	</GroupQueryTicketsResponse>

	 */
}
