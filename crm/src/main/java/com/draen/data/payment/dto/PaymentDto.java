package com.draen.data.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDto {
    @NotNull
    private String numberPhone;
    @NotNull
    @Range(min = 1)
    private int money;
    private String monetaryUnit;
}
