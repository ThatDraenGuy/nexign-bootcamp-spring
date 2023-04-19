package com.draen.service.cdrplus.entryhandler;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EntryHandler {
    void handleEntry(Map<String, ReportDto> reports, CdrPlusEntry cdrPlusEntry);
}
