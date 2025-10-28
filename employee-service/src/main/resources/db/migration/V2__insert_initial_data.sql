
INSERT INTO departments ( name, description)
VALUES
    ( 'Human Resources', 'Handles employee relations and recruitment'),
    ('Finance', 'Manages budgeting, payroll, and accounting'),
    ('Engineering', 'Responsible for product development and maintenance'),
    ('Sales', 'Handles client relationships and revenue generation');


INSERT INTO employees ( first_name, last_name, email, status, department_id)
VALUES
    ('Seyi', 'Akande', 'seyi.akande@corp.com', 'ACTIVE', 1),
    ('John', 'Smith', 'john.smith@corp.com', 'ACTIVE',3 ),
    ( 'Jane', 'Doe', 'jane.doe@corp.com', 'ACTIVE', 2),
    ('Tunde', 'Ola', 'tunde.ola@corp.com', 'ACTIVE', 4),
    ( 'Mary', 'Johnson', 'mary.johnson@corp.com', 'ACTIVE', 1);
