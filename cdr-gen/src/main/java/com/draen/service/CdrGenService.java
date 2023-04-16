package com.draen.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CdrGenService {
    @Value("${custom.files.cdr-url}")
    private String cdrUrl;

    public void generate() {

    }
}
