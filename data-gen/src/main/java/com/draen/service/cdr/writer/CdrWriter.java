package com.draen.service.cdr.writer;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

@Service
public interface CdrWriter {
    void writeEntry(CdrEntry entry);
}
