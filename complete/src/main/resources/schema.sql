DROP TABLE IF EXISTS PERSON;
  
CREATE TABLE PERSON (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS REGIONS;
  
CREATE TABLE REGIONS (
  region_id NUMBER PRIMARY KEY,
  region_name VARCHAR(25) NOT NULL
);

DROP TABLE IF EXISTS COUNTRY;

CREATE TABLE COUNTRY (
  country_id CHAR(2) PRIMARY KEY,
  country_name VARCHAR2(40),
  region_region_id NUMBER
);