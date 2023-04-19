package com.draen.data.callsummary.repository;

import com.draen.domain.entity.CallSummary;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallSummaryRepository extends CrudRepository<CallSummary, Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE call_summaries CASCADE", nativeQuery = true)
    void truncateTable();
}
