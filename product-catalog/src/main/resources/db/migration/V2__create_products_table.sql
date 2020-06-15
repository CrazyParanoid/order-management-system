CREATE TABLE PRODUCTS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATION_DATE               TIMESTAMP                 NOT NULL ,
  LAST_MODIFIED_DATE          TIMESTAMP                 NULL ,
  TITLE                       VARCHAR (255)             NOT NULL ,
  PRICE                       DOUBLE PRECISION          NOT NULL
);

CREATE INDEX PRODUCTS_ID_INDEX
  ON PRODUCTS(ID);
