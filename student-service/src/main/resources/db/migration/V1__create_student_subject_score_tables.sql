-- Enable UUID generation
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE students (     id UUID PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE
                        );

CREATE TABLE subject_scores (
                           id UUID PRIMARY KEY,
                           subject VARCHAR(100) NOT NULL,
                           score INTEGER,

                           student_id UUID,
                           CONSTRAINT fk_students_subject_scores FOREIGN KEY (student_id)
                               REFERENCES students(id) ON DELETE SET NULL
);