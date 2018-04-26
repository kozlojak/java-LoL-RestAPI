drop table if exists champion;
CREATE TABLE IF NOT EXISTS `champion` (
    `id` bigint(20) NOT NULL auto_increment,
    `name` varchar(255) NOT NULL,
    UNIQUE KEY `name` (`name`),
    PRIMARY KEY (`id`)
);

drop table if exists user;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `favoriteRoleId` int(20) NOT NULL,
  `favoriteChampionId` bigint(20) NOT NULL,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  PRIMARY KEY (`id`)
  );

  drop table if exists item_page;
  CREATE TABLE IF NOT EXISTS `item_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pagename` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `championId` bigint(20) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `item_order` (
  `itemPageId` bigint(20) DEFAULT NULL,
  `itemId` bigint(20) DEFAULT NULL,
  `itemOrder` int(20) DEFAULT NULL,
);



