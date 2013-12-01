package com.banking.xc.entity;

import java.io.Serializable;

public class HotelAward implements Serializable {
	private String provider;
	private String rating;
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	
}
