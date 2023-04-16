package com.draen.data.cdrplus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CdrPlusEntryDto {
    private String callType;
    private String phoneNumber;
    private String startTime;
    private String endTime;
    private String duration;
    private String tariffCode;
}
