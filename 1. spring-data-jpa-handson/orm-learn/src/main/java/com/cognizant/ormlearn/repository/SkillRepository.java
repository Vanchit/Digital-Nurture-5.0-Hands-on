package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Skill entity.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 6 (Section 2): Used in ManyToMany with Employee
 *
 * Inherits built-in methods from JpaRepository:
 *   findById(Integer id)
 *   save(Skill skill)
 *   findAll()
 *   deleteById(Integer id)
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
