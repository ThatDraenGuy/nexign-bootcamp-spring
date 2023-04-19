package com.draen.service.report.provider;

import com.draen.data.report.dto.ReportDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportProvider {
    List<ReportDto> getReports();
}
