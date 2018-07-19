INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

-- test data --
SET @currentDate = GETDATE();

INSERT INTO office(name, created_at, last_modified_at) VALUES('Exton', @currentDate, @currentDate);
INSERT INTO office(name, created_at, last_modified_at) VALUES('Dublin', @currentDate, @currentDate);
INSERT INTO office(name, created_at, last_modified_at) VALUES('Foster City', @currentDate, @currentDate);

insert INTO user(name, username, password, created_at, last_modified_at) VALUES ('test', 'test', '$2a$04$JfG91YTzI/CpHEY8GAo4/.owASEYkmXb44fNLmD0PNkMYPtJe4nr.', @currentDate, @currentDate);