package com.draen.service.phonenumber.generator;

import com.draen.data.client.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PhoneNumberGeneratorImpl implements PhoneNumberGenerator {
    @Value("${custom.chance.existing-phone-number}")
    private double existingNumberChance;

    private final Random random = new Random();

    public String generateNewNumber() {
        long number = random.nextLong(100000000L, 999999999L);
        return "79" + number;
    }
}
