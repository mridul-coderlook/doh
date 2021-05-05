package com.doh.backend.service;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.doh.backend.event.ExportFolderEvent;

@Service
public class FolderContentsService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public String getFolderDetails() {

		String jobTracker = UUID.randomUUID().toString();

		ExportFolderEvent exportFolderEvent = new ExportFolderEvent(this, jobTracker);
		applicationEventPublisher.publishEvent(exportFolderEvent);

		return jobTracker;
	}

	public int getTotalFilesInDirectory(File maindir) {
		int count = 0;
		for (File file : maindir.listFiles()) {
			if (file.isFile()) {
				count++;
			}
			if (file.isDirectory()) {
				count += getTotalFilesInDirectory(file);
			}
		}
		return count;
	}

	public void getFolderAbsolutePath() {

		String maindirpath = "C:\\coderlook\\workspace";

		File maindir = new File(maindirpath);
		int count = getTotalFilesInDirectory(maindir);
		System.out.println("Total File : " + count);
		if (maindir.exists() && maindir.isDirectory()) {
			File arr[] = maindir.listFiles();

			for (File f : arr) {
				if (f.isFile()) {
				} else if (f.isDirectory()) {
					System.out.println("Folder Path :- " + f.getAbsolutePath());
				}
			}
		}
	}

//	public void getFolders() {
//		
//		ExportTask et = new ExportTask();
//		et.setJobRef("J1");
//		et.setStartTime(new Date());
//		et.setTaskType("T1");
//		et.setStatus("STARTED");
//		et.setTaskTitle(ProjectConstants.GET_EXPORT_TASKS_EVENT);
//		
//		et = this.exportTaskRepository.save(et);
////		try {
////			Thread.sleep(5000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		List<FolderDetails> folderDetailsList = (List<FolderDetails>) this.folderDetailsRepository.findAll();
//		
//		et.setEndTime(new Date());
//		et.setStatus("COMPLETED");
//		et = this.exportTaskRepository.save(et);
////		try {
////			Thread.sleep(5000);
////		} catch (InterruptedException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//	}

}
