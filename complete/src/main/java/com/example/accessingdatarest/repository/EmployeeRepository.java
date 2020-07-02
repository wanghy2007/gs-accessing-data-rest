package com.example.accessingdatarest.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.accessingdatarest.entity.Employee;

@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, BigInteger> {

}
