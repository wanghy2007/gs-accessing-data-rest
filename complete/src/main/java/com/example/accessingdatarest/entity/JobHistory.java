package com.example.accessingdatarest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(JobHistoryKey.class)
public class JobHistory {

	private @Id @ManyToOne @JoinColumn(name = "employee_id", nullable = false) Employee employee;
	private @Column(nullable = false) Date startDate;
	private @Column(nullable = false) Date endDate;
	private @Id @ManyToOne @JoinColumn(name = "job_id", nullable = false) Job job;
	private @Id @ManyToOne @JoinColumn(name = "department_id") Department department;

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
