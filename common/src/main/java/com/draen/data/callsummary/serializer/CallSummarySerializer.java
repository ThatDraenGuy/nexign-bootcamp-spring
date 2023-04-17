package com.draen.data.callsummary.serializer;

import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.exception.ParseException;
import com.draen.service.Serializer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

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

    @Override
    public Optional<CallSummaryDto> deserialize(BufferedReader reader) throws IOException{
        String line = reader.readLine();
        if (line == null) return Optional.empty();

        String[] split = line.split(", ");
        if (split.length != 6) throw new ParseException("Call summary parse exception: " + line);

        return Optional.of(new CallSummaryDto(
                split[0],
                split[1],
                split[2],
                split[3],
                Double.parseDouble(split[4]),
                split[5]
        ));
    }
}
