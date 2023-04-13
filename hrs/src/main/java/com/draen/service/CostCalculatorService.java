package com.draen.service;

import com.draen.domain.entity.Tariff;
import com.draen.domain.model.CallCost;
import com.draen.domain.model.CdrPlus;
import com.draen.service.tariff.TariffLogicService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorService {
    private final ApplicationContext applicationContext;

    public CostCalculatorService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public CallCost getCost(CdrPlus cdrPlus) {
        Tariff tariff = cdrPlus.getTariff();
        if (! applicationContext.containsBeanDefinition(tariff.getName())) {
            throw new RuntimeException(); //TODO
        }
        TariffLogicService tariffLogicService = applicationContext.getBean(tariff.getName(), TariffLogicService.class);

        return tariffLogicService.tarifficate(cdrPlus);
    }
}
