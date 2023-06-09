package com.draen.service.cdrplus.provider;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.Deserializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CdrPlusProviderImpl implements CdrPlusProvider {
    @Value("${custom.url.cdr-plus}")
    private String sourceURL;
    private BufferedReader reader;

    private final Deserializer<CdrPlusEntry> cdrPlusEntryDeserializer;

    public CdrPlusProviderImpl(Deserializer<CdrPlusEntry> cdrPlusEntryDeserializer) {
        this.cdrPlusEntryDeserializer = cdrPlusEntryDeserializer;
    }

    public void init() throws IOException {
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
                try {
                    add(entries, entry.get());
                } catch (Exception ignored) {}
            }
            return entries;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void add(List<CdrPlusEntry> entries, @Validated CdrPlusEntry entry) {
        entries.add(entry);
    }
}
