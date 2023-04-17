package com.draen.service.cdrplus.provider;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.Deserializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CdrPlusProviderServiceImpl implements CdrPlusProviderService {
    @Value("${custom.files.cdr-plus-url}")
    private String sourceURL;
    private BufferedReader reader;

    private final Deserializer<CdrPlusEntry> cdrPlusEntryDeserializer;

    public CdrPlusProviderServiceImpl(Deserializer<CdrPlusEntry> cdrPlusEntryDeserializer) {
        this.cdrPlusEntryDeserializer = cdrPlusEntryDeserializer;
    }

    @PostConstruct
    private void init() throws IOException {
        UrlResource source = new UrlResource(sourceURL);
        reader = new BufferedReader(new InputStreamReader(source.getInputStream()));
    }

    @Override
    public List<CdrPlusEntry> getCdrPlus() {
        List<CdrPlusEntry> entries = new ArrayList<>();
        try {
            while (true) {
                Optional<CdrPlusEntry> entry = cdrPlusEntryDeserializer.deserialize(reader);
                if (entry.isEmpty()) break;
                entries.add(entry.get());
            }
            return entries;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
