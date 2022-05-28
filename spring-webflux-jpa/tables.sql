CREATE DATABASE `test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
use test;

CREATE TABLE IF NOT EXISTS `author` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45),
	`createdTime` datetime DEFAULT NULL,
    `updatedTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `category` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(45),
	`createdTime` datetime DEFAULT NULL,
    `updatedTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS `book` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`releaseTime` DATETIME,
	`releaseDate` DATE,
	`price` DECIMAL(19,5),
	`name` VARCHAR(45),
	`authorId` BIGINT,
	`categoryId` BIGINT,
	`createdTime` datetime DEFAULT NULL,
    `updatedTime` datetime DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci AUTO_INCREMENT = 1;