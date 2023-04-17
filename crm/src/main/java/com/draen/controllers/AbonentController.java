package com.draen.controllers;

import com.draen.data.client.dto.ClientDto;
import com.draen.data.client.service.ClientService;
import com.draen.data.payment.dto.PaymentDto;
import com.draen.data.report.dto.ReportDto;
import com.draen.data.report.service.ReportService;
import com.draen.domain.entity.Client;
import com.draen.domain.entity.Report;
import com.draen.domain.model.Payment;
import com.draen.service.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("/abonent")
public class AbonentController {
    private final ClientService clientService;
    private final ReportService reportService;
    private final Mapper<Client, ClientDto> clientMapper;
    private final Mapper<Report, ReportDto> reportMapper;
    private final Mapper<Payment, PaymentDto> paymentMapper;

    public AbonentController(ClientService clientService, ReportService reportService,
                             Mapper<Client, ClientDto> clientMapper, Mapper<Report, ReportDto> reportMapper,
                             Mapper<Payment, PaymentDto> paymentMapper) {
        this.clientService = clientService;
        this.reportService = reportService;
        this.clientMapper = clientMapper;
        this.reportMapper = reportMapper;
        this.paymentMapper = paymentMapper;
    }

    @PatchMapping("/pay")
    public ResponseEntity<ClientDto> pay(@RequestBody @Validated PaymentDto paymentDto) {
        return ResponseEntity.ok(clientMapper.toDto(clientService.update(paymentMapper.toEntity(paymentDto))));
    }

    @GetMapping("/report/{numberPhone}")
    public ResponseEntity<ReportDto> getReport(@PathVariable String numberPhone) {
        return ResponseEntity.ok(reportMapper.toDto(reportService.find(numberPhone)));
    }
}
