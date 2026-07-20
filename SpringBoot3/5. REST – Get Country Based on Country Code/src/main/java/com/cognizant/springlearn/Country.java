package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    public Country() {
        LOGGER.debug("Inside Country Constructor.");
    }

    public String getCode() {
        LOGGER.debug("Inside getCode Getter method. Code: {}", this.code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("Inside setCode Setter method. Setting code to: {}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("Inside getName Getter method. Name: {}", this.name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("Inside setName Setter method. Setting name to: {}", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]";
    }
}
