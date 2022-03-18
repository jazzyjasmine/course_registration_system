-- sudo mysql -u root -p < ./db/20220317T155400-create_tables.sql

use regie;

DROP TABLE IF EXISTS people;
CREATE TABLE people
(
    id        INT PRIMARY KEY,
    firstname VARCHAR(30) NOT NULL,
    lastname  VARCHAR(30) NOT NULL,
    email     VARCHAR(30) NOT NULL,
    division  VARCHAR(30) NOT NULL, -- each person belongs to a division
    role      INT         NOT NULL  -- 1: student, 2: instructor, 3: TA, 4: administrator
);

DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id              INT         NOT NULL PRIMARY KEY,
    student_type    INT         NOT NULL, -- 1: full-time, 2: part-time
    major           VARCHAR(30) NOT NULL,
    graduation_date DATE                  -- only full-time student has graduation date
);

DROP TABLE IF EXISTS course;
CREATE TABLE course
(
    course_id     INT PRIMARY KEY,
    course_name   VARCHAR(30) NOT NULL,
    instructor_id INT         NOT NULL,
    capacity      INT         NOT NULL,
    address       VARCHAR(50) NOT NULL,
    weekday       INT         NOT NULL, -- 1: Mon, 2: Tue, 3: Wed, 4: Thu, 5: Fri, 6: Sat, 7: Sun
    start_time    TIME        NOT NULL,
    end_time      TIME        NOT NULL,
    division      VARCHAR(30) NOT NULL,
    year          CHAR(4)     NOT NULL,
    quarter       INT         NOT NULL, -- 1: winter, 2: spring, 3: summer, 4: fall
    grade_type    INT         NOT NULL, -- 1: P/F, 2: A/B/C/D/F
    description   TEXT
);

-- student:course many-to-many
DROP TABLE IF EXISTS student_course_relation;
CREATE TABLE student_course_relation
(
    student_id INT NOT NULL,
    course_id  INT NOT NULL
);

-- ta:course many-to-many
DROP TABLE IF EXISTS ta_course_relation;
CREATE TABLE ta_course_relation
(
    ta_id     INT NOT NULL,
    course_id INT NOT NULL
);

-- primary key (course_id, student_id)
DROP TABLE IF EXISTS grade;
CREATE TABLE grade
(
    course_id  INT     NOT NULL,
    student_id INT     NOT NULL,
    grade      CHAR(1) NOT NULL
);