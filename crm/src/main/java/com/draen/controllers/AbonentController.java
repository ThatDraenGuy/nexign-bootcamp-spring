package com.draen.controllers;

import com.draen.annotation.validation.groups.Create;
import com.draen.data.payment.dto.PaymentDto;
import com.draen.data.payment.service.PaymentService;
import com.draen.data.report.dto.ReportDto;
import com.draen.data.report.service.ReportService;
import com.draen.domain.entity.Report;
import com.draen.domain.entity.Payment;
import com.draen.service.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("/abonent")
public class AbonentController {
    private final ReportService reportService;
    private final PaymentService paymentService;
    private final Mapper<Report, ReportDto> reportMapper;
    private final Mapper<Payment, PaymentDto> paymentMapper;

    public AbonentController(ReportService reportService, PaymentService paymentService,
                             Mapper<Report, ReportDto> reportMapper, Mapper<Payment, PaymentDto> paymentMapper) {
        this.reportService = reportService;
        this.paymentService = paymentService;
        this.reportMapper = reportMapper;
        this.paymentMapper = paymentMapper;
    }

    @PatchMapping("/pay")
    public ResponseEntity<PaymentDto> pay(@RequestBody @Validated({Create.class}) PaymentDto paymentDto) {
        return ResponseEntity.ok(paymentMapper.toDto(paymentService.create(paymentMapper.toEntity(paymentDto))));
    }

    @GetMapping("/report/{numberPhone}")
    public ResponseEntity<ReportDto> getReport(@PathVariable String numberPhone) {
        return ResponseEntity.ok(reportMapper.toDto(reportService.find(numberPhone)));
    }
}
