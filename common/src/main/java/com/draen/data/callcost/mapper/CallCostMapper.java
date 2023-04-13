package com.draen.data.callcost.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.callcost.dto.CallCostDto;
import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.domain.model.CallCost;
import com.draen.mapper.Mapper;

@MapperService
public class CallCostMapper implements Mapper<CallCost, CallCostDto> {
    private final MonetaryUnitService monetaryUnitService;

    public CallCostMapper(MonetaryUnitService monetaryUnitService) {
        this.monetaryUnitService = monetaryUnitService;
    }

    @Override
    public CallCost toEntity(CallCostDto dto) {
        return new CallCost(
                dto.getCost(),
                monetaryUnitService.findByCode(dto.getMonetaryUnit())
        );
    }

    @Override
    public CallCostDto toDto(CallCost entity) {
        return new CallCostDto(
                entity.getCost(),
                entity.getMonetaryUnit().getCode()
        );
    }
}
