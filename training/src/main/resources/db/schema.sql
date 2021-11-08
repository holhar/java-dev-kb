DROP TABLE Movie IF EXISTS;

CREATE TABLE Movie
(
    id          int          NOT NULL AUTO_INCREMENT,
    title       varchar(255) NOT NULL,
    releaseDate timestamp    NOT NULL,
    PRIMARY KEY (id)
);