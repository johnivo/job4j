--create new database tracker
CREATE DATABASE car_catalog;

--create tables
CREATE TABLE body (
	body_id serial primary key,
	name varchar(128)
);

CREATE TABLE engine (
	engine_id serial primary key,
	name varchar(128)
);

CREATE TABLE transmission (
	transmission_id serial primary key,
	name varchar(128)
);

CREATE TABLE car (
	car_id serial primary key,
	name varchar(128),
	body_id int references body(body_id) ,
	engine_id int references engine(engine_id),
	transmission_id int references transmission(transmission_id)
);

--filling tables
INSERT INTO body (name) VALUES
	('sedan'),
	('pickup'),
	('minivan'),
	('cabriolet'),
	('hatchback'),
	('crossover');

INSERT INTO engine (name) VALUES
	('gasoline'),
	('diesel'),
	('turbocharged diesel'),
	('hybrid'),
	('hydrogen'),
	('electric');

INSERT INTO transmission (name) VALUES
	('manual'),
	('automatic'),
	('robot');

INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES
	('kia ceed', 1, 1, 1),
	('hyundai elantra', 1, 1, 1),
	('opel zafira', 3, 2, 1),
	('chevrolet lacetti', 5, 2, 2),
	('kia sportage', 6, 1, 2),
	('kia optima', 1, 1, 2),
	('citroen grand', 3, 3, 2),
	('lada granta', 5, 3, 2),
	('lada granta', 5, 3, 2);

--select * from car;

--1. Вывести список всех машин и все привязанные к ним детали.
select c.name, b.name, e.name, t.name, count(t.name) from car as c
inner join body as b ON b.body_id = c.body_id
inner join engine as e ON e.engine_id = c.engine_id
inner join transmission as t ON t.transmission_id = c.transmission_id
group by c.name, b.name, e.name, t.name;

--2. Вывести отдельно детали, которые не используются в машине
-- кузова:
select b.name from car as c
right outer join body as b on b.body_id = c.body_id where c.body_id is null;
--или
select b.name from body as b
left outer join car as c on b.body_id = c.body_id where c.body_id is null;

-- двигатели:
select e.name from car as c
right outer join engine as e on e.engine_id = c.engine_id where c.engine_id is null;
--или
select e.name from engine as e
left outer join car as c on e.engine_id = c.engine_id where c.engine_id is null;

-- коробки передач:
select t.name from car as c
right outer join transmission as t on t.transmission_id = c.transmission_id where c.transmission_id is null;
--или
select t.name from transmission as t
left outer join car as c on t.transmission_id = c.transmission_id where c.transmission_id is null;