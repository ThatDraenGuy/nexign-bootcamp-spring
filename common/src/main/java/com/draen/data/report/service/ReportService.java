package com.draen.data.report.service;

import com.draen.domain.entity.Report;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public interface ReportService {
    void saveAll(Iterable<Report> reports);
    Report find(String phoneNumber);
}
