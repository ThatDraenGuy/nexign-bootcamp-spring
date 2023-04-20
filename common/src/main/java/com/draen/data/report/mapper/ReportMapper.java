package com.draen.data.report.mapper;

import com.draen.annotation.service.MapperService;
import com.draen.data.billingoperation.service.BillingOperationService;
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
    private final BillingOperationService billingOperationService;
    private final Mapper<CallSummary, CallSummaryDto> callSummaryMapper;

    public ReportMapper(ClientService clientService, MonetaryUnitService monetaryUnitService,
                        BillingOperationService billingOperationService,
                        Mapper<CallSummary, CallSummaryDto> callSummaryMapper) {
        this.clientService = clientService;
        this.monetaryUnitService = monetaryUnitService;
        this.billingOperationService = billingOperationService;
        this.callSummaryMapper = callSummaryMapper;
    }

    @Override
    public Report toEntity(ReportDto dto) {
        return new Report(
                dto.getId(),
                dto.getBillingOperationNumber() != null
                        ? billingOperationService.findByNumber(dto.getBillingOperationNumber())
                        : null,
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
                entity.getBillingOperation().getOperationNumber(),
                entity.getClient().getPhoneNumber(),
                entity.getClient().getTariff().getCode(),
                callSummaryMapper.toDtos(entity.getRecords()),
                entity.getTotalMinutes(),
                entity.getTotalCost(),
                entity.getMonetaryUnit().getCode()
        );
    }
}
