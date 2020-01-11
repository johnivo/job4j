CREATE TABLE IF NOT EXISTS country (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32)
  );

CREATE TABLE IF NOT EXISTS city (
  id SERIAL PRIMARY KEY,
  name VARCHAR(32),
  country_id INT REFERENCES country(id) ON UPDATE CASCADE ON DELETE CASCADE
  );

INSERT INTO country (name) VALUES
('Russia'),
('Belarus');

INSERT INTO city (name, country_id) VALUES
('Moscow', '1'),
('Vologda', '1'),
('Minsk', '2'),
('Brest', '2');
