DROP SCHEMA IF EXISTS `railway_ticket_office_db`;

CREATE DATABASE `railway_ticket_office_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET GLOBAL event_scheduler=ON;

USE `railway_ticket_office_db`;

CREATE TABLE `languages`( 
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`label` VARCHAR(5) UNIQUE NOT NULL
);

CREATE TABLE `users`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`email` VARCHAR(320) UNIQUE NOT NULL,
	`password_hash` VARCHAR(100) NOT NULL,
	`role` ENUM('ADMIN', 'CLIENT') NOT NULL DEFAULT 'CLIENT',	
	`activation_status` ENUM('ACTIVETED', 'INACTIVE') NOT NULL DEFAULT 'INACTIVE',
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`date_registration` TIMESTAMP NOT NULL
);

CREATE TABLE `activation_codes`(
	`user_id` BIGINT UNSIGNED NOT NULL,
	`activation_code` VARCHAR(10) NOT NULL,
FOREIGN KEY(`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE
);

CREATE TABLE `stations`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY
 );

CREATE TABLE `stations_names`(
	`station_id` BIGINT UNSIGNED AUTO_INCREMENT,
	`language_id` BIGINT UNSIGNED NOT NULL,
	`name` VARCHAR(100) NOT NULL,
PRIMARY KEY(`station_id`, `language_id`),
UNIQUE(`name`, `language_id`),
FOREIGN KEY(`station_id`) REFERENCES `stations`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`language_id`) REFERENCES `languages`(`id`) ON DELETE CASCADE
 );

CREATE TABLE `routes`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`code` VARCHAR(10) NOT NULL,
    `is_canceled` ENUM('true','false') NOT NULL DEFAULT 'false'
);

CREATE TABLE `stops`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`route_id` BIGINT UNSIGNED NOT NULL,
	`station_id` BIGINT UNSIGNED NOT NULL,
	`stop_number` TINYINT UNSIGNED NOT NULL,
	`arrival_date` DATE NOT NULL,
	`arrival_time` TIME NOT NULL,
	`departure_date` DATE NOT NULL,
	`departure_time` TIME NOT NULL,
	`price` DECIMAL(7,2) NOT NULL DEFAULT 0,
UNIQUE(`route_id`, `stop_number`),
FOREIGN KEY(`route_id`) REFERENCES `routes`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`station_id`) REFERENCES `stations`(`id`) ON DELETE CASCADE
);

CREATE TABLE `carriages`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`model` VARCHAR(20) UNIQUE NOT NULL,
	`image` VARCHAR(100) UNIQUE NOT NULL,
	`total_seats` SMALLINT UNSIGNED NOT NULL,
	`comfort_type` ENUM('COMPARTMENT', 'COUCHETTE', 'SITTING') NOT NULL,
	`price_coefficient` DECIMAL(7,2) NOT NULL
); 

CREATE TABLE `route_carriages`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`route_id` BIGINT UNSIGNED NOT NULL,
	`carriage_id` BIGINT UNSIGNED NOT NULL,
	`number` TINYINT NOT NULL,
UNIQUE(`route_id`, `number`),
FOREIGN KEY(`route_id`) REFERENCES `routes`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`carriage_id`) REFERENCES `carriages`(`id`) ON DELETE CASCADE
);

CREATE TABLE `seats`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`route_carriage_id` BIGINT UNSIGNED NOT NULL,
	`seat_number` SMALLINT UNSIGNED NOT NULL,
UNIQUE(`route_carriage_id`, `seat_number`),
FOREIGN KEY(`route_carriage_id`) REFERENCES `route_carriages`(`id`) ON DELETE CASCADE
);

CREATE TABLE `seats_conditions`(
	`seat_id` BIGINT UNSIGNED NOT NULL,
	`stop_id` BIGINT UNSIGNED NOT NULL,
	`is_free` ENUM('true', 'false') NOT NULL DEFAULT 'true',
PRIMARY KEY(`seat_id`, `stop_id`),
FOREIGN KEY(`seat_id`) REFERENCES `seats`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`stop_id`) REFERENCES `stops`(`id`) ON DELETE CASCADE
); 

Create TABLE `prices`(
	`route_carriage_id` BIGINT UNSIGNED NOT NULL,
	`stop_id` BIGINT UNSIGNED NOT NULL,
	`price` DECIMAL(7,2) NOT NULL,
PRIMARY KEY(`route_carriage_id`, `stop_id`),
FOREIGN KEY(`route_carriage_id`) REFERENCES `route_carriages`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`stop_id`) REFERENCES `stops`(`id`) ON DELETE CASCADE
);

CREATE TABLE `tickets`(
	`id` BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `route_id` BIGINT UNSIGNED NOT NULL,
    `user_id` BIGINT UNSIGNED NOT NULL,
	`seat_id` BIGINT UNSIGNED NOT NULL,
    `departure_stop_id` BIGINT UNSIGNED NOT NULL,
    `arrival_stop_id` BIGINT UNSIGNED NOT NULL,
    `price` DECIMAL NOT NULL,
	`is_paid` ENUM('true', 'false') NOT NULL DEFAULT 'false',
    `date_created` TIMESTAMP NOT NULL DEFAULT NOW(),
FOREIGN KEY(`route_id`) REFERENCES `routes`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`user_id`) REFERENCES `users`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`seat_id`) REFERENCES `seats`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`departure_stop_id`) REFERENCES `stops`(`id`) ON DELETE CASCADE,
FOREIGN KEY(`arrival_stop_id`) REFERENCES `stops`(`id`) ON DELETE CASCADE
);

INSERT INTO `languages` VALUES(1, 'ru');
INSERT INTO `languages` VALUES(2, 'en'); 
INSERT INTO `languages` VALUES(3, 'ua');

INSERT INTO `users`VALUES(default, 'trainticketoffice.summarytask4@gmail.com', '$s0$41010$uQcWpg8tA+eJXnVmFWOVEg==$wGF0Q84sKR5ihp1DZtp4g0a9SFZfodPvccndvbNN3tg=', 'ADMIN', 'ACTIVETED', 'Admin', 'Admin', NOW());

DELIMITER $$
USE `railway_ticket_office_db`$$
CREATE PROCEDURE `select_routes_by_stations_and_date` (`in_departure_station_id` BIGINT, `in_arrival_station_id` BIGINT, `in_date` DATE)
BEGIN
SELECT 
	`temp`.`route_id` AS `id`, 
	`routes`.`code`, `routes`.`is_canceled` 
FROM `stops` AS `temp` 
	INNER JOIN `stops` ON  `temp`.`station_id` = `in_departure_station_id` AND `temp`.`departure_date` = `in_date` 
	INNER JOIN `routes` ON `routes`.`id` = `stops`.`route_id` 
WHERE `stops`.`station_id` = `in_arrival_station_id`
	AND `temp`.`route_id`=`stops`.`route_id` AND `temp`.`stop_number` < `stops`.`stop_number`;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `generate_activation_code` 
AFTER INSERT ON `users` FOR EACH ROW
BEGIN
INSERT INTO `activation_codes` VALUES(NEW.`id`, SUBSTRING(MD5(RAND()) FROM 1 FOR 10));
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `delete_activation_code` 
AFTER UPDATE ON `users` FOR EACH ROW
BEGIN
IF NEW.`activation_status` = 'ACTIVETED' THEN 
DELETE FROM `activation_codes` WHERE `activation_codes`.`user_id`= NEW.`id`;
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `update_seats_conditions_is_free_after_delete_ticket` 
AFTER DELETE ON `tickets` FOR EACH ROW
BEGIN
SET @departure_stop_number := (SELECT `stop_number` FROM `stops`WHERE `stops`.`id` = OLD.`departure_stop_id` LIMIT 1);
SET @arrival_stop_number := (SELECT `stop_number` FROM `stops`WHERE `stops`.`id` = OLD.`arrival_stop_id` LIMIT 1);
UPDATE `seats_conditions` 
	INNER JOIN `stops` ON `stops`.`id`=`seats_conditions`.`stop_id` 
		AND `stops`.`stop_number` >= @departure_stop_number 
        AND `stops`.`stop_number` < @arrival_stop_number 
SET is_free='true'  
WHERE `seat_id`= OLD.`seat_id`;
END$$
DELIMITER ;

CREATE EVENT `delete_unpaid_tickets` 
ON SCHEDULE EVERY 6 MINUTE STARTS CURRENT_TIMESTAMP
COMMENT 'Delete unpaid tickets'
DO 
DELETE FROM `tickets` WHERE  `tickets`.`is_paid` = 'false' 
AND `tickets`.`date_created` + INTERVAL 7 MINUTE <= current_timestamp();

CREATE EVENT `delete_not_active_users` 
ON SCHEDULE EVERY 5 MINUTE STARTS CURRENT_TIMESTAMP
COMMENT 'Delete not active users'
DO 
DELETE FROM `users` WHERE  `users`.`activation_status` = 'INACTIVE' 
AND `users`.`date_registration` + INTERVAL 5 MINUTE <= current_timestamp();
	
CREATE EVENT `delete_canceled_routes` 
ON SCHEDULE EVERY 5 MINUTE STARTS CURRENT_TIMESTAMP
COMMENT 'Delete canceled routes'
DO 
DELETE FROM `routes` WHERE NOT EXISTS (SELECT * FROM `tickets` WHERE `routes`.`id` = `tickets`.`route_id`)
AND `routes`.`is_canceled` = 'true';


