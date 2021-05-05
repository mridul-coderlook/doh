package com.doh.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doh.backend.entity.FileStatus;

@Repository
public interface FileStausRepository extends JpaRepository<FileStatus, Long> {

	List<FileStatus> findAllByFileId(Long id);

}
