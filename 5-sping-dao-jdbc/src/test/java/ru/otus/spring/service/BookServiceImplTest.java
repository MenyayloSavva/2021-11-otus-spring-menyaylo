package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.BookDaoJdbc;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Service для работы с книгами должен")
public class BookServiceImplTest {

    @MockBean
    private BookDaoJdbc bookDao;

    @Autowired
    private BookServiceImpl bookService;

    @DisplayName("получать книгу по id")
    @Test
    void shouldGetBookById() {
        Book expectedBook = createBook();
        when(bookDao.getById(1)).thenReturn(expectedBook);

        Book actualBook = bookService.getBook(1);

        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
        verify(bookDao, times(1)).getById(anyInt());
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        bookService.deleteBook(1);
        bookService.deleteBook(2);
        bookService.deleteBook(3);

        verify(bookDao, times(3)).deleteById(anyInt());
    }

    @DisplayName("добавлять новую книгу")
    @Test
    void shouldInsertBook() {
        Book expectedBook = createBook();

        bookService.insertBook(expectedBook);

        verify(bookDao, times(1)).insert(any());
    }

    @DisplayName("обновлять параметры книги")
    @Test
    void shouldUpdateBook() {
        Book expectedBook = createBook();

        bookService.updateBook(expectedBook);

        verify(bookDao, times(1)).update(any());
    }

    private Book createBook() {
        Author expectedAuthor = new Author(1, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
        Genre expectedGenre = new Genre(1, "Любовная проза");
        return new Book(1, "book1", "9999", expectedAuthor, expectedGenre);
    }
}
