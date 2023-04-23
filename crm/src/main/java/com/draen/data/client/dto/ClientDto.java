package com.draen.data.client.dto;

import com.draen.annotation.validation.LikePattern;
import com.draen.annotation.validation.groups.Create;
import com.draen.annotation.validation.groups.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto {
    @Null(groups = {Create.class, Update.class})
    private Long id;

    @NotBlank
    @LikePattern(regexp = "${custom.regex.phone-number.regexp}")
    private String phoneNumber;

    @Null(groups = {Update.class})
    private double balance;

    @NotNull(groups = {Create.class, Update.class})
    private String tariffCode;
}
