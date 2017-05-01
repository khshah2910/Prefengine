DELIMITER //

--
DROP PROCEDURE IF EXISTS `get_satisfactory_flights`;
/* Receives the max flight's satisfaction degree to select on. */
/* Returns the list of flights that satisfies the received parameter. */
CREATE PROCEDURE get_satisfactory_flights (IN sat_deg DOUBLE)
BEGIN
	SELECT tripId,departure,destination,stops,departureTime,arrivalTime,price,carrier,duration,mileage,cabin,thisTrip,departureCityName,destinationCityName,carrierName,price_sat_deg,stop_sat_deg,duration_sat_deg,mileage_sat_deg,flight_sat_deg
	FROM temp_flight_record
	WHERE flight_sat_deg = sat_deg;
END //

--
DELIMITER ;
