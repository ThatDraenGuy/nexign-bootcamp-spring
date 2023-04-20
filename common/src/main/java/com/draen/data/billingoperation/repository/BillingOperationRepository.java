package com.draen.data.billingoperation.repository;

import com.draen.domain.entity.BillingOperation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BillingOperationRepository extends CrudRepository<BillingOperation, Long> {
    Optional<BillingOperation> findByOperationNumber(Integer operationNumber);
    BillingOperation findTopByOrderByOperationNumberDesc();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE billing_operations CASCADE", nativeQuery = true)
    void truncateTable();
}
