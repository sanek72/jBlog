
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login` varchar(45) NOT NULL COMMENT 'login user',
  `password` varchar(45) NOT NULL,
  `group` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `randpass` varchar(45) NOT NULL COMMENT 'cookie login password',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8

