package com.draen.data.client.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.client.dto.ClientDto;
import com.draen.data.tariff.service.TariffService;
import com.draen.domain.entity.Client;
import com.draen.service.Mapper;

@MapperService
public class ClientMapper implements Mapper<Client, ClientDto> {
    private final TariffService tariffService;

    public ClientMapper(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public Client toEntity(ClientDto dto) {
        return new Client(
                null,
                dto.getNumberPhone(),
                dto.getBalance(),
                tariffService.findByCode(dto.getTariffCode())
        );
    }

    @Override
    public ClientDto toDto(Client entity) {
        return new ClientDto(
                entity.getId(),
                entity.getPhoneNumber(),
                entity.getBalance(),
                entity.getTariff().getCode()
        );
    }
}
