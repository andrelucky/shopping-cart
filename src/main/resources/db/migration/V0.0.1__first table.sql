-- tabel Product --
CREATE TABLE product (
  id    VARCHAR(40) PRIMARY KEY,
  code  VARCHAR(10)    NOT NULL UNIQUE,
  name  VARCHAR(255)   NOT NULL,
  price DECIMAL(19, 2) NOT NULL
);

CREATE TABLE discount (
  id          VARCHAR(40) PRIMARY KEY,
  code        VARCHAR(10)  NOT NULL UNIQUE,
  description VARCHAR(255) NOT NULL,
  value       DECIMAL(19, 2),
  rate        DECIMAL(19, 2)
);

CREATE TABLE cart (
  id                 VARCHAR(40) PRIMARY KEY,
  session_id         VARCHAR(100) NOT NULL UNIQUE,
  name               VARCHAR(255),
  address            VARCHAR(500),
  email              VARCHAR(255),
  discount_id        VARCHAR(100),
  total_price        DECIMAL(19, 2),
  total_price_actual DECIMAL(19, 2),
  status             INT          NOT NULL,
  created_date       TIMESTAMP,
  updated_date       TIMESTAMP
);

CREATE TABLE cart_detail (
  id           VARCHAR(40) PRIMARY KEY,
  cart_id      VARCHAR(100)   NOT NULL,
  product_id   VARCHAR(100)   NOT NULL,
  price        DECIMAL(19, 2) NOT NULL,
  qty          INT            NOT NULL,
  created_date TIMESTAMP
);

INSERT INTO product (
  id, code, name, price)
VALUES ('c53262fc-f5b4-d7de-c794-3f2187afd0f9', 'A001', 'T-Shirt', 50000);

INSERT INTO product (
  id, code, name, price)
VALUES ('6121d546-7340-daa7-e10f-ccd0a57b29ae', 'A002', 'Jeans', 250000);

INSERT INTO discount (
  id, code, description, value, rate)
VALUES ('"202016da-aea9-a22f-1af1-9c39e44c4a2b"', 'DISKON200', 'Diskon sebesar 200 ribu', 200000, NULL);

INSERT INTO discount (
  id, code, description, value, rate)
VALUES ('"202016da-aea9-a22f-1af1-9c39e44c4a564"', 'DISKON50%', 'Diskon sebesar 50%', NULL, 50);