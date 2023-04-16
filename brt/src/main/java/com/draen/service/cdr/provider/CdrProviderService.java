package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public interface CdrProviderService {
    Stream<CdrEntry> getEntries();
}
