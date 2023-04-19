package com.draen.controller;

import com.draen.service.DataGenerationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController("/generate")
public class DataGenerationController {
    private final DataGenerationService dataGenerationService;

    public DataGenerationController(DataGenerationService dataGenerationService) {
        this.dataGenerationService = dataGenerationService;
    }

    @PostMapping("/cdr")
    public ResponseEntity<Boolean> generateCdr() {
        try {
            dataGenerationService.generateCdrs();
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<Boolean> generateClients() {
        try {
            dataGenerationService.generateClients();
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
