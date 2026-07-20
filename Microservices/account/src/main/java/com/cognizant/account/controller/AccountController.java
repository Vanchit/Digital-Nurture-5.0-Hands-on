package com.cognizant.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.account.model.Account;

/**
 * AccountController - handles REST endpoints for Account microservice.
 *
 * Endpoint: GET /accounts/{number}
 * Returns a dummy Account object (no backend connectivity).
 *
 * Sample Request:  http://localhost:8080/accounts/00987987973432
 * Sample Response: { "number": "00987987973432", "type": "savings", "balance": 234343 }
 */
@RestController
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable("number") String number) {
        LOGGER.info("Start getAccount() for account number: {}", number);

        // Return a dummy account response without any backend connectivity
        Account account = new Account();
        account.setNumber("00987987973432");
        account.setType("savings");
        account.setBalance(234343);

        LOGGER.debug("Returning Account: {}", account);
        LOGGER.info("End getAccount()");
        return account;
    }
}
