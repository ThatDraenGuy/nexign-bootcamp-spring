package com.draen.service.cdrplus.entryhandler;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface EntryHandlerService {
    void handleEntry(Map<String, ReportDto> reports, CdrPlusEntry cdrPlusEntry);
}
