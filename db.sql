/* Create DB and user */

CREATE DATABASE  IF NOT EXISTS `spring_starter`;
USE `spring_starter`;

CREATE USER 'spring_starter'@'%' IDENTIFIED BY 'spring_starter'  ;
GRANT ALL PRIVILEGES ON *.* TO 'spring_starter'@'%';
FLUSH PRIVILEGES ;

