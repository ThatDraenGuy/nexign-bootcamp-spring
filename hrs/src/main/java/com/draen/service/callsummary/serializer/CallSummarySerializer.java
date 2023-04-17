package com.draen.service.callsummary.serializer;

import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.service.Serializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
public class CallSummarySerializer implements Serializer<CallSummaryDto> {

    @Override
    public void serialize(CallSummaryDto item, Writer writer) throws IOException {
        String str = item.getCallTypeCode() + ", " +
                item.getStartTime() + ", " +
                item.getEndTime() + ", " +
                item.getDuration() + ", " +
                item.getCost() + ", " +
                item.getMonetaryUnitCode();
        writer.write(str);
    }
}
