package com.draen.service;

import com.draen.data.billingoperation.repository.BillingOperationRepository;
import com.draen.data.callsummary.repository.CallSummaryRepository;
import com.draen.data.client.repository.ClientRepository;
import com.draen.data.report.repository.ReportRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Service
public class DatabaseService {
    private final ClientRepository clientRepository;
    private final ReportRepository reportRepository;
    private final CallSummaryRepository callSummaryRepository;
    private final BillingOperationRepository billingOperationRepository;
    private final TransactionTemplate transactionTemplate;
    private final DataGenerationService dataGenerationService;
    private final DataSource dataSource;

    private final ResourceDatabasePopulator databasePopulator =
            new ResourceDatabasePopulator(false, false, "UTF-8",
                    new ClassPathResource("data.sql"));
    private final ResourceDatabasePopulator databaseDropper =
            new ResourceDatabasePopulator(false, false, "UTF-8",
                    new ClassPathResource("truncate.sql"));

    public DatabaseService(ClientRepository clientRepository, ReportRepository reportRepository,
                           CallSummaryRepository callSummaryRepository,
                           BillingOperationRepository billingOperationRepository,
                           TransactionTemplate transactionTemplate, DataGenerationService dataGenerationService,
                           DataSource dataSource) {
        this.clientRepository = clientRepository;
        this.reportRepository = reportRepository;
        this.callSummaryRepository = callSummaryRepository;
        this.billingOperationRepository = billingOperationRepository;
        this.transactionTemplate = transactionTemplate;
        this.dataGenerationService = dataGenerationService;
        this.dataSource = dataSource;
    }


    public void populate() {
        databasePopulator.execute(dataSource);
        dataGenerationService.generateClients();
    }

    public void truncate() {
        databaseDropper.execute(dataSource);
    }

    public void reset() {
        truncate();
        populate();
    }
}
