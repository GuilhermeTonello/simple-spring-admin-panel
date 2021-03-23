INSERT INTO category(id, name) VALUES(1, 'Foods');

INSERT INTO category(id, name) VALUES(2, 'Drinks');

INSERT INTO category(id, name) VALUES(3, 'Tools');

INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (1, 'Breaking rocks was never this easy', 'Pickaxe', 30.82, 32, 3);
	
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (2, 'Destroying Mother Nature with ease measures', 'Axe', 15.99, 4, 3);
	
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (3, 'Yumi yumi food from the sea', 'Fish', 5.99, 16, 1);
	
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (4, 'Refreshing is sooooo good', 'Water', 1.99, 90, 2);
	
SELECT nextval('seq_product_id');
SELECT nextval('seq_product_id');
SELECT nextval('seq_product_id');
SELECT nextval('seq_product_id');

SELECT nextval('seq_category_id');
SELECT nextval('seq_category_id');
SELECT nextval('seq_category_id');