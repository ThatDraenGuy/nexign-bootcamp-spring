package com.draen.service.cdrplus.entryhandler;

import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.Report;
import com.draen.domain.entity.Tariff;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.service.tariff.TariffLogicService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class EntryHandlerServiceImpl implements EntryHandlerService {
    private final ApplicationContext applicationContext;

    public EntryHandlerServiceImpl(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void handleEntry(Map<String, ReportDto> reports, CdrPlusEntry cdrPlusEntry) {
        Tariff tariff = cdrPlusEntry.getTariff();
        if (! applicationContext.containsBeanDefinition(tariff.getName())) {
            throw new RuntimeException(); //TODO
        }
        TariffLogicService tariffLogicService = applicationContext.getBean(tariff.getName(), TariffLogicService.class);

        ReportDto report;
        String phoneNumber = cdrPlusEntry.getPhoneNumber();
        if (! reports.containsKey(phoneNumber)) {
            report = new ReportDto(
                    phoneNumber,
                    0,
                    0,
                    new ArrayList<>(),
                    null
            );
            tariffLogicService.initializeReport(report);
            reports.put(phoneNumber, report);
        } else
            report = reports.get(phoneNumber);


        tariffLogicService.tarifficate(report, cdrPlusEntry);
    }
}
