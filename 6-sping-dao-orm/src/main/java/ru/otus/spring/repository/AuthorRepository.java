package ru.otus.spring.repository;

import ru.otus.spring.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findById(long id);
}
