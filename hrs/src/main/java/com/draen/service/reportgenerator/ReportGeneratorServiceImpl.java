package com.draen.service.reportgenerator;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.cdrplus.entryhandler.EntryHandlerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportGeneratorServiceImpl implements ReportGeneratorService {
    private final EntryHandlerService entryHandlerService;

    public ReportGeneratorServiceImpl(EntryHandlerService entryHandlerService) {
        this.entryHandlerService = entryHandlerService;
    }

    @Override
    public List<ReportDto> generateReports(Stream<CdrPlusEntry> entries) {
        Map<String, ReportDto> reports = new HashMap<>();
        entries.forEach(entry -> entryHandlerService.handleEntry(reports, entry));
        return reports.values().stream().toList();
    }
}
