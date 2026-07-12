-- ============================================================
-- Hands on 3 (Section 2): Create Payroll Tables
-- Run in MySQL Workbench or CLI:
--   mysql -u root -p ormlearn < payroll-setup.sql
-- ============================================================

USE ormlearn;

-- -------------------------------------------------------
-- Create department table
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS department (
    dp_id   INT         NOT NULL AUTO_INCREMENT,
    dp_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (dp_id)
);

-- -------------------------------------------------------
-- Create skill table
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS skill (
    sk_id   INT         NOT NULL AUTO_INCREMENT,
    sk_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (sk_id)
);

-- -------------------------------------------------------
-- Create employee table
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS employee (
    em_id            INT         NOT NULL AUTO_INCREMENT,
    em_name          VARCHAR(50) NOT NULL,
    em_salary        DOUBLE      NOT NULL DEFAULT 0,
    em_permanent     TINYINT(1)  NOT NULL DEFAULT 0,
    em_date_of_birth DATE,
    em_dp_id         INT,
    PRIMARY KEY (em_id),
    CONSTRAINT fk_emp_dept FOREIGN KEY (em_dp_id) REFERENCES department (dp_id)
);

-- -------------------------------------------------------
-- Create employee-skill join table (ManyToMany)
-- -------------------------------------------------------
CREATE TABLE IF NOT EXISTS em_sk (
    em_sk_id INT NOT NULL,
    sk_sk_id INT NOT NULL,
    PRIMARY KEY (em_sk_id, sk_sk_id),
    CONSTRAINT fk_emsk_emp   FOREIGN KEY (em_sk_id) REFERENCES employee (em_id),
    CONSTRAINT fk_emsk_skill FOREIGN KEY (sk_sk_id) REFERENCES skill    (sk_id)
);

-- -------------------------------------------------------
-- Insert sample departments
-- -------------------------------------------------------
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (1, 'Engineering');
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (2, 'Marketing');
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (3, 'Human Resources');
INSERT IGNORE INTO department (dp_id, dp_name) VALUES (4, 'Finance');

-- -------------------------------------------------------
-- Insert sample skills
-- -------------------------------------------------------
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (1, 'Java');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (2, 'Spring Boot');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (3, 'MySQL');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (4, 'Python');
INSERT IGNORE INTO skill (sk_id, sk_name) VALUES (5, 'DevOps');

-- -------------------------------------------------------
-- Insert sample employees
-- -------------------------------------------------------
INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (1, 'Alice Smith',   75000.00, 1, '1990-05-15', 1);

INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (2, 'Bob Johnson',   60000.00, 1, '1988-03-22', 2);

INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (3, 'Carol Williams', 55000.00, 0, '1995-11-10', 1);

INSERT IGNORE INTO employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id)
VALUES (4, 'David Brown',   80000.00, 1, '1985-07-30', 3);

-- -------------------------------------------------------
-- Insert sample employee-skill mappings (ManyToMany)
-- -------------------------------------------------------
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (1, 1); -- Alice -> Java
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (1, 2); -- Alice -> Spring Boot
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (1, 3); -- Alice -> MySQL
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (2, 4); -- Bob -> Python
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (3, 1); -- Carol -> Java
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (3, 5); -- Carol -> DevOps
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (4, 3); -- David -> MySQL
INSERT IGNORE INTO em_sk (em_sk_id, sk_sk_id) VALUES (4, 5); -- David -> DevOps
