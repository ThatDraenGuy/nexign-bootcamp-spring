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

    private final ClientService clientService;

    public PhoneNumberGeneratorImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String generateNumber() {
        if (random.nextDouble() <= existingNumberChance) {
            return clientService.findRandom().getPhoneNumber();
        } else {
            return generateNewNumber();
        }

    }

    public String generateNewNumber() {
        long number = random.nextLong(9999999999L);
        return "7" + number;
    }
}
