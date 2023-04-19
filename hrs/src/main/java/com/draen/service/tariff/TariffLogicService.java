package com.draen.service.tariff;

import com.draen.data.callsummary.dto.CallSummaryDto;
import com.draen.data.report.dto.ReportDto;
import com.draen.domain.entity.MonetaryUnit;
import com.draen.domain.model.CdrPlusEntry;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

@Service
public interface TariffLogicService {
    void initializeReport(ReportDto report);
    double getCost(int currentMinutes, int callMinutes);
    MonetaryUnit getMonetaryUnit();

    default void tarifficate(ReportDto report, CdrPlusEntry cdrPlusEntry) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        int currentMinutes = report.getTotalMinutes();
        int callMinutes = getMinutes(cdrPlusEntry.getDuration());

        double cost = getCost(currentMinutes, callMinutes);

        report.setTotalCost(report.getTotalCost() + cost);
        report.setTotalMinutes(currentMinutes + callMinutes);
        report.getPayload().add(new CallSummaryDto(
                null,
                cdrPlusEntry.getCallTypeCode(),
                cdrPlusEntry.getStartTime(),
                cdrPlusEntry.getEndTime(),
                cdrPlusEntry.getDuration().toString(),
                cost,
                getMonetaryUnit().getCode()
        ));
    }

    default int getMinutes(Duration duration) {
        return (int) (duration.toMinutes() + 1);
    }
}
