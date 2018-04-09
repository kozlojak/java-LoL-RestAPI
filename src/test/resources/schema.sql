drop table if exists champion;
CREATE TABLE IF NOT EXISTS `champion` (
    `id` integer primary key auto_increment,
    `name` varchar(255) NOT NULL
)
