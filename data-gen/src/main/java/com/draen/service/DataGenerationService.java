package com.draen.service;

import com.draen.data.client.service.ClientService;
import com.draen.domain.entity.Client;
import com.draen.domain.model.CdrEntry;
import com.draen.service.cdr.generator.CdrEntryGenerator;
import com.draen.service.cdr.writer.CdrWriter;
import com.draen.service.client.generator.ClientGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataGenerationService {
    @Value("${custom.amount.cdr-entries}")
    private int cdrEntriesAmount;

    @Value("${custom.amount.clients}")
    private int clientsAmount;

    private final CdrEntryGenerator cdrEntryGenerator;
    private final CdrWriter cdrWriter;
    private final ClientGenerator clientGenerator;
    private final ClientService clientService;

    public DataGenerationService(CdrEntryGenerator cdrEntryGenerator, CdrWriter cdrWriter,
                                 ClientGenerator clientGenerator, ClientService clientService) {
        this.cdrEntryGenerator = cdrEntryGenerator;
        this.cdrWriter = cdrWriter;
        this.clientGenerator = clientGenerator;
        this.clientService = clientService;
    }

    public void generateCdrs() throws IOException {
        cdrWriter.init();
        for (int i = 0; i < cdrEntriesAmount; i++) {
            CdrEntry entry = cdrEntryGenerator.generateEntry();
            cdrWriter.writeEntry(entry);
        }
    }

    public void generateClients() {
        for (int i = 0; i < clientsAmount; i++) {
            Client client = clientGenerator.generateClient();
            clientService.create(client);
        }
    }
}
