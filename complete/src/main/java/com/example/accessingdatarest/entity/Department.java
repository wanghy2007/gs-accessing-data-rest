package com.example.accessingdatarest.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Departments")
public class Department {

	@Id
	@Column(precision = 4, nullable = false)
	private BigInteger departmentId;

	@Column(length = 30, nullable = false)
	private String departmentName;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	public BigInteger getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(BigInteger departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
