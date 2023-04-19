package com.draen.service.balance.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class BalanceGeneratorImpl implements BalanceGenerator {
    @Value("${custom.boundaries.balance.lower}")
    private int lowerBound;

    @Value("${custom.boundaries.balance.higher}")
    private int higherBound;

    private final Random random = new Random();

    @Override
    public double generateBalance() {
        return random.nextInt(lowerBound, higherBound);
    }
}
