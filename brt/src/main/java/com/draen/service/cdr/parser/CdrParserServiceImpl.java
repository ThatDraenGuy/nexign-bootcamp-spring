package com.draen.service.cdr.parser;

import com.draen.data.calltype.service.CallTypeService;
import com.draen.domain.model.CdrEntry;
import com.draen.exception.ParseException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CdrParserServiceImpl implements CdrParserService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final CallTypeService callTypeService;

    public CdrParserServiceImpl(CallTypeService callTypeService) {
        this.callTypeService = callTypeService;
    }

    @Override
    public CdrEntry parse(String s) {
        String[] items = s.split(", ");
        if (items.length != 4) throw new ParseException("Cdr parse exception: " + s); //TODO
        return new CdrEntry(
                callTypeService.findByCode(items[0]),
                items[1], //TODO
                LocalDateTime.parse(items[2], formatter),
                LocalDateTime.parse(items[3], formatter)
        );
    }
}
