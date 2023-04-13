package com.draen.domain.repository;

import com.draen.domain.entity.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
    Optional<Report> findByClient_PhoneNumber(String phoneNumber);
}
