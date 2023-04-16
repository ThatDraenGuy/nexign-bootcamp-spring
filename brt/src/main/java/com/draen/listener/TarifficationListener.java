package com.draen.listener;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceRequest;
import com.draen.message.ServiceResponse;
import com.draen.service.TarifficationService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TarifficationListener {
    private final TarifficationService tarifficationService;

    public TarifficationListener(TarifficationService tarifficationService) {
        this.tarifficationService = tarifficationService;
    }

    @JmsListener(destination = "${custom.jms.destination.tariffication}")
    @SendTo("${custom.jms.destination.tariffication}")
    public ServiceResponse<Void> tarifficate(@Payload ServiceRequest request) {
        try {
            ServiceResponse<List<ReportDto>> response = tarifficationService.tarifficate();
            if (response.getStatus().equals(ResponseStatus.SUCCESS))
                return new ServiceResponse<>(ResponseStatus.SUCCESS, "Successfully tarifficated clients",
                        null);
            else
                return new ServiceResponse<>(response.getStatus(), response.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResponse<>(ResponseStatus.CONSUMER_ERROR, e.getMessage(), null);
        }
    }
}
