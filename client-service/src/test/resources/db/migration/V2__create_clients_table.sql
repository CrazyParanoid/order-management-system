CREATE TABLE CLIENTS(
  ID                          bigserial PRIMARY KEY     NOT NULL ,
  CREATION_DATE               TIMESTAMP                 NOT NULL ,
  LAST_MODIFIED_DATE          TIMESTAMP                 NULL ,
  NAME                        VARCHAR (255)             NOT NULL ,
  SURNAME                     VARCHAR (255)             NOT NULL ,
  PATRONYMIC                  VARCHAR (255)             NULL
);

CREATE INDEX CLIENTS_ID_INDEX
  ON CLIENTS(ID);
