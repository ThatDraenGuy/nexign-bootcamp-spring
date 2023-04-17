package com.draen.service.cdr.deserializer;

import com.draen.domain.model.CdrEntry;
import com.draen.exception.ParseException;
import com.draen.service.Deserializer;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class CdrDeserializer implements Deserializer<CdrEntry> {

    @Override
    public Optional<CdrEntry> deserialize(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) return Optional.empty();

        String[] split = line.split(", ");
        if (split.length != 4) throw new ParseException("Cdr parse exception: " + line); //TODO
        return Optional.of(new CdrEntry(
                split[0],
                split[1],
                split[2],
                split[3]
        ));
    }
}
