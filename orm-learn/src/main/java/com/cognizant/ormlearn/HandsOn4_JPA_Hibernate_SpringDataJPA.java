package com.cognizant.ormlearn;

/**
 * ============================================================
 * Hands on 4: Difference between JPA, Hibernate and Spring Data JPA
 * ============================================================
 *
 * This file is for REFERENCE/STUDY purposes only.
 * It demonstrates how the same "addEmployee" operation looks
 * in plain Hibernate vs Spring Data JPA.
 *
 * ------------------------------------------------------------
 * 1. JPA (Java Persistence API)
 * ------------------------------------------------------------
 * - JSR 338 Specification for persisting, reading and managing data from Java objects
 * - JPA is only a SPECIFICATION (set of interfaces/rules) — it has NO implementation
 * - Hibernate is one of the most popular IMPLEMENTATIONS of JPA
 * - Other JPA implementations: EclipseLink, OpenJPA
 *
 * ------------------------------------------------------------
 * 2. Hibernate
 * ------------------------------------------------------------
 * - ORM (Object-Relational Mapping) tool that IMPLEMENTS JPA
 * - Provides concrete implementation of JPA interfaces
 * - You manage Session, Transaction, and exception handling yourself
 *
 * Example (plain Hibernate) - createEmployee:
 *
 *   Session session = factory.openSession();
 *   Transaction tx = null;
 *   Integer employeeID = null;
 *
 *   try {
 *       tx = session.beginTransaction();          // begin transaction manually
 *       employeeID = (Integer) session.save(employee);  // save
 *       tx.commit();                              // commit manually
 *   } catch (HibernateException e) {
 *       if (tx != null) tx.rollback();            // rollback on error
 *       e.printStackTrace();
 *   } finally {
 *       session.close();                          // close session manually
 *   }
 *   return employeeID;
 *
 * Issues with plain Hibernate:
 *   - Lots of boilerplate code (session open, tx begin, commit, rollback, close)
 *   - Error-prone if you forget to close session or rollback
 *   - Need to repeat this pattern for every CRUD operation
 *
 * ------------------------------------------------------------
 * 3. Spring Data JPA
 * ------------------------------------------------------------
 * - Does NOT have its own JPA implementation
 * - Is an ABSTRACTION LAYER above JPA providers like Hibernate
 * - Removes all the boilerplate code
 * - Manages transactions automatically via @Transactional
 * - Auto-generates queries from method names (Query Methods)
 *
 * Example (Spring Data JPA) - same createEmployee:
 *
 *   // EmployeeRepository.java
 *   @Repository
 *   public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
 *       // No code needed - save(), findById(), deleteById() are inherited!
 *   }
 *
 *   // EmployeeService.java
 *   @Service
 *   public class EmployeeService {
 *       @Autowired
 *       private EmployeeRepository employeeRepository;
 *
 *       @Transactional  // Spring manages session + transaction automatically
 *       public void addEmployee(Employee employee) {
 *           employeeRepository.save(employee);  // one line!
 *       }
 *   }
 *
 * Advantages of Spring Data JPA over plain Hibernate:
 *   - No manual session management
 *   - No manual transaction begin/commit/rollback
 *   - @Transactional annotation handles everything
 *   - Built-in methods: findAll(), findById(), save(), deleteById(), count()
 *   - Query Methods: auto-generate SQL from method names
 *   - Pagination and sorting built-in
 *
 * ------------------------------------------------------------
 * Architecture Overview:
 * ------------------------------------------------------------
 *
 *   Your Code (Service Layer)
 *         |
 *         v
 *   Spring Data JPA   <-- abstraction, removes boilerplate
 *         |
 *         v
 *   JPA (Specification / Interfaces)
 *         |
 *         v
 *   Hibernate         <-- actual JPA implementation
 *         |
 *         v
 *   JDBC / MySQL Driver
 *         |
 *         v
 *   MySQL Database (ormlearn schema)
 *
 * ------------------------------------------------------------
 * References:
 * ------------------------------------------------------------
 * - Difference between Spring Data JPA and Hibernate:
 *   https://dzone.com/articles/what-is-the-difference-between-hibernate-and-sprin-1
 * - Introduction to JPA:
 *   https://www.javaworld.com/article/3379043/what-is-jpa-introduction-to-the-java-persistence-api.html
 */
public class HandsOn4_JPA_Hibernate_SpringDataJPA {
    // This class is intentionally empty.
    // All content is in the Javadoc above for learning reference.
}
