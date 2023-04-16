package com.draen.service.tariff;

import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.MonetaryUnit;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service("unlimitedTariff")
public class UnlimitedTariffLogicService implements TariffLogicService {
    private final double minuteCost = 1;
    private final int freeMinutes = 300;

    private final MonetaryUnitService monetaryUnitService;

    @Getter
    private MonetaryUnit monetaryUnit;

    public UnlimitedTariffLogicService(MonetaryUnitService monetaryUnitService) {
        this.monetaryUnitService = monetaryUnitService;
    }

    @PostConstruct
    private void init() {
        monetaryUnit = monetaryUnitService.findByCode("RUB");
    }

    @Override
    public void initializeReport(ReportDto report) {
        report.setMonetaryUnitCode(monetaryUnit.getCode());
        report.setTotalCost(100);
    }

    @Override
    public double getCost(int currentMinutes, int callMinutes) {
        int freeMinutesLeft = freeMinutes - currentMinutes;

        int paidMinutes;
        if (freeMinutesLeft <= 0) {
            paidMinutes = callMinutes;
        } else if (freeMinutesLeft < callMinutes) {
            paidMinutes = callMinutes - freeMinutesLeft;
        } else {
            paidMinutes = 0;
        }

        return paidMinutes * minuteCost;
    }
}
