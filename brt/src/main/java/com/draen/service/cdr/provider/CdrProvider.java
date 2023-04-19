package com.draen.service.cdr.provider;

import com.draen.domain.model.CdrEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CdrProvider {
    List<CdrEntry> getEntries();
}
