package com.draen.service.cdrplus.provider;

import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CdrPlusProvider {
    List<CdrPlusEntry> getCdrPlus();
}
