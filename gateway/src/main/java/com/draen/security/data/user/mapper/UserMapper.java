package com.draen.security.data.user.mapper;

import com.draen.annotation.service.MapperService;
import com.draen.data.client.service.ClientService;
import com.draen.security.data.user.dto.UserDto;
import com.draen.security.data.user.entity.User;
import com.draen.service.Mapper;

import java.util.HashSet;

@MapperService
public class UserMapper implements Mapper<User, UserDto> {
    private final ClientService clientService;

    public UserMapper(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public User toEntity(UserDto dto) {
        return new User(
                dto.getId(),
                clientService.findByNumber(dto.getUsername()),
                dto.getPassword(),
                new HashSet<>()
        );
    }

    @Override
    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getClient().getPhoneNumber(),
                null,
                entity.getRoles()
        );
    }
}
