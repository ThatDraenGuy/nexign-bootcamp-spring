package com.draen.data.billingoperation.service;

import com.draen.domain.entity.BillingOperation;
import org.springframework.stereotype.Service;

@Service
public interface BillingOperationService {
    BillingOperation create(BillingOperation operation);
    BillingOperation findByNumber(Integer operationNumber);
    BillingOperation findLast();
}
