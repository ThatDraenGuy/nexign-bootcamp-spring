package com.draen.service.report.generator;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.cdrplus.entryhandler.EntryHandlerService;
import com.draen.service.report.writer.ReportWriterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportGeneratorServiceImpl implements ReportGeneratorService {
    private final EntryHandlerService entryHandlerService;
    private final ReportWriterService reportWriterService;

    public ReportGeneratorServiceImpl(EntryHandlerService entryHandlerService, ReportWriterService reportWriterService) {
        this.entryHandlerService = entryHandlerService;
        this.reportWriterService = reportWriterService;
    }

    @Override
    public void generateReports(Stream<CdrPlusEntry> entries) {
        Map<String, ReportDto> reports = new HashMap<>();
        entries.forEach(entry -> entryHandlerService.handleEntry(reports, entry));
        for (ReportDto report : reports.values()) {
            reportWriterService.writeReport(report);
        }
    }
}
