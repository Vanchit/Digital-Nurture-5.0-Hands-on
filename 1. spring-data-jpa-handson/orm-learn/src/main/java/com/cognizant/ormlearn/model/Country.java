package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistence class mapped to the 'country' table in ormlearn schema.
 *
 * @Entity  - Marks this class as a JPA entity
 * @Table   - Maps to the 'country' database table
 * @Id     - Marks the primary key field
 * @Column  - Maps field to specific column name in the table
 */
@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "co_code")
    private String code;

    @Column(name = "co_name")
    private String name;

    // -------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // -------------------------------------------------------
    // toString
    // -------------------------------------------------------

    @Override
    public String toString() {
        return "Country{"
                + "code='" + code + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
