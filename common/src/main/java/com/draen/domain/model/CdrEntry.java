package com.draen.domain.model;

import com.draen.domain.entity.CallType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CdrEntry {
    private CallType callType;

    private String phoneNumber;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}
