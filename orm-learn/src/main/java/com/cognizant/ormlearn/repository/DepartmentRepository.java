package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Department entity.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 4 (Section 2): Used to fetch department for Employee relationship
 *
 * Inherits built-in methods from JpaRepository:
 *   findById(Integer id)
 *   save(Department department)
 *   findAll()
 *   deleteById(Integer id)
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
