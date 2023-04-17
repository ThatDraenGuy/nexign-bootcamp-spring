package com.draen.data.cdrplus.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.calltype.service.CallTypeService;
import com.draen.data.cdrplus.dto.CdrPlusEntryDto;
import com.draen.data.tariff.service.TariffService;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.Mapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MapperService
public class CdrPlusEntryMapper implements Mapper<CdrPlusEntry, CdrPlusEntryDto> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final CallTypeService callTypeService;
    private final TariffService tariffService;

    public CdrPlusEntryMapper(CallTypeService callTypeService, TariffService tariffService) {
        this.callTypeService = callTypeService;
        this.tariffService = tariffService;
    }

    @Override
    public CdrPlusEntry toEntity(CdrPlusEntryDto dto) {
        return new CdrPlusEntry(
                callTypeService.findByCode(dto.getCallType()),
                dto.getPhoneNumber(),
                LocalDateTime.parse(dto.getStartTime(), formatter),
                LocalDateTime.parse(dto.getEndTime(), formatter),
                Duration.parse(dto.getDuration()),
                tariffService.findByCode(dto.getTariffCode())
        );
    }

    @Override
    public CdrPlusEntryDto toDto(CdrPlusEntry entity) {
        return new CdrPlusEntryDto(
                entity.getCallType().getCode(),
                entity.getPhoneNumber(),
                entity.getStartTime().format(formatter),
                entity.getEndTime().format(formatter),
                entity.getDuration().toString(),
                entity.getTariff().getCode()
        );
    }
}
