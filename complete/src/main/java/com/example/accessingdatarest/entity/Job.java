package com.example.accessingdatarest.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Jobs")
public class Job {

	@Id
	@Column(nullable = false)
	private String jobId;

	@Column(length = 35, nullable = false)
	private String jobTitle;

	@Column(precision = 6)
	private BigInteger minSalary;

	@Column(precision = 6)
	private BigInteger maxSalary;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public BigInteger getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(BigInteger minSalary) {
		this.minSalary = minSalary;
	}

	public BigInteger getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(BigInteger maxSalary) {
		this.maxSalary = maxSalary;
	}
}
