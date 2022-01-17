package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.repository.BookCommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service для работы с книжными комментариями должен ")
public class BookCommentServiceImplTest {

    @MockBean
    private BookCommentRepositoryJpa repository;

    @Autowired
    private BookCommentServiceImpl bookCommentService;

    @DisplayName("сохранять комментарий")
    @Test
    void shouldSaveComment() {
        BookComment bookComment = createBookComment();

        bookCommentService.save(bookComment);

        verify(repository, times(1)).save(any());
    }

    @DisplayName("находить комментарий по id")
    @Test
    void shouldFindCommentById() {
        BookComment expectedComment = createBookComment();

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expectedComment));
        Optional<BookComment> actualComment = bookCommentService.findById(1L);

        assertThat(actualComment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
        verify(repository, times(1)).findById(anyLong());
    }

    @DisplayName("возвращать список всех комментариев по id книги")
    @Test
    void shouldFindByBookId() {
        BookComment expectedComment1 = createBookComment();
        BookComment expectedComment2 = createBookComment();

        when(repository.findAll()).thenReturn(List.of(expectedComment1, expectedComment2));
        List<BookComment> actualCommentList = bookCommentService.findByBookId(999L);

        assertThat(actualCommentList).hasSize(2)
                .allMatch(bc -> bc.getText().equals("Some comment text"))
                .allMatch(bc -> bc.getBook() != null);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("возвращать список всех комментариев")
    @Test
    void shouldFindAll() {
        BookComment expectedComment1 = createBookComment();
        BookComment expectedComment2 = createBookComment();

        when(repository.findAll()).thenReturn(List.of(expectedComment1, expectedComment2));
        List<BookComment> actualCommentList = bookCommentService.findAll();

        assertThat(actualCommentList).hasSize(2)
                .allMatch(bc -> bc.getText().equals("Some comment text"))
                .allMatch(bc -> bc.getBook() != null);
        verify(repository, times(1)).findAll();
    }

    @DisplayName("обновлять текст комментария по id")
    @Test
    void shouldUpdateTextById() {
        BookComment givenComment = createBookComment();

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(givenComment));
        Optional<BookComment> actualBookComment = bookCommentService.findById(1L);
        bookCommentService.updateTextById(1L, "New comment text");

        assertThat(actualBookComment).isPresent().get().matches(bc -> bc.getText().equals("New comment text"));
        verify(repository, times(2)).findById(anyLong());
    }


    @DisplayName("удалять комментарий по id")
    @Test
    void shouldDeleteCommentById() {
        bookCommentService.deleteById(1L);

        verify(repository, times(1)).deleteById(anyLong());
    }


    private BookComment createBookComment() {
        Book book = Book.builder()
                .id(999L)
                .build();
        return new BookComment(1L, "Some comment text", book);
    }
}
