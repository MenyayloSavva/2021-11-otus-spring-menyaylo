package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.AuthorDaoJdbc;
import ru.otus.spring.domain.Author;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Service для работы с авторами должен")
public class AuthorServiceImplTest {

    @MockBean
    private AuthorDaoJdbc authorDao;

    @Autowired
    private AuthorServiceImpl authorService;

    @DisplayName("получать автора по id")
    @Test
    void shouldGetAuthorById() {
        Author expectedAuthor = createAuthor();

        when(authorDao.getById(1)).thenReturn(expectedAuthor);
        Author actualAuthor = authorService.getAuthor(1);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
        verify(authorDao, times(1)).getById(anyInt());
    }

    private Author createAuthor() {
        return new Author(1, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
    }
}
