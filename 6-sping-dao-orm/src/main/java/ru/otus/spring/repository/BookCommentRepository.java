package ru.otus.spring.repository;

import ru.otus.spring.domain.BookComment;

import java.util.List;
import java.util.Optional;

public interface BookCommentRepository {
    BookComment save(BookComment comment);

    Optional<BookComment> findById(long id);

    List<BookComment> findAll();

    void deleteById(long id);
}
