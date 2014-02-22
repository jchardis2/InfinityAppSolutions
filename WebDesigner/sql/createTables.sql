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

CREATE TABLE IF NOT EXISTS `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orgid` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL COMMENT 'java or php',
  `descriptor` varchar(50) NOT NULL COMMENT '''main'', ''branch-''uid, etc',
  PRIMARY KEY (`id`),
  KEY `orgid` (`orgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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

CREATE TABLE IF NOT EXISTS `widgets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orgid` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `widgettypeid` bigint(20) NOT NULL,
  `iscontainer` tinyint(1) NOT NULL COMMENT 'true if an object can be dropped into this object',
  `element` text NOT NULL COMMENT 'the html element',
  `droppableTarget` text NOT NULL COMMENT 'A descriptor of the element that will have other elements dropped into it, this could be a class, id, or element type',
  PRIMARY KEY (`id`),
  KEY `widgettypeid` (`widgettypeid`),
  KEY `orgid` (`orgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `widget_types` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `orgid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`orgid`),
  KEY `orgid` (`orgid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


ALTER TABLE `org_users`
  ADD CONSTRAINT `org_users_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `org_users_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `org` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `project`
  ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `org` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_name`) REFERENCES `roles` (`role`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `widgets`
  ADD CONSTRAINT `widgets_ibfk_2` FOREIGN KEY (`widgettypeid`) REFERENCES `widget_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `widgets_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `org` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `widget_types`
  ADD CONSTRAINT `widget_types_ibfk_1` FOREIGN KEY (`orgid`) REFERENCES `org` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

