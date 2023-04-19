package com.draen.data.callsummary.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.data.calltype.service.CallTypeService;
import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.domain.entity.CallSummary;
import com.draen.service.Mapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MapperService
public class CallSummaryMapper implements Mapper<CallSummary, CallSummaryDto> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private final CallTypeService callTypeService;
    private final MonetaryUnitService monetaryUnitService;

    public CallSummaryMapper(CallTypeService callTypeService, MonetaryUnitService monetaryUnitService) {
        this.callTypeService = callTypeService;
        this.monetaryUnitService = monetaryUnitService;
    }

    @Override
    public CallSummary toEntity(CallSummaryDto dto) {
        return new CallSummary(
                dto.getId(),
                callTypeService.findByCode(dto.getCallTypeCode()),
                LocalDateTime.parse(dto.getStartTime(), formatter),
                LocalDateTime.parse(dto.getEndTime(), formatter),
                Duration.parse(dto.getDuration()),
                dto.getCost(),
                monetaryUnitService.findByCode(dto.getMonetaryUnitCode())
        );
    }

    @Override
    public CallSummaryDto toDto(CallSummary entity) {
        return new CallSummaryDto(
                entity.getId(),
                entity.getCallType().getCode(),
                formatter.format(entity.getStartTime()),
                formatter.format(entity.getEndTime()),
                entity.getDuration().toString(),
                entity.getCost(),
                entity.getMonetaryUnit().getCode()
        );
    }
}
