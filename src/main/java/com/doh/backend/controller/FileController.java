package com.doh.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doh.backend.entity.DataSetDto;
import com.doh.backend.entity.File;
import com.doh.backend.service.FileService;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@GetMapping("/file-list")
	public ResponseEntity<List<File>> getFileList() {
		List<File> files = this.fileService.getAllFilesList();
		return new ResponseEntity<List<File>>(files, HttpStatus.OK);
	}
	
	@GetMapping("/get-csv-data")
	public ResponseEntity<List<DataSetDto>> getCSVData() {
		List<DataSetDto> list = this.fileService.getDataFromCSV();
		return new ResponseEntity<List<DataSetDto>>(list, HttpStatus.OK);
	}

}
