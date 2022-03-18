-- sudo mysql -u root -p < ./db/20220318T084600-insert_data.sql

use regie;
-- insert one administrator
INSERT INTO people
VALUES (1,
        "Root",
        "Root",
        "root@regiemail.com",
        "PSD",
        4);

-- insert two instructors
INSERT INTO people
VALUES (2,
        "Iron",
        "Man",
        "iron@regiemail.com",
        "PSD",
        2);

INSERT INTO people
VALUES (3,
        "Scarlet",
        "Witch",
        "scarlet@regiemail.com",
        "PSD",
        2);

-- insert four students, of which one is a TA, one is a part-time student
INSERT INTO people
VALUES (4,
        "A",
        "LA",
        "a@regiemail.com",
        "PSD",
        1);

INSERT INTO student
VALUES (4,
        1,
        "Computer Science",
        "2022-12-01");

INSERT INTO people
VALUES (5,
        "B",
        "LB",
        "b@regiemail.com",
        "PSD",
        1);

INSERT INTO student
VALUES (5,
        1,
        "Physics",
        "2022-12-10");

INSERT INTO people
VALUES (6,
        "C",
        "LC",
        "c@regiemail.com",
        "PSD",
        1);

INSERT INTO student
VALUES (6,
        2,
        "Math",
        "2023-01-01");

INSERT INTO people
VALUES (7,
        "D",
        "LD",
        "d@regiemail.com",
        "PSD",
        3);

INSERT INTO student
VALUES (7,
        1,
        "Computer Science",
        "2023-01-01");

-- insert four courses
INSERT INTO course
VALUES (1,
        "Introduction to Physics",
        2,
        3,
        "4 Privet Drive",
        1,
        "08:00:00",
        "10:00:00",
        "PSD",
        "2022",
        1,
        1,
        "This is an entry level course.");

INSERT INTO course
VALUES (2,
        "Java Programming",
        3,
        2,
        "5 Privet Drive",
        2,
        "14:30:00",
        "16:30:00",
        "PSD",
        "2022",
        1,
        2,
        "This course teaches you how to program in Java.");

INSERT INTO course
VALUES (3,
        "Ruby Programming",
        3,
        5,
        "6 Privet Drive",
        3,
        "10:30:00",
        "12:30:00",
        "PSD",
        "2022",
        1,
        2,
        "This course teaches you how to program in Ruby.");

INSERT INTO course
VALUES (4,
        "Gravity",
        2,
        10,
        "7 Privet Drive",
        5,
        "09:30:00",
        "11:30:00",
        "PSD",
        "2022",
        1,
        2,
        "This course teaches you rules of gravity.");

INSERT INTO student_course_relation
VALUES (4,
        1);

INSERT INTO student_course_relation
VALUES (4,
        2);

INSERT INTO student_course_relation
VALUES (5,
        1);

INSERT INTO student_course_relation
VALUES (5,
        2);

INSERT INTO student_course_relation
VALUES (6,
        3);

INSERT INTO student_course_relation
VALUES (6,
        4);

INSERT INTO student_course_relation
VALUES (7,
        1);

INSERT INTO ta_course_relation
VALUES (7,
        3);



