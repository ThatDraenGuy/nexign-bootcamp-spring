package com.draen.data.payment.service;

import com.draen.domain.entity.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    Payment create(Payment payment);
}
