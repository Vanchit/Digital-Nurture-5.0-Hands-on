package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Persistence class mapped to the 'department' table.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 5 (Section 2): @OneToMany - Department has many Employees
 *
 * @OneToMany - One Department can have many Employees.
 *   mappedBy = "department" means the 'department' field in Employee
 *   owns the foreign key (em_dp_id).
 *   FetchType.LAZY = employees are loaded only when explicitly accessed
 *                    (default for OneToMany - avoids N+1 queries)
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private int id;

    @Column(name = "dp_name")
    private String name;

    // One Department has many Employees (OneToMany - inverse side)
    // FetchType.LAZY: employees are NOT loaded unless we call getEmployees()
    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<Employee> employees;

    // -------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) { this.employees = employees; }

    // -------------------------------------------------------
    // toString (excluding employees to avoid circular reference)
    // -------------------------------------------------------

    @Override
    public String toString() {
        return "Department{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
