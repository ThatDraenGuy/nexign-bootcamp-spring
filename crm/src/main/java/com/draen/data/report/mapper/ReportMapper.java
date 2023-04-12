package com.draen.data.report.mapper;

import com.draen.data.cdr.dto.CdrDto;
import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Cdr;
import com.draen.domain.entity.Report;
import com.draen.exception.UnimplementedException;
import com.draen.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper implements Mapper<Report, ReportDto> {
    private final Mapper<Cdr, CdrDto> cdrMapper;

    public ReportMapper(Mapper<Cdr, CdrDto> cdrMapper) {
        this.cdrMapper = cdrMapper;
    }

    @Override
    public Report toEntity(ReportDto dto) {
        throw new UnimplementedException();
    }

    @Override
    public ReportDto toDto(Report entity) {
        return new ReportDto(
                entity.getId(),
                entity.getClient().getPhoneNumber(),
                entity.getClient().getTariff().getCode(),
                cdrMapper.toDtos(entity.getRecords()),
                entity.getTotalCost(),
                entity.getMonetaryUnit().getName()
        );
    }
}
