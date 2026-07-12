package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Main Spring Boot Application class for orm-learn project.
 *
 * @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan
 *
 * Covers ALL Hands-on exercises:
 * ============================================================
 * SECTION 1:
 *   Hands on 1 - Spring Data JPA Quick Example       -> testGetAllCountries()
 *   Hands on 4 - JPA vs Hibernate vs Spring Data JPA -> HandsOn4_JPA_Hibernate_SpringDataJPA.java
 *   Hands on 5 - Manage Country (CRUD + search)      -> testUpdateCountry(), testDeleteCountry(),
 *                                                        testSearchCountries(), testCountriesStartingWith()
 *   Hands on 6 - Find country by code                -> testFindCountryByCode()
 *   Hands on 7 - Add new country                     -> testAddCountry()
 *   Hands on 8 - Update country                      -> testUpdateCountry()
 *   Hands on 9 - Delete country                      -> testDeleteCountry()
 *
 * SECTION 2:
 *   Hands on 1 - Query Methods on country            -> testCountriesContaining(), testCountriesStartingWith()
 *   Hands on 2 - Query Methods on stock table        -> testFBStocksSep2019(), testGOOGLAbove1250(),
 *                                                        testTop3HighestVolume(), testNFLXLowest()
 *   Hands on 3 - Payroll bean mapping                -> Employee, Department, Skill entities + repositories
 *   Hands on 4 - ManyToOne (Employee-Department)     -> testGetEmployee(), testAddEmployee(), testUpdateEmployee()
 *   Hands on 5 - OneToMany (Department-Employees)    -> testGetDepartmentWithEmployees()
 *   Hands on 6 - ManyToMany (Employee-Skills)        -> testGetEmployeeWithSkills()
 */
@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    // -------------------------------------------------------
    // Static service references (set from ApplicationContext)
    // -------------------------------------------------------
    private static CountryService    countryService;
    private static StockService      stockService;
    private static EmployeeService   employeeService;
    private static DepartmentService departmentService;
    private static SkillService      skillService;

    // -------------------------------------------------------
    // main()
    // -------------------------------------------------------

    public static void main(String[] args) {

        // Load Spring Boot Application Context
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        // Retrieve all service beans from context
        countryService    = context.getBean(CountryService.class);
        stockService      = context.getBean(StockService.class);
        employeeService   = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService      = context.getBean(SkillService.class);

        // ===================================================
        // SECTION 1 - Country Hands-on Tests
        // ===================================================

        // Hands on 1: Get all countries
        testGetAllCountries();

        // Hands on 6: Find a country based on country code
        testFindCountryByCode();

        // Hands on 7: Add a new country
        testAddCountry();

        // Hands on 8: Update a country based on code
        testUpdateCountry();

        // Hands on 9: Delete a country based on code
        testDeleteCountry();

        // Hands on 5 / Section 2 - Hands on 1: Query Methods on country table
        testCountriesContaining();
        testCountriesStartingWith();

        // ===================================================
        // SECTION 2 - Stock Hands-on Tests (Hands on 2)
        // ===================================================

        testFBStocksSep2019();
        testGOOGLAbove1250();
        testTop3HighestVolume();
        testNFLXLowest();

        // ===================================================
        // SECTION 2 - Payroll Hands-on Tests (Hands on 4/5/6)
        // ===================================================

        // Hands on 4: Get employee with department (ManyToOne - EAGER)
        testGetEmployee();

        // Hands on 4: Add a new employee with department
        testAddEmployee();

        // Hands on 4: Update employee's department
        testUpdateEmployee();

        // Hands on 5: Get department with employees (OneToMany - LAZY)
        testGetDepartmentWithEmployees();

        // Hands on 6: Get employee with skills (ManyToMany - EAGER)
        testGetEmployeeWithSkills();
    }

    // ==========================================================
    // SECTION 1 - COUNTRY TEST METHODS
    // ==========================================================

    // ----------------------------------------------------------
    // Hands on 1: Get all countries
    // ----------------------------------------------------------

    /**
     * Retrieves all countries from ormlearn database.
     * Demonstrates JpaRepository.findAll().
     * Hibernate generates: SELECT * FROM country
     */
    private static void testGetAllCountries() {
        LOGGER.info("Start testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End testGetAllCountries");
    }

    // ----------------------------------------------------------
    // Hands on 6: Find a country by code
    // ----------------------------------------------------------

    /**
     * Finds country with code 'IN'.
     * Demonstrates JpaRepository.findById() with Optional and custom exception.
     * Hibernate generates: SELECT * FROM country WHERE co_code = 'IN'
     */
    private static void testFindCountryByCode() {
        LOGGER.info("Start testFindCountryByCode");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
            LOGGER.debug("Is India? {}", "India".equals(country.getName()));
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found: {}", e.getMessage());
        }
        LOGGER.info("End testFindCountryByCode");
    }

    // ----------------------------------------------------------
    // Hands on 7: Add a new country
    // ----------------------------------------------------------

    /**
     * Adds country code 'XX' to the database, then verifies retrieval.
     * Demonstrates JpaRepository.save() for INSERT.
     * Hibernate generates: INSERT INTO country (co_code, co_name) VALUES (?, ?)
     */
    private static void testAddCountry() {
        LOGGER.info("Start testAddCountry");
        try {
            Country newCountry = new Country();
            newCountry.setCode("XX");
            newCountry.setName("Test Country");

            countryService.addCountry(newCountry);
            LOGGER.debug("Country added successfully");

            // Verify by fetching
            Country fetched = countryService.findCountryByCode("XX");
            LOGGER.debug("Fetched after add: {}", fetched);

        } catch (CountryNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
        }
        LOGGER.info("End testAddCountry");
    }

    // ----------------------------------------------------------
    // Hands on 8: Update a country
    // ----------------------------------------------------------

    /**
     * Updates country 'XX' to new name 'Updated Test Country'.
     * Demonstrates: findById() -> setName() -> save() for UPDATE.
     * Hibernate generates: SELECT ... FROM country WHERE co_code = 'XX'
     *                  then UPDATE country SET co_name = ? WHERE co_code = 'XX'
     */
    private static void testUpdateCountry() {
        LOGGER.info("Start testUpdateCountry");
        try {
            countryService.updateCountry("XX", "Updated Test Country");
            LOGGER.debug("Country updated successfully");

            Country updated = countryService.findCountryByCode("XX");
            LOGGER.debug("After update: {}", updated);

        } catch (CountryNotFoundException e) {
            LOGGER.error("Error: {}", e.getMessage());
        }
        LOGGER.info("End testUpdateCountry");
    }

    // ----------------------------------------------------------
    // Hands on 9: Delete a country
    // ----------------------------------------------------------

    /**
     * Deletes the country 'XX' added in testAddCountry().
     * Demonstrates JpaRepository.deleteById() for DELETE.
     * Hibernate generates: DELETE FROM country WHERE co_code = 'XX'
     */
    private static void testDeleteCountry() {
        LOGGER.info("Start testDeleteCountry");
        countryService.deleteCountry("XX");
        LOGGER.debug("Country 'XX' deleted");

        // Verify deletion - should throw CountryNotFoundException
        try {
            countryService.findCountryByCode("XX");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Confirmed deleted: {}", e.getMessage());
        }
        LOGGER.info("End testDeleteCountry");
    }

    // ----------------------------------------------------------
    // Hands on 5 / Section 2 Hands on 1: Countries containing text
    // ----------------------------------------------------------

    /**
     * Searches for countries whose name contains 'ou'.
     * Results in ascending alphabetical order.
     * Expected: Bouvet Island, Djibouti, French Southern Territories,
     *           Guadeloupe, Luxembourg, South Africa, South Sudan, etc.
     */
    private static void testCountriesContaining() {
        LOGGER.info("Start testCountriesContaining");
        List<Country> countries = countryService.searchCountriesByName("ou");
        LOGGER.debug("Countries containing 'ou' (sorted A-Z):");
        countries.forEach(c -> LOGGER.debug("  {} - {}", c.getCode(), c.getName()));
        LOGGER.info("End testCountriesContaining");
    }

    // ----------------------------------------------------------
    // Section 2 Hands on 1: Countries starting with letter
    // ----------------------------------------------------------

    /**
     * Gets countries starting with 'Z'.
     * Expected: Zambia, Zimbabwe
     */
    private static void testCountriesStartingWith() {
        LOGGER.info("Start testCountriesStartingWith");
        List<Country> countries = countryService.getCountriesStartingWith("Z");
        LOGGER.debug("Countries starting with 'Z':");
        countries.forEach(c -> LOGGER.debug("  {} - {}", c.getCode(), c.getName()));
        LOGGER.info("End testCountriesStartingWith");
    }

    // ==========================================================
    // SECTION 2 - STOCK TEST METHODS (Hands on 2)
    // ==========================================================

    // ----------------------------------------------------------
    // Query 1: Facebook stocks in September 2019
    // ----------------------------------------------------------

    /**
     * Gets all FB stock records for Sep 2019.
     * Uses: findByCodeAndDateBetween("FB", Sep1, Sep30)
     */
    private static void testFBStocksSep2019() {
        LOGGER.info("Start testFBStocksSep2019");
        List<Stock> stocks = stockService.getFacebookStocksInSeptember2019();
        LOGGER.debug("FB stocks in Sep 2019:");
        stocks.forEach(s -> LOGGER.debug("  {} | {} | open={} close={} vol={}",
                s.getCode(), s.getDate(), s.getOpen(), s.getClose(), s.getVolume()));
        LOGGER.info("End testFBStocksSep2019");
    }

    // ----------------------------------------------------------
    // Query 2: Google stocks close > 1250
    // ----------------------------------------------------------

    /**
     * Gets all GOOGL stocks where close price > 1250.
     * Uses: findByCodeAndCloseGreaterThan("GOOGL", 1250)
     */
    private static void testGOOGLAbove1250() {
        LOGGER.info("Start testGOOGLAbove1250");
        List<Stock> stocks = stockService.getGoogleStocksAbove1250();
        LOGGER.debug("GOOGL stocks close > 1250:");
        stocks.forEach(s -> LOGGER.debug("  {} | {} | close={}",
                s.getCode(), s.getDate(), s.getClose()));
        LOGGER.info("End testGOOGLAbove1250");
    }

    // ----------------------------------------------------------
    // Query 3: Top 3 highest transaction volumes
    // ----------------------------------------------------------

    /**
     * Gets top 3 stocks with highest volume.
     * Uses: findTop3ByOrderByVolumeDesc()
     */
    private static void testTop3HighestVolume() {
        LOGGER.info("Start testTop3HighestVolume");
        List<Stock> stocks = stockService.getTop3HighestVolumeStocks();
        LOGGER.debug("Top 3 highest volume stocks:");
        stocks.forEach(s -> LOGGER.debug("  {} | {} | volume={}",
                s.getCode(), s.getDate(), s.getVolume()));
        LOGGER.info("End testTop3HighestVolume");
    }

    // ----------------------------------------------------------
    // Query 4: Netflix top 3 lowest prices
    // ----------------------------------------------------------

    /**
     * Gets top 3 NFLX stocks with lowest close price.
     * Uses: findTop3ByCodeOrderByCloseAsc("NFLX")
     */
    private static void testNFLXLowest() {
        LOGGER.info("Start testNFLXLowest");
        List<Stock> stocks = stockService.getNetflixTop3LowestStocks();
        LOGGER.debug("NFLX top 3 lowest close prices:");
        stocks.forEach(s -> LOGGER.debug("  {} | {} | close={}",
                s.getCode(), s.getDate(), s.getClose()));
        LOGGER.info("End testNFLXLowest");
    }

    // ==========================================================
    // SECTION 2 - EMPLOYEE/PAYROLL TEST METHODS (Hands on 4/5/6)
    // ==========================================================

    // ----------------------------------------------------------
    // Hands on 4: Get employee with department (ManyToOne EAGER)
    // ----------------------------------------------------------

    /**
     * Gets employee with id=1 and logs employee + department.
     *
     * Because @ManyToOne is EAGER (JPA default), Hibernate automatically
     * generates a LEFT OUTER JOIN with department table:
     *
     *   SELECT e.*, d.*
     *   FROM employee e
     *   LEFT OUTER JOIN department d ON e.em_dp_id = d.dp_id
     *   WHERE e.em_id = 1
     */
    private static void testGetEmployee() {
        LOGGER.info("Start testGetEmployee");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.info("End testGetEmployee");
    }

    // ----------------------------------------------------------
    // Hands on 4: Add a new employee with department
    // ----------------------------------------------------------

    /**
     * Creates a new Employee and associates it with Department id=1.
     *
     * Steps:
     *   1. Create Employee instance
     *   2. Set employee fields
     *   3. Fetch department from departmentService
     *   4. Set department in employee
     *   5. Call employeeService.save()
     *
     * Hibernate generates:
     *   SELECT from department WHERE dp_id = 1
     *   INSERT INTO employee (em_name, em_salary, ..., em_dp_id) VALUES (...)
     * After save(), Hibernate sets the generated em_id back into employee object.
     */
    private static void testAddEmployee() {
        LOGGER.info("Start testAddEmployee");
        try {
            Employee employee = new Employee();
            employee.setName("Eve Taylor");
            employee.setSalary(70000.00);
            employee.setPermanent(true);
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-08-20"));

            // Fetch department id=1 (Engineering)
            Department dept = departmentService.get(1);
            employee.setDepartment(dept);

            // Save employee
            employeeService.save(employee);
            LOGGER.debug("Employee added: {}", employee);

        } catch (ParseException e) {
            LOGGER.error("Date parse error: {}", e.getMessage());
        }
        LOGGER.info("End testAddEmployee");
    }

    // ----------------------------------------------------------
    // Hands on 4: Update employee - change department
    // ----------------------------------------------------------

    /**
     * Gets employee id=1, changes department from Engineering to Marketing.
     *
     * Steps:
     *   1. Fetch employee by id
     *   2. Fetch different department
     *   3. Set new department in employee
     *   4. Call save() to UPDATE
     *
     * Hibernate generates:
     *   SELECT employee + department JOIN (EAGER)
     *   SELECT department WHERE dp_id = 2
     *   UPDATE employee SET em_dp_id = 2 WHERE em_id = 1
     */
    private static void testUpdateEmployee() {
        LOGGER.info("Start testUpdateEmployee");

        // Get employee id=1
        Employee employee = employeeService.get(1);
        LOGGER.debug("Before update - Department: {}", employee.getDepartment());

        // Fetch a different department (Marketing = id 2)
        Department newDept = departmentService.get(2);
        employee.setDepartment(newDept);

        // Save (triggers UPDATE)
        employeeService.save(employee);
        LOGGER.debug("After update - Employee: {}", employee);

        LOGGER.info("End testUpdateEmployee");
    }

    // ----------------------------------------------------------
    // Hands on 5: Get department with employees (OneToMany LAZY)
    // ----------------------------------------------------------

    /**
     * Gets department id=1 and lists its employees.
     *
     * Because @OneToMany is LAZY (JPA default), employees are loaded
     * only when getEmployees() is called — requires an open Hibernate session.
     * @Transactional in DepartmentService.get() keeps session open.
     *
     * Note: This must be accessed WITHIN the @Transactional method to avoid
     * LazyInitializationException. The LOGGER call here forces loading while
     * transaction is still open inside the service.
     */
    private static void testGetDepartmentWithEmployees() {
        LOGGER.info("Start testGetDepartmentWithEmployees");
        Department dept = departmentService.get(1);
        LOGGER.debug("Department: {}", dept);
        LOGGER.debug("Employees in department:");
        if (dept.getEmployees() != null) {
            dept.getEmployees().forEach(e -> LOGGER.debug("  {}", e));
        }
        LOGGER.info("End testGetDepartmentWithEmployees");
    }

    // ----------------------------------------------------------
    // Hands on 6: Get employee with skills (ManyToMany EAGER)
    // ----------------------------------------------------------

    /**
     * Gets employee id=1 and lists all their skills.
     *
     * Because @ManyToMany is set to EAGER, Hibernate fetches skills
     * in the same query using a JOIN with the em_sk and skill tables.
     */
    private static void testGetEmployeeWithSkills() {
        LOGGER.info("Start testGetEmployeeWithSkills");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee: {}", employee);
        LOGGER.debug("Skills:");
        if (employee.getSkills() != null) {
            employee.getSkills().forEach(s -> LOGGER.debug("  {}", s));
        }
        LOGGER.info("End testGetEmployeeWithSkills");
    }
}
