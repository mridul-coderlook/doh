package com.doh.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doh.backend.entity.ExportTask;

@Repository
public interface ExportTaskRepository extends CrudRepository<ExportTask, Long> {

}
