DROP DATABASE IF EXISTS reins;
CREATE DATABASE reins;
ALTER SCHEMA `reins` DEFAULT CHARACTER SET utf8;

USE reins;

CREATE TABLE `role` (
  `role` varchar(50) NOT NULL,
  PRIMARY KEY  (`role`)
);

CREATE TABLE `user` (
  `id` varchar(50) NOT NULL default '',
  `password` varchar(50) NOT NULL default '',
  `enabled` tinyint(1) NOT NULL default '0',
  `firstName` varchar(100) default '',
  `lastName` varchar(100) default '',
  `email` varchar(150) default '',
  `creationDate` timestamp NOT NULL default now(),
  `lastAccessDate` timestamp NOT NULL default now(),
  PRIMARY KEY  (`id`)
);

CREATE TABLE `user_role_rel` (
  `id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`,`role`),
  FOREIGN KEY(id) REFERENCES user(id),
  FOREIGN KEY(role) REFERENCES role(role)
);


CREATE TABLE `note` (
  `id` varchar(32) NOT NULL,
  `user` VARCHAR(32) NOT NULL,
  `title` varchar(32) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `creationDate` timestamp NOT NULL default now(),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user`) REFERENCES user(`id`)
);