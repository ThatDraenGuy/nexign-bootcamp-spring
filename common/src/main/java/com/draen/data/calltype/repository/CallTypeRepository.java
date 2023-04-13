package com.draen.data.calltype.repository;

import com.draen.domain.entity.CallType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CallTypeRepository extends CrudRepository<CallType, Long> {
    Optional<CallType> findByCode(String code);
}
