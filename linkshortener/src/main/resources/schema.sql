CREATE TABLE IF NOT EXISTS LINK
(
   id INT PRIMARY KEY auto_increment,
   url VARCHAR(255) NOT NULL,
   short_url VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS USERS (
 userid INT PRIMARY KEY auto_increment,
 username VARCHAR(20),
 salt VARCHAR,
 password VARCHAR,
 firstname VARCHAR(20),
 lastname VARCHAR(20)
);

