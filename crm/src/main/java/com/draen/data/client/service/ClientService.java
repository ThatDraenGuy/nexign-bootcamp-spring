package com.draen.data.client.service;

import com.draen.data.payment.dto.PaymentDto;
import com.draen.domain.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Client create(Client client);
    Client update(PaymentDto paymentDto);
}
