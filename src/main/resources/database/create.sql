DROP SCHEMA rendicontation;
CREATE SCHEMA rendicontation;
USE rendicontation;


-- Club

CREATE TABLE multidistrict (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	UNIQUE (name)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE district (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
	multidistrict INTEGER NOT NULL,
	FOREIGN KEY (multidistrict) REFERENCES multidistrict (id),
	UNIQUE (multidistrict, name)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE cap (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	number VARCHAR(7) NOT NULL,
    UNIQUE (number)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE city (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE INDEX city_name_index ON city (name);

CREATE TABLE city_cap (
	cap INTEGER,
	city INTEGER,
	PRIMARY KEY (cap, city),
	FOREIGN KEY (cap) REFERENCES cap (id),
	FOREIGN KEY (city) REFERENCES city (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE club (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	enabled BOOLEAN DEFAULT TRUE,
	name VARCHAR(150) NOT NULL,
	email VARCHAR(150) NOT NULL,
	city INTEGER,
	current_members INTEGER DEFAULT 0,
	aspirant_members INTEGER DEFAULT 0,
	district INTEGER NOT NULL,
	foundation_date DATE,
	version LONG,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (district) REFERENCES district (id),
    UNIQUE (name),
    UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE INDEX club_enabled_index ON club (enabled);
CREATE INDEX club_name_index ON club (name);
CREATE INDEX club_email_index ON club (email);
CREATE INDEX club_dstrict_index ON club (district);

-- Activity

CREATE TABLE satisfaction_degree (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(70) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
    UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE type_activity (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(70) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
    UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE activity (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	description VARCHAR(500) NOT NULL,
	date DATE NOT NULL,
	quantity_leo INTEGER NOT NULL,
	lions_participation BOOLEAN NOT NULL,
	satisfaction_degree INTEGER NOT NULL,
	city INTEGER NOT NULL,
	club INTEGER NOT NULL,
    version LONG,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (club) REFERENCES club (id),
	FOREIGN KEY (satisfaction_degree) REFERENCES satisfaction_degree (id),
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE INDEX activity_date_index ON activity (date);
CREATE INDEX activity_club_index ON activity (club);

CREATE TABLE r_type_activity (
	activity INTEGER,
	type_activity INTEGER,
	PRIMARY KEY (activity, type_activity),
	FOREIGN KEY (activity) REFERENCES activity (id),
	FOREIGN KEY (type_activity) REFERENCES type_activity (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

-- Service

CREATE TABLE type_service (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(70) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
    UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE competence_area (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(70) NOT NULL,
	enabled BOOLEAN DEFAULT TRUE,
    UNIQUE (name)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE service (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	description VARCHAR(500) NOT NULL,
	date DATE NOT NULL,
	quantity_participants INTEGER,
	satisfaction_degree INTEGER NOT NULL,
	duration INTEGER,
	other_associations VARCHAR(400),
	money_or_material_collected VARCHAR(100),
	quantity_served_people INTEGER,
	city INTEGER NOT NULL,
	club INTEGER NOT NULL,
    version LONG,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (club) REFERENCES club (id),
	FOREIGN KEY (satisfaction_degree) REFERENCES satisfaction_degree (id),
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE INDEX service_date_index ON service (date);
CREATE INDEX service_club_index ON service (club);

CREATE TABLE r_type_service (
	service INTEGER,
	type_service INTEGER,
	PRIMARY KEY (service, type_service),
	FOREIGN KEY (service) REFERENCES service (id),
	FOREIGN KEY (type_service) REFERENCES type_service (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE competence_area_service (
	service INTEGER,
	competence_area INTEGER,
	PRIMARY KEY (service, competence_area),
	FOREIGN KEY (service) REFERENCES service (id),
	FOREIGN KEY (competence_area) REFERENCES competence_area (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE research_service (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50),
	start_date DATE,
	end_date DATE,
	quantity_participants INTEGER,
	satisfaction_degree INTEGER,
	duration INTEGER,
	other_associations VARCHAR(400),
	money_or_material_collected VARCHAR(100),
	quantity_served_people INTEGER,
	city INTEGER,
	club INTEGER,
	type_service INTEGER,
    competence_area INTEGER,
	district INTEGER,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (club) REFERENCES club (id),
    FOREIGN KEY (district) REFERENCES district (id),
	FOREIGN KEY (satisfaction_degree) REFERENCES satisfaction_degree (id),
	FOREIGN KEY (competence_area) REFERENCES competence_area (id),
	FOREIGN KEY (type_service) REFERENCES type_service (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

CREATE TABLE research_activity (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50),
	start_date DATE,
	end_date DATE,
	quantity_leo INTEGER,
	satisfaction_degree INTEGER,
	lions_participation BOOLEAN,
	city INTEGER,
	club INTEGER,
	type_activity INTEGER,
	district INTEGER,
	FOREIGN KEY (city) REFERENCES city (id),
	FOREIGN KEY (club) REFERENCES club (id),
    FOREIGN KEY (district) REFERENCES district (id),
	FOREIGN KEY (satisfaction_degree) REFERENCES satisfaction_degree (id),
	FOREIGN KEY (type_activity) REFERENCES type_service (id)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;
