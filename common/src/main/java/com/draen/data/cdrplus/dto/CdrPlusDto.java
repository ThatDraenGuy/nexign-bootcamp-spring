package com.draen.data.cdrplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CdrPlusDto {
    private String callType;
    private String startTime;
    private String endTime;
    private long duration;
    private String tariffCode;
}
