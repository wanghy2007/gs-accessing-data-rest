package com.example.accessingdatarest.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Regions")
public class Region {

	private @Id @Column(nullable = false) BigInteger regionId;
	private @Column(length = 25) String regionName;

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