package com.draen.data.report.service;

import com.draen.domain.entity.Report;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    Report find(String phoneNumber);
}
