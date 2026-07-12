package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for Employee operations.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 4 (Section 2): Fetch Employee with Department (ManyToOne - EAGER fetch)
 *                          Add Employee with Department association
 *                          Update Employee (change department)
 * Hands on 5 (Section 2): OneToMany - Department -> Employees
 * Hands on 6 (Section 2): ManyToMany - Employee <-> Skills
 *
 * Key notes on @Transactional:
 *   - Spring creates and manages a Hibernate Session for the duration of the method.
 *   - Automatically commits on success, rolls back on RuntimeException.
 *   - Mandatory for lazy-loaded relationships (LAZY fetch needs an open session).
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    // -------------------------------------------------------
    // Hands on 4 (Section 2): Get an employee with department
    // -------------------------------------------------------

    /**
     * Fetches an employee by ID.
     * Since @ManyToOne is EAGER by default, Department is fetched via JOIN
     * in the same query automatically by Hibernate.
     *
     * @param id - Employee primary key
     * @return Employee with Department populated
     */
    @Transactional
    public Employee get(int id) {
        LOGGER.info("Start EmployeeService.get, id={}", id);
        Employee employee = employeeRepository.findById(id).get();
        LOGGER.info("End EmployeeService.get, employee={}", employee);
        return employee;
    }

    // -------------------------------------------------------
    // Hands on 4 (Section 2): Save employee (Add / Update)
    // -------------------------------------------------------

    /**
     * Saves (inserts or updates) an employee.
     * - If employee has no id → INSERT (Hibernate auto-generates id via @GeneratedValue)
     * - If employee has existing id → UPDATE
     *
     * After save(), Hibernate sets the generated id back into the Employee object.
     *
     * @param employee - Employee object to persist
     */
    @Transactional
    public void save(Employee employee) {
        LOGGER.info("Start EmployeeService.save");
        employeeRepository.save(employee);
        LOGGER.info("End EmployeeService.save, employee={}", employee);
    }
}
