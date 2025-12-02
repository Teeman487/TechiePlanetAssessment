
WITH inserted_students AS (
INSERT INTO students (id, name)
VALUES
    (gen_random_uuid(), 'Azeez'),
    (gen_random_uuid(), 'Ade'),
    (gen_random_uuid(), 'Kola'),
    (gen_random_uuid(), 'Tosin')
    RETURNING id, name
    )


INSERT INTO subject_scores (id, subject,score, student_id) VALUES
(gen_random_uuid(), 'MATH', 50, (SELECT id FROM inserted_students WHERE name = 'Azeez')),
(gen_random_uuid(), 'ENGLISH',60, (SELECT id FROM inserted_students WHERE name = 'Ade')),
(gen_random_uuid(), 'ICT',80, (SELECT id FROM inserted_students WHERE name = 'Kola')),
(gen_random_uuid(), 'SOCIAL',45, (SELECT id FROM inserted_students WHERE name = 'Tosin'));