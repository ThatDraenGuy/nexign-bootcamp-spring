package com.draen.service.cdrplus.writer;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CdrPlusWriterService {
    void writeEntry(CdrPlusEntry entry);
}
