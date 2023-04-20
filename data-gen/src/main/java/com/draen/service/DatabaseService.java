package com.draen.service;

import com.draen.data.billingoperation.repository.BillingOperationRepository;
import com.draen.data.callsummary.repository.CallSummaryRepository;
import com.draen.data.client.repository.ClientRepository;
import com.draen.data.report.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class DatabaseService {
    private final ClientRepository clientRepository;
    private final ReportRepository reportRepository;
    private final CallSummaryRepository callSummaryRepository;
    private final BillingOperationRepository billingOperationRepository;
    private final TransactionTemplate transactionTemplate;
    private final DataGenerationService dataGenerationService;

    public DatabaseService(ClientRepository clientRepository, ReportRepository reportRepository,
                           CallSummaryRepository callSummaryRepository,
                           BillingOperationRepository billingOperationRepository,
                           TransactionTemplate transactionTemplate, DataGenerationService dataGenerationService) {
        this.clientRepository = clientRepository;
        this.reportRepository = reportRepository;
        this.callSummaryRepository = callSummaryRepository;
        this.billingOperationRepository = billingOperationRepository;
        this.transactionTemplate = transactionTemplate;
        this.dataGenerationService = dataGenerationService;
    }


    public void populate() {
        dataGenerationService.generateClients();
    }

    public void truncate() {
        transactionTemplate.execute(status -> {
            reportRepository.truncateTable();
            clientRepository.truncateTable();
            callSummaryRepository.truncateTable();
            billingOperationRepository.truncateTable();
            return null;
        });
    }

    public void reset() {
        truncate();
        populate();
    }
}
