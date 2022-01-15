package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.repository.BookCommentRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookCommentServiceImpl implements BookCommentService {

    private final BookCommentRepositoryJpa repository;

    @Override
    @Transactional
    public void save(BookComment comment) {
        repository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BookComment> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void updateTextById(long id, String text) {
        repository.updateTextById(id, text);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
