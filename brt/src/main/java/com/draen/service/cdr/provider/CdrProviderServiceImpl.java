package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
import com.draen.service.cdr.parser.CdrParserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

@Service
public class CdrProviderServiceImpl implements CdrProviderService {
    @Value("${custom.files.cdr-url}")
    private String sourceURL;
    private BufferedReader reader;

    private final CdrParserService cdrParserService;


    public CdrProviderServiceImpl(CdrParserService cdrParserService) {
        this.cdrParserService = cdrParserService;
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
    public Stream<CdrEntry> getEntries() {
        init();
        return reader.lines().map(cdrParserService::parse);
    }
}
