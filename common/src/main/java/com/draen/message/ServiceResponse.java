package com.draen.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse<T> {
    private ResponseStatus status;
    private String message;
    private T payload;
}
