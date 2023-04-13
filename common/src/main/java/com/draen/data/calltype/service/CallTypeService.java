package com.draen.data.calltype.service;

import com.draen.domain.entity.CallType;
import org.springframework.stereotype.Service;

@Service
public interface CallTypeService {
    CallType findByCode(String code);
}
