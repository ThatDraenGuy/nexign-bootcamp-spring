package com.draen.data.callsummary.dto;

import com.draen.annotation.validation.groups.Create;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CallSummaryDto {
    @Null(groups = {Create.class})
    private Long id;
    private String callTypeCode;
    private String startTime;
    private String endTime;
    private String duration;
    private double cost;
}
