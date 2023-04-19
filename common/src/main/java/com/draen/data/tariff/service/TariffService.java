package com.draen.data.tariff.service;

import com.draen.domain.entity.Tariff;
import org.springframework.stereotype.Service;


@Service
public interface TariffService {
    Tariff findByCode(String code);
    Iterable<Tariff> findAll();
    Tariff findRandom();
}
