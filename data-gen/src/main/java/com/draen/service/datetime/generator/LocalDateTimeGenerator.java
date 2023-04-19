package com.draen.service.datetime.generator;

import com.draen.data.DateTimePair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface LocalDateTimeGenerator {
    DateTimePair generateDateTime();
}
