DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE authors
(
    id         BIGSERIAL primary key,
    name       VARCHAR(50),
    country    VARCHAR(50),
    birth_date DATE
);

DROP TABLE IF EXISTS genres CASCADE;
CREATE TABLE genres
(
    id   BIGSERIAL primary key,
    name VARCHAR(50)
);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books
(
    id                  BIGSERIAL primary key,
    name                VARCHAR(50),
    year_of_publication VARCHAR(4),
    author_id           BIGINT references authors (id),
    genre_id            BIGINT references genres (id)
);

DROP TABLE IF EXISTS book_comments CASCADE;
CREATE TABLE book_comments
(
    id      BIGSERIAL primary key,
    text    VARCHAR(4000),
    book_id BIGINT references books (id)
);