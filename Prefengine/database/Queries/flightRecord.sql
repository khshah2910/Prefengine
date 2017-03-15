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
