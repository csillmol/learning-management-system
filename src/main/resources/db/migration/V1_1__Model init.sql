CREATE TABLE course
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    subject_id BIGINT,
    student_id BIGINT,
    CONSTRAINT pk_course PRIMARY KEY (id)
);

CREATE TABLE lecturer
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    name     VARCHAR(255),
    position VARCHAR(255),
    age      INT,
    CONSTRAINT pk_lecturer PRIMARY KEY (id)
);

CREATE TABLE student
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255),
    birth_place   VARCHAR(255),
    date_of_birth DATE,
    gpa           DOUBLE,
    CONSTRAINT pk_student PRIMARY KEY (id)
);

CREATE TABLE subject
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255),
    exam           BOOLEAN,
    hours_per_week INT,
    credit         INT,
    CONSTRAINT pk_subject PRIMARY KEY (id)
);

CREATE TABLE subject_lecturer
(
    lecturer_id BIGINT NOT NULL,
    subject_id  BIGINT NOT NULL
);

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_STUDENT FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE;

ALTER TABLE course
    ADD CONSTRAINT FK_COURSE_ON_SUBJECT FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE;

ALTER TABLE subject_lecturer
    ADD CONSTRAINT fk_subject_on_lecturer FOREIGN KEY (lecturer_id) REFERENCES lecturer (id) ON DELETE CASCADE;

ALTER TABLE subject_lecturer
    ADD CONSTRAINT fk_subject_on_subject FOREIGN KEY (subject_id) REFERENCES subject (id) ON DELETE CASCADE;