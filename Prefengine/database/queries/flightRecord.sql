CREATE TABLE `flightRecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tripId` varchar(45) NOT NULL,
  `departure` varchar(45) DEFAULT NULL,
  `destination` varchar(45) DEFAULT NULL,
  `route` varchar(45) DEFAULT NULL,
  `stops` int(11) DEFAULT NULL,
  `departureTime` varchar(45) DEFAULT NULL,
  `arrivalTime` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `carrier` varchar(45) DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `milage` int(11) DEFAULT NULL,
  `cabin` varchar(45) DEFAULT NULL,
  `thisTrip` varchar(45) DEFAULT NULL,
  `jsonData` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=latin1;
