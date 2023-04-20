package com.draen.service.report.generator;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceResponse;
import com.draen.service.cdrplus.entryhandler.EntryHandler;
import com.draen.service.cdrplus.provider.CdrPlusProvider;
import com.draen.service.report.writer.ReportWriter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportGeneratorImpl implements ReportGenerator {
    private final CdrPlusProvider cdrPlusProvider;
    private final EntryHandler entryHandler;
    private final ReportWriter reportWriter;

    public ReportGeneratorImpl(CdrPlusProvider cdrPlusProvider, EntryHandler entryHandler, ReportWriter reportWriter) {
        this.cdrPlusProvider = cdrPlusProvider;
        this.entryHandler = entryHandler;
        this.reportWriter = reportWriter;
    }

    @Override
    public void generateReports() throws IOException {
        cdrPlusProvider.init();
        reportWriter.init();

        List<CdrPlusEntry> entries = cdrPlusProvider.getCdrPlus();
        Map<String, ReportDto> reports = new HashMap<>();
        entries.forEach(entry -> entryHandler.handleEntry(reports, entry));
        for (ReportDto report : reports.values()) {
            reportWriter.writeReport(report);
        }
    }
}
