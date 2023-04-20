package com.draen.data.payment.dto;

import com.draen.annotation.validation.LikePattern;
import com.draen.annotation.validation.groups.Create;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    @LikePattern(regexp = "${custom.regex.phone-number.regexp}")
    private String numberPhone;
    @NotNull
    @Range(min = 1)
    private int money;
    private String monetaryUnit;
}
