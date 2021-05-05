package com.doh.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.doh.backend.entity.File;
import com.doh.backend.entity.FileStatus;
import com.doh.backend.repository.FileStausRepository;

@Service
public class FileStatusService {

	@Autowired
	private FileStausRepository fileStausRepository;

	@Autowired
	private FileService fileService;

	@Autowired
	private ApplicationContext applicationContext;

	public List<FileStatus> getAllFileStatus() {
		List<FileStatus> fileStatusList = new ArrayList<>();

		List<File> files = this.fileService.getAllFilesList();
		if (files != null && files.size() > 0) {
			for (File file : files) {
				List<FileStatus> fileStatus = this.fileStausRepository.findAllByFileId(file.getId());
				for (FileStatus status : fileStatus) {
					final FileStatusService proxy = this.getSpringProxy(FileStatusService.class);
					FileStatus fileStatusObj = proxy.getFileStatus(file, status);
					fileStatusList.add(fileStatusObj);
				}
			}
		}

		return fileStatusList;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public FileStatus getFileStatus(File file, FileStatus status) {
		if (status != null) {
			if (file.getFilePath() != null && file.getFilePath() != "") {
				status.setFound(true);
			} else {
				status.setFound(false);
			}
		}
		return status;
	}

	protected <P> P getSpringProxy(Class clazz) {
		return (P) applicationContext.getBean(clazz);
	}

}