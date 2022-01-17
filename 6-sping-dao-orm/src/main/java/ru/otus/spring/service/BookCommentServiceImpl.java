package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.domain.BookComment;
import ru.otus.spring.repository.BookCommentRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<BookComment> findById(long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> findByBookId(long id) {
        return repository.findAll().stream()
                .filter(bc -> bc.getBook().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookComment> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void updateTextById(long id, String text) {
        Optional<BookComment> bookComment = repository.findById(id);
        bookComment.ifPresent(bc -> bc.setText(text));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
