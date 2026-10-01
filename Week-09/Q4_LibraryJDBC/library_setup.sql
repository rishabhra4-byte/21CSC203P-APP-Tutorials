-- Run this once in MySQL before executing LibraryManagement.java
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

DROP TABLE IF EXISTS Book;
CREATE TABLE Book (
    BookID       INT PRIMARY KEY,
    Title        VARCHAR(100) NOT NULL,
    Author       VARCHAR(100) NOT NULL,
    Price        DECIMAL(8, 2) NOT NULL,
    Availability VARCHAR(20)  NOT NULL DEFAULT 'Available'
);

INSERT INTO Book VALUES
(101, 'Let Us C',                    'Yashavant Kanetkar', 399.00, 'Available'),
(102, 'Head First Java',             'Kathy Sierra',       650.00, 'Available'),
(103, 'Clean Code',                  'Robert C. Martin',   799.00, 'Issued'),
(104, 'Database System Concepts',    'Abraham Silberschatz', 925.00, 'Available');
