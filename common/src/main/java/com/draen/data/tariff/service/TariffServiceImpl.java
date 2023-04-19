package com.draen.data.tariff.service;

import com.draen.data.tariff.repostiory.TariffRepository;
import com.draen.domain.entity.Tariff;
import com.draen.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;


@Service
public class TariffServiceImpl implements TariffService {
    private final TariffRepository repository;
    private final TransactionTemplate transactionTemplate;

    public TariffServiceImpl(TariffRepository repository, TransactionTemplate transactionTemplate) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Tariff findByCode(String code) {
        return transactionTemplate.execute(status -> {
            return repository.findByCodeEquals(code).orElseThrow(() -> new NotFoundException("No such tariff"));
        });
    }

    @Override
    public Iterable<Tariff> findAll() {
        return transactionTemplate.execute(status -> {
            return repository.findAll();
        });
    }

    @Override
    public Tariff findRandom() {
        return transactionTemplate.execute(status -> {
            long tariffNum = repository.count();
            int pageNumber = (int)(Math.random() * tariffNum);
            Page<Tariff> page = repository.findAll(PageRequest.of(pageNumber, 1));
            if (! page.hasContent()) return null;
            return page.getContent().get(0);
        });
    }
}
