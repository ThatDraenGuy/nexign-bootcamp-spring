package com.draen.service.report.writer;

import com.draen.data.report.dto.ReportDto;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ReportWriter {
    void init() throws IOException;
    void writeReport(ReportDto report);
}
