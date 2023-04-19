package com.draen.data.payment.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.client.service.ClientService;
import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.data.payment.dto.PaymentDto;
import com.draen.domain.entity.Payment;
import com.draen.service.Mapper;

@MapperService
public class PaymentMapper implements Mapper<Payment, PaymentDto> {
    private final ClientService clientService;
    private final MonetaryUnitService monetaryUnitService;

    public PaymentMapper(ClientService clientService, MonetaryUnitService monetaryUnitService) {
        this.clientService = clientService;
        this.monetaryUnitService = monetaryUnitService;
    }

    @Override
    public Payment toEntity(PaymentDto dto) {
        return new Payment(
                dto.getId(),
                clientService.findByNumber(dto.getNumberPhone()),
                dto.getMoney(),
                monetaryUnitService.findByCode(dto.getMonetaryUnit())
        );
    }

    @Override
    public PaymentDto toDto(Payment entity) {
        return new PaymentDto(
                entity.getId(),
                entity.getClient().getPhoneNumber(),
                entity.getMoney(),
                entity.getMonetaryUnit().getCode()
        );
    }
}
