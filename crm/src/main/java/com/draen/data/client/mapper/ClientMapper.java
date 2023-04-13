package com.draen.data.client.mapper;

import com.draen.annotation.MapperService;
import com.draen.data.client.dto.ClientDto;
import com.draen.domain.entity.Client;
import com.draen.exception.UnimplementedException;
import com.draen.mapper.Mapper;

@MapperService
public class ClientMapper implements Mapper<Client, ClientDto> {
    @Override
    public Client toEntity(ClientDto dto) {
        throw new UnimplementedException();
    }

    @Override
    public ClientDto toDto(Client entity) {
        return new ClientDto(
                entity.getId(),
                entity.getPhoneNumber(),
                entity.getMoney(),
                entity.getTariff().getCode()
        );
    }
}
