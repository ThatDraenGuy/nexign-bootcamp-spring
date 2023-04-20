package com.draen.data.billingoperation.service;

import com.draen.data.billingoperation.repository.BillingOperationRepository;
import com.draen.data.client.repository.ClientRepository;
import com.draen.domain.entity.BillingOperation;
import com.draen.domain.entity.CallSummary;
import com.draen.domain.entity.Client;
import com.draen.domain.entity.Report;
import com.draen.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;


@Service
public class BillingOperationServiceImpl implements BillingOperationService {
    private final BillingOperationRepository billingOperationRepository;
    private final ClientRepository clientRepository;
    private final TransactionTemplate transactionTemplate;

    public BillingOperationServiceImpl(BillingOperationRepository billingOperationRepository,
                                       ClientRepository clientRepository, TransactionTemplate transactionTemplate) {
        this.billingOperationRepository = billingOperationRepository;
        this.clientRepository = clientRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public BillingOperation create(BillingOperation operation) {
        return transactionTemplate.execute(status -> {
            BillingOperation lastOperation = billingOperationRepository.findTopByOrderByOperationNumberDesc();
            int operationNumber = lastOperation != null
                    ? lastOperation.getOperationNumber() + 1
                    : 0;
            operation.setOperationNumber(operationNumber);
            for (Report report : operation.getReports()) {
                report.setBillingOperation(operation);
                Client client = report.getClient();
                client.setBalance(client.getBalance() - report.getTotalCost());
                clientRepository.save(client);
                for (CallSummary callSummary : report.getRecords()) {
                    callSummary.setReport(report);
                }
            }
            return billingOperationRepository.save(operation);
        });
    }

    @Override
    public BillingOperation findByNumber(Integer operationNumber) {
        return transactionTemplate.execute(status -> {
            return billingOperationRepository.findByOperationNumber(operationNumber).orElseThrow(
                    () -> new NotFoundException("No such billing operation")
            );
        });
    }

    @Override
    public BillingOperation findLast() {
        return transactionTemplate.execute(status -> {
            return billingOperationRepository.findTopByOrderByOperationNumberDesc();
        });
    }
}
