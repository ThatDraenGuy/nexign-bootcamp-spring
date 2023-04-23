package com.draen.init;

import com.draen.service.DatabaseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final DatabaseService databaseService;

    public DatabaseInitializer(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public void run(String... args) {
        try {
            databaseService.populate();
        } catch (Exception ignored) {}
    }
}
