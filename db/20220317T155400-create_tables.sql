-- sudo mysql -u root -p < 20220317T155400-create_tables.sql

use regie;

DROP TABLE IF EXISTS people;
CREATE TABLE people
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname  VARCHAR(30) NOT NULL,
    email     VARCHAR(30) NOT NULL,
    division  VARCHAR(30) NOT NULL,
    role      INT         NOT NULL -- 1: student, 2: instructor, 3: TA, 4: administrator
);

DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id           INT NOT NULL PRIMARY KEY,
    student_type INT NOT NULL -- 1: full-time, 2: part-time
);

DROP TABLE IF EXISTS course;
CREATE TABLE course
(
    course_id     INT AUTO_INCREMENT PRIMARY KEY,
    course_name   VARCHAR(30) NOT NULL,
    instructor_id INT         NOT NULL,
    capacity      INT         NOT NULL,
    division      VARCHAR(30) NOT NULL,
    address       VARCHAR(50) NOT NULL,
    year          CHAR(4)     NOT NULL,
    quarter       INT         NOT NULL, -- 1: winter, 2: spring, 3: summer, 4: fall
    weekday       INT         NOT NULL, -- 1: Mon, 2: Tue, 3: Wed, 4: Thu, 5: Fri, 6: Sat, 7: Sun
    start_time    TIME        NOT NULL,
    end_time      TIME        NOT NULL,
    grade_type    INT         NOT NULL, -- 1: P/F, 2: A/B/C/D/F
    description   TEXT
);

DROP TABLE IF EXISTS student_course_relation;
CREATE TABLE student_course_relation
(
    course_id  INT NOT NULL,
    student_id INT NOT NULL
);

DROP TABLE IF EXISTS ta_course_relation;
CREATE TABLE ta_course_relation
(
    course_id INT NOT NULL,
    ta_id     INT NOT NULL
);

DROP TABLE IF EXISTS grade;
CREATE TABLE grade
(
    course_id  INT     NOT NULL,
    student_id INT     NOT NULL,
    grade      CHAR(1) NOT NULL
);