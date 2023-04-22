package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
import com.draen.service.Deserializer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CdrProviderImpl implements CdrProvider {
    @Value("${custom.url.cdr}")
    private String sourceURL;
    private BufferedReader reader;

    private final Deserializer<CdrEntry> cdrDeserializer;

    public CdrProviderImpl(Deserializer<CdrEntry> cdrDeserializer) {
        this.cdrDeserializer = cdrDeserializer;
    }

    public void init() throws IOException {
        UrlResource source = new UrlResource(sourceURL);
        reader = new BufferedReader(new InputStreamReader(source.getInputStream()));
    }

    @Override
    public List<CdrEntry> getEntries() {
        List<CdrEntry> cdrEntries = new ArrayList<>();
        try {
            while (true) {
                Optional<CdrEntry> cdrEntry = cdrDeserializer.deserialize(reader);
                if (cdrEntry.isEmpty()) break;
                try {
                    add(cdrEntries, cdrEntry.get());
                } catch (Exception ignored) {}
            }
            return cdrEntries;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void add(List<CdrEntry> entries, @Validated CdrEntry entry) {
        entries.add(entry);
    }
}
