CREATE TABLE INVOICES(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATION_DATE               TIMESTAMP                 NOT NULL ,
  LAST_MODIFIED_DATE          TIMESTAMP                 NULL ,
  INVOICE_ID                  VARCHAR (255)             NOT NULL ,
  PAYMENT_ID                  VARCHAR (255)             NOT NULL ,
  ORDER_ID                    VARCHAR (255)             NOT NULL ,
  STATUS                      VARCHAR (255)             NOT NULL ,
  ISSUE_DATE                  TIMESTAMP                 NULL
);

CREATE INDEX INVOICES_ID_INDEX
  ON INVOICES(ID);
