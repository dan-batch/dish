BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, restrictions, user_restrictions, pluck, pluck_dish, category, pluck_cat, pluck_user;

DROP SEQUENCE IF EXISTS seq_user_id, seq_restrictions, seq_pluck_id, seq_dish_id, seq_cat_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;
  
CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	email varchar (50) NOT NULL,
	first_name varchar(20),
	last_name varchar(20),
	password_hash varchar(200) NOT NULL,
	role varchar(20) NOT NULL,
	picture_url varchar (200),
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT proper_email CHECK (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
);

CREATE SEQUENCE seq_restrictions
  INCREMENT BY 1
  START WITH 2001
  NO MAXVALUE;

CREATE TABLE restrictions(
	restriction_id int NOT NULL DEFAULT nextval('seq_restrictions'),
	restriction_abbrev varchar (2) NOT NULL,
	restriction_name varchar (25) NOT NULL,
	CONSTRAINT PK_restrictions PRIMARY KEY (restriction_id)
);

CREATE TABLE user_restrictions(
	user_id int NOT NULL,
	restriction_id int NOT NULL,
	CONSTRAINT PK_user_restrictions PRIMARY KEY (restriction_id, user_id),
	CONSTRAINT FK_user_restrictions_restriction FOREIGN KEY (restriction_id) REFERENCES restrictions (restriction_id),
	CONSTRAINT FK_user_restrictions_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE SEQUENCE seq_pluck_id
  INCREMENT BY 1
  START WITH 3001
  NO MAXVALUE;
  
CREATE TABLE pluck(
	pluck_id int NOT NULL DEFAULT nextval('seq_pluck_id'),
	pluck_name varchar(50) NOT NULL,
	pluck_description varchar(500),
	pluck_date_time timestamp NOT NULL,
	pluck_place varchar(50) NOT NULL,
	CONSTRAINT PK_pluck PRIMARY KEY (pluck_id)
);

CREATE SEQUENCE seq_cat_id
  INCREMENT BY 1
  START WITH 5001
  NO MAXVALUE;

CREATE TABLE category(
	cat_id int NOT NULL DEFAULT nextval('seq_cat_id'),
	cat_name varchar(20) NOT NULL,
	CONSTRAINT PK_cat_id PRIMARY KEY (cat_id)
);

CREATE SEQUENCE seq_dish_id
  INCREMENT BY 1
  START WITH 4001
  NO MAXVALUE;
  
 CREATE TABLE pluck_dish(
	dish_id int NOT NULL DEFAULT nextval('seq_dish_id'), 
	pluck_id int NOT NULL,
	cat_id int, 
	user_id int NOT NULL,
	dish_name varchar(25) NOT NULL,
	servings int NOT NULL,
	description varchar(500),
	CONSTRAINT PK_dish_id PRIMARY KEY (dish_id),
	CONSTRAINT FK_pluck_dish_id FOREIGN KEY (pluck_id) REFERENCES pluck (pluck_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_dish_cat FOREIGN KEY (cat_id) REFERENCES category (cat_id)
);

CREATE TABLE pluck_cat(
	pluck_id int NOT NULL,
	cat_id int NOT NULL,
	cat_limit int,
	CONSTRAINT PK_pluck_cat PRIMARY KEY (pluck_id, cat_id),
	CONSTRAINT FK_pluck_cat_pluck_id FOREIGN KEY (pluck_id) REFERENCES pluck (pluck_id),
	CONSTRAINT FK_pluck_cat_cat_id FOREIGN KEY (cat_id) REFERENCES category (cat_id)	
);

CREATE TABLE pluck_user(
	pluck_id int NOT NULL, 
	user_id int NOT NULL, 
	pluck_role varchar(5),
	CONSTRAINT PK_pluck_user PRIMARY KEY (pluck_id, user_id),
	CONSTRAINT FK_pluck_id FOREIGN KEY (pluck_id) REFERENCES pluck (pluck_id), 
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE dish_restrictions(
	dish_id int NOT NULL, 
	restriction_id int, 
	CONSTRAINT PK_dish_restrictions_id PRIMARY KEY (dish_id), 
	CONSTRAINT FK_dish_restrictions_dish_id FOREIGN KEY (dish_id) REFERENCES pluck_dish (dish_id),
	CONSTRAINT FK_dish_restrictions_restriction_id FOREIGN KEY (restriction_id) REFERENCES restrictions (restriction_id)
);

COMMIT TRANSACTION;
