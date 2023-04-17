package com.draen.listener;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceRequest;
import com.draen.message.ServiceResponse;
import com.draen.service.report.generator.ReportGeneratorService;
import com.draen.service.cdrplus.provider.CdrPlusProviderService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public ServiceResponse generateReports(@Payload ServiceRequest request) {
        try {
            List<CdrPlusEntry> entries = cdrPlusProviderService.getCdrPlus();
            reportGeneratorService.generateReports(entries);
            return new ServiceResponse(ResponseStatus.SUCCESS, "Successfully generated reports");
        } catch (Exception e) {
            return new ServiceResponse(ResponseStatus.CONSUMER_ERROR, "hrs error: " + e.getMessage());
        }
    }
}
