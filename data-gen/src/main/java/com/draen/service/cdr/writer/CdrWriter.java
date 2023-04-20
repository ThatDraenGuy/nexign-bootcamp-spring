package com.draen.service.cdr.writer;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CdrWriter {
    void init() throws IOException;
    void writeEntry(CdrEntry entry);
}
