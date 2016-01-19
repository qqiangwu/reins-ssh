DROP DATABASE IF EXISTS zy_blog;

CREATE DATABASE zy_blog;
ALTER SCHEMA zy_blog DEFAULT CHARACTER SET utf8;

USE zy_blog;

CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL UNIQUE,
  `password` varchar(32) NOT NULL,
  `enabled` tinyint(1) NOT NULL default '0',
  `name` varchar(32) NOT NULL,
  `creationDate` timestamp NOT NULL default now(),
  `lastAccessDate` timestamp NOT NULL default now(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` INT NOT NULL,
  `title` varchar(32) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `creationDate` timestamp NOT NULL default now(),
  `modifiedDate` timestamp NOT NULL default now(),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user`) REFERENCES user(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(1024) NOT NULL,
  `creationDate` timestamp NOT NULL default now(),
  `blog` INT NOT NULL,
  `user` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`blog`) REFERENCES blog(`id`) ON DELETE CASCADE ,
  FOREIGN KEY (`user`) REFERENCES user(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `blog` ADD COLUMN `comment_count` INT NOT NULL DEFAULT 0;
ALTER TABLE `blog` ADD COLUMN `view_count` INT NOT NULL DEFAULT 0;
ALTER TABLE `user` ADD COLUMN `comment_count` INT NOT NULL DEFAULT 0;
ALTER TABLE `user` ADD COLUMN `blog_count` INT NOT NULL DEFAULT 0;