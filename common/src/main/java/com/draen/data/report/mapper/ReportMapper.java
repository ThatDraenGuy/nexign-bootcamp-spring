package com.draen.data.report.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.data.client.service.ClientService;
import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.CallSummary;
import com.draen.domain.entity.Report;
import com.draen.service.Mapper;

@MapperService
public class ReportMapper implements Mapper<Report, ReportDto> {
    private final ClientService clientService;
    private final MonetaryUnitService monetaryUnitService;
    private final Mapper<CallSummary, CallSummaryDto> callSummaryMapper;

    public ReportMapper(ClientService clientService, MonetaryUnitService monetaryUnitService,
                        Mapper<CallSummary, CallSummaryDto> callSummaryMapper) {
        this.clientService = clientService;
        this.monetaryUnitService = monetaryUnitService;
        this.callSummaryMapper = callSummaryMapper;
    }

    @Override
    public Report toEntity(ReportDto dto) {
        return new Report(
                dto.getId(),
                clientService.findActiveByNumber(dto.getPhoneNumber()),
                dto.getTotalCost(),
                dto.getTotalMinutes(),
                callSummaryMapper.toEntities(dto.getPayload()),
                monetaryUnitService.findByCode(dto.getMonetaryUnitCode())
        );
    }

    @Override
    public ReportDto toDto(Report entity) {
        return new ReportDto(
                entity.getId(),
                entity.getClient().getPhoneNumber(),
                entity.getClient().getTariff().getCode(),
                callSummaryMapper.toDtos(entity.getRecords()),
                entity.getTotalMinutes(),
                entity.getTotalCost(),
                entity.getMonetaryUnit().getCode()
        );
    }
}
