package com.draen.data.cdr.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.cdr.dto.CdrDto;
import com.draen.domain.entity.Cdr;
import com.draen.exception.UnimplementedException;
import com.draen.mapper.Mapper;

import java.time.format.DateTimeFormatter;

@MapperService
public class CdrMapper implements Mapper<Cdr, CdrDto> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public Cdr toEntity(CdrDto dto) {
        throw new UnimplementedException();
    }

    @Override
    public CdrDto toDto(Cdr entity) {
        return new CdrDto(
                entity.getCallType().getCode(),
                entity.getStartTime().format(formatter),
                entity.getEndTime().format(formatter),
                entity.getDuration(),
                entity.getCost()
        );
    }
}
