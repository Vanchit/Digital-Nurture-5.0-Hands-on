package com.cognizant.ormlearn.service.exception;

/**
 * Custom exception thrown when a country is not found
 * for a given country code in the database.
 *
 * Used in Hands on 6 - Find a country based on country code.
 */
public class CountryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CountryNotFoundException(String message) {
        super(message);
    }
}
