CREATE CONSTRAINT ON (u:User) ASSERT u.username IS UNIQUE;

USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///twitter_relationships_top_100.txt" AS line
FIELDTERMINATOR '\t'
MERGE (u:User {username: line.username});

USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///twitter_relationships_top_100.txt" AS line
FIELDTERMINATOR '\t'
MATCH (u:User {username: line.username}), (f:User {username: line.followed_by})
MERGE (f)-[:FOLLOWS]->(u);

