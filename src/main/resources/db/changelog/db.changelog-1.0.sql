--liquibase formatted sql

--changeset matvei:1
CREATE TABLE IF NOT EXISTS organizer
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(75) NOT NULL UNIQUE
);


--changeset matvei:2
CREATE TABLE IF NOT EXISTS place
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(75),
    address VARCHAR(75) NOT NULL UNIQUE
);

--changeset matvei:3
CREATE TABLE IF NOT EXISTS event
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR(75) NOT NULL UNIQUE,
    description  VARCHAR(200),
    date         TIMESTAMP   NOT NULL,
    organizer_id INT REFERENCES organizer (id),
    place_id     INT REFERENCES place (id)
);

