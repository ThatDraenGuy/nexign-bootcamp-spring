package com.draen.data.payment.dto;

import com.draen.annotation.validationgroups.Create;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@AllArgsConstructor
public class PaymentDto {
    @Null(groups = {Create.class})
    private Long id;
    @NotNull
    private String numberPhone;
    @NotNull
    @Range(min = 1)
    private int money;
    private String monetaryUnit;
}
