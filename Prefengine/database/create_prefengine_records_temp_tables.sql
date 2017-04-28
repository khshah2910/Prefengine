DROP TABLE IF EXISTS temp_attributes_records;

CREATE TEMPORARY TABLE IF NOT EXISTS temp_attributes_records (
	`att_id` int NOT NULL AUTO_INCREMENT,
	`attribute` VARCHAR(20),
	PRIMARY KEY (`att_id`));
	
DROP TABLE IF EXISTS temp_flight_record;

CREATE TEMPORARY TABLE IF NOT EXISTS temp_flight_record (
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
  `mileage` int(11) DEFAULT NULL,
  `cabin` varchar(45) DEFAULT NULL,
  `thisTrip` varchar(45) DEFAULT NULL,
  `jsonData` json DEFAULT NULL,
  `departureCityName` varchar(45) DEFAULT NULL,
  `destinationCityName` varchar(45) DEFAULT NULL,
  `carrierName` varchar(45) DEFAULT NULL,
  `price_sat_deg` double,
  `stop_sat_deg` double,
  `duration_sat_deg` double,
  `mileage_sat_deg` double,
  `flight_sat_deg` double,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=latin1;
