package com.draen.service.cdrplus.writer;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.cdrplus.serializer.CdrPlusSerializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CdrPlusWriterImpl implements CdrPlusWriter {
    @Value("${custom.files.cdr-plus-url}")
    private String cdrPlusUrl;
    private BufferedWriter writer;

    private final CdrPlusSerializer serializer;

    public CdrPlusWriterImpl(CdrPlusSerializer serializer) {
        this.serializer = serializer;
    }

    @PostConstruct
    private void init() throws IOException {
        File file = new File(cdrPlusUrl.replace("file:", ""));
        writer = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void writeEntry(CdrPlusEntry entry) {
        String str = serializer.serializeEntry(entry);
        try {
            writer.append(str).append("\n");
            writer.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
