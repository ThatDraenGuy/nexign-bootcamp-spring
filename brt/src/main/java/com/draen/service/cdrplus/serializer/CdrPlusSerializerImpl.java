package com.draen.service.cdrplus.serializer;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class CdrPlusSerializerImpl implements CdrPlusSerializer {
    @Override
    public String serializeEntry(CdrPlusEntry entry) {
        return entry.getCallTypeCode() + ", " +
                entry.getPhoneNumber() + ", " +
                entry.getStartTime() + ", " +
                entry.getEndTime() + ", " +
                entry.getDuration().toString() + ", " +
                entry.getTariffCode();
    }
}
