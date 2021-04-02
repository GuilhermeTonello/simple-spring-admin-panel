-- INSERTING CATEGORIES
INSERT INTO category(id, name) VALUES(1, 'Foods');
SELECT nextval('seq_category_id');

INSERT INTO category(id, name) VALUES(2, 'Drinks');
SELECT nextval('seq_category_id');

INSERT INTO category(id, name) VALUES(3, 'Tools');
SELECT nextval('seq_category_id');

-- INSERTING PRODUCTS
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (1, 'Breaking rocks was never this easy', 'Pickaxe', 30.82, 32, 3);
SELECT nextval('seq_product_id');
	
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (2, 'Destroying Mother Nature with ease measures', 'Axe', 15.99, 4, 3);
SELECT nextval('seq_product_id');
	
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (3, 'Yumi yumi food from the sea', 'Fish', 5.99, 16, 1);
SELECT nextval('seq_product_id');
	
INSERT INTO product(
	id, description, name, price, quantity, category_id)
	VALUES (4, 'Refreshing is sooooo good', 'Water', 1.99, 90, 2);
SELECT nextval('seq_product_id');

-- INSERTING ROLES
INSERT INTO role(
	id, role)
	VALUES (1, 'ROLE_ADMIN');
SELECT nextval('seq_role_id');

INSERT INTO role(
	id, role)
	VALUES (2, 'ROLE_EMPLOYEE');
SELECT nextval('seq_role_id');

INSERT INTO role(
	id, role)
	VALUES (3, 'ROLE_TRAINEE');
SELECT nextval('seq_role_id');

-- INSERTING USERS
INSERT INTO users(
	id, first_name, last_name, username, password, email, phone, rg, cpf, gender, date_of_birth, salary, address)
	VALUES (1, 'John', 'Doe', 'admin', '$2a$10$/itBmOYgnKHtQRUO7x4n7OXS0c9XK5QuESbv4/U7Cdk7jTwrrfIfe', 'johndoe@email.com',
			'944444444', '999999999', '99999999999', 'MALE', '1990-01-15', 2500.50,
			'{"zipcode": "69901064", "street": "Rua Morango", "city": "Rio Branco", "state": "AC", "complement": "number 20, apartment 98"}');
SELECT nextval('seq_user_id');

INSERT INTO users(
	id, first_name, last_name, username, password, email, phone, rg, cpf, gender, date_of_birth, salary, address)
	VALUES (2, 'Jane', 'Doe', 'employee', '$2a$10$bTLsn8hLussGo86nyLAVfO2IypahB2SjYIRCf5SOOhX42F3B3q0yO', 'janedoe@email.com',
			'922222222', '111111111', '11111111111', 'FEMALE', '1988-12-17', 2000.60,
			'{"zipcode": "78138036", "street": "Rua Marechal Mascarenha de Moraes", "city": "Várzea Grande", "state": "MT", "complement": "number 7008"}');
SELECT nextval('seq_user_id');

INSERT INTO users(
	id, first_name, last_name, username, password, email, phone, rg, cpf, gender, date_of_birth, salary, address)
	VALUES (3, 'Richard', 'Roe', 'trainee', '$2a$10$JckZDQrZp2J5oLLbiXHFDetKqT4aJtnx3AH5ErphjYlmw6O6N4ySu', 'richardroe@email.com',
			'933333333', '333333333', '55555555555', 'MALE', '1999-06-08', 1500.10,
			'{"zipcode": "58081270", "street": "Rua Professor João Freire da Nóbrega", "city": "João Pessoa", "state": "PB", "complement": "number 2901, first floor, apartment 15"}');
SELECT nextval('seq_user_id');

-- INSERTING USERS WITH ROLES
INSERT INTO users_roles(
	user_id, roles_id)
	VALUES(1, 1);
INSERT INTO users_roles(
	user_id, roles_id)
	VALUES(1, 2);

INSERT INTO users_roles(
	user_id, roles_id)
	VALUES(2, 2);
INSERT INTO users_roles(
	user_id, roles_id)
	VALUES(3, 3);