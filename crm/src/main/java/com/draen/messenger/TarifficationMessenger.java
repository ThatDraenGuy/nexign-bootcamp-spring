package com.draen.messenger;


import com.draen.message.ServiceRequest;
import com.draen.message.ServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;



@Service
public class TarifficationMessenger {
    @Value("${custom.jms.destination.tariffication}")
    private String destination;

    private final JmsMessagingTemplate jmsMessagingTemplate;

    public TarifficationMessenger(JmsMessagingTemplate jmsMessagingTemplate) {
        this.jmsMessagingTemplate = jmsMessagingTemplate;
    }


    public ServiceResponse<Void> requestTariffication() {
        return jmsMessagingTemplate.convertSendAndReceive(destination, new ServiceRequest(destination),
                ServiceResponse.class);
    }
}
