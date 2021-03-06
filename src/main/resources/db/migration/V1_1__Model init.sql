drop table if exists course CASCADE;
drop table if exists student CASCADE;
drop table if exists subject CASCADE;

create table course
(
    id                bigint generated by default as identity,
    lecturer_name     varchar(255),
    lecturer_position varchar(255),
    subject_id        bigint,
    primary key (id)
);

create table student
(
    id            varchar(255),
    name          varchar(255),
    birth_place   varchar(255),
    date_of_birth date,
    gpa           double,
    course_id     bigint,
    primary key (id)
);

create table subject
(
    id     bigint generated by default as identity,
    credit integer not null,
    exam   varchar(255),
    name   varchar(255),
    primary key (id)
);

alter table course
    add constraint fk_course_subject foreign key (subject_id) references subject on delete cascade;

alter table student
    add constraint fk_course_student foreign key (course_id) references course on delete cascade