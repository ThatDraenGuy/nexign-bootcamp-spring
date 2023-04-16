package com.draen.service.cdr.parser;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

@Service
public interface CdrParserService {
    CdrEntry parse(String s);
}
