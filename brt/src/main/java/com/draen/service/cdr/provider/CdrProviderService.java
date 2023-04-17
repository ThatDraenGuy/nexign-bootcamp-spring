package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CdrProviderService {
    List<CdrEntry> getEntries();
}
