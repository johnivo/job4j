CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  name VARCHAR(64),
  login VARCHAR(64),
  email VARCHAR(64),
  createDate TIMESTAMP
  );
-- SERIAL - целое число с автоувеличением
