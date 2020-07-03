package com.example.accessingdatarest.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "Employees")
public class Employee {

	@Id
	@Column(precision = 6, nullable = false)
	private BigInteger employeeId;

	@Column(length = 20)
	private String firstName;

	@Column(length = 25, nullable = false)
	private String lastName;

	@Column(length = 25, nullable = false)
	private String email;

	@Column(length = 20)
	private String phoneNumber;

	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "America/Vancouver")
	private Date hireDate;

	@ManyToOne
	@JoinColumn(name = "job_id", nullable = false)
	private Job job;

	@Column(precision = 8, scale = 2)
	private BigDecimal salary;

	@Column(precision = 2, scale = 2)
	private BigDecimal commissionPct;

	@ManyToOne
	@JoinColumn(name = "manager_id")
	private Employee manager;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public BigInteger getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(BigInteger employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getCommissionPct() {
		return commissionPct;
	}

	public void setCommissionPct(BigDecimal commissionPct) {
		this.commissionPct = commissionPct;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
