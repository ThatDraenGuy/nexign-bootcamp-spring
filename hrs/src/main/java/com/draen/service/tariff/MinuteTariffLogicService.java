package com.draen.service.tariff;

import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.CallSummary;
import com.draen.domain.entity.MonetaryUnit;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service("minuteTariff")
public class MinuteTariffLogicService implements TariffLogicService {
    private final double minuteCost = 1.5;

    private final MonetaryUnitService monetaryUnitService;

    @Getter
    private MonetaryUnit monetaryUnit;

    public MinuteTariffLogicService(MonetaryUnitService monetaryUnitService) {
        this.monetaryUnitService = monetaryUnitService;
    }

    @PostConstruct
    private void init() {
        monetaryUnit = monetaryUnitService.findByCode("RUB");
    }

    @Override
    public void initializeReport(ReportDto report) {
        report.setMonetaryUnitCode(monetaryUnit.getCode());
    }

    public double getCost(int currentMinutes, int callMinutes) {
        return callMinutes * minuteCost;
    }
}
