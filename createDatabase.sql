    -- Tworzenie bazy danych
CREATE DATABASE IF NOT EXISTS SalonGier;
USE SalonGier;


CREATE USER 'owner'@'localhost' IDENTIFIED BY 'salon123';
GRANT ALL PRIVILEGES ON SalonGier.* TO 'owner'@'localhost';
FLUSH PRIVILEGES;
