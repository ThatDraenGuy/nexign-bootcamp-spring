package com.draen.service.report.generator;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;


@Service
public interface ReportGeneratorService {
    void generateReports(Stream<CdrPlusEntry> entries);
}
