package com.draen.data.monetaryunit.repository;

import com.draen.domain.entity.MonetaryUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonetaryUnitRepository extends CrudRepository<MonetaryUnit, Long> {
    Optional<MonetaryUnit> findByCode(String code);
}
