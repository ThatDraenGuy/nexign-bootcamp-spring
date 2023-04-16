package com.draen.domain.model;


import com.draen.domain.entity.CallType;
import com.draen.domain.entity.Tariff;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CdrPlusEntry {
    private CallType callType;

    private String phoneNumber;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Duration duration;

    private Tariff tariff;
}
