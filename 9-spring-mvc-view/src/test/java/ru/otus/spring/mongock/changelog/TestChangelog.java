package ru.otus.spring.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.AuthorRepository;
import ru.otus.spring.repository.BookCommentRepository;
import ru.otus.spring.repository.BookRepository;
import ru.otus.spring.repository.GenreRepository;

import java.time.LocalDate;
import java.util.List;

@ChangeLog
public class TestChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "MenyayloSavva", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "MenyayloSavva")
    public void insertAuthors(AuthorRepository authorRepository) {
        authorRepository.saveAll(List.of(
                new Author(1, "Мария Петрова", "Russia", LocalDate.of(1992, 8, 23)),
                new Author(2, "Александр Пушкин", "Russia", LocalDate.of(1799, 6, 6)),
                new Author(3, "Федор Тютчев", "Russia", LocalDate.of(1803, 12, 5))
        ));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "MenyayloSavva")
    public void insertGenres(GenreRepository genreRepository) {
        genreRepository.saveAll(List.of(
                new Genre(1, "Любовная проза"),
                new Genre(2, "Поэзия"),
                new Genre(3, "Роман"),
                new Genre(4, "Комедия")
        ));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "MenyayloSavva")
    public void insertBooks(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        Author author1 = authorRepository.findById(1L).orElseThrow();
        Author author2 = authorRepository.findById(2L).orElseThrow();
        Author author3 = authorRepository.findById(3L).orElseThrow();

        Genre genre1 = genreRepository.findById(1L).orElseThrow();
        Genre genre2 = genreRepository.findById(2L).orElseThrow();
        Genre genre3 = genreRepository.findById(3L).orElseThrow();

        bookRepository.saveAll(List.of(
                Book.builder().id(1).name("Васильки").yearOfPublication("2018").author(author1).genre(genre1).build(),
                Book.builder().id(2).name("Евгений Онегин").yearOfPublication("1830").author(author2).genre(genre3).build(),
                Book.builder().id(3).name("Декабрьское утро").yearOfPublication("1859").author(author3).genre(genre2).build(),
                Book.builder().id(4).name("Звезды в саду").yearOfPublication("2019").author(author1).genre(genre1).build()
        ));
    }

    @ChangeSet(order = "005", id = "insertBookComments", author = "MenyayloSavva")
    public void insertBookComments(BookRepository bookRepository, BookCommentRepository bookCommentRepository) {
        Book book1 = bookRepository.findById(1L).orElseThrow();
        Book book2 = bookRepository.findById(2L).orElseThrow();
        Book book4 = bookRepository.findById(4L).orElseThrow();

        bookCommentRepository.saveAll(List.of(
                new BookComment(1, "Первый комментарий", book1),
                new BookComment(2, "Второй комментарий", book1),
                new BookComment(3, "Третий комментарий", book2),
                new BookComment(4, "Четвертый комментарий", book4),
                new BookComment(5, "Пятый комментарий", book4),
                new BookComment(6, "Шестой комментарий", book4)
        ));
    }
}
