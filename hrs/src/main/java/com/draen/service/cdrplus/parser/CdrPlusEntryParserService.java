package com.draen.service.cdrplus.parser;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.exception.ParseException;
import org.springframework.stereotype.Service;

@Service
public interface CdrPlusEntryParserService {
    CdrPlusEntry parse(String s) throws ParseException;
}
