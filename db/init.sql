USE reins;

INSERT INTO `user` (`id`,`password`,`enabled`,`firstName`,`lastName`,`email`,`creationDate`,`lastAccessDate`) VALUES ("admin","password","1","Admin","User","","2000-01-01 00:00:00","2000-01-01 00:00:00");
INSERT INTO `user` (`id`,`password`,`enabled`,`firstName`,`lastName`,`email`,`creationDate`,`lastAccessDate`) VALUES ("user","password","1","Default","User","","2000-01-01 00:00:00","2000-01-01 00:00:00");

INSERT INTO `role` (`role`) VALUES ("ROLE_ADMIN");
INSERT INTO `role` (`role`) VALUES ("ROLE_USER");
INSERT INTO `user_role_rel` (`id`,`role`) VALUES ("admin","ROLE_ADMIN");
INSERT INTO `user_role_rel` (`id`,`role`) VALUES ("admin","ROLE_USER");