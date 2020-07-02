package com.example.accessingdatarest.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Jobs")
public class Job {

	private @Id @Column(nullable = false) String jobId;
	private @Column(length = 35, nullable = false) String jobTitle;
	private @Column(precision = 6) BigInteger minSalary;
	private @Column(precision = 6) BigInteger maxSalary;

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
