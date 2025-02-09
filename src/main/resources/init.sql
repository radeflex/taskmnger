CREATE SCHEMA IF NOT EXISTS tmdb;

USE tmdb;

CREATE TABLE IF NOT EXISTS users(
  id BIGINT AUTO_INCREMENT,
  username VARCHAR(100),
  password_hash VARCHAR(100),
  full_name VARCHAR(100),
  email VARCHAR(100),
  github VARCHAR(100),
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS tasks(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(100),
    status TINYINT,
    date_added DATETIME,
    owner VARCHAR(100),
    PRIMARY KEY(id)
);