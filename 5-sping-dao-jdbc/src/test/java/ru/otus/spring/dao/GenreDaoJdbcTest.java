package ru.otus.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("возвращать жанр по id")
    @Test
    void shouldReturnGenreById() {
        Genre expectedGenre = createExistingGenre();
        Genre actualGenre = genreDao.getById(5);

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать исключение при попытке вызвать getById")
    @Test
    void shouldReturnException_whenCallGetById() {
        assertThatThrownBy(() -> genreDao.getById(999)).hasMessage("Incorrect result size: expected 1, actual 0");
    }

    private Genre createExistingGenre() {
        return new Genre(5, "Трагедия");
    }
}