package com.nexus.jobsearchtracker.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
//	@NotNull
	@Size(max = 100)
	private String address1;
	
//	@NotNull
	@Size(max = 100)
	private String address2;
	
//	@NotNull
	@Size(max = 40)
	private String city;
	
//	@NotNull
	@Size(max = 10)
	private String state;
	
//	@NotNull
	@Size(max = 5)
	private Integer zip;
	
	public  Address() {}
	
	public Address(String address1, String address2, String city, String state, Integer zip) {
		super();
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state="
				+ state + ", zip=" + zip + "]";
	}
}
