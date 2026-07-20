package com.cognizant.loan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cognizant.loan.model.Loan;

/**
 * LoanController - handles REST endpoints for Loan microservice.
 *
 * Endpoint: GET /loans/{number}
 * Returns a dummy Loan object (no backend connectivity).
 *
 * Sample Request:  http://localhost:8081/loans/H00987987972342
 * Sample Response:
 * {
 *   "number": "H00987987972342",
 *   "type": "car",
 *   "loan": 400000,
 *   "emi": 3258,
 *   "tenure": 18
 * }
 */
@RestController
public class LoanController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoanController.class);

    @GetMapping("/loans/{number}")
    public Loan getLoan(@PathVariable("number") String number) {
        LOGGER.info("Start getLoan() for loan number: {}", number);

        // Return a dummy loan response without any backend connectivity
        Loan loan = new Loan();
        loan.setNumber("H00987987972342");
        loan.setType("car");
        loan.setLoan(400000);
        loan.setEmi(3258);
        loan.setTenure(18);

        LOGGER.debug("Returning Loan: {}", loan);
        LOGGER.info("End getLoan()");
        return loan;
    }
}
