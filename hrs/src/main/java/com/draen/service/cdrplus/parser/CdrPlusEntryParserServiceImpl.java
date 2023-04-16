package com.draen.service.cdrplus.parser;

import com.draen.data.calltype.service.CallTypeService;
import com.draen.data.tariff.service.TariffService;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.exception.ParseException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class CdrPlusEntryParserServiceImpl implements CdrPlusEntryParserService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final CallTypeService callTypeService;
    private final TariffService tariffService;

    public CdrPlusEntryParserServiceImpl(CallTypeService callTypeService, TariffService tariffService) {
        this.callTypeService = callTypeService;
        this.tariffService = tariffService;
    }

    @Override
    public CdrPlusEntry parse(String s) throws ParseException {
        String[] split = s.split(", ");
        if (split.length != 6) throw new ParseException("Cdr plus parse exception");
        return new CdrPlusEntry(
                callTypeService.findByCode(split[0]),
                split[1], //TODO
                LocalDateTime.parse(split[2], formatter),
                LocalDateTime.parse(split[3], formatter),
                Duration.parse(split[4]),
                tariffService.findByCode(split[5])
        );
    }
}
