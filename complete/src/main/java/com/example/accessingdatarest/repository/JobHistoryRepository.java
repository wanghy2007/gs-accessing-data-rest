package com.example.accessingdatarest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.accessingdatarest.entity.JobHistory;
import com.example.accessingdatarest.entity.JobHistoryKey;

@RepositoryRestResource(collectionResourceRel = "jobHistory", path = "jobHistory")
public interface JobHistoryRepository extends PagingAndSortingRepository<JobHistory, JobHistoryKey> {

}
