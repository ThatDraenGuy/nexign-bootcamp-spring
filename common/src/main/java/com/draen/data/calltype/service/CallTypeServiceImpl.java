package com.draen.data.calltype.service;

import com.draen.data.calltype.repository.CallTypeRepository;
import com.draen.domain.entity.CallType;
import com.draen.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class CallTypeServiceImpl implements CallTypeService{
    private final CallTypeRepository repository;
    private final TransactionTemplate transactionTemplate;

    public CallTypeServiceImpl(CallTypeRepository repository, TransactionTemplate transactionTemplate) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public CallType findByCode(String code) {
        return transactionTemplate.execute(status -> {
            return repository.findByCode(code).orElseThrow(() -> new NotFoundException("No such call type"));
        });
    }
}
