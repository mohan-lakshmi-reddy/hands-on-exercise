-- Run this in MySQL Workbench (or the mysql CLI) BEFORE starting the app.
--
-- Why this is needed: application.properties has
--   spring.jpa.hibernate.ddl-auto=validate
-- "validate" means Hibernate checks the table/columns ALREADY EXIST and
-- throws an error if they don't - it will NOT create them for you. (Compare
-- to "update", used in our main Library Management System project, which
-- DOES auto-create/alter tables - a useful contrast to explain if asked
-- about ddl-auto options in the interview.)

CREATE SCHEMA IF NOT EXISTS ormlearn;
USE ormlearn;

CREATE TABLE country (
    co_code VARCHAR(2) PRIMARY KEY,
    co_name VARCHAR(50)
);

INSERT INTO country VALUES ('IN', 'India');
INSERT INTO country VALUES ('US', 'United States of America');
