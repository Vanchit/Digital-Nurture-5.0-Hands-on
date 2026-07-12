package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Persistence class mapped to the 'employee' table.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 4 (Section 2): @ManyToOne - Many Employees belong to one Department
 *                          @JoinColumn - defines the FK column (em_dp_id)
 *                          FetchType.EAGER - Department is loaded together with Employee
 *                          (default for @ManyToOne as per JPA specification)
 * Hands on 5 (Section 2): @OneToMany - Department has many Employees (mapped in Department.java)
 * Hands on 6 (Section 2): @ManyToMany - Employee can have many Skills
 *                          @JoinTable - defines the join table (em_sk)
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_id")
    private int id;

    @Column(name = "em_name")
    private String name;

    @Column(name = "em_salary")
    private double salary;

    @Column(name = "em_permanent")
    private boolean permanent;

    @Column(name = "em_date_of_birth")
    private Date dateOfBirth;

    // -------------------------------------------------------
    // Hands on 4 (Section 2): ManyToOne - Department
    // -------------------------------------------------------
    // @ManyToOne: Many employees can belong to ONE department.
    //   Default FetchType is EAGER for @ManyToOne:
    //   Hibernate fetches Department with a JOIN when loading Employee.
    // @JoinColumn: Specifies the FK column in the employee table.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "em_dp_id")
    private Department department;

    // -------------------------------------------------------
    // Hands on 6 (Section 2): ManyToMany - Skills
    // -------------------------------------------------------
    // @ManyToMany: One employee can have many skills.
    //              One skill can be assigned to many employees.
    // @JoinTable: Defines the mapping/join table 'em_sk'.
    //   joinColumns       = FK column pointing to Employee (em_sk_id)
    //   inverseJoinColumns = FK column pointing to Skill (sk_sk_id)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "em_sk",
        joinColumns = @JoinColumn(name = "em_sk_id"),
        inverseJoinColumns = @JoinColumn(name = "sk_sk_id")
    )
    private List<Skill> skills;

    // -------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public boolean isPermanent() { return permanent; }
    public void setPermanent(boolean permanent) { this.permanent = permanent; }

    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public List<Skill> getSkills() { return skills; }
    public void setSkills(List<Skill> skills) { this.skills = skills; }

    // -------------------------------------------------------
    // toString
    // -------------------------------------------------------

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", salary=" + salary
                + ", permanent=" + permanent
                + ", dateOfBirth=" + dateOfBirth
                + ", department=" + department
                + '}';
    }
}
