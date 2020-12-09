CREATE DATABASE employees;

USE employees;

CREATE TABLE employee(
	id int not null auto_increment primary key,
	name varchar(50),
	gender varchar(10),
	experience_in_years int,
	experience_in_months int,
	experience_in_days int
);
