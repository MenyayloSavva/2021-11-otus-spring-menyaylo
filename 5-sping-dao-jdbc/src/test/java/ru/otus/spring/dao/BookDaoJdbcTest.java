package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.time.LocalDate;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDao;

    @DisplayName("вставлять книгу")
    @Test
    void shouldInsertBook() {
        Author expectedAuthor = new Author(5, "Random User", "Random Country", LocalDate.of(5999, 12, 31));
        Genre expectedGenre = new Genre(3, "Роман");
        Book expectedBook = new Book(10, "Горе от ума", "1833", expectedAuthor, expectedGenre);

        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(10);

        assertThat(actualBook).matches(isEqualToAnotherBook(expectedBook));
    }

    @DisplayName("возвращать исключение при попытке вставить книгу")
    @Test
    void shouldReturnException_whenCallInsertBook() {
        Author expectedAuthor = new Author(5, "Random User", "Random Country", LocalDate.of(5999, 12, 31));
        Genre expectedGenre = new Genre(2, "Поэзия");
        Book expectedBook = new Book(1, "Горе от ума", "1833", expectedAuthor, expectedGenre);

        assertThatExceptionOfType(DuplicateKeyException.class)
                .isThrownBy(() -> bookDao.insert(expectedBook))
                .withMessageContaining("Нарушение уникального индекса или первичного ключа");
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        bookDao.deleteById(5);

        assertThatThrownBy(() -> bookDao.getById(5))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessage("Incorrect result size: expected 1, actual 0");
    }

    @DisplayName("обновлять параметры книги")
    @Test
    void shouldUpdateBook() {
        Author expectedAuthor = new Author(5, "Random User", "Random Country", LocalDate.of(5999, 12, 31));
        Genre expectedGenre = new Genre(3, "Роман");
        Book expectedBook = new Book(5, "Updated Name", "7000", expectedAuthor, expectedGenre);

        bookDao.update(expectedBook);
        Book actualBook = bookDao.getById(5);

        assertThat(actualBook).matches(isEqualToAnotherBook(expectedBook));
    }

    @DisplayName("получать книгу по id")
    @Test
    void shouldGetBookById() {
        Author expectedAuthor = new Author(1, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
        Genre expectedGenre = new Genre(5, "Трагедия");
        Book expectedBook = new Book(5, "Random book5", "6003", expectedAuthor, expectedGenre);

        Book actualBook = bookDao.getById(5);

        assertThat(actualBook).matches(isEqualToAnotherBook(expectedBook));
    }

    @DisplayName("возвращать исключение при попытке вызвать getById")
    @Test
    void shouldReturnException_whenCallGetById() {
        assertThatThrownBy(() -> bookDao.getById(999))
                .isInstanceOf(EmptyResultDataAccessException.class)
                .hasMessage("Incorrect result size: expected 1, actual 0");
    }

    private Predicate<? super Book> isEqualToAnotherBook(Book book) {
        return b -> b.getId() == book.getId() &&
                b.getName().equals(book.getName()) &&
                b.getYearOfPublication().equals(book.getYearOfPublication()) &&
                b.getAuthor().getId() == book.getAuthor().getId() &&
                b.getGenre().getId() == book.getGenre().getId();
    }

}