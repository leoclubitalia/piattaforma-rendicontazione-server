DROP SCHEMA rendicontation;
CREATE SCHEMA rendicontation;
USE rendicontation;


-- Club

CREATE TABLE district (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20)
);

CREATE TABLE cap (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	number VARCHAR(7)
);

CREATE TABLE city (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE city_cap (
	cap INTEGER,
	city INTEGER,
	PRIMARY KEY (cap, city),
	FOREIGN KEY (cap) REFERENCES cap (id),
	FOREIGN KEY (city) REFERENCES city (id)
);

CREATE TABLE club (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	enabled BOOLEAN,
	name VARCHAR(150),
	email VARCHAR(150),
	city INTEGER,
	district INTEGER,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (district) REFERENCES district (id)
);

-- Activity

CREATE TABLE type_activity (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(70)
);

CREATE TABLE activity (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50),
	description VARCHAR(500),
	date DATE,
	quantity_leo INTEGER,
	lions_participation BOOLEAN,
	satisfaction_degree INTEGER,
	city INTEGER,
	club INTEGER,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (club) REFERENCES club (id)
);

CREATE TABLE r_type_activity (
	activity INTEGER,
	type_activity INTEGER,
	PRIMARY KEY (activity, type_activity),
	FOREIGN KEY (activity) REFERENCES activity (id),
	FOREIGN KEY (type_activity) REFERENCES type_activity (id)
);

-- Service

CREATE TABLE type_service (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(70)
);

CREATE TABLE competence_area (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(70)
);

CREATE TABLE service (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50),
	description VARCHAR(500),
	date DATE,
	quantity_participants INTEGER,
	satisfaction_degree INTEGER,
	impact INTEGER,
	duration INTEGER,
	other_associations VARCHAR(400),
	money_raised FLOAT,
	quantity_served_people INTEGER,
	city INTEGER,
	club INTEGER,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (club) REFERENCES club (id)
);

CREATE TABLE r_type_service (
	service INTEGER,
	type_service INTEGER,
	PRIMARY KEY (service, type_service),
	FOREIGN KEY (service) REFERENCES service (id),
	FOREIGN KEY (type_service) REFERENCES type_service (id)
);

CREATE TABLE competence_area_service (
	service INTEGER,
	competence_area INTEGER,
	PRIMARY KEY (service, competence_area),
	FOREIGN KEY (service) REFERENCES service (id),
	FOREIGN KEY (competence_area) REFERENCES competence_area (id)
);
