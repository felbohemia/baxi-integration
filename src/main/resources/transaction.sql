
--DROP TABLE IF EXISTS transaction;
--IF NOT EXISTS

CREATE TABLE IF NOT EXISTS transaction
(
  id int not null primary key AUTO_INCREMENT,
  account varchar(10) not null,
  phone varchar(20) not null,
  type varchar(10) not null,
  amount decimal not null,
  period datetime not null

);
