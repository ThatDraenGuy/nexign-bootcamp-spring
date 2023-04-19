package com.draen.service.datetime.generator;

import com.draen.data.DateTimePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

@Service
public class LocalDateTimeGeneratorImpl implements LocalDateTimeGenerator {
    @Value("${custom.boundaries.datetime.lower}")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime lowerBound;

    @Value("${custom.boundaries.datetime.higher}")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime higherBound;

    @Value("${custom.boundaries.datetime.max-duration}")
    private Duration maxDuration;

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
