package com.draen.init;

import com.draen.service.TarifficationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialTarifficationRunner implements CommandLineRunner {
    private final TarifficationService tarifficationService;

    public InitialTarifficationRunner(TarifficationService tarifficationService) {
        this.tarifficationService = tarifficationService;
    }

    @Override
    public void run(String... args) throws Exception {
        tarifficationService.tarifficate();
    }
}
