package com.draen.controllers;

import com.draen.annotation.validationgroups.Create;
import com.draen.annotation.validationgroups.Update;
import com.draen.data.client.dto.ClientDto;
import com.draen.data.client.service.ClientService;
import com.draen.domain.entity.Client;
import com.draen.mapper.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/manager")
public class ManagerController {
    private final ClientService clientService;
    private final Mapper<Client, ClientDto> clientMapper;

    public ManagerController(ClientService clientService, Mapper<Client, ClientDto> clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @PatchMapping("/changeTariff")
    public ResponseEntity<ClientDto> changeTariff(@RequestBody @Validated({Update.class}) ClientDto clientDto) {
        //TODO
        return null;
    }

    @PostMapping("/abonent")
    public ResponseEntity<ClientDto> createClient(@RequestBody @Validated({Create.class}) ClientDto clientDto) {
        return ResponseEntity.ok(clientMapper.toDto(clientService.create(clientMapper.toEntity(clientDto))));
    }

    @PatchMapping("/billing")
    public ResponseEntity<List<ClientDto>> tarifficate() {
        //TODO
        return null;
    }
}
