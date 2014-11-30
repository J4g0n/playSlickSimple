# Resources schema

# --- !Ups

CREATE TABLE RESOURCE (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    first varchar(255) NOT NULL,
    last varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE RESOURCE;