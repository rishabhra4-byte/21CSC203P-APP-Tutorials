-- Run this once in MySQL before executing ProductStore.java
CREATE DATABASE IF NOT EXISTS store_db;
USE store_db;

DROP TABLE IF EXISTS Product;
CREATE TABLE Product (
    ProductID   INT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Price       DECIMAL(10, 2) NOT NULL,
    Quantity    INT NOT NULL
);

INSERT INTO Product VALUES
(1, 'Wireless Mouse',     599.00, 25),
(2, 'USB Keyboard',       899.00,  8),
(3, 'HDMI Cable',         349.00, 40),
(4, 'Laptop Stand',      1299.00,  5),
(5, 'Webcam 1080p',      2499.00, 12);
