package com.example.accessingdatarest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class JobHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Vancouver")
	private Date startDate;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Vancouver")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "job_id", nullable = false)
	private Job job;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
