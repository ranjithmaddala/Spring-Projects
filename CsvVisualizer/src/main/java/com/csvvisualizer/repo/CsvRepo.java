package com.csvvisualizer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csvvisualizer.entity.CsvData;

public interface CsvRepo extends JpaRepository<CsvData, Long>{

}
