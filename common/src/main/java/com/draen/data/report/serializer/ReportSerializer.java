package com.draen.data.report.serializer;

import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.data.report.dto.ReportDto;
import com.draen.exception.ParseException;
import com.draen.service.Serializer;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReportSerializer implements Serializer<ReportDto> {
    private final Serializer<CallSummaryDto> callSummarySerializer;

    public ReportSerializer(Serializer<CallSummaryDto> callSummarySerializer) {
        this.callSummarySerializer = callSummarySerializer;
    }

    @Override
    public void serialize(ReportDto item, Writer writer) throws IOException {
        String str = item.getPhoneNumber() + ", " +
                item.getTotalCost() + ", " +
                item.getTotalMinutes() + ", " +
                item.getMonetaryUnitCode() + ", " +
                item.getRecords().size() + "\n";
        writer.write(str);
    }

    @Override
    public Optional<ReportDto> deserialize(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) return Optional.empty();
        String[] split = line.split(", ");
        if (split.length != 5) throw new ParseException("Report parse exception: " + line);
        int callSummariesNum = Integer.parseInt(split[4]);
        ReportDto report = new ReportDto(
                split[0],
                Double.parseDouble(split[1]),
                Integer.parseInt(split[2]),
                new ArrayList<>(callSummariesNum),
                split[3]
        );
        for (int i = 0; i < callSummariesNum; i++) {
            Optional<CallSummaryDto> callSummary = callSummarySerializer.deserialize(reader);
            if (callSummary.isEmpty()) throw new ParseException("Report parse exception: wrong call summaries num");
            report.getRecords().add(callSummary.get());
        }
        return Optional.of(report);
    }
}
