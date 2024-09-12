package com.jokim.sivillage;

import com.jokim.sivillage.data.CsvProcessingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SivillageApplication implements CommandLineRunner {

    private final CsvProcessingService csvProcessingService;

    public SivillageApplication(CsvProcessingService csvProcessingService) {
        this.csvProcessingService = csvProcessingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SivillageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        csvProcessingService.processCsvFile();
    }

}
