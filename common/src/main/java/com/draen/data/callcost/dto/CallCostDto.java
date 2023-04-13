package com.draen.data.callcost.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CallCostDto {
    private double cost;
    private String monetaryUnit;
}
