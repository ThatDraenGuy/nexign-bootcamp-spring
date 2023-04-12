package com.draen.data.report.service;

import com.draen.domain.entity.Report;
import com.draen.domain.repository.ReportRepository;
import org.springframework.transaction.support.TransactionTemplate;

public class ReportServiceImpl implements ReportService {
    private final ReportRepository repository;
    private final TransactionTemplate transactionTemplate;

    public ReportServiceImpl(ReportRepository repository, TransactionTemplate transactionTemplate) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Report find(String phoneNumber) {
        return transactionTemplate.execute(status -> {
            return repository.findByClient_PhoneNumber(phoneNumber).orElseThrow();
        });
    }
}
