package com.draen.service.report.deserializer;

import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.data.report.dto.ReportDto;
import com.draen.exception.ParseException;
import com.draen.service.Deserializer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReportDeserializer implements Deserializer<ReportDto> {
    private final Deserializer<CallSummaryDto> callSummaryDeserializer;

    public ReportDeserializer(Deserializer<CallSummaryDto> callSummaryDeserializer) {
        this.callSummaryDeserializer = callSummaryDeserializer;
    }

    @Override
    public Optional<ReportDto> deserialize(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) return Optional.empty();
        String[] split = line.split(", ");
        if (split.length != 6) throw new ParseException("Report parse exception: " + line);
        int callSummariesNum = Integer.parseInt(split[5]);
        ReportDto report = new ReportDto(
                null,
                split[0],
                split[1],
                new ArrayList<>(callSummariesNum),
                Integer.parseInt(split[2]),
                Double.parseDouble(split[3]),
                split[4]
        );
        for (int i = 0; i < callSummariesNum; i++) {
            Optional<CallSummaryDto> callSummary = callSummaryDeserializer.deserialize(reader);
            if (callSummary.isEmpty()) throw new ParseException("Report parse exception: wrong call summaries num");
            report.getPayload().add(callSummary.get());
        }
        return Optional.of(report);
    }
}
