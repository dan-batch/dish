BEGIN TRANSACTION;

INSERT INTO users (email,first_name,last_name,password_hash,role) VALUES ('user1@hotmail.com', 'User', 'One', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (email,first_name,last_name,password_hash,role) VALUES ('user2@gmail.org', 'User', 'Two', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');


INSERT INTO restrictions (restriction_abbrev, restriction_name) VALUES ('df', 'Dairy Free'),('gf', 'Gluten Free'),('h', 'Halal'),('k', 'Kosher'),('lc', 'Low-carb'),('nf', 'Nut Free'),('vn', 'Vegan'),('v', 'Vegetarian');

INSERT INTO category (cat_name) VALUES ('sides & snacks');
INSERT INTO category (cat_name) VALUES ('main dishes');
INSERT INTO category (cat_name) VALUES ('drinks');
INSERT INTO category (cat_name) VALUES ('desserts');
INSERT INTO category (cat_name) VALUES ('utensils, etc.');

COMMIT TRANSACTION;
