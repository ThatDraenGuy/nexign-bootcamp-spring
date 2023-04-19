package com.draen.data.report.dto;

import com.draen.annotation.validationgroups.Create;
import com.draen.data.callsummary.dto.CallSummaryDto;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ReportDto {
    @Null(groups = {Create.class})
    private Long id;
    private String phoneNumber;
    private String tariffCode;
    private List<CallSummaryDto> payload;
    private int totalMinutes;
    private double totalCost;
    private String monetaryUnitCode;
}
