-- --------------- --
-- TEST INPUT DATA --
-- --------------- --


INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ХАРЬКОВ'), (last_insert_id(), 2, 'KHARKIV'), (last_insert_id(), 3, 'ХАРЬКІВ');
 
INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'КИЕВ'), (last_insert_id(), 2, 'KIEV'), (last_insert_id(), 3, 'КИЇВ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ЛЬВОВ'), (last_insert_id(), 2, 'LVIV'), (last_insert_id(), 3, 'ЛЬВІВ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ХЕРСОН'), (last_insert_id(), 2, 'KHERSON'), (last_insert_id(), 3, 'ХЕРСОН');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ОДЕССА'), (last_insert_id(), 2, 'ODESSA'), (last_insert_id(), 3, 'ОДЕССА');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'НИКОЛАЕВ'), (last_insert_id(), 2, 'NIKOLAEV'), (last_insert_id(), 3, 'МИКОЛАЇВ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ПОЛТАВА'), (last_insert_id(), 2, 'POLTAVA'), (last_insert_id(), 3, 'ПОЛТАВА');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ДНЕПР'), (last_insert_id(), 2, 'DNIPRO'), (last_insert_id(), 3, 'ДНІПРО');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ЖИТОМИР'), (last_insert_id(), 2, 'ZHYTOMYR'), (last_insert_id(), 3, 'ЖИТОМИР');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'РОВНО'), (last_insert_id(), 2, 'ROVNO'), (last_insert_id(), 3, 'РІВНЕ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ЛУЦК'), (last_insert_id(), 2, 'LUTSK'), (last_insert_id(), 3, 'ЛУЦЬК');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'УЖГОРОД'), (last_insert_id(), 2, 'UZHHOROD'), (last_insert_id(), 3, 'УЖГОРОД');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ЧЕРКАССЫ'), (last_insert_id(), 2, 'CHERKASY'), (last_insert_id(), 3, 'ЧЕРКАСИ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'СУМЫ'), (last_insert_id(), 2, 'SUMY'), (last_insert_id(), 3, 'СУМИ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ВИННИЦА'), (last_insert_id(), 2, 'VINNYTSIA'), (last_insert_id(), 3, 'ВІННИЦЯ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ЗАПОРОЖЬЕ'), (last_insert_id(), 2, 'ZAPOROZHYE'), (last_insert_id(), 3, 'ЗАПОРІЖЖЯ');

INSERT INTO `railway_ticket_office_db`.`stations` VALUES();
INSERT INTO `railway_ticket_office_db`.`stations_names` 
VALUES(last_insert_id(), 1, 'ЧЕРНИГОВ'), (last_insert_id(), 2, 'CHERNIGIV'), (last_insert_id(), 3, 'ЧЕРНІГІВ');

INSERT INTO `railway_ticket_office_db`.`carriages`(`model`, `image`, `comfort_type`, `total_seats`, `price_coefficient`) 
VALUES('kupe', 'kupe.png', 'COMPARTMENT', 36, '3'), ('plazkart', 'plazkart.png', 'COUCHETTE', 54, '2'), ('sv', 'sv.png', 'COMPARTMENT', 18, '6');