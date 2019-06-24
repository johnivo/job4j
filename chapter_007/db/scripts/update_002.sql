INSERT INTO Role(name) VALUES ('Admin'), ('User');
INSERT INTO Rules (resolution) VALUES ('Create user'), ('Update user'), ('Delete user'), ('Add item'), ('Edit any item'), ('Delete any item'), ('Edit own item'), ('Delete own item');
INSERT INTO RulesToRole (rules_id, role_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (4, 2), (7, 2), (8, 2);
INSERT INTO Category (name) VALUES ('Suggestion'), ('Violation of the delivery time'), ('Quality complaint'), ('Technical problem');
INSERT INTO State (status) VALUES ('Open'), ('In the work'), ('Closed');
INSERT INTO Users (firstName, lastName, city, phone, role_id) VALUES ('Leo', 'Messi', 'Barca', '+79111234567', 2), ('Zinedine', 'Zidane', 'Madrid', '+79111234589', 2), ('Paul', 'Scholes', 'Manchester', '+79117654321', 1), ('Andreas', 'Iniesta', 'Barca', '+79117654322', 1);