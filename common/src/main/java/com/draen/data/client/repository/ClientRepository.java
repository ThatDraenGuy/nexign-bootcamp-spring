package com.draen.data.client.repository;

import com.draen.domain.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByPhoneNumber(String phoneNumber);

    Optional<Client> findByPhoneNumberEqualsAndBalanceGreaterThan(String phoneNumber, double money);

    List<Client> findByReports_BillingOperation_OperationNumber(Integer operationNumber);

    Page<Client> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE clients CASCADE", nativeQuery = true)
    void truncateTable();
}
