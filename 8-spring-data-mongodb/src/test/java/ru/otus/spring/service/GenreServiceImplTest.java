package ru.otus.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "mongock.enabled=false")
@DisplayName("Service для работы с жанрами должен ")
public class GenreServiceImplTest {

    @MockBean
    private GenreRepository repository;

    @Autowired
    private GenreService genreService;

    @DisplayName("находить жанр по id")
    @Test
    void shouldFindGenreById() {
        Genre expectedGenre = createGenre();

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(expectedGenre));
        Optional<Genre> actualGenre = genreService.findById(1L);

        assertThat(actualGenre).isPresent().get().usingRecursiveComparison().isEqualTo(expectedGenre);
        verify(repository, times(1)).findById(anyLong());
    }


    private Genre createGenre() {
        return new Genre(1L, "Любовная проза");
    }
}
