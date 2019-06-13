CREATE TABLE type (
	id serial primary key,
	name varchar(64)
);

INSERT INTO type (name) VALUES
	('Сыр'),
	('Молоко'),
	('Творог');

--select * from type;

CREATE TABLE product (
	id serial primary key,
	name varchar(64),
	type_id int references type(id),
	expired_date timestamp,
	price double precision
);

INSERT INTO product (name, type_id, expired_date, price) VALUES
	('Костромской', 1, '2019-06-28 10:23:54', 240.5),
	('Российский', 1, '2019-06-27 10:23:54', 220),
	('Голландский', 1, '2019-06-28 10:23:54', 350),
	('Пошехонский', 1, '2019-06-28 10:23:54', 260.5),
	('Российский', 1, '2019-07-02 10:23:54', 240),
	('Российский', 1, '2019-07-08 10:23:54', 250),
	('ВМК 3.2%', 2, '2019-06-28 10:23:54', 48.5),
	('ВМК 2.5%', 2, '2019-06-28 10:23:54', 40.3),
	('УОМЗ 3.2%', 2, '2019-06-28 10:23:54', 50.2),
	('УОМЗ 2.5%', 2, '2019-06-28 10:23:54', 42.42),
	('Домашний', 3, '2019-06-28 10:23:54', 85.5),
	('Обезжиренный', 3, '2019-06-28 10:23:54', 100.0);

	--select * from product;

--1. Написать запрос получение всех продуктов с типом "Сыр"
SELECT * FROM product as p
WHERE p.type_id = 1;
--или
SELECT * FROM product as p
INNER JOIN type as t ON t.id = p.type_id
WHERE t.name = 'Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT p.name FROM product as p
WHERE p.name LIKE '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product as p
WHERE extract(MONTH FROM p.expired_date) > extract(MONTH FROM now());

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product as p
WHERE p.price = (SELECT max(p.price) FROM product as p);
--или
SELECT * FROM product as p
ORDER BY p.price DESC
LIMIT 1;

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, count(p.name) FROM product as p
INNER JOIN type as t ON t.id = p.type_id
GROUP BY t.name;

--6. Написать запрос получение всех продуктов с типом "Сыр" и "Молоко"
SELECT t.name, p.name FROM product as p
INNER JOIN type as t ON t.id = p.type_id
WHERE t.name IN('Сыр', 'Молоко');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name FROM product as p
INNER JOIN type as t ON t.id = p.type_id
GROUP BY t.name HAVING count(p.name) < 10;

--8. Вывести все продукты и их тип.
SELECT t.name, p.name FROM product as p
INNER JOIN type as t ON t.id = p.type_id;
