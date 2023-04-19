package com.draen.service.cdr.serializer;

import com.draen.domain.model.CdrEntry;
import com.draen.service.Serializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;

@Service
public class CdrSerializer implements Serializer<CdrEntry> {

    @Override
    public void serialize(CdrEntry item, Writer writer) throws IOException {
        String str = item.getCallTypeCode() + ", " +
                item.getPhoneNumber() + ", " +
                item.getStartTime() + ", " +
                item.getEndTime() + '\n';
        writer.write(str);
    }
}
