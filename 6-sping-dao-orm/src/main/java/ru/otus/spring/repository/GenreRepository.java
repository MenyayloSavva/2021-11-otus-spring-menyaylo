package ru.otus.spring.repository;

import ru.otus.spring.domain.Genre;

import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(long id);
}
