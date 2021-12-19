package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.dao.GenreDaoJdbc;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Service для работы с жанрами должен")
public class GenreServiceImplTest {

    @MockBean
    private GenreDaoJdbc genreDao;

    @Autowired
    private GenreServiceImpl genreService;

    @DisplayName("получать жанр по id")
    @Test
    void shouldGetGenreById() {
        Genre expectedGenre = createGenre();
        Mockito.when(genreDao.getById(1)).thenReturn(expectedGenre);

        Genre actualGenre = genreService.getGenre(1);

        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
        verify(genreDao, times(1)).getById(anyInt());
    }

    private Genre createGenre() {
        return new Genre(1, "Любовная проза");
    }
}
