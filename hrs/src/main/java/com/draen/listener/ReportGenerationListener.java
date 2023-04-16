package com.draen.listener;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceRequest;
import com.draen.message.ServiceResponse;
import com.draen.service.reportgenerator.ReportGeneratorService;
import com.draen.service.cdrplus.provider.CdrPlusProviderService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class ReportGenerationListener {
    private final ReportGeneratorService reportGeneratorService;
    private final CdrPlusProviderService cdrPlusProviderService;

    public ReportGenerationListener(ReportGeneratorService reportGeneratorService, CdrPlusProviderService cdrPlusProviderService) {
        this.reportGeneratorService = reportGeneratorService;
        this.cdrPlusProviderService = cdrPlusProviderService;
    }


    @JmsListener(destination = "${custom.jms.destination.report-generation}")
    @SendTo("${custom.jms.destination.report-generation}")
    public ServiceResponse<List<ReportDto>> generateReports(@Payload ServiceRequest request) {
        try {
            Stream<CdrPlusEntry> entries = cdrPlusProviderService.getCdrPlus();
            return new ServiceResponse<>(ResponseStatus.SUCCESS, "Successfully generated reports",
                    reportGeneratorService.generateReports(entries));
        } catch (Exception e) {
            return new ServiceResponse<>(ResponseStatus.CONSUMER_ERROR, e.getMessage(), null);
        }
    }
}
