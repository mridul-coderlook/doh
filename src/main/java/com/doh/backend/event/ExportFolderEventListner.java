package com.doh.backend.event;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.doh.backend.entity.ExportTask;
import com.doh.backend.entity.FolderDetails;
import com.doh.backend.repository.ExportTaskRepository;
import com.doh.backend.repository.FolderDetailsRepository;
import com.doh.backend.utils.ProjectConstants;

@Component
public class ExportFolderEventListner implements ApplicationListener<ExportFolderEvent> {

	@Value("${dir.path}")
	private String mainDir;

	@Autowired
	private FolderDetailsRepository folderDetailsRepository;

	@Autowired
	private ExportTaskRepository exportTaskRepository;

	@Override
	public void onApplicationEvent(ExportFolderEvent event) {

		ExportTask et = new ExportTask();
		et.setJobRef(event.getJobRef());
		et.setStartTime(new Date());
		et.setTaskType("T1");
		et.setStatus("STARTED");
		et.setTaskTitle(ProjectConstants.GET_EXPORT_TASKS_EVENT);

		et = this.exportTaskRepository.save(et);
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		this.exportFolderAndFiles();

		et.setEndTime(new Date());
//		List<FolderDetails> folderDetailsList = this.folderDetailsRepository.findByStatus(ProjectConstants.COMPLETE);
//		if (folderDetailsList != null) {
//			for (FolderDetails folderDetails : folderDetailsList) {
//				if (folderDetails.getStatus().equals(ProjectConstants.COMPLETE)) {
//					System.out.println("Say Hii");
//					et.setStatus("COMPLETED");
//				} else {
//					et.setStatus("FAILED");
//				}
//			}
//		}
		et = this.exportTaskRepository.save(et);
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	public void exportFolderAndFiles() {

		File dir = new File(mainDir);
		File[] files = dir.listFiles();
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		files = dir.listFiles(fileFilter);

		int count = getTotalFilesInDirectory(dir);
		for (File file : files) {
			FolderDetails folderDetails = new FolderDetails();
			folderDetails.setFolderName(file.getName());
			folderDetails.setFiles((long) countFilesInDirectory(file));
			folderDetails.setTotalFile((long) count);
			long folderSize = getFolderSize(file);
			folderDetails.setSize(folderSize / 1024);
			folderDetails.setStatus("COMPLETED");
			folderDetails = this.folderDetailsRepository.save(folderDetails);
		}

	}

	public long getFolderSize(File folder) {
		long size = 0;
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				size += file.length();
			} else {
				size += getFolderSize(file);
			}
		}
		return size;
	}

	public int countFilesInDirectory(File dir) {
		int count = 0;
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				count++;
			} else if (file.isDirectory()) {
				count += countFilesInDirectory(file);
			}
		}
		return count;
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

}
