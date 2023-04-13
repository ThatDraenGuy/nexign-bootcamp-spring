package com.draen.data.cdrplus.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.calltype.service.CallTypeService;
import com.draen.data.cdrplus.dto.CdrPlusDto;
import com.draen.data.tariff.service.TariffService;
import com.draen.domain.model.CdrPlus;
import com.draen.mapper.Mapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MapperService
public class CdrPlusMapper implements Mapper<CdrPlus, CdrPlusDto> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final CallTypeService callTypeService;
    private final TariffService tariffService;

    public CdrPlusMapper(CallTypeService callTypeService, TariffService tariffService) {
        this.callTypeService = callTypeService;
        this.tariffService = tariffService;
    }

    @Override
    public CdrPlus toEntity(CdrPlusDto dto) {
        return new CdrPlus(
                callTypeService.findByCode(dto.getCallType()),
                LocalDateTime.parse(dto.getStartTime(), formatter),
                LocalDateTime.parse(dto.getEndTime(), formatter),
                Duration.ofSeconds(dto.getDuration()),
                tariffService.findByCode(dto.getTariffCode())
        );
    }

    @Override
    public CdrPlusDto toDto(CdrPlus entity) {
        return new CdrPlusDto(
                entity.getCallType().getCode(),
                entity.getStartTime().format(formatter),
                entity.getEndTime().format(formatter),
                entity.getDuration().toSeconds(),
                entity.getTariff().getCode()
        );
    }
}
