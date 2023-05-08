package com.draen.service.datetime.generator;

import com.draen.data.DateTimePair;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Random;

@Service
public class LocalDateTimeGeneratorImpl implements LocalDateTimeGenerator {

    private LocalDateTime lowerBound = LocalDateTime.of(2023, Month.JANUARY, 1, 0, 0, 0);

    private LocalDateTime higherBound = LocalDateTime.now();

    private Duration maxDuration = Duration.ofHours(1);

    private final Random random = new Random();

    @Override
    public DateTimePair generateDateTime() {
        long min = lowerBound.toEpochSecond(ZoneOffset.UTC);
        long max = higherBound.toEpochSecond(ZoneOffset.UTC);

        long start = random.nextLong(min, max);
        long end = random.nextLong(start, Math.min(start + maxDuration.toSeconds(), max));
        LocalDateTime startDateTime = LocalDateTime.ofEpochSecond(start, 0, ZoneOffset.UTC);
        LocalDateTime endDateTime = LocalDateTime.ofEpochSecond(end, 0, ZoneOffset.UTC);
        return new DateTimePair(startDateTime, endDateTime);
    }
}
