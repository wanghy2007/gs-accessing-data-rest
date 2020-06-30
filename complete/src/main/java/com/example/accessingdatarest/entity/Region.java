package com.example.accessingdatarest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Region {

	private @Id Long regionId;
	private @Column(length = 25) String regionName;

	public Region() {
	}

	public Region(Long regionId, String regionName) {
		this.regionId = regionId;
		this.regionName = regionName;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}