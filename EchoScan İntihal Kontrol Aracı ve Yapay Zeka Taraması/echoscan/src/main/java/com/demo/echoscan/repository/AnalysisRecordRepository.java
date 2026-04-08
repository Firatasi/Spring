package com.demo.echoscan.repository;

import com.demo.echoscan.entity.AnalysisRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRecordRepository extends JpaRepository<AnalysisRecord, Long> {

}
