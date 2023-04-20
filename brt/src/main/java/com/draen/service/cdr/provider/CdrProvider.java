package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface CdrProvider {
    void init() throws IOException;
    List<CdrEntry> getEntries();
}
