package com.draen.service.reportgenerator;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Service
public interface ReportGeneratorService {
    List<ReportDto> generateReports(Stream<CdrPlusEntry> entries);
}
