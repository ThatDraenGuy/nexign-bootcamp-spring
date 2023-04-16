package com.draen.service.tariff;

import com.draen.data.monetaryunit.service.MonetaryUnitService;
import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.MonetaryUnit;
import com.draen.domain.entity.Report;
import com.draen.domain.model.CdrPlusEntry;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("regularTariff")
public class RegularTariffLogicService implements TariffLogicService {
    @Value("${custom.constants.call-type-code.incoming}")
    private String incomingCallTypeCode;

    private final int discountMinutes = 100;
    private final double discountMinuteCost = 0.5;

    private final MonetaryUnitService monetaryUnitService;
    private final TariffLogicService fullPriceTariffLogicService;

    @Getter
    private MonetaryUnit monetaryUnit;

    public RegularTariffLogicService(MonetaryUnitService monetaryUnitService,
                                     @Qualifier("minuteTariff") TariffLogicService fullPriceTariffLogicService) {
        this.monetaryUnitService = monetaryUnitService;
        this.fullPriceTariffLogicService = fullPriceTariffLogicService;
    }

    @PostConstruct
    private void init() {
        monetaryUnit = monetaryUnitService.findByCode("RUB");
    }

    @Override
    public void initializeReport(ReportDto report) {
        report.setMonetaryUnitCode(monetaryUnit.getCode());
    }

    @Override
    public void tarifficate(ReportDto report, CdrPlusEntry cdrPlusEntry) {
        if (cdrPlusEntry.getCallType().getCode().equals(incomingCallTypeCode)) return;
        TariffLogicService.super.tarifficate(report, cdrPlusEntry);
    }

    @Override
    public double getCost(int currentMinutes, int callMinutes) {
        int discountMinutesLeft = discountMinutes - currentMinutes;

        int discountMinutes;
        int fullPriceMinutes;
        if (discountMinutesLeft <= 0) {
            discountMinutes = 0;
            fullPriceMinutes = callMinutes;
        } else if (discountMinutesLeft < callMinutes) {
            discountMinutes = discountMinutesLeft;
            fullPriceMinutes = callMinutes - discountMinutesLeft;
        } else {
            discountMinutes = callMinutes;
            fullPriceMinutes = 0;
        }

        return discountMinutes * discountMinuteCost +
                fullPriceTariffLogicService.getCost(currentMinutes, fullPriceMinutes);
    }
}
