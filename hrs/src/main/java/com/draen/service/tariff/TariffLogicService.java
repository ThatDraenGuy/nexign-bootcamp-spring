package com.draen.service.tariff;

import com.draen.domain.model.CallCost;
import com.draen.domain.model.CdrPlus;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public interface TariffLogicService {
    CallCost tarifficate(CdrPlus cdrPlus);

    default long getMinutes(Duration duration) {
        return duration.toMinutes() + 1;
    }
}
