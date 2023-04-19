package com.draen.service.calltype.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CallTypeGeneratorImpl implements CallTypeGenerator {
    @Value("${custom.chance.incoming-call-type}")
    private double incomingCallTypeChance = 0.5;

    @Value("${custom.constants.call-type-code.incoming}")
    private String incomingCallTypeCode;

    @Value("${custom.constants.call-type-code.outgoing}")
    private String outgoingCallTypeCode;

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
