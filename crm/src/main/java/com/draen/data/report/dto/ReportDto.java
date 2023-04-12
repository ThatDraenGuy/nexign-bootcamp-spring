package com.draen.data.report.dto;

import com.draen.data.cdr.dto.CdrDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ReportDto {
    private Long id;
    private String numberPhone;
    private String tariffCode;
    private List<CdrDto> payload;
    private double totalCost;
    private String monetaryUnit;
}
