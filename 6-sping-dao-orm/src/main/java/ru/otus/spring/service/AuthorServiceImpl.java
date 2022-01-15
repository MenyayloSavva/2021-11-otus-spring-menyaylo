package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Author;
import ru.otus.spring.repository.AuthorRepositoryJpa;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findById(long id) {
        return repository.findById(id);
    }
}
