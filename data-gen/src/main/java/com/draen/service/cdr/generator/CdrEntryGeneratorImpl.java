package com.draen.service.cdr.generator;

import com.draen.data.DateTimePair;
import com.draen.domain.model.CdrEntry;
import com.draen.service.calltype.generator.CallTypeGenerator;
import com.draen.service.datetime.generator.LocalDateTimeGenerator;
import com.draen.service.phonenumber.generator.PhoneNumberGenerator;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class CdrEntryGeneratorImpl implements CdrEntryGenerator {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final CallTypeGenerator callTypeGenerator;
    private final PhoneNumberGenerator phoneNumberGenerator;
    private final LocalDateTimeGenerator localDateTimeGenerator;

    public CdrEntryGeneratorImpl(CallTypeGenerator callTypeGenerator, PhoneNumberGenerator phoneNumberGenerator,
                                 LocalDateTimeGenerator localDateTimeGenerator) {
        this.callTypeGenerator = callTypeGenerator;
        this.phoneNumberGenerator = phoneNumberGenerator;
        this.localDateTimeGenerator = localDateTimeGenerator;
    }

    @Override
    public CdrEntry generateEntry() {
        DateTimePair dateTimePair = localDateTimeGenerator.generateDateTime();
        return new CdrEntry(
                callTypeGenerator.generateCallType(),
                phoneNumberGenerator.generateNumber(),
                dateTimePair.getStart().format(formatter),
                dateTimePair.getEnd().format(formatter)
        );
    }
}
