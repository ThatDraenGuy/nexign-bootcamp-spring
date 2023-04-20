package com.draen.data.client.service;

import com.draen.data.billingoperation.repository.BillingOperationRepository;
import com.draen.data.client.repository.ClientRepository;
import com.draen.domain.entity.BillingOperation;
import com.draen.domain.entity.Client;
import com.draen.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final BillingOperationRepository billingOperationRepository;
    private final TransactionTemplate transactionTemplate;

    public ClientServiceImpl(ClientRepository repository, BillingOperationRepository billingOperationRepository,
                             TransactionTemplate transactionTemplate) {
        this.repository = repository;
        this.billingOperationRepository = billingOperationRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Client create(Client client) {
        return transactionTemplate.execute(status -> {
            return repository.save(client);
        });
    }

    @Override
    public Client update(Client client) {
        return transactionTemplate.execute(status -> {
            Client entity = repository.findByPhoneNumber(client.getPhoneNumber()).orElseThrow(() ->
                    new NotFoundException("No such client"));
            entity.setTariff(client.getTariff());
            return entity;
        });
    }

    @Override
    public Client findByNumber(String phoneNumber) {
        return transactionTemplate.execute(status -> {
            return repository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new NotFoundException("No such client"));
        });
    }

    @Override
    public Client findActiveByNumber(String phoneNumber) {
        return transactionTemplate.execute(status -> {
            return repository.findByPhoneNumberEqualsAndBalanceGreaterThan(phoneNumber, 0).orElseThrow(
                    () -> new NotFoundException("No such client")
            );
        });
    }

    @Override
    public List<Client> findAllByLastBilling() {
        return transactionTemplate.execute(status -> {
            BillingOperation operation = billingOperationRepository.findTopByOrderByOperationNumberDesc();
            return repository.findByReports_BillingOperation_OperationNumber(operation.getOperationNumber());
        });
    }

    @Override
    public Client findRandom() {
        return transactionTemplate.execute(status -> {
            long clientNum = repository.count();
            int pageNumber = (int)(Math.random() * clientNum);
            Page<Client> page = repository.findAll(PageRequest.of(pageNumber, 1));
            if (! page.hasContent()) return null;
            return page.getContent().get(0);
        });
    }
}
