package com.draen.service.cdrplus.serializer;

import com.draen.domain.model.CdrPlusEntry;

public interface CdrPlusSerializer {
    String serializeEntry(CdrPlusEntry entry);
}
