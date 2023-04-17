package com.draen.domain.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
public class CdrPlusEntry {
    private String callTypeCode;
    private String phoneNumber;
    private String startTime;
    private String endTime;
    private Duration duration;
    private String tariffCode;
}
