CREATE TABLE PAYMENTS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATION_DATE               TIMESTAMP                 NOT NULL ,
  LAST_MODIFIED_DATE          TIMESTAMP                 NULL ,
  PAYMENT_ID                  VARCHAR (255)             NOT NULL ,
  ORDER_ID                    VARCHAR (255)             NOT NULL ,
  AMOUNT                      DOUBLE PRECISION          NOT NULL
);

CREATE INDEX PAYMENTS_ID_INDEX
  ON PAYMENTS(ID);
