package com.example.accessingdatarest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.accessingdatarest.entity.Region;

@RepositoryRestResource(collectionResourceRel = "regions", path = "regions")
public interface RegionRepository extends PagingAndSortingRepository<Region, Long> {

}
