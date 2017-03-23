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
);

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
);

CREATE TABLE `trip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departure` varchar(45) NOT NULL,
  `destination` varchar(45) NOT NULL,
  `departure_time` timestamp NOT NULL,
  `arrival_time` timestamp NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `carrier` varchar(45) NOT NULL,
  `duration` int(4)NOT NULL,
  `mileage` int(5) NOT NULL,
  `cabin` varchar(20) NOT NULL,
  `json_data` text NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `trip_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trip_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_preferences` text NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`trip_id`) REFERENCES trip(`id`),
  FOREIGN KEY (`user_id`) REFERENCES user(`id`)
);

CREATE TEMPORARY TABLE `temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departure` varchar(45) NOT NULL,
  `destination` varchar(45) NOT NULL,
  `departure_time` timestamp NOT NULL,
  `arrival_time` timestamp NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `carrier` varchar(45) NOT NULL,
  `duration` int(4)NOT NULL,
  `mileage` int(5) NOT NULL,
  `cabin` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
);
