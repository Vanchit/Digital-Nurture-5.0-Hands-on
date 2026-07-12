package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Country entity.
 *
 * By extending JpaRepository<Country, String>:
 *   - Country  = Entity type
 *   - String   = Type of the primary key (co_code is a String)
 *
 * Spring Data JPA automatically provides built-in methods:
 *   findAll(), findById(), save(), deleteById(), count(), etc.
 *
 * Query Methods (Hands on 1 - Query Methods on country table):
 *   Spring Data JPA generates the SQL query automatically from the method name.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /**
     * Hands on 1 - Query 1 & 2:
     * Find all countries whose name contains the given text (case-insensitive).
     * Returns results in ascending order of country name.
     * Method name pattern: findBy + FieldName + Containing + IgnoreCase + OrderBy + FieldName + Asc
     */
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    /**
     * Hands on 1 - Query 3:
     * Find all countries whose name starts with the given letter/prefix.
     * Method name pattern: findBy + FieldName + StartingWith
     */
    List<Country> findByNameStartingWith(String prefix);

}
