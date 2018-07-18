INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

-- test data --
SET @currentDate = GETDATE();

INSERT INTO office(name, created_at, last_modified_at) VALUES('Exton', @currentDate, @currentDate);
INSERT INTO office(name, created_at, last_modified_at) VALUES('Dublin', @currentDate, @currentDate);
INSERT INTO office(name, created_at, last_modified_at) VALUES('Foster City', @currentDate, @currentDate);