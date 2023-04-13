package com.draen.data.monetaryunit.service;

import com.draen.domain.entity.MonetaryUnit;
import org.springframework.stereotype.Service;

@Service
public interface MonetaryUnitService {
    MonetaryUnit findByCode(String code);
}
