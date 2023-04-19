package com.draen.service.cdr.generator;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

@Service
public interface CdrEntryGenerator {
    CdrEntry generateEntry();
}
