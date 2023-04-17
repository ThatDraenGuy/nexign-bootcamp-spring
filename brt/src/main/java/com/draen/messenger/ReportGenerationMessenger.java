package com.draen.messenger;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.message.ServiceRequest;
import com.draen.message.ServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportGenerationMessenger {
    @Value("${custom.jms.destination.report-generation}")
    private String destination;

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public ReportGenerationMessenger(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }


    public ServiceResponse requestReportGeneration() {
        return jmsMessagingTemplate.convertSendAndReceive(destination, new ServiceRequest(destination),
                ServiceResponse.class);
    }
}
