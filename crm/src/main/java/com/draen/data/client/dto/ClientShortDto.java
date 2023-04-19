package com.draen.data.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientShortDto {
    private String phoneNumber;
    private int balance;
}
