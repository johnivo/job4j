CREATE TABLE Category (category_id serial primary key, name varchar(128));
CREATE TABLE State (state_id serial primary key, status varchar(128));
CREATE TABLE Role (role_id serial primary key, name varchar(128));
CREATE TABLE Rules (rules_id serial primary key, resolution varchar(128));
CREATE TABLE RulesToRole (rules_to_role_id serial primary key, role_id int references Role(role_id) ON UPDATE CASCADE ON DELETE CASCADE, rules_id int references Rules(rules_id) ON UPDATE CASCADE ON DELETE CASCADE);
CREATE TABLE Users (user_id serial primary key, firstName varchar(128), lastName varchar(128), city varchar(128), phone varchar(64), role_id int references Role(role_id) ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE Item (item_id serial primary key, name varchar(128), description varchar(512), created timestamp, user_id int references Users(user_id) ON UPDATE CASCADE ON DELETE SET NULL, category_id int references Category(category_id) ON UPDATE CASCADE ON DELETE SET NULL, state_id int references State(state_id) ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE Comments (comments_id serial primary key, comment text, item_id int references Item(item_id) ON UPDATE CASCADE ON DELETE CASCADE);
CREATE TABLE Attached (attached_id serial primary key, link varchar(2048), item_id int references Item(item_id) ON UPDATE CASCADE ON DELETE CASCADE);