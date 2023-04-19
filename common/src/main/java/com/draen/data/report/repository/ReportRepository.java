package com.draen.data.report.repository;

import com.draen.domain.entity.Report;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    Optional<Report> findByClient_PhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE reports CASCADE", nativeQuery = true)
    void truncateTable();
}
