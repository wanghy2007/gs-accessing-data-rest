package com.example.accessingdatarest.entity;

import java.io.Serializable;

public class JobHistoryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private Employee employee;
	private Job job;
	private Department department;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
