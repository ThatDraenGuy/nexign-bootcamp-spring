package com.draen.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TarifficationListener {

    @KafkaListener(topics = "${custom.topics.tariffication}")
    @SendTo
    public void tarifficate() {

    }
}
