package com.draen.domain.model;

import com.draen.domain.entity.MonetaryUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CallCost {
    private double cost;
    private MonetaryUnit monetaryUnit;
}
