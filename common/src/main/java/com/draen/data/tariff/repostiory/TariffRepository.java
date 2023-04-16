package com.draen.data.tariff.repostiory;

import com.draen.domain.entity.Tariff;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TariffRepository extends CrudRepository<Tariff, Long> {
    Optional<Tariff> findByCodeEquals(String code);
}
