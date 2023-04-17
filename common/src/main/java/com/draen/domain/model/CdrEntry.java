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
    private String callTypeCode;
    private String phoneNumber;
    private String startTime;
    private String endTime;
}
