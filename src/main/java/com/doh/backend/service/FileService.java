package com.doh.backend.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doh.backend.entity.DataSetDto;
import com.doh.backend.entity.File;
import com.doh.backend.repository.FileRepository;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	private static final Logger logger = Logger.getLogger(FileService.class);

	@Value("${csv.file.read.path}")
	private String READ_PATH;

	public List<File> getAllFilesList() {

		List<File> filesList = this.fileRepository.findAll();
		if (filesList != null && filesList.size() > 0) {
			for (File file : filesList) {
				if (file.getFilePath() != null && file.getFilePath() != "") {
//					logger.info("Completed - Record found while calling getAllFilesList {}", file.getFilePath());
				}
			}
			return filesList;
		}
		return null;

	}

	public List<DataSetDto> getDataFromCSV() {
		// Input file which needs to be parsed
		String fileToParse = READ_PATH;
		BufferedReader fileReader = null;
		List<DataSetDto> dataSetDtosList = new ArrayList<>();
		// Delimiter used in CSV file
		final String DELIMITER = ",";
		try {
			String line = "";
			// Create the file reader
			fileReader = new BufferedReader(new FileReader(fileToParse));

			// Read the file line by line
			while ((line = fileReader.readLine()) != null) {
				// Get all tokens available in line
				String[] data = line.split(DELIMITER);
				DataSetDto dataSetDto = new DataSetDto();
				dataSetDto.setId(data[0]);
				dataSetDto.setFilePath(data[1]);

				java.io.File f = new java.io.File(dataSetDto.getFilePath());
				if (f.exists()) {
					logger.info("File is Exists");
				} else {
					logger.error("File does not Exists");
				}

				dataSetDtosList.add(dataSetDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSetDtosList;
	}

}
