package com.draen.listener;

import com.draen.data.callcost.dto.CallCostDto;
import com.draen.data.cdrplus.dto.CdrPlusDto;

import com.draen.domain.model.CallCost;
import com.draen.domain.model.CdrPlus;
import com.draen.mapper.Mapper;
import com.draen.service.CostCalculatorService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class CdrPlusListener {
    private final CostCalculatorService costCalculatorService;
    private final Mapper<CdrPlus, CdrPlusDto> cdrPlusMapper;
    private final Mapper<CallCost, CallCostDto> callCostMapper;

    public CdrPlusListener(CostCalculatorService costCalculatorService, Mapper<CdrPlus, CdrPlusDto> cdrPlusMapper,
                           Mapper<CallCost, CallCostDto> callCostMapper) {
        this.costCalculatorService = costCalculatorService;
        this.cdrPlusMapper = cdrPlusMapper;
        this.callCostMapper = callCostMapper;
    }


    @KafkaListener //TODO
    @SendTo
    public CallCostDto tarifficateCdrPlus(@Payload CdrPlusDto cdrPlusDto) {
        CdrPlus cdrPlus = cdrPlusMapper.toEntity(cdrPlusDto);
        CallCost callCost = costCalculatorService.getCost(cdrPlus);
        return callCostMapper.toDto(callCost);
    }
}
