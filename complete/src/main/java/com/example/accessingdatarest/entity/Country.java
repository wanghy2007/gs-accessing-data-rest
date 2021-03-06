package com.example.accessingdatarest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Countries")
public class Country {

	@Id
	@Column(length = 2, nullable = false)
	private String countryId;

	@Column(length = 40)
	private String countryName;

	@ManyToOne
	@JoinColumn(name = "region_id")
	private Region region;

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
