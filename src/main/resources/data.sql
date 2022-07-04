INSERT INTO subject (credit, exam, name) VALUES (4, 'MIDTERM_MARK', 'Introduction to Mathematics');
INSERT INTO subject (credit, exam, name) VALUES (6, 'EXAM', 'Introduction to Applied Linear Algebra');

INSERT INTO course (lecturer_name, lecturer_position, subject_id) VALUES ('Scott Bradley', 'Assistant Professor', 1);
INSERT INTO course (lecturer_name, lecturer_position, subject_id) VALUES ('Matthew Smith', 'Instructor', 1);
INSERT INTO course (lecturer_name, lecturer_position, subject_id) VALUES ('Scott Bradley', 'Assistant Professor', 2);
INSERT INTO course (lecturer_name, lecturer_position, subject_id) VALUES ('Matthew Smith', 'Instructor', 2);

INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('P5BAHT', 'Anna Broderick', 'London', '2000-04-06', 2.5, 1);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('R54GGH', 'Linda Moose', 'Edinburgh', '2002-05-16', 3.9, 1);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('LSG65H', 'Katherine Burgess', 'Edinburgh', '1997-10-16', 3.4, 2);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('AODH5K', 'Frederick Welsh', 'Edinburgh', '1999-08-13', 1.4, 2);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('SDN9VG', 'Jeremy Hughes', 'Edinburgh', '2003-02-10', 2.6, 3);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('GROCER', 'Jonah Keys', 'Edinburgh', '2000-01-06', 3.0, 3);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('GPFLOW', 'Frank Franklin', 'Edinburgh', '2000-07-30', 3.6, 4);
INSERT INTO student (id, name, birth_place, date_of_birth, gpa, course_id) VALUES ('ERLIDE', 'Florence Soule', 'Edinburgh', '2001-04-18', 2.7, 4);