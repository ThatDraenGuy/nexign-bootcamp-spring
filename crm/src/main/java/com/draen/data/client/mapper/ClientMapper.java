package com.draen.data.client.mapper;

import com.draen.data.client.dto.ClientDto;
import com.draen.domain.entity.Client;
import com.draen.domain.entity.Tariff;
import com.draen.exception.UnimplementedException;
import com.draen.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
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
