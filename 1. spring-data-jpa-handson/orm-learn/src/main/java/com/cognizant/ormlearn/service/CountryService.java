package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Country operations.
 *
 * @Service     - Marks this class as a Spring service component
 * @Transactional - Spring manages the Hibernate session and transaction
 *                  automatically for each method annotated with it.
 *
 * Covers:
 *   Hands on 1 - getAllCountries()
 *   Hands on 5 - addCountry(), updateCountry(), deleteCountry(), searchCountriesByName()
 *   Hands on 6 - findCountryByCode()
 *   Hands on 7 - addCountry()
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    // -------------------------------------------------------
    // Hands on 1 - Get all countries
    // -------------------------------------------------------

    /**
     * Retrieves all countries from the database.
     * Uses the built-in JpaRepository.findAll() method.
     *
     * @return List of all Country objects
     */
    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.debug("Start getAllCountries");
        List<Country> countries = countryRepository.findAll();
        LOGGER.debug("End getAllCountries, count={}", countries.size());
        return countries;
    }

    // -------------------------------------------------------
    // Hands on 6 - Find a country based on country code
    // -------------------------------------------------------

    /**
     * Finds a country by its 2-letter ISO code.
     * Uses JpaRepository.findById() which returns an Optional.
     *
     * @param countryCode - 2-letter country code (e.g., "IN", "US")
     * @return Country object if found
     * @throws CountryNotFoundException if no country matches the given code
     */
    @Transactional
    public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
        LOGGER.debug("Start findCountryByCode, code={}", countryCode);

        Optional<Country> result = countryRepository.findById(countryCode);

        // If not found, throw custom exception
        if (!result.isPresent()) {
            throw new CountryNotFoundException(
                    "Country with code '" + countryCode + "' not found in database.");
        }

        Country country = result.get();
        LOGGER.debug("End findCountryByCode, country={}", country);
        return country;
    }

    // -------------------------------------------------------
    // Hands on 7 - Add a new country
    // -------------------------------------------------------

    /**
     * Adds a new country to the database.
     * Uses JpaRepository.save() to persist the entity.
     *
     * @param country - Country object to be saved
     */
    @Transactional
    public void addCountry(Country country) {
        LOGGER.debug("Start addCountry, country={}", country);
        countryRepository.save(country);
        LOGGER.debug("End addCountry");
    }

    // -------------------------------------------------------
    // Hands on 5 - Update a country based on code
    // -------------------------------------------------------

    /**
     * Updates the name of an existing country identified by its code.
     *
     * Steps:
     *   1. Fetch the country using findById()
     *   2. Set the new name using setter
     *   3. Save the updated country using save()
     *
     * @param code    - Country code to identify the record
     * @param newName - New name to set for the country
     * @throws CountryNotFoundException if country with given code is not found
     */
    @Transactional
    public void updateCountry(String code, String newName) throws CountryNotFoundException {
        LOGGER.debug("Start updateCountry, code={}, newName={}", code, newName);

        Optional<Country> result = countryRepository.findById(code);
        if (!result.isPresent()) {
            throw new CountryNotFoundException(
                    "Cannot update. Country with code '" + code + "' not found.");
        }

        Country country = result.get();
        country.setName(newName);
        countryRepository.save(country);

        LOGGER.debug("End updateCountry, updated={}", country);
    }

    // -------------------------------------------------------
    // Hands on 5 - Delete a country based on code
    // -------------------------------------------------------

    /**
     * Deletes a country from the database by its code.
     * Uses JpaRepository.deleteById().
     *
     * @param code - Country code to delete
     */
    @Transactional
    public void deleteCountry(String code) {
        LOGGER.debug("Start deleteCountry, code={}", code);
        countryRepository.deleteById(code);
        LOGGER.debug("End deleteCountry");
    }

    // -------------------------------------------------------
    // Hands on 5 - Find list of countries matching partial name
    // -------------------------------------------------------

    /**
     * Returns all countries whose name contains the given search text.
     * Results are returned in ascending alphabetical order by name.
     * Uses Query Method: findByNameContainingIgnoreCaseOrderByNameAsc()
     *
     * @param searchText - partial country name to search for
     * @return sorted list of matching Country objects
     */
    @Transactional
    public List<Country> searchCountriesByName(String searchText) {
        LOGGER.debug("Start searchCountriesByName, searchText={}", searchText);
        List<Country> result = countryRepository
                .findByNameContainingIgnoreCaseOrderByNameAsc(searchText);
        LOGGER.debug("End searchCountriesByName, count={}", result.size());
        return result;
    }

    // -------------------------------------------------------
    // Hands on 5 - Find countries starting with a given letter
    // -------------------------------------------------------

    /**
     * Returns all countries whose name starts with the given prefix/letter.
     * Uses Query Method: findByNameStartingWith()
     *
     * @param prefix - starting character(s) to filter by (e.g., "Z")
     * @return list of Country objects starting with the prefix
     */
    @Transactional
    public List<Country> getCountriesStartingWith(String prefix) {
        LOGGER.debug("Start getCountriesStartingWith, prefix={}", prefix);
        List<Country> result = countryRepository.findByNameStartingWith(prefix);
        LOGGER.debug("End getCountriesStartingWith, count={}", result.size());
        return result;
    }
}
