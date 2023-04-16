package com.draen.data.payment.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.data.payment.dto.PaymentDto;
import com.draen.domain.model.Payment;
import com.draen.mapper.Mapper;

@MapperService
public class PaymentMapper implements Mapper<Payment, PaymentDto> {
    private final MonetaryUnitService monetaryUnitService;

    public PaymentMapper(MonetaryUnitService monetaryUnitService) {
        this.monetaryUnitService = monetaryUnitService;
    }

    @Override
    public Payment toEntity(PaymentDto dto) {
        return new Payment(
                dto.getNumberPhone(),
                dto.getMoney(),
                monetaryUnitService.findByCode(dto.getMonetaryUnit())
        );
    }

    @Override
    public PaymentDto toDto(Payment entity) {
        return new PaymentDto(
                entity.getNumberPhone(),
                entity.getMoney(),
                entity.getMonetaryUnit().getCode()
        );
    }
}
