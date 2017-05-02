DELIMITER //

--
DROP FUNCTION IF EXISTS `set_flights_satisfaction_degree`;
/* Fills up the satisfaction degree for each flight. */
CREATE FUNCTION set_flights_satisfaction_degree () RETURNS DOUBLE
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE f_id INT;
	DECLARE f_price_sat_deg DOUBLE;
	DECLARE f_stop_sat_deg DOUBLE;
	DECLARE f_duration_sat_deg DOUBLE;
	DECLARE f_mileage_sat_deg DOUBLE;
	DECLARE satisfation_deg DOUBLE;
	DECLARE max_satisfation_deg DOUBLE;
	DECLARE flights CURSOR FOR SELECT id,price_sat_deg,stop_sat_deg,duration_sat_deg,mileage_sat_deg FROM temp_flight_record;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	OPEN flights;
	
	SET max_satisfation_deg = 0;
	
	loop_flights: LOOP
		FETCH flights INTO f_id,f_price_sat_deg,f_stop_sat_deg,f_duration_sat_deg,f_mileage_sat_deg;
		
		IF done THEN
			LEAVE loop_flights;
		END IF;
		
		SELECT get_satisfaction_degree(f_id,f_price_sat_deg,f_stop_sat_deg,f_duration_sat_deg,f_mileage_sat_deg) INTO satisfation_deg;
		
		UPDATE temp_flight_record
	    SET
	       flight_sat_deg = satisfation_deg
    	WHERE id = f_id;
    	
    	IF satisfation_deg > max_satisfation_deg THEN
    		SET max_satisfation_deg = satisfation_deg;
    	END IF;
  	END LOOP;
	
	CLOSE flights;
	
	RETURN max_satisfation_deg;
END //

--
DROP FUNCTION IF EXISTS `set_attributes_satisfaction_degree`;
/* Fills up the satisfaction degree for each attribute for each flight. */
CREATE FUNCTION set_attributes_satisfaction_degree (min_price DOUBLE,max_price DOUBLE,up_stops INT,min_duration DOUBLE,max_duration DOUBLE,min_mileage DOUBLE,max_mileage DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE status DOUBLE DEFAULT 0;
	DECLARE status_temp DOUBLE DEFAULT 0;
	DECLARE price_deg DOUBLE;
	DECLARE stops_deg DOUBLE;
	DECLARE duration_deg DOUBLE;
	DECLARE mileage_deg DOUBLE;
	DECLARE f_mileage DOUBLE;
	DECLARE f_price DOUBLE;
	DECLARE f_stops DOUBLE;
	DECLARE f_duration DOUBLE;
	DECLARE f_id INT;
	
	DECLARE flights CURSOR FOR SELECT id,price,stops,duration,mileage FROM aux_temp_flight_record;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	IF min_price IS NULL THEN
		SELECT get_min_price() INTO min_price;
	END IF;
	
	IF max_price IS NULL THEN
		SELECT get_max_price() INTO max_price;
	END IF;
	
	IF up_stops IS NULL THEN
		SELECT get_max_stops() INTO up_stops;
	END IF;
	
	IF min_duration IS NULL THEN
		SELECT get_min_duration() INTO min_duration;
	END IF;
	
	IF max_duration IS NULL THEN
		SELECT get_max_duration() INTO max_duration;
	END IF;
	
	IF min_mileage IS NULL THEN
		SELECT get_min_mileage() INTO min_mileage;
	END IF;
	
	IF max_mileage IS NULL THEN
		SELECT get_max_mileage() INTO max_mileage;
	END IF;
	
	OPEN flights;
	
	loop_flights: LOOP
		FETCH flights INTO f_id,f_price,f_stops,f_duration,f_mileage;
		
		IF done THEN
			LEAVE loop_flights;
		END IF;
		
		SELECT calculate_price_satisfaction (max_price , min_price , f_price) INTO price_deg;
		
		SELECT calculate_stops_satisfaction (f_stops, up_stops) INTO stops_deg;
		
		SELECT calculate_duration_satisfaction (max_duration, min_duration, f_duration) INTO duration_deg;
		
		SELECT calculate_mileage_satisfaction (max_mileage, min_mileage, f_mileage) INTO mileage_deg;
		
		SELECT set_attributes_satisfaction(f_id, price_deg, stops_deg, duration_deg, mileage_deg) INTO status;
    	
  	END LOOP;
	
	CLOSE flights;
	
	RETURN status;
END //

--
DROP FUNCTION IF EXISTS `set_attributes_satisfaction`;
/* Sets the satisfaction degree of all attributes for one flight. */
CREATE FUNCTION set_attributes_satisfaction(f_id INT, price_deg DOUBLE, stops_deg DOUBLE, duration_deg DOUBLE, mileage_deg DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE status DOUBLE;
	SET status = 1;
	
	UPDATE temp_flight_record
	SET
		price_sat_deg = price_deg,
		stop_sat_deg = stops_deg,
		duration_sat_deg = duration_deg,
		mileage_sat_deg = mileage_deg
	WHERE id = f_id;
	
	RETURN status;
END //

--
DROP FUNCTION IF EXISTS `get_satisfaction_degree`;
/* Calculate the satisfaction degree for one flight, given flight id. */
CREATE FUNCTION get_satisfaction_degree (f_id INT,f_price DOUBLE,f_stops DOUBLE,f_duration DOUBLE,f_mileage DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE att_id INT;
	DECLARE satisfaction DOUBLE;
	DECLARE att_sat_degree DOUBLE;
	DECLARE attr CHAR(20);
	DECLARE attribute1 CHAR(20);
	DECLARE attribute2 CHAR(20);
	DECLARE logic_operator CHAR(20);
	DECLARE attributes CURSOR FOR SELECT att_id,attribute FROM attributes_temp_table;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	SET satisfaction = 0;
	SET att_sat_degree = 0;
	
	OPEN attributes;
	
	FETCH attributes INTO att_id,attr;
	SET attribute1 = attr;
	
	loop_attributes: LOOP
		FETCH attributes INTO att_id,attr;
		
	    IF done THEN
			LEAVE loop_attributes;
		END IF;
		
		SET logic_operator = UCASE(attr);
		
		FETCH attributes INTO att_id,attr;
		
		SET attribute2 = LCASE(attr);
		
		IF attribute2 = "price" THEN
			SET att_sat_degree = f_price;
		END IF;
		
		IF attribute2 = "stops" THEN
			SET att_sat_degree = f_stops;
		END IF;
		
		IF attribute2 = "duration" THEN
			SET att_sat_degree = f_duration;
		END IF;
		
		IF attribute2 = "mileage" THEN
			SET att_sat_degree = f_mileage;
		END IF;
		
		IF logic_operator = "AND" THEN
			SELECT get_conjunctional_satisfaction(satisfaction, att_sat_degree) INTO satisfaction;
		END IF;
		
		IF logic_operator = "OR" THEN
			SELECT get_disjunctional_satisfaction(satisfaction, att_sat_degree) INTO satisfaction;
		END IF;
		
		IF logic_operator = "COMPROMISE" THEN
			SELECT get_compromised_satisfaction(satisfaction, att_sat_degree) INTO satisfaction;
		END IF;
	END LOOP;
	
	CLOSE attributes;
	
	RETURN satisfaction;
END //

--
DROP FUNCTION IF EXISTS `get_conjunctional_satisfaction`;
/*Returns the satisfaction degree for conjunctions. */
CREATE FUNCTION get_conjunctional_satisfaction(satisfaction1 DOUBLE, satisfaction2 DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	IF satisfaction1 > satisfaction2 THEN
		SET degree = satisfaction1;
	ELSE
		SET degree = satisfaction2;
	END IF;
	
	RETURN degree;
END //

--
DROP FUNCTION IF EXISTS `get_disjunctional_satisfaction`;
/*Returns the satisfaction degree for disjunctions. */
CREATE FUNCTION get_disjunctional_satisfaction(satisfaction1 DOUBLE, satisfaction2 DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	IF satisfaction1 < satisfaction2 THEN
		SET degree = satisfaction1;
	ELSE
		SET degree = satisfaction2;
	END IF;
	
	RETURN degree;
END //

--
DROP FUNCTION IF EXISTS `get_compromised_satisfaction`;
/*Returns the satisfaction degree for compromises. */
CREATE FUNCTION get_compromised_satisfaction(satisfaction1 DOUBLE, satisfaction2 DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	SET degree = (satisfaction1 + satisfaction2)/2;
	
	RETURN degree;
END //

--
DROP FUNCTION IF EXISTS `calculate_mileage_satisfaction`;
/* Returns the satisfaction degree for mileage attribute. */
CREATE FUNCTION calculate_mileage_satisfaction (max_mileage DOUBLE, min_mileage DOUBLE, current_mileage DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	IF current_mileage <= min_mileage THEN
		SET degree = 1;
	ELSEIF current_mileage > max_mileage THEN
		SET degree = 0;
	ELSE
		SET degree = (max_mileage-current_mileage)/(max_mileage-min_mileage);
	END IF;
	
	RETURN ROUND(degree,1);
END //

--
DROP FUNCTION IF EXISTS `calculate_duration_satisfaction`;
/* Returns the satisfaction degree for duration attribute. */
CREATE FUNCTION calculate_duration_satisfaction (max_duration DOUBLE, min_duration DOUBLE, current_duration DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	IF current_duration <= min_duration THEN
		SET degree = 1;
	ELSEIF current_duration > max_duration THEN
		SET degree = 0;
	ELSE
		SET degree = (max_duration-current_duration)/(max_duration-min_duration);
	END IF;
	
	RETURN ROUND(degree,2);
END //

--
DROP FUNCTION IF EXISTS `calculate_price_satisfaction`;
/* Returns the satisfaction degree for price attribute. */
CREATE FUNCTION calculate_price_satisfaction (max_price DOUBLE, min_price DOUBLE, current_price DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	IF current_price <= min_price THEN
		SET degree = 1;
	ELSEIF current_price > max_price THEN
		SET degree = 0;
	ELSE
		SET degree = (max_price-current_price)/(max_price-min_price);
	END IF;
	
	RETURN ROUND(degree,2);
END //

--
DROP FUNCTION IF EXISTS `calculate_stops_satisfaction`;
/* Returns the satisfaction degree for stops attribute. */
CREATE FUNCTION calculate_stops_satisfaction (stops INT, u_stops INT) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	SET degree = 0;
	
	IF stops <= u_stops THEN
    	SET degree = 1.0;
    ELSE
    	IF stops = u_stops + 1 THEN
 			SET degree = 0.75;
		ELSEIF stops = u_stops + 2 THEN
			SET degree = 0.50;
		ELSEIF stops = u_stops + 3 THEN
			SET degree = 0.30;
		ELSEIF stops = u_stops + 4 THEN
			SET degree = 0.10;
		ELSE
			SET degree = 0;
		END IF;
	END IF;
	
	RETURN degree;
END //

--
DROP FUNCTION IF EXISTS `create_attributes_temp_table`;
/* Create the temp table to hold attributes and operators for logical operations. */
CREATE FUNCTION create_attributes_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS attributes_temp_table;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE IF NOT EXISTS attributes_temp_table (
				attribute_id int NOT NULL AUTO_INCREMENT,
				attribute VARCHAR(20),
			  	PRIMARY KEY (attribute_id));
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `insert_attribute_into_temp_table`;
/* Fills up the attribute temp table woth one entry. */
CREATE FUNCTION insert_attribute_into_temp_table(attr VARCHAR(20)) RETURNS INT
BEGIN
	DECLARE id INT;
	SET id = 0;
	
	INSERT INTO attributes_temp_table (attribute)
				 VALUES (attr);
	
	SELECT LAST_INSERT_ID() INTO id;
	
	RETURN id;
END //

--
DROP FUNCTION IF EXISTS `create_flight_record_temp_table`;
/* Create temp table to hold the flight records for operations and queries. */
CREATE FUNCTION create_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS `temp_flight_record`;
	
	/*Create temp table*/
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
  				PRIMARY KEY (`id`));
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to set attribute sat deg. */
CREATE FUNCTION create_aux_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux1_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get minimum price. */
CREATE FUNCTION create_aux1_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux1_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux1_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux2_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get maximum price. */
CREATE FUNCTION create_aux2_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux2_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux2_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux3_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get minimum stop. */
CREATE FUNCTION create_aux3_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux3_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux3_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux4_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get maximum stop. */
CREATE FUNCTION create_aux4_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux4_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux4_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux5_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get minimum duration. */
CREATE FUNCTION create_aux5_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux5_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux5_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux6_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get maximum duration. */
CREATE FUNCTION create_aux6_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux6_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux6_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux7_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get minimum mileage. */
CREATE FUNCTION create_aux7_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux7_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux7_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `create_aux8_flight_record_temp_table`;
/*Create a auxiliary table duplicated of flight record to loop thought to get maximum mileage. */
CREATE FUNCTION create_aux8_flight_record_temp_table () RETURNS INT
BEGIN
	DECLARE result INT;
	SET result = 1;
	/*Drop the temp table*/
	DROP TEMPORARY TABLE IF EXISTS aux8_temp_flight_record;
	
	/*Create temp table*/
	CREATE TEMPORARY TABLE aux8_temp_flight_record SELECT * FROM temp_flight_record;
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `insert_record_in_flight_temp_table`;
/*Fills one record into flight temp table. */
CREATE FUNCTION insert_record_in_flight_temp_table(f_tripId VARCHAR(45),f_departure VARCHAR(45),f_destination VARCHAR(45),f_stops INT,f_departureTime VARCHAR(45),f_arrivalTime VARCHAR(45),f_price DOUBLE,f_carrier VARCHAR(45),f_duration DOUBLE,f_mileage INT,f_cabin VARCHAR(45),f_thisTrip VARCHAR(45),f_jsonData JSON,f_departureCityName VARCHAR(45),f_destinationCityName VARCHAR(45),f_carrierName VARCHAR(45))
RETURNS INT
BEGIN
	DECLARE id INT;
	SET id = 0;
	
	INSERT INTO temp_flight_record (tripId,departure,destination,stops,departureTime,arrivalTime,price,carrier,duration,mileage,cabin,thisTrip,jsonData,departureCityName,destinationCityName,carrierName)
				 VALUES (f_tripId,f_departure,f_destination,f_stops,f_departureTime,f_arrivalTime,f_price,f_carrier,f_duration,f_mileage,f_cabin,f_thisTrip,f_jsonData,f_departureCityName,f_destinationCityName,f_carrierName);
	
	SELECT LAST_INSERT_ID() INTO id;
	
	RETURN id;
END //

--
DROP FUNCTION IF EXISTS `get_min_price`;
/* Find the minimum price from list of flights. */
CREATE FUNCTION get_min_price() RETURNS DOUBLE
BEGIN
	DECLARE min_price DOUBLE;
	
	SET min_price = 0;
	
	SELECT MIN(price) INTO min_price
	FROM aux1_temp_flight_record;
	
	RETURN min_price;
END //

--
DROP FUNCTION IF EXISTS `get_max_price`;
/* Find the maximum price from list of flights. */
CREATE FUNCTION get_max_price() RETURNS DOUBLE
BEGIN
	DECLARE max_price DOUBLE;
	
	SET max_price = 0;
	
	SELECT MAX(price) INTO max_price
	FROM aux2_temp_flight_record;
	
	RETURN max_price;
END //

--
DROP FUNCTION IF EXISTS `get_min_stops`;
/* Find the minimum stop from list of flights. */
CREATE FUNCTION get_min_stops() RETURNS DOUBLE
BEGIN
	DECLARE min_stops DOUBLE;
	
	SET min_stops = 0;
	
	SELECT MIN(stops) INTO min_stops
	FROM aux3_temp_flight_record;
	
	RETURN min_stops;
END //

--
DROP FUNCTION IF EXISTS `get_max_stops`;
/* Find the maximum stop from list of flights. */
CREATE FUNCTION get_max_stops() RETURNS DOUBLE
BEGIN
	DECLARE max_stops DOUBLE;
	
	SET max_stops = 0;
	
	SELECT MAX(stops) INTO max_stops
	FROM aux4_temp_flight_record;
	
	RETURN max_stops;
END //

--
DROP FUNCTION IF EXISTS `get_min_duration`;
/* Find the minimum duration from list of flights. */
CREATE FUNCTION get_min_duration() RETURNS DOUBLE
BEGIN
	DECLARE min_duration DOUBLE;
	
	SET min_duration = 0;
	
	SELECT MIN(duration) INTO min_duration
	FROM aux5_temp_flight_record;
	
	RETURN min_duration;
END //

--
DROP FUNCTION IF EXISTS `get_max_duration`;
/* Find the maximum duration from list of flights. */
CREATE FUNCTION get_max_duration() RETURNS DOUBLE
BEGIN
	DECLARE max_duration DOUBLE;
	
	SET max_duration = 0;
	
	SELECT MAX(duration) INTO max_duration
	FROM aux6_temp_flight_record;
	
	RETURN max_duration;
END //

--
DROP FUNCTION IF EXISTS `get_min_mileage`;
/* Find the minimum mileage from list of flights. */
CREATE FUNCTION get_min_mileage() RETURNS DOUBLE
BEGIN
	DECLARE min_mileage DOUBLE;
	
	SET min_mileage = 0;
	
	SELECT MIN(mileage) INTO min_mileage
	FROM aux7_temp_flight_record;
	
	RETURN min_mileage;
END //

--
DROP FUNCTION IF EXISTS `get_max_mileage`;
/* Find the maximum mileage from list of flights. */
CREATE FUNCTION get_max_mileage() RETURNS DOUBLE
BEGIN
	DECLARE max_mileage DOUBLE;
	
	SET max_mileage = 0;
	
	SELECT MAX(mileage) INTO max_mileage
	FROM aux8_temp_flight_record;
	
	RETURN max_mileage;
END //

--
DELIMITER ;
