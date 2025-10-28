-- Create sequences
CREATE SEQUENCE IF NOT EXISTS department_id_seq START 1;
CREATE SEQUENCE IF NOT EXISTS employee_id_seq START 1;

-- Department first
CREATE TABLE departments (
                             id BIGINT DEFAULT nextval('department_id_seq') PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE,
                             description TEXT,
                             time_at TIMESTAMP DEFAULT NOW()
);

-- Employee next (references department)
CREATE TABLE employees (
                           id BIGINT DEFAULT nextval('employee_id_seq') PRIMARY KEY,
                           first_name VARCHAR(100) NOT NULL,
                           last_name VARCHAR(100) NOT NULL,
                           email VARCHAR(150) UNIQUE NOT NULL,
                           status VARCHAR(50) DEFAULT 'ACTIVE',
                           department_id BIGINT,
                           time_at TIMESTAMP DEFAULT NOW(),
                           CONSTRAINT fk_employee_department FOREIGN KEY (department_id)
                               REFERENCES departments(id) ON DELETE SET NULL
);
