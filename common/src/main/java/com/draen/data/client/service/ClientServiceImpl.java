package com.draen.data.client.service;

import com.draen.data.client.repository.ClientRepository;
import com.draen.domain.entity.Client;
import com.draen.domain.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final TransactionTemplate transactionTemplate;

    public ClientServiceImpl(ClientRepository repository, TransactionTemplate transactionTemplate) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Client create(Client client) {
        return transactionTemplate.execute(status -> {
            return repository.save(client);
        });
    }

    @Override
    public Client update(Payment paymentDto) {
        return transactionTemplate.execute(status -> {
            Client client = repository.findByPhoneNumber(paymentDto.getNumberPhone())
                    .orElseThrow();
            int newMoney = client.getBalance() + paymentDto.getMoney();
            client.setBalance(newMoney);
            return repository.save(client);
        });

    }

    @Override
    public Client findActiveByNumber(String phoneNumber) {
        return transactionTemplate.execute(status -> {
            return repository.findByPhoneNumberEqualsAndBalanceGreaterThan(phoneNumber, 0).orElseThrow();
        });
    }
}
