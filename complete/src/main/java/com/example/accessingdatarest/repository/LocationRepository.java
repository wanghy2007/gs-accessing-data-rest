package com.example.accessingdatarest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.accessingdatarest.entity.Location;

@RepositoryRestResource(collectionResourceRel = "locations", path = "locations")
public interface LocationRepository extends PagingAndSortingRepository<Location, Long> {

}
