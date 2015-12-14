CREATE DATABASE IF NOT EXISTS people;
USE people;

CREATE EXTERNAL TABLE people_v1 (
  id               INT,
  username         STRING,
  email_address    STRING,
  phone_number     STRING,
  first_name       STRING,
  last_name        STRING,
  middle_name      STRING,
  sex              STRING,
  birthdate        DATE,
  join_date        STRING,
  previous_logins  INT,
  last_ip          STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LOCATION '/people_v1'
TBLPROPERTIES ('skip.header.line.count'='1','serialization.null.format' = '');
