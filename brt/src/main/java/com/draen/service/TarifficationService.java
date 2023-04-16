package com.draen.service;

import com.draen.data.report.dto.ReportDto;
import com.draen.data.report.service.ReportService;
import com.draen.domain.entity.Report;
import com.draen.mapper.Mapper;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceResponse;
import com.draen.messenger.ReportGenerationMessenger;
import com.draen.service.cdr.provider.CdrProviderService;
import com.draen.service.cdrplus.creator.CdrPlusCreatorService;
import com.draen.service.cdrplus.writer.CdrPlusWriterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TarifficationService {
    private final CdrProviderService cdrProviderService;
    private final CdrPlusCreatorService cdrPlusCreatorService;
    private final CdrPlusWriterService cdrPlusWriterService;
    private final ReportGenerationMessenger reportGenerationMessenger;
    private final ReportService reportService;
    private final Mapper<Report, ReportDto> reportMapper;

    public TarifficationService(CdrProviderService cdrProviderService, CdrPlusCreatorService cdrPlusCreatorService,
                                CdrPlusWriterService cdrPlusWriterService,
                                ReportGenerationMessenger reportGenerationMessenger, ReportService reportService,
                                Mapper<Report, ReportDto> reportMapper) {
        this.cdrProviderService = cdrProviderService;
        this.cdrPlusCreatorService = cdrPlusCreatorService;
        this.cdrPlusWriterService = cdrPlusWriterService;
        this.reportGenerationMessenger = reportGenerationMessenger;
        this.reportService = reportService;
        this.reportMapper = reportMapper;
    }

    public ServiceResponse<List<ReportDto>> tarifficate() {
        cdrProviderService.getEntries()
                .map(cdrPlusCreatorService::createEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(cdrPlusWriterService::writeEntry);
        ServiceResponse<List<ReportDto>> response = reportGenerationMessenger.requestReportGeneration();

        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            reportService.saveAll(reportMapper.toEntities(response.getPayload()));
        }
        return response;
    }
}
