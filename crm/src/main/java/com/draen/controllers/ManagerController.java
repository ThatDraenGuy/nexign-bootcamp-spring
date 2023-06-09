package com.draen.controllers;

import com.draen.annotation.validation.groups.Create;
import com.draen.annotation.validation.groups.Update;
import com.draen.data.client.dto.ClientDto;
import com.draen.data.client.dto.ClientShortDto;
import com.draen.data.client.service.ClientService;
import com.draen.domain.entity.Client;
import com.draen.message.ResponseStatus;
import com.draen.message.ServiceResponse;
import com.draen.service.Mapper;
import com.draen.messenger.TarifficationMessenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ClientService clientService;
    private final Mapper<Client, ClientDto> clientMapper;
    private final Mapper<Client, ClientShortDto> clientShortMapper;
    private final TarifficationMessenger tarifficationMessenger;

    public ManagerController(ClientService clientService, Mapper<Client, ClientDto> clientMapper,
                             Mapper<Client, ClientShortDto> clientShortMapper,
                             TarifficationMessenger tarifficationMessenger) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
        this.clientShortMapper = clientShortMapper;
        this.tarifficationMessenger = tarifficationMessenger;
    }

    @PatchMapping("/changeTariff")
    public ResponseEntity<ClientDto> changeTariff(@RequestBody @Validated({Update.class}) ClientDto clientDto) {
        return ResponseEntity.ok(clientMapper.toDto(clientService.update(clientMapper.toEntity(clientDto))));
    }

    @PostMapping("/abonent")
    public ResponseEntity<ClientDto> createClient(@RequestBody @Validated({Create.class}) ClientDto clientDto) {
        return ResponseEntity.ok(clientMapper.toDto(clientService.create(clientMapper.toEntity(clientDto))));
    }

    @PatchMapping("/billing")
    public ResponseEntity<List<ClientShortDto>> tarifficate() {
        ServiceResponse response = tarifficationMessenger.requestTariffication();
        if (response.getStatus().equals(ResponseStatus.SUCCESS)) {
            return ResponseEntity.ok(clientShortMapper.toDtos(clientService.findAllByLastBilling()));
        } else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, response.getMessage());
    }
}
