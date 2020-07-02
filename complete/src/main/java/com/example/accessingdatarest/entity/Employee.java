package com.example.accessingdatarest.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Employees")
public class Employee {

	private @Id @Column(precision = 6, nullable = false) BigInteger employeeId;
	private @Column(length = 20) String firstName;
	private @Column(length = 25, nullable = false) String lastName;
	private @Column(length = 25, nullable = false) String email;
	private @Column(length = 20) String phoneNumber;
	private @Column(nullable = false) Date hireDate;
	private @ManyToOne @JoinColumn(name = "job_id", nullable = false) Job job;
	private @Column(precision = 8, scale = 2) BigDecimal salary;
	private @Column(precision = 2, scale = 2) BigDecimal commissionPct;

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

	private @ManyToOne @JoinColumn(name = "manager_id") Employee manager;
	// private @ManyToOne @JoinColumn(name = "department_id") Department department;
}
