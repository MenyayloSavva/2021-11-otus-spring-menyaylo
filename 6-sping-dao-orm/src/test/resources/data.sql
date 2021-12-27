insert into authors (id, name, country, birth_date) values (1, 'Vasiliy Ivanov', 'Russia', '1900-01-01');
insert into authors (id, name, country, birth_date) values (5, 'Random User', 'Random Country', '5999-12-31');

insert into genres (id, name) values (1, 'Любовная проза');
insert into genres (id, name) values (2, 'Поэзия');
insert into genres (id, name) values (3, 'Роман');
insert into genres (id, name) values (4, 'Комедия');
insert into genres (id, name) values (5, 'Трагедия');

insert into books (id, name, year_of_publication, author_id, genre_id) values (1, 'Random book', '1903', 5, 1);
insert into books (id, name, year_of_publication, author_id, genre_id) values (2, 'Random book2', '1906', 5, 2);
insert into books (id, name, year_of_publication, author_id, genre_id) values (3, 'Random book3', '1909', 5, 3);
insert into books (id, name, year_of_publication, author_id, genre_id) values (4, 'Random book4', '6002', 1, 4);
insert into books (id, name, year_of_publication, author_id, genre_id) values (5, 'Random book5', '6003', 1, 5);