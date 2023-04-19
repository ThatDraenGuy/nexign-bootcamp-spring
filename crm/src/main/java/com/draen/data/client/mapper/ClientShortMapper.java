package com.draen.data.client.mapper;

import com.draen.data.client.dto.ClientShortDto;
import com.draen.domain.entity.Client;
import com.draen.exception.UnimplementedException;
import com.draen.service.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ClientShortMapper implements Mapper<Client, ClientShortDto> {
    @Override
    public Client toEntity(ClientShortDto dto) {
        throw new UnimplementedException();
    }

    @Override
    public ClientShortDto toDto(Client entity) {
        return new ClientShortDto(
                entity.getPhoneNumber(),
                entity.getBalance()
        );
    }
}
