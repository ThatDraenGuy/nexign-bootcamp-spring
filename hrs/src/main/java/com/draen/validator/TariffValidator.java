package com.draen.validator;

import com.draen.data.tariff.service.TariffService;
import com.draen.domain.entity.Tariff;
import com.draen.exception.UnimplementedException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TariffValidator implements ApplicationListener<ApplicationReadyEvent> {
    private final TariffService tariffService;
    private final ApplicationContext applicationContext;

    public TariffValidator(TariffService tariffService, ApplicationContext applicationContext) {
        this.tariffService = tariffService;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        for (Tariff tariff : tariffService.findAll()) {
            if (! applicationContext.containsBeanDefinition(tariff.getName())) {
                throw new UnimplementedException();
            }
        }
    }


}
