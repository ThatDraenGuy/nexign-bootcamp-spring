package com.draen.domain.model;


import com.draen.annotation.validation.LikeDateTime;
import com.draen.annotation.validation.LikeEither;
import com.draen.annotation.validation.LikePattern;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.time.DurationMin;

import java.time.Duration;

@Getter
@Setter
@AllArgsConstructor
public class CdrPlusEntry {
    @LikeEither(options = {
            "${custom.constants.call-type-code.outgoing}",
            "${custom.constants.call-type-code.incoming}"
    })
    private String callTypeCode;

    @NotBlank
    @LikePattern(regexp = "${custom.regex.phone-number.regexp}")
    private String phoneNumber;

    @LikeDateTime(pattern = "${custom.constants.cdr.date-time-format}")
    private String startTime;

    @LikeDateTime(pattern = "${custom.constants.cdr.date-time-format}")
    private String endTime;

    @DurationMin(seconds = 1)
    private Duration duration;

    private String tariffCode;
}
