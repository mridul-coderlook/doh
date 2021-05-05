package com.doh.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doh.backend.entity.FolderDetails;

@Repository
public interface FolderDetailsRepository extends CrudRepository<FolderDetails, Long> {

	List<FolderDetails> findByStatus(String complete);

}
