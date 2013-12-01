package com.banking.xc.entity;

public class Customer {
	private String customerSurname;
	private String contactSurname;
	private String phoneNumber;
	private String phoneTechType;
	private String email;
	private String contactType;
	
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getCustomerSurname() {
		return customerSurname;
	}
	public void setCustomerSurname(String customerSurname) {
		this.customerSurname = customerSurname;
	}
	public String getContactSurname() {
		return contactSurname;
	}
	public void setContactSurname(String contactSurname) {
		this.contactSurname = contactSurname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneTechType() {
		return phoneTechType;
	}
	public void setPhoneTechType(String phoneTechType) {
		this.phoneTechType = phoneTechType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
