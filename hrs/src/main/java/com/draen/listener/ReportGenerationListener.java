package com.draen.listener;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceRequest;
import com.draen.message.ServiceResponse;
import com.draen.service.report.generator.ReportGenerator;
import com.draen.service.cdrplus.provider.CdrPlusProvider;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportGenerationListener {
    private final ReportGenerator reportGenerator;

    public ReportGenerationListener(ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }


    @JmsListener(destination = "${custom.jms.destination.report-generation}")
    @SendTo("${custom.jms.destination.report-generation}")
    public ServiceResponse generateReports(@Payload ServiceRequest request) {
        try {
            reportGenerator.generateReports();
            return new ServiceResponse(ResponseStatus.SUCCESS, "Successfully generated reports");
        } catch (Exception e) {
            return new ServiceResponse(ResponseStatus.CONSUMER_ERROR, "hrs error: " + e.getMessage());
        }
    }
}
