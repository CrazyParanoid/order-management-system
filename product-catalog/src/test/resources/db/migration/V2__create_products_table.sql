CREATE TABLE PRODUCTS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATION_DATE               DATE                      NOT NULL ,
  LAST_MODIFIED_DATE          DATE                      NULL ,
  TITLE                       VARCHAR (255)             NOT NULL ,
  PRICE                       DOUBLE                    NOT NULL
);

CREATE INDEX PRODUCTS_ID_INDEX
  ON PRODUCTS(ID);
