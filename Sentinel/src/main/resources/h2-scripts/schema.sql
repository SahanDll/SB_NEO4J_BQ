CREATE SCHEMA IF NOT EXISTS SENTINEL;

CREATE TABLE IF NOT EXISTS SENTINEL.UserLogin(
	UserName varchar(255) NOT NULL,
	InsertTime datetime NULL,
	Password varchar(255) NULL,
	UpdateTime datetime NULL,
	UserRole int NULL,
  PRIMARY KEY (UserName)
);

