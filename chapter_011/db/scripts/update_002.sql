CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(64),
  login VARCHAR(64),
  email VARCHAR(64),
  createDate TIMESTAMP,
  photoId VARCHAR(128),
  password VARCHAR(32),
  role VARCHAR(32),
  city_id INT REFERENCES city(id) ON UPDATE CASCADE ON DELETE SET NULL
  );
-- SERIAL - целое число с автоувеличением

INSERT INTO users (name, login, email, createDate, password, role, city_id)
VALUES ('admin', 'admin', 'admin', '2019-11-26 00:00:00', 'admin', 'admin', 1),
 ('user', 'user', 'user', '2019-11-27 00:00:00', 'user', 'user', 2);