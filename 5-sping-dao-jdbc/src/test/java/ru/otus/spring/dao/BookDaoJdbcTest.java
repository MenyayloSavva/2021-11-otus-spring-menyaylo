package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@DisplayName("Dao для работы с книгами должно")
@JdbcTest
@Import({BookDaoJdbc.class, AuthorDaoJdbc.class, GenreDaoJdbc.class})
public class BookDaoJdbcTest {

    @MockBean
    private GenreDaoJdbc genreDao;

    @MockBean
    private AuthorDaoJdbc authorDao;

    @Autowired
    private BookDaoJdbc bookDao;

    @DisplayName("вставлять книгу")
    @Test
    void shouldInsertBook() {
        Author expectedAuthor = new Author(5, "Random User", "Random Country", LocalDate.of(5999, 12, 31));
        Genre expectedGenre = new Genre(3, "Роман");
        Book expectedBook = new Book(10, "Горе от ума", "1833", expectedAuthor, expectedGenre);
        when(genreDao.getById(anyInt())).thenReturn(expectedGenre);
        when(authorDao.getById(anyInt())).thenReturn(expectedAuthor);

        bookDao.insert(expectedBook);
        Book actualBook = bookDao.getById(10);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
        verify(genreDao, times(1)).getById(anyInt());
        verify(authorDao, times(1)).getById(anyInt());
    }

    @DisplayName("возвращать исключение при попытке вставить книгу")
    @Test
    void shouldReturnException_whenCallInsertBook() {
        Author expectedAuthor = new Author(5, "Random User", "Random Country", LocalDate.of(5999, 12, 31));
        Genre expectedGenre = new Genre(2, "Поэзия");
        Book expectedBook = new Book(1, "Горе от ума", "1833", expectedAuthor, expectedGenre);

        assertThatExceptionOfType(DataAccessException.class).isThrownBy(() -> bookDao.insert(expectedBook))
                .withMessageContaining("Нарушение уникального индекса или первичного ключа");
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        bookDao.deleteById(5);

        assertThatThrownBy(() -> bookDao.getById(5)).isInstanceOf(DataAccessException.class)
                .hasMessageContaining("Incorrect result size: expected 1, actual 0");
    }

    @DisplayName("обновлять параметры книги")
    @Test
    void shouldUpdateBook() {
        Author expectedAuthor = new Author(5, "Random User", "Random Country", LocalDate.of(5999, 12, 31));
        Genre expectedGenre = new Genre(3, "Роман");
        Book expectedBook = new Book(5, "Updated Name", "7000", expectedAuthor, expectedGenre);
        when(genreDao.getById(anyInt())).thenReturn(expectedGenre);
        when(authorDao.getById(anyInt())).thenReturn(expectedAuthor);

        bookDao.update(expectedBook);
        Book actualBook = bookDao.getById(5);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
        verify(genreDao, times(1)).getById(anyInt());
        verify(authorDao, times(1)).getById(anyInt());
    }

    @DisplayName("получать книгу по id")
    @Test
    void shouldGetBookById() {
        Author expectedAuthor = new Author(1, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
        Genre expectedGenre = new Genre(5, "Трагедия");
        Book expectedBook = new Book(5, "Random book5", "6003", expectedAuthor, expectedGenre);
        when(genreDao.getById(anyInt())).thenReturn(expectedGenre);
        when(authorDao.getById(anyInt())).thenReturn(expectedAuthor);

        Book actualBook = bookDao.getById(5);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
        verify(genreDao, times(1)).getById(anyInt());
        verify(authorDao, times(1)).getById(anyInt());
    }

    @DisplayName("возвращать исключение при попытке вызвать getById")
    @Test
    void shouldReturnException_whenCallGetById() {
        assertThatThrownBy(() -> bookDao.getById(999)).hasMessage("Incorrect result size: expected 1, actual 0");
    }

}