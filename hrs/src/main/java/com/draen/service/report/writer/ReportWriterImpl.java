package com.draen.service.report.writer;

import com.draen.data.report.dto.ReportDto;
import com.draen.service.Serializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ReportWriterImpl implements ReportWriter {
    @Value("${custom.files.reports}")
    private String reportsUrl;
    private BufferedWriter writer;

    private final Serializer<ReportDto> reportSerializer;

    public ReportWriterImpl(Serializer<ReportDto> reportSerializer) {
        this.reportSerializer = reportSerializer;
    }

    public void init() throws IOException {
        File file = new File(reportsUrl);
        if (! file.exists()) file.createNewFile();
        writer = new BufferedWriter(new FileWriter(file));
    }
    @Override
    public void writeReport(ReportDto report) {
        try {
            reportSerializer.serialize(report, writer);
            writer.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
