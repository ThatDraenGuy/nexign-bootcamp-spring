package com.draen.service.report.provider;

import com.draen.data.report.dto.ReportDto;
import com.draen.service.Deserializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportProviderImpl implements ReportProvider {
    @Value("${custom.url.reports}")
    private String sourceURL;
    private BufferedReader reader;

    private final Deserializer<ReportDto> reportDeserializer;

    public ReportProviderImpl(Deserializer<ReportDto> reportDeserializer) {
        this.reportDeserializer = reportDeserializer;
    }

    public void init() throws IOException {
        UrlResource source = new UrlResource(sourceURL);
        reader = new BufferedReader(new InputStreamReader(source.getInputStream()));
    }

    @Override
    public List<ReportDto> getReports() {
        List<ReportDto> reports = new ArrayList<>();
        try {
            while (true) {
                Optional<ReportDto> report = reportDeserializer.deserialize(reader);
                if (report.isEmpty()) break;
                reports.add(report.get());
            }
            return reports;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
