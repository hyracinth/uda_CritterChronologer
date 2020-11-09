CREATE SCHEMA `critter` ; -- Create the critter database

-- Expects user "sa" to exist in DB
GRANT ALL ON critter.* TO 'sa'@'localhost'; -- Gives all privileges to the user on critter