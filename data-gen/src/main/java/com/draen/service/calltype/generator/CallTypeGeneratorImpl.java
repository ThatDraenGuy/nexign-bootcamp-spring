package com.draen.service.calltype.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CallTypeGeneratorImpl implements CallTypeGenerator {
    private double incomingCallTypeChance = 0.5;

    private String incomingCallTypeCode = "01";

    private String outgoingCallTypeCode = "02";

    private final Random random = new Random();

    @Override
    public String generateCallType() {
        if (random.nextDouble() <= incomingCallTypeChance) {
            return incomingCallTypeCode;
        } else {
            return outgoingCallTypeCode;
        }
    }
}
