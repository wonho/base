create schema happyhouse AUTHORIZATION DBA;
set schema happyhouse;
SET DATABASE DEFAULT INITIAL SCHEMA happyhouse;
 
drop table tbl_user if exists;
 
CREATE TABLE tbl_user (
  id varchar(40) NOT NULL,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL
);
