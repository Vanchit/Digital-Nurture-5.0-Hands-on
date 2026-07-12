package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for Department operations.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 4 (Section 2): Used to fetch department for Employee
 * Hands on 5 (Section 2): Used to test OneToMany - get department with employees
 */
@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Get a department by its ID.
     * Uses JpaRepository.findById().
     *
     * @param id - Department primary key
     * @return Department object
     */
    @Transactional
    public Department get(int id) {
        LOGGER.info("Start DepartmentService.get, id={}", id);
        Department department = departmentRepository.findById(id).get();
        LOGGER.info("End DepartmentService.get, department={}", department);
        return department;
    }

    /**
     * Save (insert or update) a department.
     * Uses JpaRepository.save().
     *
     * @param department - Department object to persist
     */
    @Transactional
    public void save(Department department) {
        LOGGER.info("Start DepartmentService.save");
        departmentRepository.save(department);
        LOGGER.info("End DepartmentService.save");
    }
}
