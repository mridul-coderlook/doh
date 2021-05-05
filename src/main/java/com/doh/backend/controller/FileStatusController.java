package com.doh.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doh.backend.entity.FileStatus;
import com.doh.backend.service.FileStatusService;

@RestController
public class FileStatusController {
	
	@Autowired
	private FileStatusService fileStatusService; 
	
	@GetMapping("/file-status-list")
	public ResponseEntity<List<FileStatus>> getFileStatusList() {
		List<FileStatus> fileStatus = this.fileStatusService.getAllFileStatus();
		return new ResponseEntity<List<FileStatus>>(fileStatus, HttpStatus.OK);
	}

}
