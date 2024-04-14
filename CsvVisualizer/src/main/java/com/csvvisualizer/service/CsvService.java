package com.csvvisualizer.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csvvisualizer.entity.CsvData;
import com.csvvisualizer.repo.CsvRepo;

@Service
public class CsvService {
	
	@Autowired
	  CsvRepo repository;

	  public void save(MultipartFile file) {
	    try {
	      List<CsvData> tutorials = CsvServiceHelper.csvToTutorials(file.getInputStream());
	      repository.saveAll(tutorials);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }

	  public ByteArrayInputStream load() {
	    List<CsvData> tutorials = repository.findAll();

	    ByteArrayInputStream in = CsvServiceHelper.tutorialsToCSV(tutorials);
	    return in;
	  }

	  public List<CsvData> getAllTutorials() {
	    return repository.findAll();
	  }

}
