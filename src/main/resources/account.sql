
--DROP TABLE IF EXISTS challenge_attempt;
--IF NOT EXISTS

CREATE TABLE IF NOT EXISTS account
(
  id int not null primary key AUTO_INCREMENT,
  name varchar(255) not null,
  email varchar(255) not null unique,
  phone varchar(20) not null unique,
  password varchar(50)  not null,
  account varchar(10) not null unique,
  period datetime not null

);