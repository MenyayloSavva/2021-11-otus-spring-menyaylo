package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepositoryJpa;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Service для работы с авторами должен ")
public class AuthorServiceImplTest {

    @MockBean
    private AuthorRepositoryJpa repository;

    @Autowired
    private AuthorServiceImpl authorService;

    @DisplayName("находить автора по id")
    @Test
    void shouldFindGenreById() {
        Author expectedAuthor = createAuthor();

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expectedAuthor));
        Optional<Author> actualAuthor = authorService.findById(1L);

        assertThat(actualAuthor).isPresent().get().usingRecursiveComparison().isEqualTo(expectedAuthor);
        verify(repository, times(1)).findById(anyLong());
    }


    private Author createAuthor() {
        return new Author(1L, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
    }
}
