BEGIN TRANSACTION;

INSERT INTO users (user_email,first_name,last_name,password_hash,role) VALUES ('user1@hotmail.com', 'User', 'One', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (user_email,first_name,last_name,password_hash,role) VALUES ('user2@gmail.org', 'User', 'Two', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');


INSERT INTO restrictions (restriction_abbrev, restriction_name) VALUES ('df', 'Dairy Free'),('gf', 'Gluten Free'),('h', 'Halal'),('k', 'Kosher'),('lc', 'Low-carb'),('nf', 'Nut Free'),('vn', 'Vegan'),('v', 'Vegetarian');

INSERT INTO user_restrictions (user_id, restriction_id) VALUES (1001, 2001), (1001, 2002), (1001, 2008);

COMMIT TRANSACTION;
