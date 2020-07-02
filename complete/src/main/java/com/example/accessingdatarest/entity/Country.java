package com.example.accessingdatarest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "Countries")
public class Country {

	private @Id @Column(length = 2) String countryId;
	private @Column(length = 40) String countryName;
	private @ManyToOne Region region;

	public Country() {
	}

	public Country(String countryId, String countryName, Region region) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.region = region;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}
