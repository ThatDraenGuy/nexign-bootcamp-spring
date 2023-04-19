package com.draen.data.monetaryunit.service;

import com.draen.data.monetaryunit.repository.MonetaryUnitRepository;
import com.draen.domain.entity.MonetaryUnit;
import com.draen.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class MonetaryUnitServiceImpl implements MonetaryUnitService {
    private final MonetaryUnitRepository repository;
    private final TransactionTemplate transactionTemplate;

    public MonetaryUnitServiceImpl(MonetaryUnitRepository repository, TransactionTemplate transactionTemplate) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public MonetaryUnit findByCode(String code) {
        return transactionTemplate.execute(status -> {
            return repository.findByCode(code).orElseThrow(() -> new NotFoundException("No such monetary unit"));
        });
    }
}
