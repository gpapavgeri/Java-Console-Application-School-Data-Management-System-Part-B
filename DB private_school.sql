CREATE DATABASE PRIVATE_SCHOOL;
USE PRIVATE_SCHOOL;

CREATE TABLE students (
	student_id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name    VARCHAR(255) NOT NULL ,
	last_name     VARCHAR(255) NOT NULL ,
	date_of_birth DATE ,
	tuition_fees  DECIMAL(6,2)
 );

CREATE TABLE trainers (
	trainer_id 	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	first_name 	VARCHAR(255) NOT NULL ,
	last_name  	VARCHAR(255) NOT NULL ,
	t_subject	VARCHAR(255) 
);

CREATE TABLE assignments (
	assignment_id	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title        	VARCHAR(255) NOT NULL ,
	a_description   VARCHAR(255),
	sub_DateTime  	TIMESTAMP NOT NULL ,
	oral_mark     	DECIMAL(4,2) ,
	total_mark    	DECIMAL(4,2)
 );

CREATE TABLE courses (
	course_id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	title         VARCHAR(255) NOT NULL,
	stream        VARCHAR(255),
	c_type        VARCHAR(255),
	start_date    TIMESTAMP,
	end_date      TIMESTAMP
);

CREATE TABLE students_per_course (
	course_id  INT NOT NULL,
	student_id INT NOT NULL,
PRIMARY KEY (course_id, student_id),
FOREIGN KEY (course_id) REFERENCES courses (course_id),
FOREIGN KEY (student_id) REFERENCES students (student_id)
);

CREATE TABLE trainers_per_course (
	course_id  INT NOT NULL,
	trainer_id INT NOT NULL,
PRIMARY KEY (course_id, trainer_id),
FOREIGN KEY (course_id) REFERENCES courses (course_id),
FOREIGN KEY (trainer_id) REFERENCES trainers (trainer_id)
);

CREATE TABLE assignments_per_course (
	course_id     int NOT NULL,
	assignment_id int NOT NULL,    
PRIMARY KEY (assignment_id, course_id),
FOREIGN KEY (assignment_id) REFERENCES assignments (assignment_id),
FOREIGN KEY (course_id) REFERENCES courses (course_id)
);

-- 4. Populate the tables with enough data
INSERT INTO students(first_name, last_name, date_of_birth, tuition_fees)
VALUES ("Alex", "Brown", "1985-01-02", 800),
("George", "Williams", "1983-07-05", 700),
("John", "Johnson", "1987-09-15", 650),
("Nick", "Jones", "1981-11-24", 450.5),
("Sam", "Smith", "1989-05-03", 250.6),
("Anna", "Moore", "1984-04-17", 740.3),
("Georgia", "Cruise", "1988-10-28", 900),
("Helen", "Miller", "1986-03-08", 250),
("Kate", "Wilson", "1982-12-16", 650),
("Maria", "Cooper", "1983-05-30", 320),
("Jason", "Davis", "1990-02-15", 1000),
("Chrys", "Adams", "1991-03-16", 950),
("Martha", "Richardson", "1992-04-17", 1200),
("Jenny", "Morgan", "1993-05-18", 880),
("Tom", "Taylor", "1995-08-23", 1150);

INSERT INTO trainers (first_name, last_name, t_subject) 
VALUES ("David", "Parker", "Hydraulic"),
("Mike", "Rogers", "Transportation"),
("Sophia", "Anderson", "Geotechnical"),
("Olivia", "Lewis", "Structural");

INSERT INTO courses (title, stream, c_type,	start_date,	end_date)
VALUES ("Foundations", "Geotechnical", "Full time", "2019-01-05", "2019-05-20"),
("Reinforced Concrete", "Structural", "Part time", "2019-03-20", "2019-08-10"),
("Hydrology", "Hydraulic", "Full time", "2019-01-06", "2019-05-18"),
("Road Construction", "Transportation", "Part time", "2019-03-30", "2019-08-12");

INSERT INTO assignments (title, a_description, sub_DateTime)
VALUES ("Soil Mechanics", "Plane-strain and axis-symmetric loading", "2019-01-17 15:00:00"),
("Earthquake Engineering", "Fundamental principles of earthquake engineering and seismic design", "2019-02-20 15:00:00"),
("Computational Geotechnics", "Simple numerical methods", "2019-03-10 15:00:00"),
("Laboratory on Materials", "Calculating and statistical analysis", "2019-04-06 15:00:00"),
("Strength Of Materials", "Pure Bending, Eccentric Axial Loading", "2019-05-22 15:00:00"),
("Limit states", "Ultimate and serviceability limit states", "2019-06-13 15:00:00"),
("Hydrometry", "Analysis and processing of hydrometric data", "2019-07-03 15:00:00"),
("Hydrostatics", "Hydrostatics equation", "2019-08-24 15:00:00"),
("External and Internal Harbour Works", "Design of projects with slopes", "2019-09-12 15:00:00"), 
("Probability and Statistics", "Random sample and sampling distributions", "2019-10-01 15:00:00"), 
("Railway Engineering", "Static and dynamic loads and relevant calculations", "2019-11-29 15:00:00"), 
("Traffic Flow", "Statistical analysis distributions and sampling regarding traffic counts", "2019-12-07 15:00:00");

INSERT INTO trainers_per_course (course_id, trainer_id)
VALUES (1, 3), (2, 4), (3, 1), (4, 2);

INSERT INTO students_per_course (course_id, student_id)
VALUES 	(1, 1), (1, 2), (1, 3), (1, 4), (1, 14),
		(2, 5), (2, 6), (2, 7), (2, 8), (2, 1),
		(3, 9), (3, 10), (3, 11), (3, 12), (3, 7),
		(4, 13), (4, 14), (4, 15), (4, 9);

INSERT INTO assignments_per_course (course_id, assignment_id)
        VALUES 	(1, 1), (1, 2), (1, 3), 
		(2, 4), (2, 5), (2, 6),
		(3, 7), (3, 8), (3, 9),
		(4, 10), (4, 11), (4, 12);

-- 5. Produce sql queries that output the following:
-- A list of all the students
SELECT * FROM students;

-- A list of all the trainers
SELECT * FROM trainers;

-- A list of all the assignments
SELECT * FROM assignments;

-- A list of all the courses
SELECT * FROM courses;

-- All the students per course
SELECT 
	c.course_id as courseId,
    c.title as course,
    s.student_id as studentId,
    s.first_name as fname,
    s.last_name as lname
FROM students_per_course sp
INNER JOIN students s 
	ON s.student_id = sp.student_id
INNER JOIN courses c 
	ON c.course_id = sp.course_id
ORDER BY c.course_id;

-- All the trainers per course
SELECT 
	c.course_id as courseId,
    c.title as course,
	t.trainer_id  as trainerId, 
    t.first_name as fname, 
    t.last_name as lname
FROM trainers_per_course tp
INNER JOIN trainers t 
	ON t.trainer_id = tp.trainer_id
INNER JOIN courses c 
	ON c.course_id = tp.course_id
ORDER BY c.course_id;

-- All the assignments per course
SELECT 
	c.course_id as courseId,
    c.title as course,
	a.assignment_id as assignmentId, 
    a.title AS assignment,
	a.a_description as description, 
    a.sub_DateTime AS subDateTime
FROM assignments_per_course ap
INNER JOIN assignments a 
	ON a.assignment_id = ap.assignment_id
INNER JOIN courses c 
	ON c.course_id = ap.course_id
ORDER BY c.course_id;

-- All the assignments per course per student
SELECT 
	s.student_id as studentId, 
    s.first_name as fname, 
    s.last_name as lname,
    c.course_id as courseId, 
    c.title as course,
    a.assignment_id as assignmentId, 
    a.title AS assignment
FROM assignments_per_course ap
INNER JOIN assignments a 
	ON a.assignment_id = ap.assignment_id
INNER JOIN courses c 
	ON c.course_id = ap.course_id
INNER JOIN students_per_course sp 
	ON sp.course_id = ap.course_id
INNER JOIN students s 
	ON s.student_id = sp.student_id
    ORDER BY s.student_id, c.course_id;

-- A list of students that belong to more than one courses
SELECT 
	s.student_id as studentId,
    s.first_name as fname, 
    s.last_name as lname,
	s.date_of_birth as dateOfBirth, 
    s.tuition_fees as tuitionFees,
    COUNT(sp.course_id) AS Number_of_courses
FROM students_per_course sp
INNER JOIN students s 
	ON s.student_id = sp.student_id
GROUP BY sp.student_id
HAVING Number_of_courses > 1;