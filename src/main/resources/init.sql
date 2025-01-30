CREATE SCHEMA IF NOT EXISTS tmdb;

USE tmdb;

CREATE TABLE IF NOT EXISTS tasks(
    id BIGINT AUTO_INCREMENT,
    name VARCHAR(100),
    status TINYINT,
    date_added DATETIME,
    PRIMARY KEY(id)
);