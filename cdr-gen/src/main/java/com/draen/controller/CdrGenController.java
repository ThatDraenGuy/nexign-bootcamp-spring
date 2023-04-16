package com.draen.controller;

import com.draen.service.CdrGenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CdrGenController {
    private final CdrGenService cdrGenService;

    public CdrGenController(CdrGenService cdrGenService) {
        this.cdrGenService = cdrGenService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Boolean> generate() {
        cdrGenService.generate();
        return ResponseEntity.ok(true);
    }
}
