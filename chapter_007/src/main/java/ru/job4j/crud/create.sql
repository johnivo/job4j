--create new database tracker
CREATE DATABASE tracker3;

--create tables
CREATE TABLE Category (
	category_id serial primary key,
	name varchar(128)
);

CREATE TABLE State (
	state_id serial primary key,
	status varchar(128)
);

CREATE TABLE Role (
	role_id serial primary key,
	name varchar(128)
);

CREATE TABLE Rules (
	rules_id serial primary key,
	resolution varchar(128)
);

CREATE TABLE RulesToRole (
	rules_to_role_id serial primary key,
	role_id int references Role(role_id),
	rules_id int references Rules(rules_id)
);

CREATE TABLE Users (
	user_id serial primary key,
	firstName varchar(128),
	lastName varchar(128),
	city varchar(128),
	phone varchar(64),
	role_id int references Role(role_id)
);

CREATE TABLE Item (
	item_id serial primary key,
	name varchar(128),
	description varchar(512),
	user_id int references Users(user_id),
	category_id int references Category(category_id),
	state_id int references State(state_id)
);

CREATE TABLE Comments (
	comments_id serial primary key,
	comment text,
	item_id int references Item(item_id)
);

CREATE TABLE Attached (
  attached_id serial primary key,
  link varchar(2048),
  item_id int references Item(item_id)
);

--filling tables
--insert into Role, Roles and join together use RulesToRole
INSERT INTO Role(name) VALUES
	('Admin'),
	('User');

INSERT INTO Rules (resolution) VALUES
	('Create user'),
	('Update user'),
	('Delete user'),
	('Add item'),
	('Edit any item'),
	('Delete any item'),
	('Edit own item'),
	('Delete own item');

INSERT INTO RulesToRole (rules_id, role_id) VALUES
	(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1),
	(4, 2),	(7, 2), (8, 2);

--insert into Users
INSERT INTO Users (firstName, lastName, city, phone, role_id) VALUES
	('Leo', 'Messi', 'Barca', '+79111234567', 2),
	('Zinedine', 'Zidane', 'Madrid', '+79111234589', 2),
	('Paul', 'Scholes', 'Manchester', '+79117654321', 1);

--insert into Category and State
INSERT INTO Category (name) VALUES
	('Suggestion'),
	('Violation of the delivery time'),
	('Quality complaint'),
	('Technical problem');

INSERT INTO State (status) VALUES
	('Open'),
	('In the work'),
	('Closed');

--Insert into Item
INSERT INTO Item (name, description, user_id, category_id, state_id) VALUES
	('Goods by mail', 'Заказ номер х потерялся на таможне', 1, 4, 1),
	('Estimated delivery time', 'Превышен срок доставки', 3, 3, 3),
	('Goods by mail', 'Получен товар ненадлежащего качества', 3, 2, 3),
	('Technical problem', 'Не отслеживается трек по заказу х', 2, 1, 2);

--Insert into comments
INSERT INTO Comments (comment, item_id) VALUES
	('ожидаемый срок доставки 5 дней', 1),
	('aaaaaaaaa', 2);

--Insert into attached
INSERT INTO Attached (link, item_id) VALUES
	('image.jpg', 2);