BEGIN TRANSACTION;

INSERT INTO users (user_email,first_name,last_name,password_hash) VALUES ('user1@hotmail.com', 'User', 'One', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');
INSERT INTO users (user_email,first_name,last_name,password_hash) VALUES ('user2@gmail.org', 'User', 'Two', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC');


INSERT INTO restrictions (restriction_name) VALUES ('Dairy Free'),('Gluten Free'),('Halal'),('Kosher'),('Low-carb'),('Nut Free'),('Vegan'),('Vegetarian');

INSERT INTO user_restrictions (user_id, restriction_id) VALUES (1001, 2001), (1001, 2002), (1001, 2008);

COMMIT TRANSACTION;
