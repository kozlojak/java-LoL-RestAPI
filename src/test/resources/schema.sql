--drop table if exists champion;
--CREATE TABLE IF NOT EXISTS `champion` (
--    `id` integer primary key auto_increment,
--    `name` varchar(255) NOT NULL,
--    UNIQUE KEY `name` (`name`)
--)
--
drop table if exists user;
CREATE TABLE IF NOT EXISTS `user` (
  `id` Integer primary key AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `favoriteRoleId` Integer NOT NULL,
  `favoriteChampionId` Integer NOT NULL,
)

