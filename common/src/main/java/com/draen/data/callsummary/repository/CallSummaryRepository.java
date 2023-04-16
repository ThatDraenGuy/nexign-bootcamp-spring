package com.draen.data.callsummary.repository;

import com.draen.domain.entity.CallSummary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallSummaryRepository extends CrudRepository<CallSummary, Long> {
}
