
CREATE DATABASE IF NOT EXISTS car_gallery;
USE car_gallery;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    surname VARCHAR(50),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(50)
);


CREATE TABLE IF NOT EXISTS cars (
    brand VARCHAR(50),
    model VARCHAR(50),
    km INT,
    fault_status VARCHAR(50),
    image_path VARCHAR(255),
    sale_price DOUBLE

);


CREATE TABLE IF NOT EXISTS sold_cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    km INT,
    fault_status VARCHAR(50),
    customer_name VARCHAR(50),
    customer_surname VARCHAR(50),
    customer_email VARCHAR(100),
    customer_phone VARCHAR(20),
    sale_price DOUBLE
);