package com.doh.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doh.backend.service.FolderContentsService;

@RestController
public class FolderContentsController {
	
	@Autowired
	private FolderContentsService folderContentsService;
	
	@GetMapping("/file-content")
	public void getFolderAndFileList() {
		 this.folderContentsService.getFolderDetails();

	}
	
	@GetMapping("/file-path")
	public void getFolderAbsolutePathList() {
		 this.folderContentsService.getFolderAbsolutePath();

	}
	
//	@GetMapping("/file-path")
//	public void getFolderList() {
//		 this.folderContentsService.getFolders();
//
//	}

}
