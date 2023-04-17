package com.draen.service.cdrplus.provider;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public interface CdrPlusProviderService {
    List<CdrPlusEntry> getCdrPlus();
}
