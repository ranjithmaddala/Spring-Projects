package com.csvvisualizer.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CsvResponseMessenger {
	
	private String message;
	private String fileDownloadUri;
	
}
