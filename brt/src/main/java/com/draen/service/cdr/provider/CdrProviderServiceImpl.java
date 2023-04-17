package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
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
public class CdrProviderServiceImpl implements CdrProviderService {
    @Value("${custom.files.cdr-url}")
    private String sourceURL;
    private BufferedReader reader;

    private final Deserializer<CdrEntry> cdrDeserializer;

    public CdrProviderServiceImpl(Deserializer<CdrEntry> cdrDeserializer) {
        this.cdrDeserializer = cdrDeserializer;
    }

    @PostConstruct
    public void init() {
        try {
            UrlResource source = new UrlResource(sourceURL);
            reader = new BufferedReader(new InputStreamReader(source.getInputStream()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }

    @Override
    public List<CdrEntry> getEntries() {
        List<CdrEntry> cdrEntries = new ArrayList<>();
        try {
            while (true) {
                Optional<CdrEntry> cdrEntry = cdrDeserializer.deserialize(reader);
                if (cdrEntry.isEmpty()) break;
                cdrEntries.add(cdrEntry.get());
            }
            return cdrEntries;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
