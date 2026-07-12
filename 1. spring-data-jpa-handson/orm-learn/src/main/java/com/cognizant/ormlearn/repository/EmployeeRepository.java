package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Employee entity.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 4 (Section 2): Many-to-One (Employee -> Department)
 * Hands on 5 (Section 2): One-to-Many (Department -> Employees)
 * Hands on 6 (Section 2): Many-to-Many (Employee <-> Skills)
 *
 * Inherits built-in methods from JpaRepository:
 *   findById(Integer id)
 *   save(Employee employee)  --> INSERT or UPDATE
 *   findAll()
 *   deleteById(Integer id)
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
