insert into authors (id, name, country, birth_date) values (1, 'Мария Петрова', 'Russia', '1992-08-23');
insert into authors (id, name, country, birth_date) values (2, 'Александр Пушкин', 'Russia', '1799-06-06');
insert into authors (id, name, country, birth_date) values (3, 'Федор Тютчев', 'Russia', '1803-12-05');

insert into genres (id, name) values (1, 'Любовная проза');
insert into genres (id, name) values (2, 'Поэзия');
insert into genres (id, name) values (3, 'Роман');
insert into genres (id, name) values (4, 'Комедия');

insert into books (id, name, year_of_publication, author_id, genre_id) values (1, 'Васильки', '2018', 1, 1);
insert into books (id, name, year_of_publication, author_id, genre_id) values (2, 'Евгений Онегин', '1830', 2, 3);
insert into books (id, name, year_of_publication, author_id, genre_id) values (3, 'Декабрьское утро', '1859', 3, 2);
insert into books (id, name, year_of_publication, author_id, genre_id) values (4, 'Звезды в саду', '2019', 1, 1);

insert into book_comments (id, text, book_id) values (1, 'Первый комментарий', 1);
insert into book_comments (id, text, book_id) values (2, 'Второй комментарий', 1);
insert into book_comments (id, text, book_id) values (3, 'Третий комментарий', 2);
insert into book_comments (id, text, book_id) values (4, 'Четвертый комментарий', 4);
insert into book_comments (id, text, book_id) values (5, 'Пятый комментарий', 4);