package com.draen.service.report.provider;

import com.draen.data.report.dto.ReportDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public interface ReportProviderService {
    List<ReportDto> getReports();
}
