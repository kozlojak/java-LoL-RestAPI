drop table if exists champion;
CREATE TABLE IF NOT EXISTS `champion` (
    `id` bigint(20) NOT NULL auto_increment,
    `name` varchar(255) NOT NULL,
    UNIQUE KEY `name` (`name`),
    PRIMARY KEY (`id`)
);

drop table if exists user;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) primary key AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `favoriteRoleId` int(20) NOT NULL,
  `favoriteChampionId` bigint(20) NOT NULL,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  );

