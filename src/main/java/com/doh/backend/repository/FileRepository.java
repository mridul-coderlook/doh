package com.doh.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doh.backend.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
