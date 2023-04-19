package com.draen.service.report.generator;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.cdrplus.entryhandler.EntryHandler;
import com.draen.service.report.writer.ReportWriter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportGeneratorImpl implements ReportGenerator {
    private final EntryHandler entryHandler;
    private final ReportWriter reportWriter;

    public ReportGeneratorImpl(EntryHandler entryHandler, ReportWriter reportWriter) {
        this.entryHandler = entryHandler;
        this.reportWriter = reportWriter;
    }

    @Override
    public void generateReports(List<CdrPlusEntry> entries) {
        Map<String, ReportDto> reports = new HashMap<>();
        entries.forEach(entry -> entryHandler.handleEntry(reports, entry));
        for (ReportDto report : reports.values()) {
            reportWriter.writeReport(report);
        }
    }
}
