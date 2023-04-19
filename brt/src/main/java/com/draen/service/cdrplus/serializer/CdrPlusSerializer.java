package com.draen.service.cdrplus.serializer;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.Serializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
public class CdrPlusSerializer implements Serializer<CdrPlusEntry> {

    @Override
    public void serialize(CdrPlusEntry item, Writer writer) throws IOException {
        String str = item.getCallTypeCode() + ", " +
                item.getPhoneNumber() + ", " +
                item.getStartTime() + ", " +
                item.getEndTime() + ", " +
                item.getDuration().toString() + ", " +
                item.getTariffCode() + '\n';
        writer.write(str);
    }
}
