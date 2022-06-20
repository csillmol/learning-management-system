INSERT INTO lecturer (age, name, position) VALUES (54, 'Scott Bradley', 'Assistant Professor');
INSERT INTO lecturer (age, name, position) VALUES (32, 'Matthew Smith', 'Instructor');

INSERT INTO student (birth_place, date_of_birth, gpa, name) VALUES ('London', '1996-04-06', 2.5, 'Anna Broderick');
INSERT INTO student (birth_place, date_of_birth, gpa, name) VALUES ('Edinburgh', '1993-05-16', 3.2, 'Linda Moose');

INSERT INTO subject (credit, exam, hours_per_week, name) VALUES (4, FALSE, 4, 'Introduction to Mathematics');
INSERT INTO subject (credit, exam, hours_per_week, name) VALUES (4, FALSE, 4, 'Introduction to Mathematics');

INSERT INTO subject_lecturer (lecturer_id, subject_id) VALUES (1, 1);

INSERT INTO course (student_id, subject_id) VALUES (1,1);
INSERT INTO course (student_id, subject_id) VALUES (1,2);
INSERT INTO course (student_id, subject_id) VALUES (2,2);