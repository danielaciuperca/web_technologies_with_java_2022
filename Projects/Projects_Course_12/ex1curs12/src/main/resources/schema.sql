CREATE DATABASE if NOT EXISTS course11;

CREATE TABLE if NOT EXISTS destination (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  country varchar(255) NOT NULL,
  destination_type varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE if NOT EXISTS holiday (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  accommodation varchar(255) NOT NULL,
  duration int(11) NOT NULL,
  transportation varchar(255) NOT NULL,
  price double NOT NULL,
  destination_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (destination_id) REFERENCES destination (id)
);