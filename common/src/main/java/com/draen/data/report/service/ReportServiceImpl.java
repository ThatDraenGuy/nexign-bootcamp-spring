package com.draen.data.report.service;

import com.draen.data.callsummary.repository.CallSummaryRepository;
import com.draen.data.client.repository.ClientRepository;
import com.draen.data.report.repository.ReportRepository;
import com.draen.domain.entity.Report;
import com.draen.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final CallSummaryRepository callSummaryRepository;
    private final ClientRepository clientRepository;
    private final TransactionTemplate transactionTemplate;

    public ReportServiceImpl(ReportRepository reportRepository, CallSummaryRepository callSummaryRepository,
                             ClientRepository clientRepository, TransactionTemplate transactionTemplate) {
        this.reportRepository = reportRepository;
        this.callSummaryRepository = callSummaryRepository;
        this.clientRepository = clientRepository;
        this.transactionTemplate = transactionTemplate;
    }

//    @Override
//    public void saveAll(Iterable<Report> reports) {
//        transactionTemplate.execute(status -> {
//            for (Report report : reports) {
//                callSummaryRepository.saveAll(report.getRecords());
//                report.getClient().setBalance(report.getClient().getBalance() - report.getTotalCost());
//                clientRepository.save(report.getClient());
//            }
//            reportRepository.saveAll(reports);
//            return null;
//        });
//    }

    @Override
    public Report find(String phoneNumber) {
        return transactionTemplate.execute(status -> {
            return reportRepository
                    .findTopByClient_PhoneNumberOrderByBillingOperation_OperationNumberDesc(phoneNumber)
                    .orElseThrow(() ->
                    new NotFoundException("No such report"));
        });
    }
}
