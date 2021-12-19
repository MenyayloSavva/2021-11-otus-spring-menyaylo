package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Author;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с авторами должно")
@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDao;

    @DisplayName("возвращать авторов по id")
    @Test
    void shouldReturnAuthorById() {
        Author expectedAuthor = createExistingAuthor();
        Author actualAuthor = authorDao.getById(1);

        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать исключение при попытке вызвать getById")
    @Test
    void shouldReturnException_whenCallGetById() {
        assertThatThrownBy(() -> authorDao.getById(999)).hasMessage("Incorrect result size: expected 1, actual 0");
    }

    private Author createExistingAuthor() {
        return new Author(1, "Vasiliy Ivanov", "Russia", LocalDate.of(1900, 1, 1));
    }
}