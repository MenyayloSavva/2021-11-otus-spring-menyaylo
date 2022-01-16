package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.repository.GenreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        return repository.findById(id);
    }
}
