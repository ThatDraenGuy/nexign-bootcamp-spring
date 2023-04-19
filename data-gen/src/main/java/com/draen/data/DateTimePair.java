package com.draen.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DateTimePair {
    private final LocalDateTime start;
    private final LocalDateTime end;
}
