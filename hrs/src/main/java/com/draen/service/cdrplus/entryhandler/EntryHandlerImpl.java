package com.draen.service.cdrplus.entryhandler;

import com.draen.data.calltype.service.CallTypeService;
import com.draen.data.report.dto.ReportDto;
import com.draen.data.tariff.service.TariffService;
import com.draen.domain.entity.Tariff;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.tariff.TariffLogicService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class EntryHandlerImpl implements EntryHandler {
    private final ApplicationContext applicationContext;
    private final CallTypeService callTypeService;
    private final TariffService tariffService;

    public EntryHandlerImpl(ApplicationContext applicationContext, CallTypeService callTypeService, TariffService tariffService) {
        this.applicationContext = applicationContext;
        this.callTypeService = callTypeService;
        this.tariffService = tariffService;
    }

    public void handleEntry(Map<String, ReportDto> reports, CdrPlusEntry cdrPlusEntry) {
        Tariff tariff = tariffService.findByCode(cdrPlusEntry.getTariffCode());
        if (! applicationContext.containsBeanDefinition(tariff.getName())) {
            throw new RuntimeException(); //TODO
        }
        TariffLogicService tariffLogicService = applicationContext.getBean(tariff.getName(), TariffLogicService.class);

        ReportDto report;
        String phoneNumber = cdrPlusEntry.getPhoneNumber();
        if (! reports.containsKey(phoneNumber)) {
            report = new ReportDto(
                    null,
                    null,
                    phoneNumber,
                    tariff.getCode(),
                    new ArrayList<>(),
                    0,
                    0,
                    null
            );
            tariffLogicService.initializeReport(report);
            reports.put(phoneNumber, report);
        } else
            report = reports.get(phoneNumber);


        tariffLogicService.tarifficate(report, cdrPlusEntry);
    }
}
