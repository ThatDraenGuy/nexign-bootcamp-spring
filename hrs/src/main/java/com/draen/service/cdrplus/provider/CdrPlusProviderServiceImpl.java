package com.draen.service.cdrplus.provider;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.cdrplus.parser.CdrPlusEntryParserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

@Service
public class CdrPlusProviderServiceImpl implements CdrPlusProviderService {
    @Value("${custom.files.cdr-plus-url}")
    private String sourceURL;
    private BufferedReader reader;

    private final CdrPlusEntryParserService cdrPlusEntryParserService;

    public CdrPlusProviderServiceImpl(CdrPlusEntryParserService cdrPlusEntryParserService) {
        this.cdrPlusEntryParserService = cdrPlusEntryParserService;
    }

    @PostConstruct
    private void init() throws IOException {
        UrlResource source = new UrlResource(sourceURL);
        reader = new BufferedReader(new InputStreamReader(source.getInputStream()));
    }

    @Override
    public Stream<CdrPlusEntry> getCdrPlus() {
        return reader.lines().map(cdrPlusEntryParserService::parse);
    }
}
