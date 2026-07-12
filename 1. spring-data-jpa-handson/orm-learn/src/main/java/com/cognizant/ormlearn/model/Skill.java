package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Persistence class mapped to the 'skill' table.
 *
 * Hands on 3 (Section 2): Payroll tables and bean mapping
 * Hands on 6 (Section 2): @ManyToMany - Employee can have many Skills,
 *                          Skill can be associated with many Employees
 */
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sk_id")
    private int id;

    @Column(name = "sk_name")
    private String name;

    // -------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // -------------------------------------------------------
    // toString
    // -------------------------------------------------------

    @Override
    public String toString() {
        return "Skill{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
