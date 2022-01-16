package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.repository.BookCommentRepository;

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
@DisplayName("Service для работы с книжными комментариями должен ")
public class BookCommentServiceImplTest {

    @MockBean
    private BookCommentRepository repository;

    @Autowired
    private BookCommentService bookCommentService;

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
        bookCommentService.updateTextById(1L, "New Comment Text");

        verify(repository, times(1)).updateTextById(anyLong(), anyString());
    }

    @DisplayName("удалять комментарий по id")
    @Test
    void shouldDeleteCommentById() {
        bookCommentService.deleteById(1L);

        verify(repository, times(1)).deleteById(anyLong());
    }


    private BookComment createBookComment() {
        Book book = new Book();
        return new BookComment(1L, "Some comment text", book);
    }
}
