package com.lcore.core.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Organization_addr")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class OrganizationAddr extends Root{

	private String place;
	private String address;
	private Boolean isInside;
	private String address_in;
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsInside() {
		return isInside;
	}
	public void setIsInside(Boolean isInside) {
		this.isInside = isInside;
	}
	public String getAddress_in() {
		return address_in;
	}
	public void setAddress_in(String address_in) {
		this.address_in = address_in;
	}
	
	
	
}
