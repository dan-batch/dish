BEGIN TRANSACTION;

DROP TABLE IF EXISTS users, restrictions, user_restrictions;

DROP SEQUENCE IF EXISTS seq_user_id, seq_restrictions;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE users (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	user_email varchar (50) NOT NULL,
	first_name varchar(20) NOT NULL,
	last_name varchar(20) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(20) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT proper_email CHECK (user_email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$')
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

COMMIT TRANSACTION;
