package com.csvvisualizer.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.csvvisualizer.entity.CsvData;
import com.csvvisualizer.service.CsvService;
import com.csvvisualizer.service.CsvServiceHelper;

@RestController
@RequestMapping("/api/csv")
@CrossOrigin
public class CsvController {

	@Autowired
	  CsvService fileService;

	  @PostMapping("/upload")
	  public ResponseEntity<CsvResponseMessenger> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (CsvServiceHelper.hasCSVFormat(file)) {
	      try {
	        fileService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        
	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/api/csv/download/")
	                .path(file.getOriginalFilename())
	                .toUriString();

	        return ResponseEntity.status(HttpStatus.OK).body(new CsvResponseMessenger(message,fileDownloadUri));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new CsvResponseMessenger(message,""));
	      }
	    }

	    message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CsvResponseMessenger(message,""));
	  }

	  @GetMapping("/viewall")
	  public ResponseEntity<List<CsvData>> getAllTutorials() {
	    try {
	      List<CsvData> tutorials = fileService.getAllTutorials();

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/download/{fileName:.+}")
	  public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
	    InputStreamResource file = new InputStreamResource(fileService.load());

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
	        .contentType(MediaType.parseMediaType("application/csv"))
	        .body(file);
	  }
}
