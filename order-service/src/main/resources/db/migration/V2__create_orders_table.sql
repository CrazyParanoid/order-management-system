CREATE TABLE ORDERS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATION_DATE               TIMESTAMP                 NOT NULL ,
  LAST_MODIFIED_DATE          TIMESTAMP                 NULL ,
  ORDER_ID                    VARCHAR (255)             NOT NULL ,
  PRODUCT_ID                  VARCHAR (255)             NOT NULL ,
  CLIENT_ID                   VARCHAR (255)             NOT NULL ,
  PAYMENT_ID                  VARCHAR (255)             NULL ,
  INVOICE_ID                  VARCHAR (255)             NULL ,
  STATUS                      VARCHAR (255)             NOT NULL
);

CREATE INDEX ORDERS_ID_INDEX
  ON ORDERS(ID);
