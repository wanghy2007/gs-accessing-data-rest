package com.example.accessingdatarest.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.accessingdatarest.entity.Department;

@RepositoryRestResource(collectionResourceRel = "departments", path = "departments")
public interface DepartmentRepository extends PagingAndSortingRepository<Department, BigInteger> {
	
}
