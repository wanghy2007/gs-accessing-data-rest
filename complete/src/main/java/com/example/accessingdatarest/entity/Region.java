package com.example.accessingdatarest.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Regions")
public class Region {

	@Id
	@Column(nullable = false)
	private BigInteger regionId;

	@Column(length = 25)
	private String regionName;

	public BigInteger getRegionId() {
		return regionId;
	}

	public void setRegionId(BigInteger regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}