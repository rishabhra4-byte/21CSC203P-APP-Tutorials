-- Run this once in MySQL before executing CourseRegistrationSearch.java
CREATE DATABASE IF NOT EXISTS college_db;
USE college_db;

DROP TABLE IF EXISTS CourseRegistration;
CREATE TABLE CourseRegistration (
    StudentID   VARCHAR(20)  NOT NULL,
    StudentName VARCHAR(100) NOT NULL,
    CourseCode  VARCHAR(20)  NOT NULL,
    CourseName  VARCHAR(100) NOT NULL,
    Semester    INT          NOT NULL,
    PRIMARY KEY (StudentID, CourseCode)
);

-- Sample data
INSERT INTO CourseRegistration VALUES
('S1001', 'Aditi Sharma', '21CSC203P', 'Advanced Programming Practice',  3),
('S1002', 'Karthik Raj',  '21CSC203P', 'Advanced Programming Practice',  3),
('S1003', 'Nikhil Menon', '21CSC203P', 'Advanced Programming Practice',  3),
('S1002', 'Karthik Raj',  '21CSC202J', 'Operating Systems',              3),
('S1004', 'Sneha Iyer',   '21CSC202J', 'Operating Systems',              3),
('S1001', 'Aditi Sharma', '21CSC201J', 'Data Structures and Algorithms', 3);
