package com.draen.service.report.generator;

import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public interface ReportGenerator {
    void generateReports() throws IOException;
}
