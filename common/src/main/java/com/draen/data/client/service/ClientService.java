package com.draen.data.client.service;

import com.draen.domain.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    Client create(Client client);
    Client update(Client client);
    Client findByNumber(String phoneNumber);
    Client findActiveByNumber(String phoneNumber);
    List<Client> findAllByLastBilling();
    Client findRandom();
}
