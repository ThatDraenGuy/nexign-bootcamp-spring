package com.draen.service.cdrplus.serializer;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class CdrPlusSerializerImpl implements CdrPlusSerializer {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    @Override
    public String serializeEntry(CdrPlusEntry entry) {
        return entry.getCallType().getCode() + ", " +
                entry.getPhoneNumber() + ", " +
                entry.getStartTime().format(formatter) + ", " +
                entry.getEndTime().format(formatter) + ", " +
                entry.getDuration().toString() + ", " +
                entry.getTariff().getCode();
    }
}
