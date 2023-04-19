package com.draen.service.cdrplus.writer;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

@Service
public interface CdrPlusWriter {
    void writeEntry(CdrPlusEntry entry);
}
