DELIMITER //

--
DROP PROCEDURE IF EXISTS `get_satisfactory_flights`;

/*Receives the max flight's satisfaction degree to select on.*/
CREATE PROCEDURE get_satisfactory_flights (IN sat_deg DOUBLE, OUT f_tripId varchar(45),OUT f_departure varchar(45),OUT f_destination varchar(45),OUT f_stops int(11),OUT f_departureTime varchar(45),OUT f_arrivalTime varchar(45),OUT f_price double,OUT f_carrier varchar(45),OUT f_duration double,f_mileage int(11),OUT f_cabin varchar(45),OUT f_thisTrip varchar(45),OUT f_departureCityName varchar(45),OUT f_destinationCityName varchar(45),OUT f_carrierName varchar(45))
BEGIN
	SELECT tripId,departure,destination,stops,departureTime,arrivalTime,price,carrier,duration,mileage,cabin,thisTrip,departureCityName,destinationCityName,carrierName
	INTO f_tripId,f_departure,f_destination,f_stops,f_departureTime,f_arrivalTime,f_price,f_carrier,f_duration,f_mileage,f_cabin,f_thisTrip,f_departureCityName,f_destinationCityName,f_carrierName
	FROM temp_flight_record AS records
	WHERE flight_sat_deg = sat_deg;
END //

--
DELIMITER ;
