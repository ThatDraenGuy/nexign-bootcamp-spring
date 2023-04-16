package com.draen.service.cdrplus.creator;

import com.draen.domain.model.CdrEntry;
import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface CdrPlusCreatorService {
    Optional<CdrPlusEntry> createEntry(CdrEntry cdrEntry);
}
