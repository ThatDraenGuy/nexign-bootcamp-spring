package com.draen.data.payment.service;

import com.draen.data.client.repository.ClientRepository;
import com.draen.data.payment.repository.PaymentRepository;
import com.draen.domain.entity.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;
    private final TransactionTemplate transactionTemplate;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ClientRepository clientRepository,
                              TransactionTemplate transactionTemplate) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Payment create(Payment payment) {
        return transactionTemplate.execute(status -> {
           Payment entity = paymentRepository.save(payment);
           entity.getClient().setBalance(entity.getClient().getBalance() + entity.getMoney());
           return entity;
        });
    }
}
