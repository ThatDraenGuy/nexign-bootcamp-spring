package com.draen.service.cdrplus.creator;

import com.draen.data.client.service.ClientService;
import com.draen.domain.entity.Client;
import com.draen.domain.model.CdrEntry;
import com.draen.domain.model.CdrPlusEntry;
import com.draen.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CdrPlusCreatorImpl implements CdrPlusCreator {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private final ClientService clientService;

    public CdrPlusCreatorImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    public Optional<CdrPlusEntry> createEntry(CdrEntry cdrEntry) {
        Client client;
        try {
            client = clientService.findActiveByNumber(cdrEntry.getPhoneNumber());
        } catch (NotFoundException e) {
            return Optional.empty();
        }

        return Optional.of(new CdrPlusEntry(
                cdrEntry.getCallTypeCode(),
                cdrEntry.getPhoneNumber(),
                cdrEntry.getStartTime(),
                cdrEntry.getEndTime(),
                getDuration(cdrEntry.getStartTime(), cdrEntry.getEndTime()),
                client.getTariff().getCode()
        ));
    }

    private Duration getDuration(String startTime, String endTime) {
        return Duration.between(LocalDateTime.parse(startTime, formatter), LocalDateTime.parse(endTime, formatter));
    }
}
