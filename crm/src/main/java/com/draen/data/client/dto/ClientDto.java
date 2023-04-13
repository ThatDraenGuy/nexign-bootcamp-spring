package com.draen.data.client.dto;

//import com.draen.annotation.validationgroups.Create;
//import com.draen.annotation.validationgroups.Update;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientDto {
//    @Null(groups = {Create.class, Update.class})
    private Long id;
//    @NotNull
    private String numberPhone;
    private int money;
    private String tariffCode;
}
