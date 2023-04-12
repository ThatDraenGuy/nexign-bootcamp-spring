package com.draen.data.cdr.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CdrDto {
    private String callType;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;
}
