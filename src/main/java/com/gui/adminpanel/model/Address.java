package com.gui.adminpanel.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Address {
	
	@NotBlank(message = "Zip code is empty")
	@Size(max = 15, message = "Username name must be between 1 and 15 characters")
	private String zipcode;
	
	@NotBlank(message = "Zip code is empty")
	@Size(max = 255, message = "Username name must be between 1 and 15 characters")
	private String street;
	
	@NotBlank(message = "Zip code is empty")
	@Size(max = 255, message = "Username name must be between 1 and 15 characters")
	private String city;
	
	@NotBlank(message = "State is empty")
	@Size(min = 2, max = 2, message = "State must be 2 characters")
	private String state;
	
	@NotBlank(message = "Complement is empty")
	@Size(max = 255, message = "Complement must be between 1 and 15 characters")
	private String complement;
	
	public String getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getComplement() {
		return complement;
	}
	
	public void setComplement(String complement) {
		this.complement = complement;
	}
	
}
