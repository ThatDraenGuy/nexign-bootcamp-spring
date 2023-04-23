package com.draen.service.cdr.writer;

import com.draen.domain.model.CdrEntry;
import com.draen.service.Serializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CdrWriterImpl implements CdrWriter {
    @Value("${custom.files.cdr}")
    private String cdrUrl;
    private BufferedWriter writer;

    private final Serializer<CdrEntry> serializer;

    public CdrWriterImpl(Serializer<CdrEntry> serializer) {
        this.serializer = serializer;
    }


    public void init() throws IOException {
        File file = new File(cdrUrl);
        if (! file.exists()) file.createNewFile();
        writer = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void writeEntry(CdrEntry entry) {
        try {
            serializer.serialize(entry, writer);
            writer.flush();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
