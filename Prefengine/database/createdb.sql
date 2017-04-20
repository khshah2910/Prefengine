--Creates the database tables

CREATE TABLE `AirlineRanks` (
  `Rank` int(11) NOT NULL,
  `AirlineName` varchar(45) DEFAULT NULL,
  `AirlineCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Rank`)
) ;

CREATE TABLE `airlines` (
  `iata` text,
  `name` text
);

CREATE TABLE `flightRecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tripId` varchar(45) NOT NULL,
  `departure` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `stops` int(11) DEFAULT NULL,
  `departureTime` varchar(45) DEFAULT NULL,
  `arrivalTime` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `carrier` varchar(45) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `milage` int(11) DEFAULT NULL,
  `cabin` varchar(45) DEFAULT NULL,
  `thisTrip` varchar(45) DEFAULT NULL,
  `jsonData` text DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `security_question` varchar(45) DEFAULT NULL,
  `security_question_answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ;
