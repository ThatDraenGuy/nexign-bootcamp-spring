package com.draen.service.cdrplus.deserializer;

import com.draen.domain.model.CdrPlusEntry;
import com.draen.exception.ParseException;
import com.draen.service.Deserializer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

@Service
public class CdrPlusEntryDeserializer implements Deserializer<CdrPlusEntry> {

    @Override
    public Optional<CdrPlusEntry> deserialize(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) return Optional.empty();

        String[] split = line.split(", ");
        if (split.length != 6) throw new ParseException("Cdr plus parse exception");
        return Optional.of(new CdrPlusEntry(
                split[0], //TODO
                split[1], //TODO
                split[2],
                split[3],
                Duration.parse(split[4]),
                split[5]
        ));
    }
}
