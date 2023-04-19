package com.draen.service.report.generator;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ReportGenerator {
    void generateReports(List<CdrPlusEntry> entries);
}
