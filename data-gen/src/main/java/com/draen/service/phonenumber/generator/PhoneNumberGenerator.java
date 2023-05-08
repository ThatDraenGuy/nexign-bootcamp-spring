package com.draen.service.phonenumber.generator;

import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberGenerator {
    String generateNewNumber();
}
