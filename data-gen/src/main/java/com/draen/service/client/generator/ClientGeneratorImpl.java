package com.draen.service.client.generator;

import com.draen.data.tariff.service.TariffService;
import com.draen.domain.entity.Client;
import com.draen.service.balance.generator.BalanceGenerator;
import com.draen.service.phonenumber.generator.PhoneNumberGenerator;
import org.springframework.stereotype.Service;

@Service
public class ClientGeneratorImpl implements ClientGenerator {
    private final PhoneNumberGenerator phoneNumberGenerator;
    private final BalanceGenerator balanceGenerator;
    private final TariffService tariffService;

    public ClientGeneratorImpl(PhoneNumberGenerator phoneNumberGenerator, BalanceGenerator balanceGenerator,
                               TariffService tariffService) {
        this.phoneNumberGenerator = phoneNumberGenerator;
        this.balanceGenerator = balanceGenerator;
        this.tariffService = tariffService;
    }

    @Override
    public Client generateClient() {
        return new Client(
                null,
                phoneNumberGenerator.generateNewNumber(),
                balanceGenerator.generateBalance(),
                tariffService.findRandom()
        );
    }
}
