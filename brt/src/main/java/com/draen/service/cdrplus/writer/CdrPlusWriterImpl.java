package com.draen.service.cdrplus.writer;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.Serializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CdrPlusWriterImpl implements CdrPlusWriter {
    @Value("${custom.files.cdr-plus}")
    private String cdrPlusUrl;
    private BufferedWriter writer;

    private final Serializer<CdrPlusEntry> serializer;

    public CdrPlusWriterImpl(Serializer<CdrPlusEntry> serializer) {
        this.serializer = serializer;
    }


    public void init() throws IOException {
        File file = new File(cdrPlusUrl);
        if (! file.exists()) file.createNewFile();
        writer = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void writeEntry(CdrPlusEntry entry) {
        try {
            serializer.serialize(entry, writer);
            writer.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
