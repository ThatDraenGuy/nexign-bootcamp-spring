package com.draen.service.report.writer;

import com.draen.data.report.dto.ReportDto;
import org.springframework.stereotype.Service;

@Service
public interface ReportWriterService {
    void writeReport(ReportDto report);
}
