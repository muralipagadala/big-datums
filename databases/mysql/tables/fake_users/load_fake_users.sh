LOAD DATA LOCAL INFILE 'fake_users.txt'
INTO TABLE data.fake_users
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
IGNORE 1 LINES;

