package com.csvvisualizer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvData {
	
	 @Id
	 @Column(name = "id")
	 private Long id;

	 @Column(name = "title")
	 private String title;

	 @Column(name = "description")
	 private String description;

	 @Column(name = "published")
	 private boolean published;
	
}
