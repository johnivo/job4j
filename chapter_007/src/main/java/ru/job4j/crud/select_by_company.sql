create table company (
	id serial primary key,
	name varchar(64)
);

insert into company (name) values
	('aaa'),
	('bbb'),
	('ccc'),
	('ddd'),
	('eee'),
	('fff');

create table person (
	id serial primary key,
	name varchar(64),
	company_id int references company(id)
);

insert into person (name, company_id) values
	('Bob', 1),
	('Mike', 1),
	('Max', 2),
	('Amy', 4),
	('Any', 4),
	('Sid', 4),
	('Nik', 5),
	('Nata', 6),
	('Ben', 1),
	('Den', 1);

--select * from company;

--1) Retrieve in a single query: names of all persons that are NOT in the company with id = 5, company name for each person.
select p.name, c.name from person as p
left outer join company as c on c.id = p.company_id where c.id != 5;

--2) Select the name of the company with the maximum number of persons + number of persons in this company
select c.name, count(p.name) from person as p
inner join company as c on c.id = p.company_id
group by c.name
order by count(p.name) desc
limit 1;