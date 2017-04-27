DELIMITER //

--
DROP FUNCTION IF EXISTS `set_flights_satisfaction_degree`;

CREATE FUNCTION set_flights_satisfaction_degree () RETURNS DOUBLE
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE f_id INT;
	DECLARE f_price_sat_deg DOUBLE;
	DECLARE f_stop_sat_deg DOUBLE;
	DECLARE f_duration_sat_deg DOUBLE;
	DECLARE f_mileage_sat_deg DOUBLE;
	DECLARE satisfation_deg DOUBLE;
	DECLARE flights CURSOR FOR SELECT id,price_sat_deg,stop_sat_deg,duration_sat_deg,mileage_sat_deg FROM temp_flight_record;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	OPEN flights;
	
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
    	
  	END LOOP;
	
	CLOSE flights;
	
	RETURN 0;
END //

--
DROP FUNCTION IF EXISTS `set_attributes_satisfaction_degree`;

CREATE FUNCTION set_attributes_satisfaction_degree (max_price DOUBLE,min_price DOUBLE,n_stops INT,max_duration DOUBLE,min_duration DOUBLE,max_mileage DOUBLE,min_mileage DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE done BOOLEAN DEFAULT FALSE;
	DECLARE f_id INT;
	DECLARE f_price DOUBLE;
	DECLARE f_stops DOUBLE;
	DECLARE f_duration DOUBLE;
	DECLARE f_mileage DOUBLE;
	DECLARE price_deg DOUBLE;
	DECLARE stops_deg DOUBLE;
	DECLARE duration_deg DOUBLE;
	DECLARE mileage_deg DOUBLE;
	DECLARE flights CURSOR FOR SELECT id,price,stops,duration,mileage FROM temp_flight_record;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
	OPEN flights;
	
	loop_flights: LOOP
		FETCH flights INTO f_id,f_price,f_stops,f_duration,f_mileage;
		
		IF done THEN
			LEAVE loop_flights;
		END IF;
		
		SELECT calculate_price_satisfaction (max_price , min_price , f_price) INTO price_deg;
		
		SELECT calculate_stops_satisfaction (f_stops) INTO stops_deg;
		
		SELECT calculate_duration_satisfaction (max_duration, min_duration, f_duration) INTO duration_deg;
		
		SELECT calculate_mileage_satisfaction (max_mileage, min_mileage, f_mileage) INTO mileage_deg;
		
		UPDATE temp_flight_record
	    SET
			price_sat_deg = price_deg,
			stop_sat_deg = stops_deg,
			duration_sat_deg = duration_deg,
			mileage_sat_deg = mileage_deg
    	WHERE id = f_id;
    	
  	END LOOP;
	
	CLOSE flights;
	
	RETURN 0;
END //

--
DROP FUNCTION IF EXISTS `get_satisfaction_degree`;

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

CREATE FUNCTION get_compromised_satisfaction(satisfaction1 DOUBLE, satisfaction2 DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	SET degree = (satisfaction1 + satisfaction2)/2;
	
	RETURN degree;
END //

--
DROP FUNCTION IF EXISTS `calculate_mileage_satisfaction`;

CREATE FUNCTION calculate_mileage_satisfaction (max_mileage DOUBLE, min_mileage DOUBLE, current_mileage DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	SET degree = (max_mileage-current_mileage)/(max_mileage-min_mileage);
	
	RETURN ROUND(degree,2);
END //

--
DROP FUNCTION IF EXISTS `calculate_duration_satisfaction`;

CREATE FUNCTION calculate_duration_satisfaction (max_duration DOUBLE, min_duration DOUBLE, current_duration DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	SET degree = (max_duration-current_duration)/(max_duration-min_duration);
	
	RETURN ROUND(degree,2);
END //

--
DROP FUNCTION IF EXISTS `calculate_price_satisfaction`;

CREATE FUNCTION calculate_price_satisfaction (max_price DOUBLE, min_price DOUBLE, current_price DOUBLE) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	SET degree = (max_price-current_price)/(max_price-min_price);
	
	RETURN ROUND(degree,2);
END //

--
DROP FUNCTION IF EXISTS `calculate_stops_satisfaction`;

CREATE FUNCTION calculate_stops_satisfaction (stops INT) RETURNS DOUBLE
BEGIN
	DECLARE degree DOUBLE;
	
	IF stops = 0 THEN
    	SET degree = 1.0;
	END IF;
	IF stops = 1 THEN
    	SET degree = 1.0;
	END IF;
	IF stops = 2 THEN
    	SET degree = 0.75;
	END IF;
	IF stops = 3 THEN
    	SET degree = 0.30;
	END IF;
	IF stops = 4 THEN
    	SET degree = 0.10;
	END IF;
	
	RETURN degree;
END //

--
DROP FUNCTION IF EXISTS `create_attributes_temp_table`;

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
  				`price_sat_deg` double,
  				`stop_sat_deg` double,
  				`duration_sat_deg` double,
  				`mileage_sat_deg` double,
  				`flight_sat_deg` double,
  				PRIMARY KEY (`id`));
	
	RETURN result;
END //

--
DROP FUNCTION IF EXISTS `insert_record_in_flight_temp_table`;

CREATE FUNCTION insert_record_in_flight_temp_table(f_tripId VARCHAR(45),f_departure VARCHAR(45),f_destination VARCHAR(45),f_stops INT,f_departureTime VARCHAR(45),f_arrivalTime VARCHAR(45),f_price DOUBLE,f_carrier VARCHAR(45),f_duration DOUBLE,f_mileage INT,f_cabin VARCHAR(45),f_thisTrip VARCHAR(45),f_jsonData JSON)
RETURNS INT
BEGIN
	DECLARE id INT;
	SET id = 0;
	
	INSERT INTO temp_flight_record (tripId,departure,destination,stops,departureTime,arrivalTime,price,carrier,duration,mileage,cabin,thisTrip,jsonData)
				 VALUES (f_tripId,f_departure,f_destination,f_stops,f_departureTime,f_arrivalTime,f_price,f_carrier,f_duration,f_mileage,f_cabin,f_thisTrip,f_jsonData);
	
	SELECT LAST_INSERT_ID() INTO id;
	
	RETURN id;
END //

--
DELIMITER ;
