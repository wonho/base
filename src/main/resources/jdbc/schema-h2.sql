drop table tbl_user if exists;
 
CREATE TABLE tbl_user (
  id varchar(40) NOT NULL,
  username varchar(45) NOT NULL,
  password varchar(45) NOT NULL
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token VARCHAR(255),
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication VARCHAR(255),
  refresh_token VARCHAR(255)
);
create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token VARCHAR(255),
  authentication VARCHAR(255)
);