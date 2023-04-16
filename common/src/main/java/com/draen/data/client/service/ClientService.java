package com.draen.data.client.service;

import com.draen.domain.entity.Client;
import com.draen.domain.model.Payment;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Client create(Client client);
    Client update(Payment paymentDto);
    Client findActiveByNumber(String phoneNumber);
}
