package com.draen.domain.model;

import com.draen.domain.entity.MonetaryUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
    private String numberPhone;
    private int money;
    private MonetaryUnit monetaryUnit;
}
