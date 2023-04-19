package com.draen.service;

import com.draen.data.report.dto.ReportDto;
import com.draen.data.report.service.ReportService;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrEntry;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceResponse;
import com.draen.messenger.ReportGenerationMessenger;
import com.draen.service.cdr.provider.CdrProvider;
import com.draen.service.cdrplus.creator.CdrPlusCreator;
import com.draen.service.cdrplus.writer.CdrPlusWriter;
import com.draen.service.report.provider.ReportProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarifficationService {
    private final CdrProvider cdrProvider;
    private final CdrPlusCreator cdrPlusCreator;
    private final CdrPlusWriter cdrPlusWriter;
    private final ReportGenerationMessenger reportGenerationMessenger;
    private final ReportService reportService;
    private final ReportProvider reportProvider;
    private final Mapper<Report, ReportDto> reportMapper;

    public TarifficationService(CdrProvider cdrProvider, CdrPlusCreator cdrPlusCreator,
                                CdrPlusWriter cdrPlusWriter,
                                ReportGenerationMessenger reportGenerationMessenger, ReportService reportService,
                                ReportProvider reportProvider, Mapper<Report, ReportDto> reportMapper) {
        this.cdrProvider = cdrProvider;
        this.cdrPlusCreator = cdrPlusCreator;
        this.cdrPlusWriter = cdrPlusWriter;
        this.reportGenerationMessenger = reportGenerationMessenger;
        this.reportService = reportService;
        this.reportProvider = reportProvider;
        this.reportMapper = reportMapper;
    }

    public ServiceResponse tarifficate() {
        List<CdrEntry> cdrEntries = cdrProvider.getEntries();
        for (CdrEntry entry : cdrEntries) {
            Optional<CdrPlusEntry> cdrPlusEntry = cdrPlusCreator.createEntry(entry);
            cdrPlusEntry.ifPresent(cdrPlusWriter::writeEntry);
        }
        ServiceResponse response = reportGenerationMessenger.requestReportGeneration();

        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            reportService.saveAll(reportMapper.toEntities(reportProvider.getReports()));
        }
        return response;
    }
}
