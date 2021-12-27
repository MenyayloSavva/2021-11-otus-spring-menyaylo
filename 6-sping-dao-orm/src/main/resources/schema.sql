DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE authors(
                        id BIGINT PRIMARY KEY,
                        name VARCHAR(50),
                        country VARCHAR(50),
                        birth_date DATE
);

DROP TABLE IF EXISTS genres CASCADE;
CREATE TABLE genres(
                       id BIGINT PRIMARY KEY,
                       name VARCHAR(50)
);

DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE books(
                      id BIGINT PRIMARY KEY,
                      name VARCHAR(50),
                      year_of_publication VARCHAR(4),
                      author_id BIGINT,
                      genre_id BIGINT,
                      foreign key (author_id) references authors(id),
                      foreign key (genre_id) references genres(id)
);



