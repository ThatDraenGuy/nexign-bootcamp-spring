package com.draen.data.report.dto;

import com.draen.data.callsummary.dto.CallSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class ReportDto {
    private String phoneNumber;
    private double totalCost;
    private int totalMinutes;
    private List<CallSummaryDto> records;
    private String monetaryUnitCode;
}
