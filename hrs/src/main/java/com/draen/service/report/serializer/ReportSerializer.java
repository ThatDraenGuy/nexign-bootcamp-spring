package com.draen.service.report.serializer;

import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.data.report.dto.ReportDto;
import com.draen.service.Serializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
public class ReportSerializer implements Serializer<ReportDto> {
    private final Serializer<CallSummaryDto> callSummarySerializer;

    public ReportSerializer(Serializer<CallSummaryDto> callSummarySerializer) {
        this.callSummarySerializer = callSummarySerializer;
    }

    @Override
    public void serialize(ReportDto item, Writer writer) throws IOException {
        String str = item.getPhoneNumber() + ", " +
                item.getTariffCode() + ", " +
                item.getTotalMinutes() + ", " +
                item.getTotalCost() + ", " +
                item.getMonetaryUnitCode() + ", " +
                item.getPayload().size() + "\n";
        writer.write(str);
        for (CallSummaryDto callSummary : item.getPayload()) {
            callSummarySerializer.serialize(callSummary, writer);
            writer.write('\n');
        }
    }
}
