CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `saltkey` varchar(45) NOT NULL,
  `security_question` varchar(45) DEFAULT NULL,
  `security_question_answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `flightRecord` (
  `id` int(11) NOT NULL,
  `leaving_from` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `leaving_time` datetime DEFAULT NULL,
  `arrival_time` datetime DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `carrier` varchar(45) DEFAULT NULL,
  `travel_duration` datetime DEFAULT NULL,
  `milage` double DEFAULT NULL,
  `class` varchar(45) DEFAULT NULL,
  `on_time_performance` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
