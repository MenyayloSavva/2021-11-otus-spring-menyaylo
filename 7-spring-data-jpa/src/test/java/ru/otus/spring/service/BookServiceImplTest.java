package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.BookRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service для работы с книгами должен ")
public class BookServiceImplTest {

    @MockBean
    private BookRepository repository;

    @Autowired
    private BookService bookService;

    @DisplayName("сохранять книгу")
    @Test
    void shouldSaveBook() {
        Book book = createBook();

        bookService.save(book);

        verify(repository, times(1)).save(any());
    }

    @DisplayName("находить книгу по id")
    @Test
    void shouldFindBookById() {
        Book expectedBook = createBook();

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expectedBook));
        Optional<Book> actualBook = bookService.findById(1L);

        assertThat(actualBook).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
        verify(repository, times(1)).findById(anyLong());
    }

    @DisplayName("возвращать список всех книг")
    @Test
    void shouldFindAll() {
        Book expectedBook1 = createBook();
        Book expectedBook2 = createBook();

        when(repository.findAll()).thenReturn(List.of(expectedBook1, expectedBook2));
        List<Book> actualBookList = bookService.findAll();

        assertThat(actualBookList).hasSize(2)
                .allMatch(b -> b.getName().equals("book1"))
                .allMatch(b -> b.getAuthor().getName().equals("Vasiliy Ivanov"))
                .allMatch(b -> b.getGenre().getName().equals("Любовная проза"))
                .allMatch(b -> b.getComments().size() == 1);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("находить книгу по имени")
    @Test
    void shouldFindBookByName() {
        Book expectedBook = createBook();

        when(repository.findByName(anyString())).thenReturn(List.of(expectedBook));
        List<Book> actualBookList = bookService.findByName("book1");

        assertThat(actualBookList).hasSize(1)
                .allMatch(b -> b.getName().equals("book1"))
                .allMatch(b -> b.getAuthor().getName().equals("Vasiliy Ivanov"))
                .allMatch(b -> b.getGenre().getName().equals("Любовная проза"))
                .allMatch(b -> b.getComments().size() == 1);
        verify(repository, times(1)).findByName(anyString());
    }

    @DisplayName("обновлять имя книги по id")
    @Test
    void shouldUpdateBookById() {
        bookService.updateNameById(1L, "New Book Name");

        verify(repository, times(1)).updateNameById(anyLong(), anyString());
    }

    @DisplayName("удалять книгу по id")
    @Test
    void shouldDeleteBookById() {
        bookService.deleteById(1L);

        verify(repository, times(1)).deleteById(anyLong());
    }


    private Book createBook() {
        Author author = new Author(1L, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
        Genre genre = new Genre(1L, "Любовная проза");
        Book book = new Book();
        BookComment bookComment = new BookComment(1L, "Some comment text", book);
        return book.toBuilder()
                .id(1L)
                .name("book1")
                .yearOfPublication("9999")
                .author(author)
                .genre(genre)
                .comments(Collections.singletonList(bookComment))
                .build();
    }
}
