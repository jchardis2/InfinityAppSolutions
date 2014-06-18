CREATE TABLE IF NOT EXISTS `videofolder` (
  `videofolderid` bigint(20) NOT NULL,
  `parentfolderid` bigint(20) NOT NULL DEFAULT '1',
  `name` varchar(200) NOT NULL,
  `path` varchar(200) NOT NULL,
  `ismovie` tinyint(1) NOT NULL,
  `isshow` tinyint(1) NOT NULL,
  `isseason` tinyint(1) NOT NULL,
  PRIMARY KEY (`videofolderid`,`parentfolderid`,`name`,`path`),
  UNIQUE KEY `name` (`name`,`path`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
INSERT INTO `videofolder` (`videofolderid`, `parentfolderid`, `name`, `path`, `ismovie`, `isshow`, `isseason`) VALUES
(1, 1, 'videos', '/videos', 0, 0, 0);
ALTER TABLE  `videofolder` CHANGE  `videofolderid`  `videofolderid` BIGINT( 20 ) NOT NULL AUTO_INCREMENT ;
  
ALTER TABLE  `videofolder` ADD FOREIGN KEY (  `parentfolderid` ) REFERENCES  `webvideo`.`videofolder` (
`videofolderid`) ON DELETE CASCADE ON UPDATE CASCADE ;

CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `org_users` (
  `orgid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `createProjects` tinyint(1) NOT NULL DEFAULT '0',
  `deleteProjects` tinyint(1) NOT NULL DEFAULT '0',
  `editProjects` tinyint(1) NOT NULL DEFAULT '0',
  `adduser` tinyint(1) NOT NULL DEFAULT '0',
  `addadmin` tinyint(1) NOT NULL DEFAULT '0',
  `editAllProjects` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`orgid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(128) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `user_role` (
  `user_username` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  UNIQUE KEY `user_id` (`user_username`),
  KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `video` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `videofolderid` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `type` varchar(10) NOT NULL COMMENT '1080p 720p Blueray etc',
  `url` varchar(255) NOT NULL,
  `file` longtext NOT NULL,
  `hash` varchar(128) NOT NULL,
  `videoimageid` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `videoimageid` (`videoimageid`),
  KEY `videofolderid` (`videofolderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `videoimage` (
  `videoimageid` bigint(20) NOT NULL AUTO_INCREMENT,
  `imageurl` longtext NOT NULL,
  PRIMARY KEY (`videoimageid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `servervideo` (
  `servervideoid` bigint(20) NOT NULL AUTO_INCREMENT,
  `videoid` bigint(20) NOT NULL,
  `serverpath` longtext NOT NULL,
  `serverurl` longtext NOT NULL,
  PRIMARY KEY (`servervideoid`),
  UNIQUE KEY `videoid` (`videoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

ALTER TABLE `org_users`
  ADD CONSTRAINT `org_users_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `org_users_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `org` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_name`) REFERENCES `roles` (`role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
  
ALTER TABLE `video`
  ADD CONSTRAINT `video_ibfk_2` FOREIGN KEY (`videofolderid`) REFERENCES `videofolder` (`videofolderid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `video_ibfk_1` FOREIGN KEY (`videoimageid`) REFERENCES `videoimage` (`videoimageid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `servervideo`
  ADD CONSTRAINT `servervideo_ibfk_1` FOREIGN KEY (`videoid`) REFERENCES `video` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
