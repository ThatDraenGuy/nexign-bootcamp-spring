package com.draen.data.callsummary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CallSummaryDto {
    private String callTypeCode;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;
    private String monetaryUnitCode;
}
