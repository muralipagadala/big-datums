--create
CREATE TABLE data.users (
	id 		INTEGER,
	username 	VARCHAR(100),
	email_address 	VARCHAR(100),
	phone_number 	VARCHAR(20),
	first_name 	VARCHAR(100),
	last_name 	VARCHAR(100),
	middle_name 	VARCHAR(100),
	sex 		VARCHAR(10),
	birthdate 	DATE,
	join_date 	TIMESTAMP,
	previous_logins INTEGER,
	last_ip	 	VARCHAR(20)
);
