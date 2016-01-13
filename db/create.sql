DROP DATABASE IF EXISTS zy_blog;

CREATE DATABASE zy_blog;
ALTER SCHEMA zy_blog DEFAULT CHARACTER SET utf8;

USE zy_blog;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL,
  `password` varchar(32) NOT NULL,
  `enabled` tinyint(1) NOT NULL default '0',
  `name` varchar(32) NOT NULL,
  `creationDate` timestamp NOT NULL default now(),
  `lastAccessDate` timestamp NOT NULL default now(),
  PRIMARY KEY  (`id`)
);

CREATE TABLE `blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` varchar(32) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `creationDate` timestamp NOT NULL default now(),
  `modifiedDate` timestamp NOT NULL default now(),
  PRIMARY KEY (`id`)
);

CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  `content` VARCHAR(1024) NOT NULL,
  `parent` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(`id`) REFERENCES blog(`id`)
);