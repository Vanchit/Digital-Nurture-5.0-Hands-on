package com.cognizant.loan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LoanApplication - Main class for the Loan Microservice.
 *
 * This microservice runs on port 8081 (configured in application.properties).
 * It independently handles bank loan operations.
 *
 * NOTE: Port 8081 is used because port 8080 is already occupied by
 * the Account microservice. Without "server.port=8081", this application
 * would fail to start with "Address already in use" error.
 *
 * Test URL: http://localhost:8081/loans/H00987987972342
 */
@SpringBootApplication
public class LoanApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Loan Microservice...");
        SpringApplication.run(LoanApplication.class, args);
        LOGGER.info("Loan Microservice started successfully on port 8081!");
    }
}
